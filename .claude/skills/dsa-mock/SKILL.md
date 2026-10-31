---
name: dsa-mock
description: Use when the user asks for a mock coding interview, timed DSA practice, or interview-style practice on a problem they haven't solved.
---

# DSA mock interview

Run a realistic coding interview: one unseen problem, a time limit, limited help, and the user explaining their thinking. Act as a neutral interviewer, not a tutor, until the debrief.

## 1. Set up

- Ask once: category (or "surprise me"), difficulty (Medium / Hard), and time (default 45 minutes).
- Pick a well-known problem the user has **not** solved: check the root README index of github.com/ShazAhmed10/DSA if available. Prefer categories they've already finished so the test is application, not new theory.
- Present the problem in your own words with 2 examples and constraints. Don't name the problem or its pattern.
- Note the start time with the current-time tool and say when time is up (e.g. "You have until 19:45").

## 2. During the interview

Expected flow; nudge the user back into it if they skip a step:
1. Clarifying questions (answer them like an interviewer would)
2. Brute force idea and its complexity
3. Better approach, explained in words before coding
4. Code
5. Dry run on an example and edge cases
6. Final TC/SC

Interviewer rules:
- Don't confirm whether an approach is right while they're still reasoning; ask probing questions instead ("What's the state?", "Why does that work?", "What happens for n = 1?").
- At most **one** hint for the whole interview, only if they're stuck for a while or ask. It must nudge, not tell. Record that it was used.
- Each time the user replies, check the clock; mention the remaining time at roughly the halfway point and with 10 minutes left.
- If they finish early, ask one follow-up (space optimisation, a variant, or a harder constraint).
- Stop at the time limit and move to the debrief, even if unfinished.

## 3. Debrief

Scorecard (1–4 each, with one line of evidence):
- Problem understanding & clarifying questions
- Approach & correctness
- Complexity analysis
- Code quality (naming, structure, edge cases)
- Communication (explained before coding, thought out loud)
- Time management

Then:
- Hint used: yes/no.
- What went well (2 points), what to improve (3 concrete points).
- The intended approach, briefly, if they didn't reach it.
- The real problem name and link.
- Offer to add it to the repo with the `dsa-repo-entry` skill, recording mistakes from this session in its README.
