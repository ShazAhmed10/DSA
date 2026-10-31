# Ninja's Training

Blind 180 DP · State Transition · Medium (Coding Ninjas)

## Problem

```text
A ninja has planned a n-day training schedule. Each day he has to perform one of three activities - running,
stealth training, or fighting practice. The same activity cannot be done on two consecutive days and the ninja earns
a specific number of merit points, based on the activity and the given day. Given a n x 3-sized matrix, where
matrix[i][0], matrix[i][1], and matrix[i][2], represent the merit points associated with running, stealth and
fighting practice, on the (i+1)th day respectively. Return the maximum possible merit points that the ninja can earn.

Example 1:
Input: matrix = [[10, 40, 70], [20, 50, 80], [30, 60, 90]]
Output: 210
Explanation:
Day 1: fighting practice = 70
Day 2: stealth training = 50
Day 3: fighting practice = 90
Total = 70 + 50 + 90 = 210
This gives the optimal points.

Example 2:
Input: matrix = [[70, 40, 10], [180, 20, 5], [200, 60, 30]]
Output: 290
Explanation:
Day 1: running = 70
Day 2: stealth training = 20
Day 3: running = 200
Total = 70 + 20 + 200 = 290
This gives the optimal points.

Constraints:
1 <= n <= 104
n == number of rows in matrix
3 == number of columns in matrix
0 <= matrix[i][j] <= 1000
```

## Solutions

Each file has a `main` that runs the examples and prints actual vs expected.

| File | Approach |
|---|---|
| [`A_RecursiveNaive.java`](A_RecursiveNaive.java) | Recursive (naive) |
| [`B_Memoization.java`](B_Memoization.java) | Memoization |
| [`C_Tabulation.java`](C_Tabulation.java) | Tabulation |

## Key insight

Greedy (best activity each day) fails because of the no-repeat rule. The decision for a day depends only on **which activity is not allowed** today — that is the extra state.

## State, recurrence, base case

- **State:** `f(day, banned)` = max points for days `0..day` when activity `banned` is not allowed on `day` (because the following day used it). `banned = 3` means **nothing banned** — only true for the **last** day, since it has no following day.
- **Recurrence:** `f(day, banned) = max over c ≠ banned of matrix[day][c] + f(day − 1, c)`
- **Base case:** `day == 0` → best of row 0 excluding `banned`
- **Answer:** `f(n − 1, 3)` — one entry point, also handles n = 1
- **Table:** `n × 4`. Column 3 really holds one useful cell (`dp[n−1][3]`); the rest exist because arrays are rectangular.
- **Tabulation:** fill row 0 for all four `banned` values (every parameter becomes a loop variable), then rows 1..n−1. Each row reads only the previous row → O(1) space possible with two arrays of size 4.

## Complexity

| Approach | TC | SC |
|---|---|---|
| Naive recursion | O(2ⁿ) | O(n) stack |
| Memoization | O(n × 4 × 3) = O(n) | O(n) table + O(n) stack |
| Tabulation | O(n) | O(n) |
| Space-optimised | O(n) | O(1) |

Naive: 2 allowed activities per call over n levels (×3 at the top, a constant).

## Mistakes I made

1. **Ran the whole recursion three times at the top level**, once with each activity banned on the last day — nothing is banned on the last day.
2. **Moved the last day's choice into the main function and crashed for n = 1** (`helper(−1)`). → Use the sentinel 3 and a single `helper(n − 1, 3)`.
3. **Mixed two meanings in the memo table.** The check assumed `dp[row][col]` = "col banned", but I stored "col chosen". `[[0,0,0],[0,100,0],[0,1,0]]` returned 101 (stealth twice in a row). → Define in one sentence what a cell means; every write must match it.
4. **Read a shared table cell outside the `if`.** In the naive version the same pattern read a local 0; in memo it read whatever another call had left in `dp[row][col]`. → Locals belong to one call; the table is shared by all calls.
5. **Thought "nothing banned" applied to the first day.** It's the day without a *following* day — the last one.
6. **Sentinel:** points can be 0, so check against −1, not 0.

## Related

- Pattern: [State design](../../../../../../../../patterns/state-design.md), [Memoization](../../../../../../../../patterns/memoization.md)
