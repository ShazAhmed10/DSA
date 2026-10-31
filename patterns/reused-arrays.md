# Reused arrays: never rely on default values

In a full 2D table, a cell you don't write is still 0 / false. Once arrays are **swapped or reused** for space optimisation, an unwritten cell holds whatever an earlier row left there.

## Rules

1. **Write every cell you will read, in every row.** Mismatch → write `0` / `false` explicitly (Longest Common Substring, Wildcard Matching).
2. **Reset index 0 (column 0) every row** if its value differs between rows (Edit Distance `= i`, Wildcard `= false` for rows ≥ 1). If it's the same in every row (LCS 0, Last Stone `true`), set it in **both** arrays once.
3. **Swap with a temp variable.** `ahead = curr;` makes both names refer to one array (aliasing). It happened to work in Stock IV and silently breaks other problems.
4. `Arrays.fill(curr, false)` after each swap is a quick way to satisfy rule 1 and 2 at O(m) per row.

## Bugs this caused

| Problem | Symptom |
|---|---|
| Wildcard (index 0 not reset) | `s = "aaa"`, `p = "a"` → true |
| Wildcard (mismatch not written) | `s = "aab"`, `p = "*a"` → true |
| Last Stone II (index 0 only in one array) | first stone unusable after a swap |
