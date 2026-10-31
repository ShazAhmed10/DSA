# Longest Common Substring

Blind 180 DP · Sequence · Medium

## Problem

```text
Given two strings str1 and str2, find the length of their longest common substring.
A substring is a contiguous sequence of characters within a string.

Example 1:
Input: str1 = "abcde", str2 = "abfce"
Output: 2
Explanation: The longest common substring is "ab", which has a length of 2.

Example 2:
Input: str1 = "abcdxyz", str2 = "xyzabcd"
Output: 4
Explanation: The longest common substring is "abcd", which has a length of 4.

n=str1.length
m=str2.length
1<= n, m <=103
str1 and str2 are in lowercase alphabet
```

## Solutions

Each file has a `main` that runs the examples and prints actual vs expected.

| File | Approach |
|---|---|
| [`Aa_IterativeNaive.java`](Aa_IterativeNaive.java) | Iterative brute force |
| [`Ab_RecursiveNaive.java`](Ab_RecursiveNaive.java) | Recursive (naive) |
| [`B_Memoization.java`](B_Memoization.java) | Memoization |
| [`C_Tabulation.java`](C_Tabulation.java) | Tabulation |
| [`Da_TabulationSpaceOptimisation.java`](Da_TabulationSpaceOptimisation.java) | Space-optimised: two arrays |
| [`Db_TabulationSpaceOptimisation.java`](Db_TabulationSpaceOptimisation.java) | Space-optimised: single array |

## Key insight

Any "skip a character" branch lets the recursion jump over gaps — that's a subsequence, not a substring. Instead, fix where the substring **starts** (or ends) and only extend along the diagonal. Since it can start anywhere, the answer is the **max over all starting pairs**.

Getting there: write the brute force first (two loops for the start in each string + a `while` that extends while characters match), then replace only the `while` loop with a recursion. Neighbouring starts `(i, j)` and `(i+1, j+1)` walk the same diagonal → memoize.

## State, recurrence, base case

- **State:** `g(i, j)` = length of the common run starting at `str1[i]` and `str2[j]`
- **Recurrence:** `str1[i] == str2[j]` → `1 + g(i+1, j+1)`; else 0
- **Base case:** `i == n` or `j == m` → 0
- **Answer:** `max g(i, j)` over all pairs
- **Tabulation:** both loops **backwards** (each cell reads `[i+1][j+1]`); track `best` while filling.
- **Single array:** reads only index `j+1` of the previous row → loop `j` **forwards**, and write 0 explicitly on mismatch.

(The textbook version is the mirror image: "run that ends at (i, j)", `1 + dp[i−1][j−1]`, forward loops.)

## Complexity

| Approach | TC | SC |
|---|---|---|
| Brute force / naive recursion | O(n·m·min(n, m)) | O(1) / O(min(n, m)) stack |
| Memoization | O(n·m) | O(n·m) table + O(min(n, m)) stack |
| Tabulation | O(n·m) | O(n·m) |
| Two arrays / single array | O(n·m) | O(m) |

## Mistakes I made

1. **Tried LCS with the max of all three calls.** Skips stitch characters across gaps: `"ab"` vs `"acb"` gave 2 instead of 1.
2. **Used one start pointer for both strings**, which misses matches at different positions (`"abcd"` is at 0 in one string and 3 in the other). → Independent starts.
3. **Tried to make the recursion do everything** (loop over `j`, carry `len`, track the max). → Keep the outer loops; only the `while` becomes recursive.
4. **Memo stored results but never read them back**, and used 0 as "not computed" although 0 is a real answer. → Add the lookup; sentinel −1.
5. **Reused arrays need an explicit 0 on mismatch** — in the 2D table untouched cells were 0, after a swap they hold old values.

## Related

- [LCS](../P01_LongestCommonSubsequence/) — subsequence version
- [LIS approach 2](../../B_Hard/P01_LongestIncreasingSubsequence/) — "ends exactly here" state
- Patterns: [State design](../../../../../../../../patterns/state-design.md), [Reused arrays](../../../../../../../../patterns/reused-arrays.md)
