# Number of Longest Increasing Subsequence

[LeetCode 673](https://leetcode.com/problems/number-of-longest-increasing-subsequence/) · Blind 180 DP · Sequence · Medium

## Problem

```text
Given an integer array nums, return the number of longest increasing subsequences.
Notice that the sequence has to be strictly increasing.

Example 1:
Input: nums = [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].

Example 2:
Input: nums = [2,2,2,2,2]
Output: 5
Explanation: The length of the longest increasing subsequence is 1, and there are 5 increasing subsequences of length 1,
so output 5.

Constraints:
1 <= nums.length <= 2000
-106 <= nums[i] <= 106
The answer is guaranteed to fit inside a 32-bit integer.
```

## Solutions

Each file has a `main` that runs the examples and prints actual vs expected.

| File | Approach |
|---|---|
| [`A_RecursiveNaive.java`](A_RecursiveNaive.java) | Recursive (naive) |
| [`B_Memoization.java`](B_Memoization.java) | Memoization |
| [`C_Tabulation.java`](C_Tabulation.java) | Tabulation |

## Key insight

Extend the [LIS](../../B_Hard/P01_LongestIncreasingSubsequence/) "ends at `i`" approach to return **two** values: the longest length ending at `i` and **how many** subsequences reach it. Each chain is counted at its last element, so nothing is double-counted.

## State, recurrence, base case

- **State:** `f(i)` = (length, count) of the longest increasing subsequences ending at `i`
- **Start:** (1, 1) — `nums[i]` alone
- **For each `j < i` with `nums[j] < nums[i]`:** `len = f(j).length + 1`
  - `len > best` → `best = len`, `count = f(j).count` (take over)
  - `len == best` → `count += f(j).count` (add)
- **Answer:** max length over all `i`; sum the counts of every `i` with that length. Start the overall best at (0, 0).
- **Memo:** `Pair[]` with `null` = not computed. **Tabulation:** two `int` arrays filled left to right.

## Complexity

| Approach | TC | SC |
|---|---|---|
| Naive recursion | O(2ⁿ) | O(n) stack |
| Memoization | O(n²) | O(n) + O(n) stack |
| Tabulation | O(n²) | O(n) |

## Mistakes I made

1. **Reset the count to 1 / added 1** instead of carrying `f(j).count` — every chain ending at `j` extends to `i`.
2. **Typo `best.length == best.length`** (always true), so shorter lengths were counted too.
3. **Started the overall best at (1, 1)**; `[2,2,2,2,2]` gave 6 instead of 5. → Start at (0, 0).
4. **Mutated the returned object (`currPair.length += 1`).** Harmless in naive, but with memoization it corrupts the cached `dp[j]`. → Compute into a local `int`. (The naive file still has this line as a record.)

## Related

- [LIS](../../B_Hard/P01_LongestIncreasingSubsequence/)
- Pattern: [Memoization](../../../../../../../../patterns/memoization.md)
