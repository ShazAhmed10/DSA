# Memoization checklist

## 1. The cache key = every parameter that changes

List the parameters. Each one that differs between calls is a table dimension; constant ones (`nums`, `prices`) are not.

- Stock IV: leaving out `k` let different `k` values share one cell → wrong answer.
- Partition: a running sum in a **global variable** leaked between the pick and not-pick branches → make it a parameter.

## 2. Table size = the range of each parameter

- Count-based index `n` (0 … n) → size `n + 1`.
- Sentinel parameter values (`banned = 3`, `last = −1`) need room: Ninja `n × 4`, LIS `last + 1` shift.
- Negative values (Target Sum running sum) → shift by a constant: `index = value + total`. Never modulo — it collides.

## 3. Sentinel = a value that can never be a real answer

| Real answers | Sentinel |
|---|---|
| ≥ 0 (counts, costs, lengths, points) | −1 |
| ≥ 1 (LIS lengths) | 0 also works |
| boolean | `Boolean[][]` with `null`, or `int` −1/0/1 |
| object pairs | `null` |

Use the **same** value in the fill and in the check (Frog Jump K: filled `MAX_VALUE`, checked `!= 0` → nothing computed).

## 4. Where the memo check goes

- Safe at the very top when every call's indices are valid table positions.
- Base cases that catch out-of-table indices (−1, `n` with an `n`-sized table, negative sums) must come **first**.
- If a base case **recurses** (Wildcard: `n == 0` walking down `*`s), put the memo check before it so its work is reused.

## 5. Read and write

- Every return path stores into the cell **and** something reads it back (Longest Common Substring first stored without ever reading).
- Never mutate an object returned by a memoized call (Number of LIS `Pair`) — you'd change the cached value.
- Locals belong to one call; the table is shared by all calls. Only write what the cell's definition says (Ninja mixed "banned" and "chosen").

## 6. Share one table across top-level calls

If the main function loops over starting points (LIS ending at `i`, Last Stone scan, Longest Common Substring), pass the **same** table to every call. Then each state is computed once in total.

## 7. Tabulation conversion

| Memo | Tab |
|---|---|
| base case | fill those cells first (or rely on Java defaults when they're 0/false) |
| each parameter | a loop variable — fill **every** value, not just the ones the base case was called with |
| `helper(x−1, …)` | `dp[x−1][…]` |
| loop direction | fill every cell after the cells it reads |
| first call `helper(…)` | the answer cell |
