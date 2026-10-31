# Space optimisation of DP tables

Tabulation keeps the whole table, but most cells are read once and never again. If a row only reads a few earlier rows, keep just those.

## Step 1: list what each cell reads

Write the recurrence and mark each read as **previous row** or **current row**, and **left**, **same column** or **right**.

| Problem | Reads | Result |
|---|---|---|
| 0/1 Knapsack, Partition, Last Stone II, Target Sum (subset count) | prev row: `j`, `j − w` (left) | one array, loop **high → low** |
| Coin Change II (unbounded) | prev row `j`, **current** row `j − c` (left) | one array, loop **low → high** |
| Longest Common Substring ("starts at") | prev row `j + 1` (right) | one array, loop **low → high** |
| LCS, Edit Distance, LPS, Wildcard | prev row `j` and `j − 1`, current row `j − 1` | one array + saved **diagonal** variable |
| LIS approach 1 | prev row `curr + 1` (never written in this row), `last + 1` (read then written) | one array, any direction |
| Target Sum ± route | prev row `j − x` **and** `j + x` | two arrays only |
| Matrix Chain | whole column below | no optimisation |

## Step 2: two arrays always work

`prev` = last row, `curr` = this row; read only from `prev` (or `curr` for current-row reads), write only to `curr`, then swap:

```java
int[] temp = prev; prev = curr; curr = temp;   // swap references, never just prev = curr
```

After the final swap the last row is in `prev`.

## Step 3: one array — pick the direction

Rule: **process each cell before the cells it depends on get overwritten**.

- Reads point **left** in the **old** row → loop **right to left** (0/1 knapsack). Looping left to right would read values already updated for the same item → that's unbounded knapsack.
- Reads point left in the **new** row → loop **left to right** (unbounded).
- Reads point **right** in the old row → loop **left to right** (common substring).

## Step 4: when two needed values share one cell — the diagonal variable

In LCS-shaped tables, `dp[j − 1]` is needed as **left** (new row) and as **diagonal** (old row). One cell can't hold both. Loop forward and save the old value before overwriting it:

```java
int diag = dp[0];          // old dp[0] = previous row's column 0 (diagonal for j = 1)
dp[0] = <this row's column-0 value>;
for (int j = 1; j <= m; j++) {
    int temp = dp[j];      // still the previous row's value ("up")
    dp[j] = <match ? f(diag) : g(dp[j] /* up */, dp[j - 1] /* left */, diag)>;
    diag = temp;           // becomes the diagonal for j + 1
}
```

Column 0 differs per problem: 0 in LCS (set once), `i` in Edit Distance, `false` for rows ≥ 1 in Wildcard Matching — reset it at the start of every row.

## Two variables

When a cell reads only the previous one or two values (Frog Jump, House Robber, Stock with fee), use variables. Update the **oldest first**:

```java
prev2 = prev;   // first
prev = curr;    // then
```

Doing it the other way round copies `curr` into both (the Climbing Stairs bug).
