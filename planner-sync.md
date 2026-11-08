# Syncing with the Personal Routine Planner

The planner is a claude.ai artifact: https://claude.ai/artifact/9ddUmjXhvGZyAFpmJeit1u

It keeps a small database that Claude reads and writes with the `ArtifactData` tool (pass that URL as `url`). [revision-log.md](revision-log.md) is the record; the planner holds a copy so it can show what's due each day, and it records re-solves ticked on the page.

Any session that changes `revision-log.md` (adding a problem, a revision, a mock) runs both steps below, **pull first, then push**. If the session has no `ArtifactData` tool, skip the sync and say so; the daily morning task catches up.

## Collections

| Collection | Doc id | Written by | Fields |
|---|---|---|---|
| `revisions` | problem ID from the log | Claude | `problem`, `topic`, `path`, `first_solved`, `last_revised` (or `""`), `last_result` (or `""`), `due`, `reason`, `sheet_name` |
| `reviews` | `<problem ID>_<YYYY-MM-DD>` | the planner page | `problem_id`, `date`, `result` (`clean` or `struggled`), optional `note` (what went wrong, one line) |
| `units` | planner task id | the planner page | `done`, `on` |

`sheet_name` is the problem's title on Striver's 180 sheet when it's from that sheet (for example `Kadane's Algorithm`), otherwise `""`. The planner uses it to tick that problem off in its DSA list automatically.

## Step 1: pull planner re-solves into the log

1. `ArtifactData` `list` on collection `reviews` (page with `query.cursor` until done).
2. For each review whose `date` is later than the problem's **Last revised** in the Queue (or which is the problem's first, when Last revised is `—`):
   - Add a History row: `| <date> | <Problem> | Clean → Solid` or `Struggled → Shaky` ` | planner | <the review's note, or "re-solved in the planner"> |`.
   - If a Struggled review has a note, also tell the user in one line, and offer to add it to that problem's README under "Mistakes I made" if it's a new kind of mistake.
   - Update the Queue row: Last revised = review date, Result = Solid or Shaky, Next due and Reason per the rules in `revision-log.md`.
3. Leave the `reviews` documents in place; the date comparison keeps them from being counted twice.

## Step 2: push the Queue to the planner

1. `ArtifactData` `list` on collection `revisions` to get each existing document's `version`.
2. One `ArtifactData` `batch` (up to 50 writes per call) of `set` writes, one per Queue row that is new or changed, with `if_version` for documents that already exist. Only write rows whose values differ. In the same batch, `delete` (with `if_version`) any `revisions` document whose ID is not in the Queue, such as a problem moved to Parked.
   Only the **Queue** syncs. Rows in **Parked** never go to the planner.
3. If a write fails on `if_version`, re-read that document and redo just that write.

Then commit `revision-log.md` with the rest of the session's changes, on its branch, with a pull request assigned to ShazAhmed10, as `CLAUDE.md` describes.
