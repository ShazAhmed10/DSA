# Designing the DP state

Before writing code, answer in words:

1. **What does `f(params)` return?** One sentence. ("Most money from `day` onwards, given whether I hold a stock.")
2. **Parameters:** everything that changes between calls, nothing else.
3. **Choices at each step**, and how they combine: alternatives → `min` / `max` / `||`; things that **all happen** → `+`; counting → `+`.
4. **Base case:** what happens when nothing is left.

If something changes but isn't a parameter, it's a bug waiting to happen.

## Recurring state shapes

| Shape | Meaning | Answer | Examples |
|---|---|---|---|
| Prefix `f(i, …)` | first `i` items / chars | `f(n, …)` | Knapsack, LCS, Edit Distance |
| Suffix / position `f(day, …)` | from here onwards | `f(0, …)` | Stocks |
| Extra flag | holding / banned / transactions left | — | Stocks, Ninja, Stock IV |
| **Ends exactly at `i`** | best answer whose last element is `i` | **max over all `i`** | LIS (approach 2), Longest Common Substring, Number of LIS |
| Interval `f(l, r)` | the range `l..r` | `f(0, n−1)` | LPS, Matrix Chain |

Contiguous answers (substring, subarray) → "ends/starts exactly here" + max over all positions.

## Forward vs backward meaning

The same indices can mean "ways to **reach** here" (forward: base `dp[0][0] = 1`, answer at the end) or "ways to **finish** from here" (backward: base at the end, answer at the start). Pick one sentence; base case, recurrence and answer cell must all follow it (Target Sum).

## Reductions worth recognising

| Looks like | Is |
|---|---|
| Split into two groups, minimise difference | Largest subset sum ≤ total / 2 (0/1 knapsack, weight = value) |
| Smash stones `y − x` | Same as above |
| Assign + / − to every number | Count subsets with sum `(total + target) / 2` |
| Longest palindromic subsequence | LCS(s, reverse(s)) |
| Order of combining (parentheses) | Split on the **last** operation → interval DP |

## Pick / not pick vs "choose what comes next"

- **Pick / not pick:** 2 branches per call, parameters map straight to a table.
- **Choose next / previous:** loop over candidates inside the call (LIS ending at `i`, permutation counting). Fewer parameters (1D table) but a loop per state.
- Every problem in this repo also has a skip branch somewhere — without it, the recursion can't wait (Stock with fee).
