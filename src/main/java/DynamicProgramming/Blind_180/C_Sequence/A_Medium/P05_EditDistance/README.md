# Edit Distance

[LeetCode 72](https://leetcode.com/problems/edit-distance/) · Blind 180 DP · Sequence · Medium

## Problem

```text
Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
You have the following three operations permitted on a word:
Insert a character
Delete a character
Replace a character

Example 1:
Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation:
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')

Example 2:
Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation:
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')

Constraints:
0 <= word1.length, word2.length <= 500
word1 and word2 consist of lowercase English letters.
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

Compare last characters. Match → free, both shrink. Otherwise one operation is needed:
- **insert** `word2[m−1]` into `word1` → `(n, m−1)`
- **delete** `word1[n−1]` → `(n−1, m)`
- **replace** → `(n−1, m−1)`

## State, recurrence, base case

- **State:** `f(i, j)` = min operations to turn the first `i` chars of `word1` into the first `j` of `word2`
- **Recurrence:** match → `f(i−1, j−1)`; else `1 + min(f(i, j−1), f(i−1, j), f(i−1, j−1))`
- **Base cases:** `i == 0` → `j` (inserts); `j == 0` → `i` (deletes)
- **Answer:** `f(n, m)`
- **Tabulation:** fill row 0 with `j` and column 0 with `i` **explicitly** (not zeros); both loops forward.

## Space optimisation

Same as LCS, but column 0 is `i` — different in every row:
- Two arrays: set `curr[0] = i` at the start of every row.
- Single array: at the start of each row, `diag = dp[0]` (old value, `i − 1`), **then** `dp[0] = i`.

## Complexity

| Approach | TC | SC |
|---|---|---|
| Naive recursion | O(3^(n+m)) | O(n+m) stack |
| Memoization | O(n·m) | O(n·m) table + O(n+m) stack |
| Tabulation | O(n·m) | O(n·m) |
| Two arrays / single array + diag | O(n·m) | O(m) |

## Mistakes I made

None in the logic. Notes: the `n == 0 && m == 0` base case is already covered by `n == 0 → m`; in the single-array version, resetting the diagonal at the **start** of each row (right where `dp[0]` changes) is clearer than at the end of the previous row.

## Related

- [LCS](../P01_LongestCommonSubsequence/), [Wildcard Matching](../../B_Hard/P02_WildcardMatching/)
- Pattern: [Space optimisation](../../../../../../../../patterns/space-optimisation.md)
