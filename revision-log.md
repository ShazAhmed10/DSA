# Revision log

The single record of what to re-solve and when. The `dsa-repo-entry`, `dsa-revise` and `dsa-mock` skills keep it up to date, and every session that changes it syncs it to the **Personal Routine Planner**, which shows the problems that are due in the daily revision slot. See [planner-sync.md](planner-sync.md) for the sync steps.

## Rules for the next due date

| Event | Next due |
|---|---|
| New problem added to the repo | solved date + 3 days, reason `Day-3 re-solve` |
| Revision scored **Forgot** | + 2 days |
| Revision scored **Shaky**, or planner re-solve marked **Struggled** | + 7 days |
| Revision scored **Solid**, or planner re-solve marked **Clean**, and it was the problem's first revision | + 10 days, reason `Day-10 re-solve` |
| Revision scored **Solid** or **Clean**, any later revision | + 21 days |
| Mock problem (average score ≤ 2 / 3 / 4) | treat as Forgot / Shaky / Solid |

Dates are `YYYY-MM-DD`. `—` means never. The ID is the problem name in lowercase with every run of other characters replaced by `-`; it is also the planner's document id.

## Queue

One row per problem, current state only.

| ID | Problem | Topic | Folder | First solved | Last revised | Result | Next due | Reason |
|---|---|---|---|---|---|---|---|---|
| count-subarrays-with-sum-divisible-by-k | [Count Subarrays with Sum Divisible by K](src/main/java/Arrays/PrefixAndHashMap/P01_CountSubarrayDivisibleByK/) | Arrays · Prefix sum + HashMap | `src/main/java/Arrays/PrefixAndHashMap/P01_CountSubarrayDivisibleByK` | 2025-10-05 | — | — | 2026-11-09 | First revision |
| minimum-knight-moves-to-reach-a-target | [Minimum Knight Moves to Reach a Target](src/main/java/Graphs/P01_MinimumKnights/) | Graphs · BFS | `src/main/java/Graphs/P01_MinimumKnights` | 2025-10-05 | — | — | 2026-11-09 | First revision |
| frog-jump | [Frog Jump](src/main/java/DynamicProgramming/A2Z/OneDimension_DP/A_Medium/P01_FrogJump/) | DP · A2Z 1D | `src/main/java/DynamicProgramming/A2Z/OneDimension_DP/A_Medium/P01_FrogJump` | 2026-10-01 | — | — | 2026-11-10 | First revision |
| frog-jump-with-k-distances | [Frog Jump with K Distances](src/main/java/DynamicProgramming/Blind_180/B_State_Transition/A_Medium/P01_FrogJumpWithKDistances/) | DP · State Transition | `src/main/java/DynamicProgramming/Blind_180/B_State_Transition/A_Medium/P01_FrogJumpWithKDistances` | 2026-10-01 | — | — | 2026-11-10 | First revision |
| maximum-sum-of-non-adjacent-elements | [Maximum Sum of Non-Adjacent Elements](src/main/java/DynamicProgramming/Blind_180/B_State_Transition/A_Medium/P02_MaximumSumOfNonAdjacentElements/) | DP · State Transition | `src/main/java/DynamicProgramming/Blind_180/B_State_Transition/A_Medium/P02_MaximumSumOfNonAdjacentElements` | 2026-10-01 | — | — | 2026-11-11 | First revision |
| ninjas-training | [Ninja's Training](src/main/java/DynamicProgramming/Blind_180/B_State_Transition/A_Medium/P03_NinjaTraining/) | DP · State Transition | `src/main/java/DynamicProgramming/Blind_180/B_State_Transition/A_Medium/P03_NinjaTraining` | 2026-10-05 | — | — | 2026-11-11 | First revision |
| unique-paths-ii | [Unique Paths II](src/main/java/DynamicProgramming/Blind_180/B_State_Transition/B_Hard/P01_UniquePathsII/) | DP · State Transition | `src/main/java/DynamicProgramming/Blind_180/B_State_Transition/B_Hard/P01_UniquePathsII` | 2026-10-06 | — | — | 2026-11-12 | First revision |
| best-time-to-buy-and-sell-stock-with-transaction-fee | [Best Time to Buy and Sell Stock with Transaction Fee](src/main/java/DynamicProgramming/Blind_180/B_State_Transition/A_Medium/P04_BestTimeToBuyAndSellStockWithTransactionFee/) | DP · State Transition | `src/main/java/DynamicProgramming/Blind_180/B_State_Transition/A_Medium/P04_BestTimeToBuyAndSellStockWithTransactionFee` | 2026-10-07 | — | — | 2026-11-12 | First revision |
| best-time-to-buy-and-sell-stock-iv | [Best Time to Buy and Sell Stock IV](src/main/java/DynamicProgramming/Blind_180/B_State_Transition/B_Hard/P02_BestTimeToBuyAndSellStockIV/) | DP · State Transition | `src/main/java/DynamicProgramming/Blind_180/B_State_Transition/B_Hard/P02_BestTimeToBuyAndSellStockIV` | 2026-10-09 | — | — | 2026-11-13 | First revision |
| 0-1-knapsack | [0/1 Knapsack](src/main/java/DynamicProgramming/Blind_180/A_Knapsack/A_Medium/P01_ZeroOneKnapsack/) | DP · Knapsack | `src/main/java/DynamicProgramming/Blind_180/A_Knapsack/A_Medium/P01_ZeroOneKnapsack` | 2026-10-10 | — | — | 2026-11-13 | First revision |
| partition-a-set-into-two-subsets-with-minimum-absolute-sum-difference | [Partition a Set into Two Subsets with Minimum Absolute Sum Difference](src/main/java/DynamicProgramming/Blind_180/A_Knapsack/B_Hard/P01_PartitionSetIntoTwoSubsetsMinDifference/) | DP · Knapsack | `src/main/java/DynamicProgramming/Blind_180/A_Knapsack/B_Hard/P01_PartitionSetIntoTwoSubsetsMinDifference` | 2026-10-13 | — | — | 2026-11-14 | First revision |
| last-stone-weight-ii | [Last Stone Weight II](src/main/java/DynamicProgramming/Blind_180/A_Knapsack/B_Hard/P02_LastStoneWeightII/) | DP · Knapsack | `src/main/java/DynamicProgramming/Blind_180/A_Knapsack/B_Hard/P02_LastStoneWeightII` | 2026-10-14 | — | — | 2026-11-14 | First revision |
| target-sum | [Target Sum](src/main/java/DynamicProgramming/Blind_180/A_Knapsack/B_Hard/P03_TargetSum/) | DP · Knapsack | `src/main/java/DynamicProgramming/Blind_180/A_Knapsack/B_Hard/P03_TargetSum` | 2026-10-18 | — | — | 2026-11-14 | First revision |
| coin-change-ii | [Coin Change II](src/main/java/DynamicProgramming/Blind_180/A_Knapsack/A_Medium/P02_CoinChangeII/) | DP · Knapsack | `src/main/java/DynamicProgramming/Blind_180/A_Knapsack/A_Medium/P02_CoinChangeII` | 2026-10-20 | — | — | 2026-11-14 | First revision |
| longest-common-subsequence | [Longest Common Subsequence](src/main/java/DynamicProgramming/Blind_180/C_Sequence/A_Medium/P01_LongestCommonSubsequence/) | DP · Sequence | `src/main/java/DynamicProgramming/Blind_180/C_Sequence/A_Medium/P01_LongestCommonSubsequence` | 2026-10-21 | — | — | 2026-11-16 | First revision |
| longest-common-substring | [Longest Common Substring](src/main/java/DynamicProgramming/Blind_180/C_Sequence/A_Medium/P02_LongestCommonSubstring/) | DP · Sequence | `src/main/java/DynamicProgramming/Blind_180/C_Sequence/A_Medium/P02_LongestCommonSubstring` | 2026-10-21 | — | — | 2026-11-16 | First revision |
| longest-palindromic-subsequence | [Longest Palindromic Subsequence](src/main/java/DynamicProgramming/Blind_180/C_Sequence/A_Medium/P03_LongestPalindromicSubsequence/) | DP · Sequence | `src/main/java/DynamicProgramming/Blind_180/C_Sequence/A_Medium/P03_LongestPalindromicSubsequence` | 2026-10-22 | — | — | 2026-11-17 | First revision |
| longest-increasing-subsequence | [Longest Increasing Subsequence](src/main/java/DynamicProgramming/Blind_180/C_Sequence/B_Hard/P01_LongestIncreasingSubsequence/) | DP · Sequence | `src/main/java/DynamicProgramming/Blind_180/C_Sequence/B_Hard/P01_LongestIncreasingSubsequence` | 2026-10-24 | — | — | 2026-11-17 | First revision |
| number-of-longest-increasing-subsequence | [Number of Longest Increasing Subsequence](src/main/java/DynamicProgramming/Blind_180/C_Sequence/A_Medium/P04_NumberOfLongestIncreasingSubsequence/) | DP · Sequence | `src/main/java/DynamicProgramming/Blind_180/C_Sequence/A_Medium/P04_NumberOfLongestIncreasingSubsequence` | 2026-10-26 | — | — | 2026-11-18 | First revision |
| edit-distance | [Edit Distance](src/main/java/DynamicProgramming/Blind_180/C_Sequence/A_Medium/P05_EditDistance/) | DP · Sequence | `src/main/java/DynamicProgramming/Blind_180/C_Sequence/A_Medium/P05_EditDistance` | 2026-10-27 | — | — | 2026-11-18 | First revision |
| wildcard-matching | [Wildcard Matching](src/main/java/DynamicProgramming/Blind_180/C_Sequence/B_Hard/P02_WildcardMatching/) | DP · Sequence | `src/main/java/DynamicProgramming/Blind_180/C_Sequence/B_Hard/P02_WildcardMatching` | 2026-10-29 | — | — | 2026-11-19 | First revision |
| matrix-chain-multiplication | [Matrix Chain Multiplication](src/main/java/DynamicProgramming/Blind_180/D_Interval/B_Hard/P01_MatrixChainMultiplication/) | DP · Interval | `src/main/java/DynamicProgramming/Blind_180/D_Interval/B_Hard/P01_MatrixChainMultiplication` | 2026-10-31 | — | — | 2026-11-19 | First revision |
| palindrome-partitioning-ii | [Palindrome Partitioning II](src/main/java/DynamicProgramming/Blind_180/D_Interval/B_Hard/P02_PalindromePartitioningII/) | DP · Interval | `src/main/java/DynamicProgramming/Blind_180/D_Interval/B_Hard/P02_PalindromePartitioningII` | 2026-11-06 | — | — | 2026-11-20 | First revision |

## History

One row per revision, newest last. `Source` is `revise`, `mock`, or `planner` (a re-solve ticked in the planner).

| Date | Problem | Result | Source | Note |
|---|---|---|---|---|
