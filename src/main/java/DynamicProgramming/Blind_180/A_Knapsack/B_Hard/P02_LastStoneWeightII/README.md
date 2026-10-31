# Last Stone Weight II

[LeetCode 1049](https://leetcode.com/problems/last-stone-weight-ii/) · Blind 180 DP · Knapsack · Hard

## Problem

```text
You are given an array of integers stones where stones[i] is the weight of the ith stone.
We are playing a game with the stones. On each turn, we choose any two stones and smash them together.
Suppose the stones have weights x and y with x <= y. The result of this smash is:
If x == y, both stones are destroyed, and
If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
At the end of the game, there is at most one stone left.
Return the smallest possible weight of the left stone. If there are no stones left, return 0.

Example 1:
Input: stones = [2,7,4,1,8,1]
Output: 1
Explanation:
We can combine 2 and 4 to get 2, so the array converts to [2,7,1,8,1] then,
we can combine 7 and 8 to get 1, so the array converts to [2,1,1,1] then,
we can combine 2 and 1 to get 1, so the array converts to [1,1,1] then,
we can combine 1 and 1 to get 0, so the array converts to [1], then that's the optimal value.

Example 2:
Input: stones = [31,26,33,21,40]
Output: 5

Constraints:
1 <= stones.length <= 30
1 <= stones[i] <= 100
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

Smashing `y − x` repeatedly gives every original stone a `+` or `−` sign in the final weight, so the answer is the minimum difference between two subsets — the [partition problem](../P01_PartitionSetIntoTwoSubsetsMinDifference/) in disguise.

Solved here with the **true/false (subset-sum) table** to learn the second approach: find, for every `s` from 0 to total/2, whether a subset sums to **exactly** `s`; then scan from total/2 down and take the first reachable `s`.

## State, recurrence, base case

- **State:** `f(i, s)` = can some subset of the first `i` stones sum to exactly `s`?
- **Recurrence:** `f(i, s) = f(i−1, s) || f(i−1, s − stones[i−1])` (second only if it fits)
- **Base cases:** `s == 0` → true (empty subset, for every `i`); `i == 0, s > 0` → false
- **Answer:** largest `s ≤ total/2` with `f(n, s)` true → `total − 2s`

Column 0 is never read by the final scan, but every `true` in the table is built on it: a stone that exactly fills `s` reads `dp[i−1][0]`.

## Complexity

| Approach | TC | SC |
|---|---|---|
| Naive recursion (with scan) | O(sum · 2ⁿ) | O(n) stack |
| Memoization (one table shared by the whole scan) | O(n·sum) | O(n·sum) table + O(n) stack |
| Tabulation | O(n·sum) | O(n·sum) |
| Two arrays / single array | O(n·sum) | O(sum) |

The memo table must be created **once** and shared by every call in the scan; each `(i, s)` is then computed at most once in total.

## Mistakes I made

1. **Added special cases the general code already handled.** `if (n == 1)` was unnecessary, and `if (maxSubsetCapacity == 0) return 0` was logically wrong (an empty subset means the difference is `totalSum`, not 0). → Check whether the general code covers an input before adding a special case.
2. **Used `Boolean[][]` in tabulation.** Only memoization needs the third "not computed" (`null`) value; tabulation should use primitive `boolean[][]`.
3. **Lost `dp[0][0] = true` while cleaning up.** Starting the row loop at 1 skipped setting row 0's column 0, so the first stone could never be picked; `[1, 2]` returned 3. → After any base-case refactor, list every base-case cell and confirm each is still set.
4. **(Two arrays) Index 0 must be true in both arrays**, because after a swap the old `prev` becomes `curr` and index 0 is never rewritten.
5. **Compared memo and tab runtimes on LeetCode.** Memo can be faster in ms because it only visits reachable states and stops early; same big-O, and LeetCode timings are noisy.

## Related

- [Partition set into two subsets](../P01_PartitionSetIntoTwoSubsetsMinDifference/) — max-value version of the same table
- [Target Sum](../P03_TargetSum/) — counting version
- Patterns: [Memoization](../../../../../../../../patterns/memoization.md), [Space optimisation](../../../../../../../../patterns/space-optimisation.md)
