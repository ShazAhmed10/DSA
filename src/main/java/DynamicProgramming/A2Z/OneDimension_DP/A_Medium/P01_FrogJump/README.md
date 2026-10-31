# Frog Jump

Striver A2Z DP · 1D DP · Medium

## Problem

```text
A frog wants to climb a staircase with n steps. Given an integer array heights, where heights[i] contains the height of
the ith step. To jump from the ith step to the jth step, the frog requires abs(heights[i] - heights[j]) energy, where
abs() denotes the absolute difference. The frog can jump from any step (ith) either one (i+1) or two (i+2) steps,
provided it exists. Return the minimum amount of energy required by the frog to go from the 0th step to the (n-1)th
step.

Example 1:
Input: heights = [2, 1, 3, 5, 4]
Output: 2
Explanation:
One possible route can be,
0th step -> 2nd Step = abs(2 - 3) = 1
2nd step -> 4th step = abs(3 - 4) = 1
Total = 1 + 1 = 2.

Example 2:
Input: heights = [7, 5, 1, 2, 6]
Output: 9
Explanation:
One possible route can be,
0th step -> 1st Step = abs(7 - 5) = 2
1st step -> 3rd step = abs(5 - 2) = 3
3rd step -> 4th step = abs(2 - 6) = 4
Total = 2 + 3 + 4 = 9.

Constraints:
1 <= n <= 104
0 <= heights[i] <= 104
```

## Solutions

Each file has a `main` that runs the examples and prints actual vs expected.

| File | Approach |
|---|---|
| [`A_Memoization.java`](A_Memoization.java) | Memoization |
| [`B_Tabulation.java`](B_Tabulation.java) | Tabulation |
| [`C_Tabulation_SpaceOptimisation.java`](C_Tabulation_SpaceOptimisation.java) | Space-optimised: variables |

## Key insight

Greedy (always take the cheaper next jump) can pick a worse route overall. Instead, for every step ask: **what was the last jump that landed here?** It came from 1 step back or 2 steps back, so try both and keep the cheaper one. Overlapping subproblems → DP.

## State, recurrence, base case

- **State:** `f(i)` = minimum energy to reach step `i` from step 0
- **Recurrence:** `f(i) = min(f(i−1) + |h[i] − h[i−1]|, f(i−2) + |h[i] − h[i−2]|)` (second option only if `i ≥ 2`)
- **Base case:** `f(0) = 0`
- **Answer:** `f(n−1)`
- **Space optimisation:** `f(i)` only needs the previous two values → two variables (`prev`, `prev2`)

## Complexity

| Approach | TC | SC |
|---|---|---|
| Naive recursion | O(2ⁿ) | O(n) stack |
| Memoization | O(n) | O(n) table + O(n) stack |
| Tabulation | O(n) | O(n) |
| Space-optimised | O(n) | O(1) |

Naive: each call makes up to 2 recursive calls and the recursion can go n levels deep → at most 2ⁿ calls.

## Mistakes I made

None recorded for this problem. (Related lesson from Climbing Stairs: when shifting two variables, update the **older** one first — `prev2 = prev; prev = curr;` — otherwise both end up holding `curr`.)

## Related

- [Frog Jump with K distances](../../../../Blind_180/B_State_Transition/A_Medium/P01_FrogJumpWithKDistances/) — same idea, look back k steps
- Pattern: [Space optimisation](../../../../../../../../patterns/space-optimisation.md)
