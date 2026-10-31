# Longest Palindromic Subsequence

[LeetCode 516](https://leetcode.com/problems/longest-palindromic-subsequence/) · Blind 180 DP · Sequence · Medium

## Problem

```text
Given a string s, find the longest palindromic subsequence's length in s.
A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing
the order of the remaining elements.

Example 1:
Input: s = "bbbab"
Output: 4
Explanation: One possible longest palindromic subsequence is "bbbb".

Example 2:
Input: s = "cbbd"
Output: 2
Explanation: One possible longest palindromic subsequence is "bb".

Constraints:
1 <= s.length <= 1000
s consists only of lowercase English letters.
```

## Solutions

Each file has a `main` that runs the examples and prints actual vs expected.

| File | Approach |
|---|---|
| [`A_RecursiveNaive.java`](A_RecursiveNaive.java) | Recursive (naive) |
| [`B_Memoization.java`](B_Memoization.java) | Memoization |
| [`Ca_Tabulation.java`](Ca_Tabulation.java) | Tabulation, (n+1)×(n+1) table, first version |
| [`Cb_Tabulation.java`](Cb_Tabulation.java) | Tabulation, n×n table, diagonal filled first |
| [`Da_TabulationSpaceOptimisation.java`](Da_TabulationSpaceOptimisation.java) | Space-optimised: two arrays |
| [`Db_TabulationSpaceOptimisation.java`](Db_TabulationSpaceOptimisation.java) | Space-optimised: single array |

## Key insight

A palindrome reads the same from both ends, so work from **both ends at once**: the state is an **interval** `[start, end]`.

Alternative: LPS(s) = LCS(s, reverse(s)) — the LCS length as is, **not doubled** (each LCS match is one character of the palindrome).

## State, recurrence, base case

- **State:** `f(start, end)` = LPS length within `s[start..end]`
- **Recurrence:** `s[start] == s[end]` → `2 + f(start+1, end−1)`; else `max(f(start+1, end), f(start, end−1))`
- **Base cases:** `start > end` → 0 (empty); `start == end` → 1 (single char)
- **Answer:** `f(0, n−1)`
- **Tabulation:** every cell reads shorter intervals → `start` from n−1 **down**, `end` from `start+1` **up**; diagonal = 1, below the diagonal stays 0.

## Space optimisation

Same up / left / diagonal shape as LCS. Single array: set `dp[start] = 1` at the start of each row; the saved diagonal starts at 0 (the old value at index `start` was below the diagonal).

## Complexity

| Approach | TC | SC |
|---|---|---|
| Naive recursion | O(2ⁿ) | O(n) stack |
| Memoization | O(n²) | O(n²) table + O(n) stack |
| Tabulation | O(n²) | O(n²) |
| Two arrays / single array + diag | O(n²) | O(n) |

## Mistakes I made

1. **First idea: generate all subsequences and check each** — O(n·2ⁿ) and the state would be the whole subsequence, so it can't become a DP.
2. **Added 1 instead of 2 on a match** (a matching pair is two characters); `"cbbd"` returned 1. And with `2 +`, `start == end` needs its own base case returning 1.
3. **Tabulation: started `end` at 0 and `break` when `start > end`**, which exited immediately for every row except 0. → Start the loop where meaningful cells begin (`end = start`).
4. **Wondered whether the LCS approach needs `2 × LCS`.** No — the LCS already is the whole palindrome.

## Related

- [LCS](../P01_LongestCommonSubsequence/)
- [Matrix Chain Multiplication](../../../D_Interval/B_Hard/P01_MatrixChainMultiplication/) — split-point interval DP
- Pattern: [Space optimisation](../../../../../../../../patterns/space-optimisation.md)
