# DSA

My data structures and algorithms practice in Java: every problem solved step by step (naive recursion → memoization → tabulation → space optimisation), with revision notes and the mistakes I made along the way.

## How the repo is organised

```
src/main/java/<Topic>/<Sheet>/<Category>/<Difficulty>/P<NN>_<ProblemName>/
    README.md                         ← problem, key insight, state, complexity, mistakes
    A_RecursiveNaive.java             ← naive recursion
    B_Memoization.java
    C_Tabulation.java
    Da_TabulationSpaceOptimisation.java   ← Da / Db / Dc for several space-optimised variants
patterns/                             ← techniques that apply across problems
```

- Two approaches to one problem: `Approach1_…`, `Approach2_…`.
- Every solution file has a `main` that runs the problem's examples and prints actual vs expected.
- Category and difficulty folders are prefixed (`A_`, `B_`, …) so they sort in study order.

## How to revise

1. Skim the **Key idea** column below and try to recall the recurrence.
2. Open the problem's README and read **Mistakes I made** — those are the parts most likely to be forgotten.
3. Re-solve from a blank file, then compare.
4. Read the [patterns](patterns/) notes before a session on a new category.

## Patterns

| Note | Covers |
|---|---|
| [State design](patterns/state-design.md) | choosing parameters, state shapes, reductions, forward vs backward meaning |
| [Memoization checklist](patterns/memoization.md) | cache key, table size, sentinels, memo-check placement, memo → tab conversion |
| [Space optimisation](patterns/space-optimisation.md) | two arrays, loop direction for one array, the diagonal variable |
| [Reused arrays](patterns/reused-arrays.md) | why every cell must be written each row; swapping vs assigning |
| [Complexity](patterns/complexity.md) | branches^depth, recurrences like T(m) = 3·T(m−1), states × work per state |

## Problems

### Dynamic Programming · Blind 180 · Knapsack

| # | Problem | Level | Key idea | TC | SC | Revisit |
|---|---|---|---|---|---|---|
| 1 | [0/1 Knapsack](src/main/java/DynamicProgramming/Blind_180/A_Knapsack/A_Medium/P01_ZeroOneKnapsack/) | Medium | Pick / not pick each item with remaining capacity as a parameter; single array loops capacity high → low | O(n·W) | O(W) |  |
| 2 | [Coin Change II](src/main/java/DynamicProgramming/Blind_180/A_Knapsack/A_Medium/P02_CoinChangeII/) | Medium | Pick keeps the same coin (n stays), not-pick moves on; counts add. Coins outer loop = combinations | O(n·amount) | O(amount) | Combination Sum IV (permutations version) |
| 1 | [Partition a Set into Two Subsets with Minimum Absolute Sum Difference](src/main/java/DynamicProgramming/Blind_180/A_Knapsack/B_Hard/P01_PartitionSetIntoTwoSubsetsMinDifference/) | Hard | Only choose subset 1: largest sum ≤ total/2 via knapsack with weight = value = arr[i]; answer total − 2·s1 | O(n·sum) | O(sum) |  |
| 2 | [Last Stone Weight II](src/main/java/DynamicProgramming/Blind_180/A_Knapsack/B_Hard/P02_LastStoneWeightII/) | Hard | Same as min-difference partition; boolean dp[i][s] = can make exactly s, scan last row from total/2 down | O(n·sum) | O(sum) |  |
| 3 | [Target Sum](src/main/java/DynamicProgramming/Blind_180/A_Knapsack/B_Hard/P03_TargetSum/) | Hard | P − N = target, P + N = total → count subsets with sum P; ± route needs an offset for negative sums | O(n·total) | O(total) |  |

### Dynamic Programming · Blind 180 · State Transition

