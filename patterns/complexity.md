# Working out time and space complexity

## Naive recursion: (branches) ^ (depth)

**Depth** = the longest path from the first call to a base case. Look at what **each** branch decreases and by how much.

| Problem | Each step decreases | Depth | TC |
|---|---|---|---|
| 0/1 Knapsack, Stock IV | `n` by 1 (k only cuts branches) | n | O(2ⁿ) |
| Coin Change II | `n` (skip) **or** amount (pick, ≥ 1) | n + amount | O(2^(n+amount)) |
| Unique Paths, LCS, Wildcard | `row + col` / `n + m` by ≥ 1 | m + n | O(2^(m+n)) |
| Edit Distance | `n + m` by ≥ 1, 3 branches | n + m | O(3^(n+m)) |

## When each call loops over smaller calls

Write `T(m)`, then subtract consecutive formulas:

```
T(i) = 1 + T(0) + … + T(i−1)            → T(i) = 2·T(i−1)  → O(2ⁿ)   (LIS, ends at i)
T(m) = 1 + 2·[T(1) + … + T(m−1)]        → T(m) = 3·T(m−1)  → O(3ⁿ)   (Matrix Chain)
T(m) = 1 + c·[sum of smaller T]         → T(m) = (c+1)·T(m−1)
```

## Add or multiply?

- **Sequential** (one call finishes, then the next starts) → **add**. A loop calling sizes 2⁰, 2¹, …, 2ⁿ⁻¹ costs 2ⁿ − 1 = O(2ⁿ): a geometric series is dominated by its largest term.
- **Nested** (inside every step of something else) → **multiply**. Equal-sized calls in a loop of n → n × cost.

## Memoization / tabulation

```
TC = (number of distinct states) × (work per state, treating recursive calls as O(1) lookups)
```

| Problem | States | Work / state | TC |
|---|---|---|---|
| Knapsack, LCS | n·W, n·m | O(1) | O(n·W), O(n·m) |
| LIS (ends at i) | n | O(n) loop | O(n²) |
| Matrix Chain | n²/2 | O(n) loop | O(n³) |

Only pairs with `l < r` are used in interval tables: n(n−1)/2 = O(n²).

## Space

- Memo: table + recursion depth (often the same depth as the naive version).
- Tab: table only. Space optimisation: the rows you keep.
- O(n) + O(n) is O(n), but writing the stack term separately shows why tabulation avoids `StackOverflowError`.
