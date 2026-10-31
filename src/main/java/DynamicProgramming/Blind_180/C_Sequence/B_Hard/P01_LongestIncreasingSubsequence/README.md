# Longest Increasing Subsequence

[LeetCode 300](https://leetcode.com/problems/longest-increasing-subsequence/) · Blind 180 DP · Sequence · Hard

## Problem

```text
Given an integer array nums, return the length of the longest strictly increasing subsequence.

Example 1:
Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

Example 2:
Input: nums = [0,1,0,3,2,3]
Output: 4

Example 3:
Input: nums = [7,7,7,7,7,7,7]
Output: 1

Constraints:
1 <= nums.length <= 2500
-104 <= nums[i] <= 104
```

## Solutions

Each file has a `main` that runs the examples and prints actual vs expected.

| File | Approach |
|---|---|
| [`Approach1_Aa_RecursiveNaive.java`](Approach1_Aa_RecursiveNaive.java) | Approach 1 · Recursive (naive), forward with last index |
| [`Approach1_Ab_RecursiveNaive.java`](Approach1_Ab_RecursiveNaive.java) | Approach 1 · Recursive (naive), mirrored: fix the end, build backwards |
| [`Approach1_B_Memoization.java`](Approach1_B_Memoization.java) | Approach 1 · Memoization |
| [`Approach1_C_Tabulation.java`](Approach1_C_Tabulation.java) | Approach 1 · Tabulation |
| [`Approach1_Da_TabulationSpaceOptimisation.java`](Approach1_Da_TabulationSpaceOptimisation.java) | Approach 1 · Space-optimised: two arrays |
| [`Approach1_Db_TabulationSpaceOptimisation.java`](Approach1_Db_TabulationSpaceOptimisation.java) | Approach 1 · Space-optimised: single array |
| [`Approach2_A_RecursiveNaive.java`](Approach2_A_RecursiveNaive.java) | Approach 2 · Recursive (naive) |
| [`Approach2_B_Memoization.java`](Approach2_B_Memoization.java) | Approach 2 · Memoization |
| [`Approach2_C_Tabulation.java`](Approach2_C_Tabulation.java) | Approach 2 · Tabulation |

## Approach 1: pick / not pick with the last picked index (files `Approach1_*`)

- **State:** `f(curr, last)` = longest increasing subsequence from `curr` onwards, given the last picked index `last` (−1 = nothing picked)
- **Recurrence:** pick if `last == −1 || nums[last] < nums[curr]` → `1 + f(curr+1, curr)`; not pick → `f(curr+1, last)`; max
- **Base case:** `curr == n` → 0
- **Answer:** `f(0, −1)`
- **Negative index:** store at `last + 1` → table `n × (n+1)` (memo) / `(n+1) × (n+1)` (tab, row n = base case)
- **Tabulation:** `curr` from n−1 down; `last` from −1 to `curr − 1` (states that can actually occur)
- **Single array works with either loop direction**: the row writes indices `0..curr` and reads `curr+1` (never written in the row) and `last+1` (read just before it's written).
- `Approach1_Ab` is the mirrored version (fix the end, build backwards); its memo would need an n × n table.

## Approach 2: LIS ending exactly at `i` (files `Approach2_*`)

- **State:** `f(i)` = length of the LIS that **ends at `nums[i]`**
- **Recurrence:** `f(i) = 1 + max f(j)` over `j < i` with `nums[j] < nums[i]`; 1 if none
- **Answer:** `max f(i)` over all `i` (the LIS can end anywhere)
- Only one comparison is needed: `f(j)`'s chain already ends at `nums[j]` and is increasing, so `nums[j] < nums[i]` makes the whole new chain increasing.
- **Tabulation:** `i` from 0 up, inner loop over `j < i`; track the max in the same pass. No further space optimisation (any earlier `dp[j]` can be read).
- **Print the LIS:** store `parent[i]` = the `j` that gave the best value; start at the max and follow parents back.

## Approach 3: binary search, O(n log n) — to do

Keep `tails[k]` = smallest ending value of an increasing subsequence of length `k+1`. For each number, binary-search the first entry ≥ it and replace it, or append. Answer = `tails` length. Trace `[10, 9, 2, 5, 3, 7, 101, 18]` by hand first.

## Complexity

| Approach | TC | SC |
|---|---|---|
| Naive (either) | O(2ⁿ) | O(n) stack |
| Approach 1 memo / tab | O(n²) | O(n²) (+ O(n) stack for memo) |
| Approach 1 two arrays / single array | O(n²) | O(n) |
| Approach 2 memo / tab | O(n²) | O(n) (+ O(n) stack for memo) |
| Binary search | O(n log n) | O(n) |

Approach 2 naive: `T(i) = 1 + T(0) + … + T(i−1)` → `T(i) = 2·T(i−1)` = 2ⁱ; the main loop adds 2⁰ + 2¹ + … + 2ⁿ⁻¹ = 2ⁿ − 1 → O(2ⁿ). Sequential calls **add**; nested calls **multiply**.

## Mistakes I made

1. **Approach 2: called `helper(j + 1)` instead of `helper(j)`** → `j = i−1` called `helper(i)` again → infinite recursion.
2. **Approach 2: added the results of every valid `j` (`+=`)** instead of taking the max — only one element comes right before `nums[i]`.
3. **Approach 2: first compared only with `nums[i−1]`** — that's a contiguous subarray rule; a subsequence can skip.
4. **Defined the state as "LIS up to i"** — doesn't say what it ends with, so there's nothing to compare against. → "ends exactly at i".
5. **Thought the outer loop multiplies the naive cost.** The call sizes grow geometrically, so the sum is dominated by the largest call (see derivation above).

## Related

- [Number of LIS](../../A_Medium/P04_NumberOfLongestIncreasingSubsequence/)
- [Longest Common Substring](../../A_Medium/P02_LongestCommonSubstring/) — same "ends here, max over all" idea
- Patterns: [State design](../../../../../../../../patterns/state-design.md), [Complexity](../../../../../../../../patterns/complexity.md)