| # | Problem | Level | Key idea | TC | SC | Revisit |
|---|---|---|---|---|---|---|
| 1 | [Frog Jump with K Distances](src/main/java/DynamicProgramming/Blind_180/B_State_Transition/A_Medium/P01_FrogJumpWithKDistances/) | Medium | dp[i] = min over j = 1..k of dp[i−j] + \|h[i] − h[i−j]\| | O(n·k) | O(n) |  |
| 2 | [Maximum Sum of Non-Adjacent Elements](src/main/java/DynamicProgramming/Blind_180/B_State_Transition/A_Medium/P02_MaximumSumOfNonAdjacentElements/) | Medium | pick nums[i] + f(i−2) vs skip f(i−1) | O(n) | O(1) |  |
| 3 | [Ninja's Training](src/main/java/DynamicProgramming/Blind_180/B_State_Transition/A_Medium/P03_NinjaTraining/) | Medium | dp[day][banned] = best up to day with that activity banned; column 3 = nothing banned (last day) | O(n) | O(n) | Space-optimised O(1) version (not written yet) |
| 4 | [Best Time to Buy and Sell Stock with Transaction Fee](src/main/java/DynamicProgramming/Blind_180/B_State_Transition/A_Medium/P04_BestTimeToBuyAndSellStockWithTransactionFee/) | Medium | f(day, holding) with cash-flow accounting: buy −price, sell +price − fee, skip keeps state | O(n) | O(1) |  |
| 1 | [Unique Paths II](src/main/java/DynamicProgramming/Blind_180/B_State_Transition/B_Hard/P01_UniquePathsII/) | Hard | paths(r, c) = paths from top + paths from left; obstacle cell = 0 | O(m·n) | O(m·n) | Space-optimised version (not written yet) |
| 2 | [Best Time to Buy and Sell Stock IV](src/main/java/DynamicProgramming/Blind_180/B_State_Transition/B_Hard/P02_BestTimeToBuyAndSellStockIV/) | Hard | Transaction-fee recursion plus k; decrement k on sell; k must be a dp dimension | O(n·k) | O(k) | Stock III (k = 2), Stock with Cooldown |

### Dynamic Programming · Blind 180 · Sequence

| # | Problem | Level | Key idea | TC | SC | Revisit |
|---|---|---|---|---|---|---|
| 1 | [Longest Common Subsequence](src/main/java/DynamicProgramming/Blind_180/C_Sequence/A_Medium/P01_LongestCommonSubsequence/) | Medium | match → 1 + diag; else max(up, left). Single array needs a saved diagonal | O(n·m) | O(m) |  |
| 2 | [Longest Common Substring](src/main/java/DynamicProgramming/Blind_180/C_Sequence/A_Medium/P02_LongestCommonSubstring/) | Medium | g(i, j) = run length starting at (i, j): match → 1 + g(i+1, j+1), else 0; answer = max over all cells | O(n·m) | O(m) |  |
| 3 | [Longest Palindromic Subsequence](src/main/java/DynamicProgramming/Blind_180/C_Sequence/A_Medium/P03_LongestPalindromicSubsequence/) | Medium | ends match → 2 + inside; else max(drop left, drop right). Or LCS(s, reverse(s)) | O(n²) | O(n) |  |
| 4 | [Number of Longest Increasing Subsequence](src/main/java/DynamicProgramming/Blind_180/C_Sequence/A_Medium/P04_NumberOfLongestIncreasingSubsequence/) | Medium | Each i stores (best length ending here, #ways); strictly longer → take count, equal → add count | O(n²) | O(n) |  |
| 5 | [Edit Distance](src/main/java/DynamicProgramming/Blind_180/C_Sequence/A_Medium/P05_EditDistance/) | Medium | match → diag; else 1 + min(insert = left, delete = up, replace = diag); base row/column = i / j | O(n·m) | O(m) |  |
| 1 | [Longest Increasing Subsequence](src/main/java/DynamicProgramming/Blind_180/C_Sequence/B_Hard/P01_LongestIncreasingSubsequence/) | Hard | (a) f(curr, last) with last shifted by +1; (b) dp[i] = 1 + max dp[j] for j < i, nums[j] < nums[i] | O(n²) | O(n) | **O(n log n) with binary search (`tails` array)** |
| 2 | [Wildcard Matching](src/main/java/DynamicProgramming/Blind_180/C_Sequence/B_Hard/P02_WildcardMatching/) | Hard | char or ? → diag; * → left (matches empty) \|\| up (matches one more); row 0 true only for all-* prefixes | O(n·m) | O(m) |  |

### Dynamic Programming · Blind 180 · Interval

| # | Problem | Level | Key idea | TC | SC | Revisit |
|---|---|---|---|---|---|---|
| 1 | [Matrix Chain Multiplication](src/main/java/DynamicProgramming/Blind_180/D_Interval/B_Hard/P01_MatrixChainMultiplication/) | Hard | Split on the last multiplication k: f(l,k) + f(k,r) + nums[l]·nums[k]·nums[r], min over k | O(n³) | O(n²) | Burst Balloons, Min Cost to Cut a Stick (same pattern) |

### Dynamic Programming · Striver A2Z · OneDimension DP

| # | Problem | Level | Key idea | TC | SC | Revisit |
|---|---|---|---|---|---|---|
| 1 | [Frog Jump](src/main/java/DynamicProgramming/A2Z/OneDimension_DP/A_Medium/P01_FrogJump/) | Medium | dp[i] = min over the last jump (1 or 2 steps back) of dp[prev] + \|h[i] − h[prev]\| | O(n) | O(1) |  |

### Arrays · Prefix sum + HashMap

| # | Problem | Level | Key idea | TC | SC | Revisit |
|---|---|---|---|---|---|---|
| 1 | [Count Subarrays with Sum Divisible by K](src/main/java/Arrays/PrefixAndHashMap/P01_CountSubarrayDivisibleByK/) |  | Two prefix sums with the same remainder mod k bound a divisible subarray; count remainders in a map | O(n) | O(k) |  |

### Graphs

| # | Problem | Level | Key idea | TC | SC | Revisit |
|---|---|---|---|---|---|---|
| 1 | [Minimum Knight Moves to Reach a Target](src/main/java/Graphs/P01_MinimumKnights/) |  | Each square is a node, each knight move an edge; BFS gives the shortest number of moves | O(n²) | O(n²) |  |

## Adding a new problem

Use the `dsa-repo-entry` skill (in [`.claude/skills/`](.claude/skills/dsa-repo-entry/SKILL.md)): it creates the folder, the solution files with example `main`s, the README in the same format, and the row in this table.
