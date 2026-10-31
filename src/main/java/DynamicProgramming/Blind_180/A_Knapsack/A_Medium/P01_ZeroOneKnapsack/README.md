# 0/1 Knapsack

Blind 180 DP · Knapsack · Medium

## Problem

```text
Given two integer arrays, val and wt, each of size n, which represent the values and weights of n items respectively,
and an integer W representing the maximum capacity of a knapsack, determine the maximum value achievable by selecting a
subset of the items such that the total weight of the selected items does not exceed the knapsack capacity W.
Each item can either be picked in its entirety or not picked at all (0-1 property). The goal is to maximize the sum of
the values of the selected items while keeping the total weight within the knapsack's capacity.

Example 1:
Input: val = [60, 100, 120], wt = [10, 20, 30], W = 50
Output: 220
Explanation: Select items with weights 20 and 30 for a total value of 100 + 120 = 220.

Example 2:
Input: val = [10, 40, 30, 50], wt = [5, 4, 6, 3], W = 10
Output: 90
Explanation: Select items with weights 4 and 3 for a total value of 40 + 50 = 90.
```

## Solutions

Each file has a `main` that runs the examples and prints actual vs expected.

| File | Approach |
|---|---|
| [`A_RecursiveNaive.java`](A_RecursiveNaive.java) | Recursive (naive) |
| [`B_Memoization.java`](B_Memoization.java) | Memoization |
| [`C_Tabulation.java`](C_Tabulation.java) | Tabulation |
| [`Da_TabulationSpaceOptimisation.java`](Da_TabulationSpaceOptimisation.java) | Space-optimised: two arrays |
| [`Db_TabulationSpaceOptimisation.java`](Db_TabulationSpaceOptimisation.java) | Space-optimised: single array |
| [`Dc_TabulationSpaceOptimisation.java`](Dc_TabulationSpaceOptimisation.java) | Space-optimised: single array (compact loop) |

> The original folder called this "Bounded Knapsack". It is the **0/1 knapsack**: each item is picked at most once. ("Bounded knapsack" usually means each item has a limited count greater than 1.)

## Key insight

For every item there are two choices: take it (if it fits) or skip it. The only thing that changes between calls is **which items are left** and **how much capacity is left**, so those two are the state.

## State, recurrence, base case

- **State:** `f(n, w)` = best value using the first `n` items with capacity `w`
- **Recurrence:** `f(n, w) = max(notPick = f(n−1, w), pick = val[n−1] + f(n−1, w − wt[n−1]))` — pick only if `wt[n−1] ≤ w`
- **Base case:** `n == 0` or `w == 0` → 0
- **Answer:** `f(n, W)`
- **Tabulation:** row 0 and column 0 stay 0 (Java default); `i` from 1 to n, `w` from 1 to W

## Space optimisation

| File | Idea |
|---|---|
| `Da` | Two arrays `prev` / `curr`, swapped after each item |
| `Db` | One array, capacity loop **from W down to 1** |
| `Dc` | One array, loop from W down to `wt[i−1]` → the `if` disappears: `dp[j] = max(dp[j], val + dp[j − wt])` |

Why backwards: `dp[j]` reads `dp[j − wt]`, a cell to the **left**. Looping right-to-left means the left side still holds the previous item's values when read. Looping left-to-right would read values already updated for the **same** item, i.e. pick it twice — that is **unbounded** knapsack.

## Complexity

| Approach | TC | SC |
|---|---|---|
| Naive recursion | O(2ⁿ) | O(n) stack |
| Memoization | O(n·W) | O(n·W) table + O(n) stack |
| Tabulation | O(n·W) | O(n·W) |
| Two arrays | O(n·W) | O(W) |
| Single array | O(n·W) | O(W) |

## Mistakes I made

1. **Didn't understand why the single-array loop must go backwards.** Traced one item (weight 2, value 10, W = 4): left-to-right gave `dp[4] = 20` because `dp[2]` had already been updated with the same item. → Reads point left, so writes must move right to left.

## Related

- [Coin Change II](../P02_CoinChangeII/) — unbounded version (forward loop)
- [Partition set into two subsets](../../B_Hard/P01_PartitionSetIntoTwoSubsetsMinDifference/), [Last Stone Weight II](../../B_Hard/P02_LastStoneWeightII/) — weight = value = element
- Pattern: [Space optimisation](../../../../../../../../patterns/space-optimisation.md)
