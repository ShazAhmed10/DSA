---
name: dsa-revise
description: Use when the user wants to revise or review DSA problems they already solved, asks to be quizzed on their DSA repo, or starts a revision session.
---

# DSA revision from my own repo

Quiz the user on problems they have already solved, using their own notes in github.com/ShazAhmed10/DSA as the answer key. The goal is active recall: they produce the answer first, then compare. Never show a README's answers before they attempt.

## 1. Load the repo

- If a shell is available: `git clone --depth 1 https://github.com/ShazAhmed10/DSA` (public repo).
- Otherwise read the project's synced repo files, the GitHub page for the repo, or ask the user to paste the problem's README.
- Read the root `README.md` index (tables of problems with key idea, TC/SC, Revisit) and `revision-log.md` if it exists.

## 2. Pick problems

- If the user names a category or problems, use those.
- Otherwise pick 3: problems never revised first, then the oldest last-revised, preferring ones whose README has a long "Mistakes I made" section or a previous "Shaky"/"Forgot" score.
- Say which problems you picked in one line, then start with the first.

## 3. Summary first (from memory)

Show only the **Problem** section (statement, examples, constraints). Then:

- **If the README has no "My summary" section:** ask the user to write one from memory — 4–6 lines: key idea, state and recurrence in their own words, the mistake they're most likely to repeat, and how they'd recognise the pattern. Don't help write it.
- **If it already has one:** ask them to write a fresh one from memory anyway, then compare it with their earlier summary afterwards.

Never write, draft or complete the summary for them.

## 4. Quiz one problem at a time

After the summary, ask only about what it left out or got wrong, one question at a time, waiting for each answer:

1. What is the state? (`f(...)` = one sentence)
2. What are the choices and the recurrence?
3. Base case(s) and where the answer is?
4. Tabulation fill order, and the space-optimisation trick (loop direction / diagonal variable / what resets per row)?
5. TC and SC of naive, memo, tab, space-optimised?

Rules:
- Follow the user's hint preference: if they're stuck, give **one** nudge that points their thinking, not the answer; give another only after they've used it.
- After each answer, compare with the README. Say what matched, then correct only what was wrong or missing, briefly.
- Then ask one **"what goes wrong if..."** question built from a real entry in their "Mistakes I made" section (e.g. "In the single-array version, what breaks if you loop forwards?"). Never invent mistakes.
- Optional: offer "Want to code it from a blank file?" If yes, they write it; check it against the examples in the problem's `main` (run it if a shell is available).

Score each problem: **Solid** (recalled state, recurrence, base cases and complexity with at most one nudge), **Shaky** (needed several nudges or got one part wrong), **Forgot** (couldn't reconstruct the state).

## 5. Save the summary

- If the README had no "My summary": add one right after the Problem section, with the user's text **word for word** and the note `> Written by me from memory during revision on <YYYY-MM-DD>, unedited.`
- If it had one: keep the old one and add the new one below it as `### Revision <YYYY-MM-DD>`, word for word, so they can see how their understanding changed. Don't edit either.
- If they want to fix their summary after the comparison, use their corrected words, not yours.

## 6. Wrap up

- One-line summary per problem with its score and the single thing to remember.
- Patterns to reread: link the matching `/patterns/*.md` note for anything that was Shaky or Forgot.
- Update `revision-log.md` at the repo root (create it if missing) with one row per problem:

  `| <YYYY-MM-DD> | <Problem> | <Solid/Shaky/Forgot> | <one-line note> |`

- With push access, ask once whether to commit the summaries and the log (`Revision: <date>`) on a branch and open a pull request; otherwise give the text to paste and the exact paths.
- Suggest when to revisit: Forgot → in 2 days, Shaky → in a week, Solid → in 3 weeks.
