# Wildcard Matching

[LeetCode 44](https://leetcode.com/problems/wildcard-matching/) · Blind 180 DP · Sequence · Hard

## Problem

```text
Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Example 1:
Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".

Example 2:
Input: s = "aa", p = "*"
Output: true
Explanation: '*' matches any sequence.

Example 3:
Input: s = "cb", p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.

Constraints:
0 <= s.length, p.length <= 2000
s contains only lowercase English letters.
p contains only lowercase English letters, '?' or '*'.
```

## Solutions

Each file has a `main` that runs the examples and prints actual vs expected.

| File | Approach |
|---|---|
| [`A_RecursiveNaive.java`](A_RecursiveNaive.java) | Recursive (naive) |
| [`B_Memoization.java`](B_Memoization.java) | Memoization |
| [`C_Tabulation.java`](C_Tabulation.java) | Tabulation |
| [`Da_Tabulation_2ArraySpaceOptimisation.java`](Da_Tabulation_2ArraySpaceOptimisation.java) | Space-optimised: two arrays |
| [`Db_Tabulation_1ArraySpaceOptimisation.java`](Db_Tabulation_1ArraySpaceOptimisation.java) | Space-optimised: single array |

## Key insight

Compare the last characters of `s` and `p`:
- same character or `?` → both shrink
- `*` → either it matches **nothing** (drop the `*`: `(n, m−1)`) or **one more character** and stays available (`(n−1, m)` — like staying on an item in unbounded knapsack)
- anything else → false

## State, recurrence, base case

- **State:** `f(n, m)` = does `s[0..n)` match `p[0..m)`?
- **Base cases:** both empty → true; `p` empty → false; `s` empty → true only if `p[0..m)` is all `*`
- **Answer:** `f(n, m)`
- **Memo:** `Boolean[][]` with `null` = not computed; put the memo check **before** the recursive `n == 0` base case (indices are always valid). Use `||` to short-circuit.
- **Tabulation:** `dp[0][0] = true`; row 0: `dp[0][j] = p[j−1] == '*' && dp[0][j−1]`; column 0 (i ≥ 1) false by default; both loops forward.

## Space optimisation

Same shape as LCS / Edit Distance. With reused arrays:
- write **every** cell each row — set `false` explicitly on a mismatch;
- index 0 is `true` only for row 0 → set `curr[0] = false` each row (two arrays), or `diag = dp[0]; dp[0] = false;` at the start of each row (single array).

## Complexity

| Approach | TC | SC |
|---|---|---|
| Naive recursion | O(2^(n+m)) | O(n+m) stack |
| Memoization | O(n·m) | O(n·m) table + O(n+m) stack |
| Tabulation | O(n·m) | O(n·m) |
| Two arrays / single array + diag | O(n·m) | O(m) |

## Mistakes I made

1. **Used `|` instead of `||`** — no short-circuit, so both subtrees always ran.
2. **Memo check after a base case that recurses** — the `n == 0` walk down the pattern was repeated instead of reused.
3. **Two arrays, index 0 never reset:** the array that started as row 0 kept `[0] = true`; `s = "aaa"`, `p = "a"` returned true.
4. **Two arrays, mismatch didn't write anything:** a cell kept `true` from two rows earlier; `s = "aab"`, `p = "*a"` returned true. → `Arrays.fill(curr, false)` after each swap, or write every cell explicitly.

## Related

- [Edit Distance](../../A_Medium/P05_EditDistance/), [LCS](../../A_Medium/P01_LongestCommonSubsequence/)
- Patterns: [Memoization](../../../../../../../../patterns/memoization.md), [Reused arrays](../../../../../../../../patterns/reused-arrays.md)
