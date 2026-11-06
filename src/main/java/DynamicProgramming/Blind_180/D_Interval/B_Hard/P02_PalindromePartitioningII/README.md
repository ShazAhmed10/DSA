# Palindrome Partitioning II

[LeetCode 132](https://leetcode.com/problems/palindrome-partitioning-ii/) · Blind 180 DP · Interval (partition) · Hard

## Problem

```text
Given a string s, partition s such that every substring of the partition is a palindrome.
Return the minimum cuts needed for a palindrome partitioning of s.

Example 1:
Input: s = "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.

Example 2:
Input: s = "a"
Output: 0

Example 3:
Input: s = "ab"
Output: 1

Constraints:
1 <= s.length <= 2000
s consists of lowercase English letters only.
```

## My summary

> Written by me from memory right after solving, unedited.

This problem is a 2 in 1 problem where we find the minCuts as well as check the substrings if palindrome or not. Both the aspects are naively built then optimised using DP's memo and tab techniques.  The minCuts part of it requires only a single state i.e. start in it's function which is initialised with 0 and slowly keeps increasing in size with the help of the end loop inside the function. This way we try to cut the string at every end and check if it is a palindrome or not. If it is then we minimise this ans among all the ends we have traversed for a particular start and return it as the current ans. The recursion is applied while checking if the substring is a palindrome or not after checking the current 2 characters match we then recurse for the remaining. The base case is hit when start reaches n then -1 is returned to outcome 0 which becomes the correct default min value if there are no cuts required. A special care is required to compute the tc and sc as we hav to take the help of different computing methods to derive it.

## Solutions

Each file has a `main` that runs the examples and prints actual vs expected.

| File | Approach |
|---|---|
| [`A_RecursiveNaive.java`](A_RecursiveNaive.java) | Recursive (naive), recursive palindrome check |
| [`Ba_Memoization.java`](Ba_Memoization.java) | Memoization of the cuts only; palindrome check still recomputed |
| [`Bb_Memoization_PalindromeDp.java`](Bb_Memoization_PalindromeDp.java) | Memoization of both: cuts `dp[start]` and palindrome `palDp[start][end]` |
| [`Ca_Tabulation.java`](Ca_Tabulation.java) | Tabulation of the cuts only; palindrome check still recomputed |
| [`Cb_Tabulation_PalindromeTable.java`](Cb_Tabulation_PalindromeTable.java) | Tabulation of both: 2D palindrome table, then the cuts table |
| [`Da_TabulationSpaceOptimisation_2Arrays.java`](Da_TabulationSpaceOptimisation_2Arrays.java) | One loop; two palindrome rows (swap + clear) |
| [`Db_TabulationSpaceOptimisation_2Arrays.java`](Db_TabulationSpaceOptimisation_2Arrays.java) | One loop; two palindrome rows, length cases merged into one rule |
| [`Dc_TabulationSpaceOptimisation_1Array.java`](Dc_TabulationSpaceOptimisation_1Array.java) | One loop; single palindrome array, `end` looping backwards |

## Key insight

Choose the **first piece** `s[start..end]`. If it's a palindrome, the rest `s[end+1..]` is the same problem on a shorter suffix. The end of the string never changes, so the state is just `start` — a 1D dp, not `(start, end)`.

Two problems in one:
1. **Minimum cuts** over the start index (the main dp).
2. **Is `s[start..end]` a palindrome?** — checked inside every iteration. Recomputing it each time costs O(n) per check; storing it in its own dp makes each check O(1) and brings the total from O(n³) to O(n²).

**Recognising the pattern:** "split a string/array into pieces where every piece must satisfy a property; minimise the number of pieces or cuts" → 1D dp on the start index (*first piece + best for the rest*), with a precomputed table for the property.

## State, recurrence, base case

**Cuts:**
- **State:** `f(start)` = minimum cuts to split the suffix `s[start..n-1]` into palindromes
- **Recurrence:** `f(start) = min over end in [start, n−1] with s[start..end] a palindrome of 1 + f(end + 1)`
- **Base case:** `f(n) = −1`. Every piece adds 1, so the sum counts **pieces**; the −1 cancels the +1 of the last piece, turning pieces into cuts (pieces − 1). Equivalent: `f(n) = 0` and return `f(0) − 1` from the main function.
- **Answer:** `f(0)`
- **Tabulation:** `start` from n−1 **down** to 0, because `f(start)` reads `f(end + 1)` with `end + 1 > start`. Table size `n + 1` for the base cell.

**Palindrome:**
- **State:** `isPal[start][end]` = is `s[start..end]` a palindrome
- **Recurrence:** `isPal[start][end] = (s[start] == s[end]) && (inside empty || isPal[start+1][end−1])`, where the inside `start+1 … end−1` is empty when `end − start ≤ 1` (lengths 1 and 2). An empty inside counts as a palindrome.
- **Tabulation:** `isPal[start][end]` reads row `start + 1` → `start` must go **down** (n−1 to 0).

## Space optimisation

- `cutsDp[start]` needs only row `start` of the palindrome table and cells of `cutsDp` to its right → fill the palindrome row and `cutsDp[start]` in the **same** iteration.
- The palindrome row only reads the previous row (`start + 1`) → two rows of length n (`Da`, `Db`): complete the swap with a temp, and make sure no stale value survives (clear `palCurr` after swapping, or write every cell including `false` on a mismatch).
- One array (`Dc`): row `start` writes `pal[end]` and reads `pal[end − 1]` from the previous row, a cell to the **left** → loop `end` **backwards** (n−1 down to `start`), like the single-array 0/1 knapsack. The order of `end` doesn't affect the minimum.
- `cutsDp` can't be reduced: `cutsDp[start]` can read **any** cell to its right.

## Complexity

| Approach | TC | SC |
|---|---|---|
| Naive | O(n² · 2ⁿ) | O(n) stack |
| Memoization, cuts only | O(n³) | O(n) + O(n) stack |
| Memoization, cuts + palindrome dp | O(n²) | O(n²) + O(n) stack |
| Tabulation, cuts only | O(n³) | O(n) |
| Tabulation, cuts + palindrome table | O(n²) | O(n²) |
| Two palindrome rows | O(n²) | O(n) |
| Single palindrome array | O(n²) | O(n) |

### How I got these

- **Naive — method: recurrence** because the recursive call is inside a loop, so a call on a suffix of length m has m children of sizes m−1, …, 0 — the number of children varies, so branches^depth (nⁿ) would overestimate badly. Counting calls on the worst case (`"aaaa"`, every piece a palindrome): `T(m) = 1 + T(m−1) + … + T(0)`, `T(0) = 1` → 1, 2, 4, 8 → **2ⁿ calls** (same recurrence as LIS "ends at i"). Work per call: a loop of up to n iterations, each with a palindrome check that is itself **O(n)** — method recurrence: `T(L) = T(L−2) + O(1)` because each call removes one character from each end → about L/2 calls → O(L) ≤ O(n). So up to O(n²) per call, and the calls happen one after another, each doing its own loop: total ≤ 2ⁿ × n² = **O(n² · 2ⁿ)** (an upper bound; deep calls work on shorter suffixes). Stack: `helper` depth ≤ n, plus the palindrome recursion ≤ n/2 on top → O(n).
- **Memoization / tabulation, cuts only — method: states × work per state.** n states (`start`). Work per state: the loop of n iterations, each with an O(n) palindrome check (not memoized, so it is real work) → O(n²) per state → **O(n³)**.
- **With the palindrome dp — method: states × work per state, one row per memoized function, added.** Palindrome: n² states × O(1) (its child is a lookup) = O(n²). Cuts: n states × (n iterations × O(1), since both the palindrome check and the child call are lookups) = O(n²). Each state's work happens once, so the two totals **add**: O(n²) + O(n²) = **O(n²)**.
- **Space-optimised:** the palindrome table only needs the previous row (two arrays) or, looping `end` backwards, a single array → O(n); `cutsDp` stays O(n).
- **Space:** O(n²) for the palindrome table in the non-optimised versions; O(n) stacks for memoization.

## Mistakes I made

1. **Thought the state was `(start, end)`** and got stuck because `end` never changed. → A parameter that never changes isn't part of the state: `f(start)`.
2. **Started the loop at `end = start + 1`**, skipping the single-character piece; `"a"` returned `Integer.MAX_VALUE`. I first tried stopping the loop at `n − 2` and adding a special check. → The shortest first piece is `s[start..start]`; start the loop at `end = start`.
3. **Every piece added 1, so the result counted pieces, not cuts.** Tried `start == n − 1 → 0` as the base case, which fails when a piece reaches the end of the string: `f(n)` is still called, returns `MAX_VALUE`, and `1 + MAX_VALUE` overflows (`"aa"`). → `f(n) = −1`, or `f(n) = 0` and return `f(0) − 1` in the main function only.
4. **Wrote `1 + Math.min(f(end + 1), currMinCuts)`** instead of `Math.min(1 + f(end + 1), currMinCuts)`. Every later palindrome added another 1 to the existing best; `"aaba"` returned 2 instead of 1. → The +1 belongs to one option's cost, added before comparing.
5. **`isPalindrome` didn't return its recursive result** (called it and fell through to `return false`); `"aa"` returned 1. → `return isPalindrome(s, start + 1, end − 1);`
6. **Naive complexity: said branches = 1** (one recursive call statement), treated the child calls and the palindrome check as O(1), and got O(n²). → Branches = how many child calls actually **run** (up to n, from the loop); in the naive version a child costs its whole subtree; the palindrome check is O(n). Then I thought the total was `n · (n + 2ⁿ)` → the O(n²) loop work happens in **every** one of the 2ⁿ calls, so it multiplies.
7. **Memo complexity: said one `helper` state costs O(1)** (it runs the whole loop → O(n)), and then multiplied the memoized palindrome work into `helper` again to get O(n³). → With memoization, each function's work is counted once under its own states; the totals add.
8. **Tabulation filled the palindrome table with `start` going up**, so `isPal[start+1][end−1]` wasn't computed yet; `"abba"` came out false in my dry run. → It reads the row below, so `start` must go down.
9. **Space optimisation with two rows:** (a) a mismatch didn't write anything, so a stale `true` from two rows earlier survived after the swap (`"abb"` returned 0); (b) clearing without swapping left `palAhead` all false (`"aba"` returned 2); (c) `palAhead = palCurr` without completing the swap aliased the arrays, so clearing `palCurr` also cleared `palAhead`. → Complete the swap with the temp, then clear `palCurr` (or write every cell, `false` included).
10. **Didn't see what "inside is empty" meant** for lengths 1 and 2. → The inside is `start+1 … end−1`; it's empty when `end − start ≤ 1`, and an empty inside counts as a palindrome, so lengths 1, 2 and longer follow one rule.

## Related

- [Matrix Chain Multiplication](../P01_MatrixChainMultiplication/) — partition DP with split points
- [Longest Palindromic Subsequence](../../../C_Sequence/A_Medium/P03_LongestPalindromicSubsequence/) — palindrome interval table with the same `start` downwards fill order
- [0/1 Knapsack](../../../A_Knapsack/A_Medium/P01_ZeroOneKnapsack/) — same backwards loop in the single-array version
- Patterns: [How to compute complexity](../../../../../../../../patterns/how-to-compute-complexity.md), [Reused arrays](../../../../../../../../patterns/reused-arrays.md), [Space optimisation](../../../../../../../../patterns/space-optimisation.md), [State design](../../../../../../../../patterns/state-design.md)
