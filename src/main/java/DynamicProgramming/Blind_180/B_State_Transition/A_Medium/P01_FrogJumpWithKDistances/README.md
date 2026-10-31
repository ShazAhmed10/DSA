# Frog Jump with K Distances

Blind 180 DP · State Transition · Medium

## Problem

```text
A frog wants to climb a staircase with n steps. Given an integer array heights, where heights[i] contains the height of
the ith step, and an integer k. To jump from the ith step to the jth step, the frog requires abs(heights[i] - heights[j])
energy, where abs() denotes the absolute difference. The frog can jump from the ith step to any step in the range
[i + 1, i + k], provided it exists. Return the minimum amount of energy required by the frog to go from the 0th step to
the (n-1)th step.

Example 1:
Input: heights = [10, 5, 20, 0, 15], k = 2
Output: 15
Explanation:
0th step -> 2nd step, cost = abs(10 - 20) = 10
2nd step -> 4th step, cost = abs(20 - 15) = 5
Total cost = 10 + 5 = 15.

Example 2:
Input: heights = [15, 4, 1, 14, 15], k = 3
Output: 2
Explanation:
0th step -> 3rd step, cost = abs(15 - 14) = 1
3rd step -> 4th step, cost = abs(14 - 15) = 1
Total cost = 1 + 1 = 2.
```

## Solutions

Each file has a `main` that runs the examples and prints actual vs expected.

| File | Approach |
|---|---|
| [`A_RecursiveNaive.java`](A_RecursiveNaive.java) | Recursive (naive) |
| [`B_Memoization.java`](B_Memoization.java) | Memoization |
| [`C_Tabulation.java`](C_Tabulation.java) | Tabulation |

## Key insight

Same as [Frog Jump](../../../../A2Z/OneDimension_DP/A_Medium/P01_FrogJump/), but the last jump can come from any of the previous `k` steps, so the inner loop looks back `k` steps instead of 2.

## State, recurrence, base case

- **State:** `f(i)` = minimum energy to reach index `i`
- **Recurrence:** `f(i) = min over j in 1..k (i − j ≥ 0) of f(i − j) + |h[i] − h[i − j]|`
- **Base case:** `f(0) = 0`
- **Answer:** `f(n − 1)`

## Complexity

| Approach | TC | SC |
|---|---|---|
| Naive recursion | O(kⁿ) | O(n) stack |
| Memoization | O(n·k) | O(n) table + O(n) stack |
| Tabulation | O(n·k) | O(n) |

Naive: up to k branches per call, depth up to n (along the jump-1 path). Memo/tab: n states × k work each. Tabulation is preferred: no recursion stack, no `StackOverflowError` at n = 10⁴.

## Mistakes I made

1. **Off-by-one in the cost.** With a count-based `n`, `helper(n − i)` lands on index `n − 1 − i`, so the cost is `|h[n−1] − h[n−1−i]|`, not `h[n−i]`. → Index-based parameters avoid the n vs n−1 confusion.
2. **Overwrote `currMin` instead of taking the min** — the loop returned whichever jump was tried last.
3. **Passed a useless `overallMin` parameter** that was always `MAX_VALUE`.
4. **Memo: filled `dp` with `MAX_VALUE` but checked `dp[n] != 0`.** The very first call returned immediately and nothing was computed. → The sentinel must be a value that can't be a real answer, used in **both** the fill and the check.
5. **Memo: returned `dp[1]` (the base case) instead of the answer for the last step.**

## Related

- [Frog Jump](../../../../A2Z/OneDimension_DP/A_Medium/P01_FrogJump/)
- Pattern: [Memoization](../../../../../../../../patterns/memoization.md)
