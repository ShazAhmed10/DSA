# Target Sum

[LeetCode 494](https://leetcode.com/problems/target-sum/) · Blind 180 DP · Knapsack · Hard

## Problem

```text
You are given an integer array nums and an integer target.
You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and
then concatenate all the integers.
For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the
expression "+2-1". Return the number of different expressions that you can build, which evaluates to target.

Example 1:
Input: nums = [1,1,1,1,1], target = 3
Output: 5
Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3

Example 2:
Input: nums = [1], target = 1
Output: 1

Constraints:

1 <= nums.length <= 20
0 <= nums[i] <= 1000
0 <= sum(nums[i]) <= 1000
-1000 <= target <= 1000
```

## Solutions

Each file has a `main` that runs the examples and prints actual vs expected.

| File | Approach |
|---|---|
| [`Approach1_A_RecursiveNaive.java`](Approach1_A_RecursiveNaive.java) | Approach 1 · Recursive (naive) |
| [`Approach1_B_Memoization.java`](Approach1_B_Memoization.java) | Approach 1 · Memoization |
| [`Approach1_C_Tabulation.java`](Approach1_C_Tabulation.java) | Approach 1 · Tabulation |
| [`Approach1_D_TabulationSpaceOptimization.java`](Approach1_D_TabulationSpaceOptimization.java) | Approach 1 · Space-optimised: two arrays |
| [`Approach2_A_RecursiveNaive.java`](Approach2_A_RecursiveNaive.java) | Approach 2 · Recursive (naive) |
| [`Approach2_B_Memoization.java`](Approach2_B_Memoization.java) | Approach 2 · Memoization |
| [`Approach2_C_Tabulation.java`](Approach2_C_Tabulation.java) | Approach 2 · Tabulation |
| [`Approach2_Da_TabulationSpaceOptimisation.java`](Approach2_Da_TabulationSpaceOptimisation.java) | Approach 2 · Space-optimised: two arrays |
| [`Approach2_Db_TabulationSpaceOptimisation.java`](Approach2_Db_TabulationSpaceOptimisation.java) | Approach 2 · Space-optimised: single array |
| [`Approach2_Dc_TabulationSpaceOptimisation.java`](Approach2_Dc_TabulationSpaceOptimisation.java) | Approach 2 · Space-optimised: single array (compact loop) |

Every number gets a `+` or `−` sign — **no number is skipped**. Count the sign assignments whose total equals the target.

## Approach 1: direct ± recursion (files `Approach1_*`)

- **State:** `f(n, curr)` = number of ways to finish at `target`, with `n` numbers still to sign and running sum `curr` (a **backward** / "from here" meaning)
- **Recurrence:** `f(n, curr) = f(n−1, curr − nums[n−1]) + f(n−1, curr + nums[n−1])`
- **Base case:** `n == 0` → `curr == target ? 1 : 0` — decide only when every number is signed
- **Answer:** `f(n, 0)`
- **Negative indices:** `curr` ranges over `[−total, +total]` → table width `2·total + 1`, index `curr + total` (one rule for every value; `curr = 0` lands in the middle). Apply the shift on **every** read and write.
- **Tabulation:** row 0 has a single 1 at `target + total`; reads outside `[0, 2·total]` count as 0 ways; answer `dp[n][0 + total]`. Return 0 early if `|target| > total` (required here, since `target` becomes an index).
- **Space:** two arrays work; a single array doesn't, because each cell reads both `curr − num` and `curr + num`.

## Approach 2: reduction to counting subsets (files `Approach2_*`) — the standard solution

Split into the `+` group (sum P) and the `−` group (sum N, without the sign):

```
P − N = target   ← condition for a valid assignment
P + N = total    ← always true
→ P = (total + target) / 2
```

Choosing the `+` group fixes the whole assignment, so the answer = **number of subsets with sum exactly P**.

- **No valid P** → 0: if `total + target` is odd (P not whole) or negative; also return 0 early if `|target| > total`. Use integer parity (`% 2`), not floats.
- **State:** `f(i, s)` = number of subsets of the first `i` numbers with sum exactly `s`
- **Recurrence:** `f(i, s) = f(i−1, s) + f(i−1, s − nums[i−1])` (pick only if it fits)
- **Base case:** `i == 0` → `s == 0 ? 1 : 0`. Don't stop when `s` hits 0: zeros can still be picked or skipped, both counted.
- **Tabulation:** set only `dp[0][0] = 1`; the `s` loop **starts at 0**, because column 0 must be computed (each zero doubles it). `[0, 0]`, target 0 → 4.
- **Single array:** loop `s` from P down to `nums[i−1]`: `dp[s] += dp[s − nums[i−1]]`.

## Complexity

| Approach | TC | SC |
|---|---|---|
| Naive (either) | O(2ⁿ) | O(n) stack |
| Approach 1 memo / tab | O(n·total) | O(n·total) (+ O(n) stack for memo) |
| Approach 1 two arrays | O(n·total) | O(total) |
| Approach 2 memo / tab | O(n·P) | O(n·P) (+ O(n) stack for memo) |
| Approach 2 single array | O(n·P) | O(P) |

P ≤ total, so Approach 2 is O(n·total) time and O(total) space with a simpler table (no offset, no bounds checks).

## Mistakes I made

1. **Solved the wrong problem first.** My pick / not-pick code counted subsets summing to the target; `[1,1,1,1,1]`, target 3 gave 10 instead of 5.
2. **Dry-ran against an expected value from the same wrong reading.** I listed three "ways" for `[1,1,1]`, target 2 that actually sum to 1; the real answer is 0 (three ±1 terms always give an odd total). → Work out expected answers from the statement, independently of the code.
3. **Returned as soon as the sum hit the target**, ignoring remaining numbers (wrong for zeros: `[0, 1]`, target 1).
4. **Considered modulo to handle negative indices**, then a mapping with different rules for negatives and positives. Both collide (e.g. −2 and +1 → same index). → Shift by `+total`.
5. **Tabulation: shifted the writes but not the reads**, read past the row's ends, and looked for the answer at `dp[n][target]` (the forward table's cell) while using the backward table's base case. → Write down one sentence for what a cell means; base case, recurrence and answer must all follow from it.
6. **Approach 2 tabulation used the constant `p` inside the loop instead of the loop variable `j`** — every cell got column P's value.
7. **Used a float comparison to check P is whole.** → `(total + target) % 2 != 0`.

## Related

- [Last Stone Weight II](../P02_LastStoneWeightII/) — boolean version of the same table
- Patterns: [State design](../../../../../../../../patterns/state-design.md), [Space optimisation](../../../../../../../../patterns/space-optimisation.md)
