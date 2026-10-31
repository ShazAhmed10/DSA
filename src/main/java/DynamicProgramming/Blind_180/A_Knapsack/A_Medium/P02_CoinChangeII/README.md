# Coin Change II

[LeetCode 518](https://leetcode.com/problems/coin-change-ii/) · Blind 180 DP · Knapsack · Medium

## Problem

```text
You are given an integer array coins representing coins of different denominations and an integer amount representing a
total amount of money. Return the number of combinations that make up that amount. If that amount of money cannot be
made up by any combination of the coins, return 0. You may assume that you have an infinite number of each kind of coin.
The final answer is guaranteed to fit into a signed 32-bit integer.

Example 1:
Input: amount = 5, coins = [1,2,5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1

Example 2:
Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.

Example 3:
Input: amount = 10, coins = [10]
Output: 1

Constraints:
1 <= coins.length <= 300
1 <= coins[i] <= 5000
All the values of coins are unique.
0 <= amount <= 5000
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

A classic **unbounded knapsack**: after picking a coin you **stay on the same coin** (pass `n`, not `n−1`) so it can be picked again. Counts from the two choices are **added**, not maxed.

## State, recurrence, base case

- **State:** `f(n, t)` = number of combinations of the first `n` coins that make amount `t`
- **Recurrence:** `f(n, t) = f(n−1, t) + f(n, t − coins[n−1])` (second term only if `coins[n−1] ≤ t`)
- **Base cases:** `t == 0` → 1 (safe to return early because every coin ≥ 1, so nothing left can still change the count); `n == 0` → 0
- **Answer:** `f(n, amount)`

## Tabulation and space optimisation

- `dp[i][j]` reads `dp[i][j − coin]` — the **same row**, to the left → `j` must loop **forwards** (opposite of 0/1 knapsack).
- Two arrays (`Da`): pick reads `curr[j − coin]`, not-pick reads `prev[j]`.
- Single array (`Db`/`Dc`): forward loop gives both for free — `dp[j]` is still the previous coin's value, `dp[j − coin]` is already this coin's value. Compact: `for (j = coin; j <= amount; j++) dp[j] += dp[j − coin];`

## Combinations vs permutations

- **Coins outside, amount inside** (this problem) → each combination counted once in a fixed coin order → combinations. `[1, 2]`, amount 3 → 2.
- **Amount outside, coins inside** → every coin can be "last" at every amount → permutations (Combination Sum IV). `[1, 2]`, amount 3 → 3.
- The permutation version comes from a different recursion: one parameter `f(t) = Σ f(t − c)` over all coins, not pick / not pick.

## Complexity

| Approach | TC | SC |
|---|---|---|
| Naive recursion | O(2^(n+amount)) | O(n + amount) stack |
| Memoization | O(n·amount) | O(n·amount) table + O(n + amount) stack |
| Tabulation | O(n·amount) | O(n·amount) |
| Two arrays / single array | O(n·amount) | O(amount) |

Naive depth is **n + amount**, not n: a pick doesn't decrease `n`, it decreases the amount (by at least 1). Every step decreases `n` or the amount, so a path is at most n + amount long.

## Mistakes I made

1. **Assumed the naive TC was O(2ⁿ).** The pick branch keeps `n`, so depth is n + amount → O(2^(n+amount)). (`coins = [1]`, amount 5 goes 5 calls deep with n = 1.)
2. **Looped capacity backwards in the 2D tabulation** (copying the 0/1 single-array habit). The pick reads `dp[i][j − coin]` from the same row, which hadn't been filled yet → every pick read 0. → Fill every cell after the cells it reads.

## Related

- [0/1 Knapsack](../P01_ZeroOneKnapsack/) — same line of code, opposite loop direction
- Pattern: [Space optimisation](../../../../../../../../patterns/space-optimisation.md)
