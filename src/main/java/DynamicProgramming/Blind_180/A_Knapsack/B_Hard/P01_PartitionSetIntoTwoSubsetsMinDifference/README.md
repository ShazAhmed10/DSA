# Partition a Set into Two Subsets with Minimum Absolute Sum Difference

Blind 180 DP · Knapsack · Hard

## Problem

```text
Given an array arr of n integers, partition the array into two subsets such that the absolute difference between their
sums is minimized.

Example 1:
Input: arr = [1, 7, 14, 5]
Output: 1
Explanation: The array can be partitioned as [1, 7, 5] and [14], with an absolute difference of 1.

Example 2:
Input: arr = [3, 1, 6, 2, 2]
Output: 0
Explanation: The array can be partitioned as [3, 2, 2] and [6, 1], with an absolute difference of 0.

Constraints:
1 ≤ n * sum of array elements ≤ 106
0 < arr[i] <= 104
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

## Key insight

You only need to choose **one** subset; the other is everything else. If subset 1 sums to `s1`, the difference is `|total − 2·s1|`, which is smallest when `s1` is as close as possible to `total / 2`.

So: **0/1 knapsack** with capacity `total / 2` and weight = value = `arr[i]`. The best value that fits is the largest reachable `s1 ≤ total/2`.

Why not search sums above half? Every partition has one side ≤ half and one ≥ half. Searching ≤ half already sees every partition (from its smaller side); above half would find the same partitions again.

## State, recurrence, base case

- **State:** `f(n, c)` = largest subset sum ≤ `c` using the first `n` elements
- **Recurrence:** `f(n, c) = max(f(n−1, c), arr[n−1] + f(n−1, c − arr[n−1]))` — pick only if it fits
- **Base case:** `n == 0` or `c == 0` → 0
- **Answer:** `total − 2 · f(n, total/2)` (always ≥ 0, so no `Math.abs` needed)

## Complexity

| Approach | TC | SC |
|---|---|---|
| Naive recursion | O(2ⁿ) | O(n) stack |
| Memoization | O(n·sum) | O(n·sum) table + O(n) stack |
| Tabulation | O(n·sum) | O(n·sum) |
| Two arrays / single array | O(n·sum) | O(sum) |

## Mistakes I made

1. **Kept the running subset sum in a global variable.** Both the pick and not-pick branches read and wrote the same `subset`, so one branch's choices leaked into the other, and the capacity check happened on the way back up instead of before picking. `[1, 2]` returned −3. → Anything that differs between branches must be a **parameter** (remaining capacity), checked **before** picking.
2. **Didn't see how this is knapsack without weights or capacity.** → Each element is both weight and value; capacity = total/2.
3. **Wrote the difference formula as `total/2 − 2·subset` in my first notes.** It is `total − 2·subset`.

## Related

- [0/1 Knapsack](../../A_Medium/P01_ZeroOneKnapsack/)
- [Last Stone Weight II](../P02_LastStoneWeightII/) — same problem in disguise, solved with the boolean table
- Pattern: [Space optimisation](../../../../../../../../patterns/space-optimisation.md)
