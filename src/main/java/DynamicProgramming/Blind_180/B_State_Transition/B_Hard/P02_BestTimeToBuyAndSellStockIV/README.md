# Best Time to Buy and Sell Stock IV

[LeetCode 188](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/) · Blind 180 DP · State Transition · Hard

## Problem

```text
You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
Find the maximum profit you can achieve. You may complete at most k transactions: i.e. you may buy at most k times and
sell at most k times. Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock
before you buy again).

Example 1:
Input: k = 2, prices = [2,4,1]
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.

Example 2:
Input: k = 2, prices = [3,2,6,5,0,3]
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and
sell on day 6 (price = 3), profit = 3-0 = 3.

Constraints:
1 <= k <= 100
1 <= prices.length <= 1000
0 <= prices[i] <= 1000
```

## Solutions

Each file has a `main` that runs the examples and prints actual vs expected.

| File | Approach |
|---|---|
| [`A_RecursiveNaive.java`](A_RecursiveNaive.java) | Recursive (naive) |
| [`B_Memoization.java`](B_Memoization.java) | Memoization |
| [`C_Tabulation.java`](C_Tabulation.java) | Tabulation |
| [`D_Tabulation_SpaceOptimisation.java`](D_Tabulation_SpaceOptimisation.java) | Space-optimised: four `[k+1]` arrays, swapped |

## Key insight

Same day-by-day recursion as the [transaction fee problem](../../A_Medium/P04_BestTimeToBuyAndSellStockWithTransactionFee/), plus **how many transactions are left**. The recursion's skip branch automatically decides which rises to skip or merge (e.g. `[0, 3, 2, 6]`, k = 1: buy at 0, hold through the dip, sell at 6).

## State, recurrence, base case

- **State:** `f(day, holding, k)` = most money from `day` onwards with `k` transactions left
- **Recurrence:** holding: `max(prices[day] + f(day+1, 0, k−1), f(day+1, 1, k))`; not holding: `max(−prices[day] + f(day+1, 1, k), f(day+1, 0, k))`
- **Decrement on the sell**, so `k == 0` only happens while not holding.
- **Base cases:** `day == n` or `k == 0` → 0
- **Answer:** `f(0, 0, k)`
- **Tabulation:** `(n+1) × 2 × (k+1)`; row n and the `k = 0` slice stay 0; `transaction` from 1 to k.
- **Space:** keep `[2][k+1]` for the next day and today; swap the references with a temp variable (assignment alone makes both names point at the same array).

## Complexity

| Approach | TC | SC |
|---|---|---|
| Naive recursion | O(2ⁿ) | O(n) stack |
| Memoization | O(n·k) | O(n·k) table + O(n) stack |
| Tabulation | O(n·k) | O(n·k) |
| Space-optimised | O(n·k) | O(k) |

Naive is O(2ⁿ), not O(2^(n·k)): every call moves to `day + 1`, so depth is n; `k` only cuts branches off.

## Mistakes I made

1. **Memoized with `dp[day][holding]` only, leaving out `k`.** Different `k` values reaching the same (day, holding) shared one cell: `[2,1,4,5,2,9,7]`, k = 2 returned 10 instead of 11. → The cache key must include **every** parameter that changes between calls.
2. **Thought the naive TC was O(2^(n·k)).** n·k is the number of states (memo), not the depth.
3. **Space optimisation: `ahead = curr` without a swap.** Both names then point at one array (aliasing). It happened to give right answers here, but the same bug breaks other problems. → Swap with a temp.
4. **Allocated a `temp` array that was immediately replaced.** A swap only needs a reference: `int[] temp;`.

## Related

- [Stock with Transaction Fee](../../A_Medium/P04_BestTimeToBuyAndSellStockWithTransactionFee/)
- Patterns: [Memoization](../../../../../../../../patterns/memoization.md), [Reused arrays](../../../../../../../../patterns/reused-arrays.md)
