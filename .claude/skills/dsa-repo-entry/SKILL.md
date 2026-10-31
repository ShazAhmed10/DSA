---
name: dsa-repo-entry
description: Use when the user has finished a DSA problem and asks to add it to their DSA repo, write revision notes, or save the solution files.
---

# DSA repo entry

Turns a finished problem from the current conversation into a consistent entry in the DSA repo (github.com/ShazAhmed10/DSA, Java 21, Maven layout). Every existing problem follows this format; new ones must match it.

## Before writing anything

Collect from the conversation:
- Problem name, platform number and link if known, statement, examples, constraints
- The user's FINAL accepted code for every approach (naive, memo, tabulation, space-optimised, alternative approaches). Use the user's code, not your own rewrites. Never include a version that was later found to be wrong.
- The key insight, state definition, recurrence, base cases, answer cell, tabulation fill order
- Final TC/SC per approach, with corrected values if the user's first estimate was wrong
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

- Existing DP categories: `DynamicProgramming/Blind_180/{A_Knapsack, B_State_Transition, C_Sequence, D_Interval}/{A_Medium, B_Hard}`. A new category gets the next letter prefix.
- `NN` = next number in that difficulty folder, zero-padded (look at the existing `P..` folders).
- Several approaches: prefix `Approach1_`, `Approach2_`. Brute force before recursion: `Aa_IterativeNaive`, `Ab_RecursiveNaive`.
- Each file: `package` matching the folder path, a public class named like the file, the solution method(s), and a `main` that runs the problem's examples (plus any failing inputs from the session) printing `actual + " (expected X)"`.
- Helper types (e.g. `Pair`) are `static` nested classes, never top-level.

## README.md template

Match the existing problem READMEs exactly:

```markdown
# <Problem Name>

[LeetCode <number>](<link>) · <Sheet> DP · <Category> · <Difficulty>

## Problem

\`\`\`text
<statement, examples, constraints>
\`\`\`

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

<derivation for any non-obvious TC: branches^depth, recurrence subtraction, states × work per state>

## Mistakes I made
1. **<mistake>** — <why it was wrong, failing input> → <fix>

## Related
- <similar problems in the repo, as relative links>
- Pattern: [<name>](<relative path to /patterns/...md>)
```

Rules:
- **Mistakes** is the most important section. Record real bugs and complexity corrections from the conversation. Never invent mistakes; if there were none, write "None recorded."
- Write in the user's voice ("I"), short and concrete.
- Pattern links are relative from the problem folder to the repo root (`../` × folder depth + 3 for `src/main/java`).

## Root index

Add one row to the right table in the root `README.md` (create the table if the category is new, keeping study order):

| # | Problem | Level | Key idea | TC | SC | Revisit |

Link the problem name to its folder. Escape `|` in key ideas as `\|`. Put follow-ups in Revisit.

## Patterns

If the problem taught a technique that applies across problems, add it to the matching file in `/patterns/` (state-design, memoization, space-optimisation, reused-arrays, complexity) or create a new one and list it in the root README's Patterns table.

## Verify before delivering

- `javac -d <tmp> $(find src -name '*.java')` must succeed for the whole repo.
- Run every new file's `main`; every line must match its expected value.
- Every relative link in the new README resolves to an existing file or folder.

## Delivering

- With push access: commit on a branch `add-<problem-slug>` with message `<Sheet> - Problem <N> - <Category>_<Difficulty> : <Problem Name>` (the user's existing style) and open a pull request.
- Otherwise: build the folder in the workspace, zip it, and send the zip plus the index row, naming the exact path where it goes.
