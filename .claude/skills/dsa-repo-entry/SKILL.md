---
name: dsa-repo-entry
description: Use when the user has finished a DSA problem and asks to add it to their DSA repo, write revision notes, or save the solution files.
---

# DSA repo entry

Turns a finished problem from the current conversation into a consistent entry in the DSA repo (github.com/ShazAhmed10/DSA, Java 21, Maven layout). Every existing problem follows this format; new ones must match it.

## Step 0: the user's own summary comes first

Before writing anything, check whether the user has written their own summary of the problem in this conversation. If not, ask for it and wait:

> Before I add it: write 4–6 lines from memory, without scrolling up — the key idea that unlocked it, the state and recurrence in your own words, the mistake you're most likely to repeat, and what would help you recognise this pattern next time.

After the user writes it, review it and complete it:

1. **Point out, in your reply, what is wrong or missing** compared with the conversation. Check for: the key insight, the state and recurrence, base cases and why they have those values, fill order / space-optimisation trick, how the complexity was derived, the mistake most likely to repeat, and how to recognise the pattern. Name each gap briefly.
2. **Produce the completed summary** by modifying theirs:
   - Keep the user's own sentences and wording wherever they are correct. Don't polish style, tone or grammar of their sentences.
   - Correct statements that are wrong, and mark each corrected sentence with *(corrected)* at its end.
   - Add the missing points as short sentences in the user's voice ("I", plain wording), placed where they fit, each marked with *(added)* at its end.
   - Keep it a summary: aim for the user's length plus one short sentence per gap.
3. **Show the completed summary in your reply before writing the README**, and ask once whether to use it or change anything. Use whatever version the user confirms.

Never write the summary from scratch: always start from the user's text. If the user hasn't written one, ask for it and wait.

## Before writing anything else

Collect from the conversation:
- Problem name, platform number and link if known, statement, examples, constraints
- The user's FINAL accepted code for every approach (naive, memo, tabulation, space-optimised, alternative approaches). Use the user's code, not your own rewrites. Never include a version that was later found to be wrong.
- The key insight, state definition, recurrence, base cases, answer cell, tabulation fill order
- Final TC/SC per approach, with corrected values if the user's first estimate was wrong, **and how each was derived**: which method (loop counting, branches^depth, recurrence, states × work per state, amortised, graph V+E), why that method fits the code, and the derivation (branches/depth/what shrinks, or the recurrence and its first few values, or the state count and work per state)
- Every mistake the user made along the way, what fixed it, and the failing input if one was found

If the category or difficulty folder is unclear, ask once.

## Folder layout

```
src/main/java/<Topic>/<Sheet>/<Category>/<Difficulty>/P<NN>_<ProblemName>/
    README.md
    A_RecursiveNaive.java
    B_Memoization.java
    C_Tabulation.java
    Da_TabulationSpaceOptimisation.java    (Da/Db/Dc for several variants)
```

- Example: `DynamicProgramming/Blind_180/C_Sequence/B_Hard/P02_WildcardMatching/`
- Existing DP categories: `DynamicProgramming/Blind_180/{A_Knapsack, B_State_Transition, C_Sequence, D_Interval}/{A_Medium, B_Hard}`. A new category gets the next letter prefix.
- `NN` = next number in that difficulty folder, zero-padded (look at the existing `P..` folders).
- Several approaches: prefix `Approach1_`, `Approach2_`. Brute force before recursion: `Aa_IterativeNaive`, `Ab_RecursiveNaive`.
- Each file: `package` matching the folder path, a public class named like the file, the solution method(s), and a `main` that runs the problem's examples (plus any failing inputs from the session) printing `actual + " (expected X)"`.
- Helper types (e.g. `Pair`) are `static` nested classes, never top-level.

## README.md template

Match the existing problem READMEs exactly:

~~~markdown
# <Problem Name>

[LeetCode <number>](<link>) · <Sheet> DP · <Category> · <Difficulty>

## Problem

```text
<statement, examples, constraints>
```

## My summary

> Written by me from memory right after solving. Additions and corrections from review are marked *(added)* / *(corrected)*.

<the completed summary the user confirmed>

## Solutions

Each file has a `main` that runs the examples and prints actual vs expected.

| File | Approach |
|---|---|
| [`A_RecursiveNaive.java`](A_RecursiveNaive.java) | Recursive (naive) |

## Key insight
<the idea that unlocks it, 2–5 lines>

## State, recurrence, base case
- **State:** f(...) = <one sentence>
- **Recurrence:** ...
- **Base case(s):** ...
- **Answer:** ...
- **Tabulation:** <fill direction and why>

