# Longest Common Subsequence

[LeetCode 1143](https://leetcode.com/problems/longest-common-subsequence/) · Blind 180 DP · Sequence · Medium

## Problem

```text
Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common
subsequence, return 0.
A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted
without changing the relative order of the remaining characters.
For example, "ace" is a subsequence of "abcde".
A common subsequence of two strings is a subsequence that is common to both strings.

Example 1:
Input: text1 = "abcde", text2 = "ace"
Output: 3
Explanation: The longest common subsequence is "ace" and its length is 3.

Example 2:
Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.

Example 3:
Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.

Constraints:
1 <= text1.length, text2.length <= 1000
text1 and text2 consist of only lowercase English characters.
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

## Key insight

Compare the last characters. If they match, they belong to some LCS → 1 + both shrink. If not, at least one isn't in the LCS → try dropping each, take the max.

## State, recurrence, base case

- **State:** `f(i, j)` = LCS length of the first `i` chars of `text1` and the first `j` of `text2`
- **Recurrence:** match → `1 + f(i−1, j−1)`; else `max(f(i−1, j), f(i, j−1))`
- **Base case:** `i == 0` or `j == 0` → 0
- **Answer:** `f(n, m)`
- **Tabulation:** both loops forward; row 0 and column 0 stay 0.

## Space optimisation

- **Two arrays (`Da`):** up and diagonal from `prev`, left from `curr`.
- **Single array (`Db`):** loop forward; `dp[j]` is still "up", `dp[j−1]` is already "left", but the **diagonal** (old `dp[j−1]`) was overwritten one step ago. Save it:

```java
int diag = 0;                 // start of each row: dp[i−1][0] = 0
for (j = 1..m) {
    int temp = dp[j];         // save old value (next j's diagonal)
    dp[j] = match ? 1 + diag : Math.max(dp[j], dp[j−1]);
    diag = temp;
}
```

Passing the shorter string as `text2` makes it O(min(n, m)) space.

## Complexity

| Approach | TC | SC |
|---|---|---|
| Naive recursion | O(2^(n+m)) | O(n+m) stack |
| Memoization | O(n·m) | O(n·m) table + O(n+m) stack |
| Tabulation | O(n·m) | O(n·m) |
| Two arrays / single array + diag | O(n·m) | O(m) |

## Mistakes I made

1. **Single array: looped backwards** (copying 0/1 knapsack). That keeps up and diagonal but breaks **left**, which must be from the current row; `"a"`, `"ab"` returned 0. → Neither direction alone works when two needed values (left: new row, diagonal: old row) live in the same cell — loop forward and save the diagonal in a variable.
2. **Didn't understand why a variable is needed at all.** Traced `"ab"` vs `"bb"`: without saving, the second `'b'` matched twice → 2 instead of 1.

## Related

- [Longest Palindromic Subsequence](../P03_LongestPalindromicSubsequence/) — LCS of `s` and `reverse(s)`
- [Edit Distance](../P05_EditDistance/), [Wildcard Matching](../../B_Hard/P02_WildcardMatching/) — same table shape
- Pattern: [Space optimisation](../../../../../../../../patterns/space-optimisation.md)
