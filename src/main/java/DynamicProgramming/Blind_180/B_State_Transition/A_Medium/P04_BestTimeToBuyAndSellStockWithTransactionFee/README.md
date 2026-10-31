# Best Time to Buy and Sell Stock with Transaction Fee

[LeetCode 714](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/) · Blind 180 DP · State Transition · Medium

## Problem

```text
You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee
representing a transaction fee. Find the maximum profit you can achieve. You may complete as many transactions as you
like, but you need to pay the transaction fee for each transaction.

Note:
You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
The transaction fee is only charged once for each stock purchase and sale.

Example 1:
Input: prices = [1,3,2,8,4,9], fee = 2
Output: 8
Explanation: The maximum profit can be achieved by:
- Buying at prices[0] = 1
- Selling at prices[3] = 8
- Buying at prices[4] = 4
- Selling at prices[5] = 9
The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.

Example 2:
Input: prices = [1,3,7,5,10,3], fee = 3
Output: 6

Constraints:
1 <= prices.length <= 5 * 104
1 <= prices[i] < 5 * 104
0 <= fee < 5 * 104
```

## Solutions

Each file has a `main` that runs the examples and prints actual vs expected.

| File | Approach |
|---|---|
| [`A_RecursiveNaive.java`](A_RecursiveNaive.java) | Recursive (naive) |
| [`B_Memoization.java`](B_Memoization.java) | Memoization |
| [`C_Tabulation.java`](C_Tabulation.java) | Tabulation |
| [`D_Tabulation_SpaceOptimisation.java`](D_Tabulation_SpaceOptimisation.java) | Space-optimised: variables |

## Key insight

Walk **one day at a time**. Each day's options depend on one extra fact: **am I holding a stock?** Track money as **cash flow on the day it happens** (buy: −price, sell: +price − fee) instead of profit per transaction, so the function never needs to remember the buy price — only whether it's holding.

## State, recurrence, base case

- **State:** `f(day, holding)` = most money you can still make from `day` onwards
- **Recurrence:**
  - holding: `max(sell = prices[day] − fee + f(day+1, 0), keep = f(day+1, 1))`
  - not holding: `max(buy = −prices[day] + f(day+1, 1), skip = f(day+1, 0))`
- **Rule:** an action **switches** state, skipping **keeps** it.
- **Base case:** `day == n` → 0
- **Answer:** `f(0, 0)`
- **Tabulation:** `(n+1) × 2`, row `n` zeros, `day` from n−1 down to 0.
- **Space:** two variables for the next day (`aheadNotHolding`, `aheadHolding`); compute **both** of today's values before overwriting either.

## Complexity

| Approach | TC | SC |
|---|---|---|
| Naive recursion | O(2ⁿ) | O(n) stack |
| Memoization | O(n) | O(2n) table + O(n) stack |
| Tabulation | O(n) | O(2n) |
| Space-optimised | O(n) | O(1) |

## Mistakes I made

1. **Forced a buy on every buy-day**, never allowing a wait; `[5, 1, 10]`, fee 0 couldn't find 9. → Every day needs a skip branch.
2. **Tried tracking a running minimum (greedy).** The hard part is when to sell: `[1, 5, 4, 9]`, fee 2 — selling at 5 gives 5, holding through the dip gives 6.
3. **Looped over all sell days inside each call** → O(n²) even with memo. → Replace the loop with "sell today or don't".
4. **Passed `buy_day` as a parameter** → ~n² states. → A holding flag (2 values) is enough with cash-flow accounting.
5. **Base case on the last day** recursed to `day = n` and read out of bounds. → Base case "past the last day".
6. **Let the holding state also buy / throw away the stock** (4 branches instead of 2). → If holding: sell or keep; else: buy or skip.
7. **Two sign errors:** subtracted the future (`price − helper(...)`) and added the price when buying.
8. **Space optimisation, first try:** shifted values inside the inner loop, mixing the two columns of one row; `[1]` returned 1. Second try: both actions read the same state instead of switching, and I returned the holding value. → Name variables by meaning (`aheadHolding`), and return the not-holding value (`dp[0][0]`).

## Related

- [Stock IV](../../B_Hard/P02_BestTimeToBuyAndSellStockIV/) — adds a transaction count
- Patterns: [State design](../../../../../../../../patterns/state-design.md), [Space optimisation](../../../../../../../../patterns/space-optimisation.md)