## Space optimisation        (if applicable: loop direction, diagonal variable, what resets per row)

## Complexity
| Approach | TC | SC |
|---|---|---|
<one row per file; memo SC = table + stack>

### How I got these
- **Naive — method: <loop counting / branches^depth / recurrence>** because <why this method fits the code's shape>. <branches, depth and what shrinks — or the recurrence, its first few values and the pattern>. Work per call: <loops + helpers> → total <…>.
- **Memoization / tabulation — method: states × work per state.** States: <parameters and ranges>. Work per state: <loop/helper cost; child calls are O(1) lookups>.
- **Space-optimised:** <what is kept and why that is enough>.
- **Space:** <table> + <stack depth and why>.

## Mistakes I made
1. **<mistake>** — <why it was wrong, failing input> → <fix>

## Related
- <similar problems in the repo, as relative links>
- Pattern: [<name>](<relative path to /patterns/...md>)
~~~

Rules:
- **My summary** is the user's text, completed as described in Step 0, with every addition or correction marked. **Mistakes** is the most important section written from the conversation: record real bugs and complexity corrections. Never invent mistakes; if there were none, write "None recorded."
- **Complexity must always include "How I got these"**: for every approach, name the method from `patterns/how-to-compute-complexity.md` in the repo, say why that method fits (e.g. "recurrence, because each call has a different number of children, so branches^depth would overestimate"), and show the derivation. Use the user's own derivation from the conversation where there was one. No bare results.
- Wrong complexity estimates the user made (and the corrected reasoning) go in **Mistakes I made**.
- Write the other sections in the user's voice ("I"), short and concrete.
- Pattern links are relative from the problem folder to the repo root (`../` × folder depth + 3 for `src/main/java`).

## Root index

Add one row to the right table in the root `README.md` (create the table if the category is new, keeping study order):

| # | Problem | Level | Key idea | TC | SC | Revisit |

Link the problem name to its folder. Escape `|` in key ideas as `\|`. Put follow-ups in Revisit (e.g. "O(n log n) with binary search").

## Patterns

If the problem taught a technique that applies across problems, add it to the matching file in `/patterns/` (state-design, memoization, space-optimisation, reused-arrays, complexity) or create a new one and list it in the root README's Patterns table.

## Revision log and planner

- If the problem is from Striver's Blind 180 sheet (its folder is under `Blind_180`), add one row to the Queue in `revision-log.md`: ID, linked Problem, Topic, Folder, First solved = today, Last revised `—`, Result `—`, Next due = today + 3 days, Reason `Day-3 re-solve`. If it came from a `dsa-mock` session, instead set Next due from the mock rule in that file and add a History row with source `mock`.
- Otherwise add it to the Parked table instead; Parked problems aren't scheduled or synced.
- If the problem is already in Parked (a re-solve of an older problem), move its row into the Queue with Next due = today + 3 days.
- Then sync with the planner as `planner-sync.md` at the repo root describes: pull, then push. Set `sheet_name` to the problem's Striver 180 title if it's from that sheet, so the planner ticks it off in its DSA list.

## Verify before delivering

- `javac -d <tmp> $(find src -name '*.java')` must succeed for the whole repo.
- Run every new file's `main`; every line must match its expected value.
- Package lines match folder paths.
- Every relative link in the new README resolves to an existing file or folder.
- The "My summary" section matches the completed summary the user confirmed, and every added or corrected sentence is marked.
- Every approach in the Complexity table has a matching "How I got these" line naming its method and the reason.
- `revision-log.md` has a Queue row for the new problem, and the planner sync ran (or you said why it couldn't).

## Delivering

- With push access:
  - Branch from the latest `main` (`git fetch origin main`), one short-lived branch per change, named `add-<problem-slug>`.
  - Commit with message `<Sheet> - Problem <N> - <Category>_<Difficulty> : <Problem Name>` (the user's existing style, e.g. `Blind 180 - Problem 20 - DP_Interval_Hard : Palindrome Partitioning II`).
  - Open a pull request into `main` with that title, then **assign it to the user** (`ShazAhmed10`): `gh api -X POST repos/ShazAhmed10/DSA/issues/<number>/assignees -f 'assignees[]=ShazAhmed10'`. (A review request isn't possible because the PR is authored under the user's own account; the assignment is what puts it in their queue.)
  - Never push to `main` directly and never merge the PR yourself.
- Otherwise: build the folder in the workspace, zip it, and send the zip plus the index row, naming the exact path where it goes.
