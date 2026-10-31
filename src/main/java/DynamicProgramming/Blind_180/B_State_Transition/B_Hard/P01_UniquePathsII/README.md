# Unique Paths II

[LeetCode 63](https://leetcode.com/problems/unique-paths-ii/) · Blind 180 DP · State Transition · Hard

## Problem

```text
You are given an m x n integer array grid. There is a robot initially located at the top-left corner (i.e., grid[0][0]).
The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or
right at any point in time. An obstacle and space are marked as 1 or 0 respectively in grid. A path that the robot takes
cannot include any square that is an obstacle. Return the number of possible unique paths that the robot can take to
reach the bottom-right corner. The testcases are generated so that the answer will be less than or equal to 2 * 109.

Example 1:
Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
Output: 2
Explanation: There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right

Example 2:
Input: obstacleGrid = [[0,1],[0,0]]
Output: 1


Constraints:
m == obstacleGrid.length
n == obstacleGrid[i].length
1 <= m, n <= 100
obstacleGrid[i][j] is 0 or 1.
```

## Solutions

Each file has a `main` that runs the examples and prints actual vs expected.

| File | Approach |
|---|---|
| [`A_RecursiveNaive.java`](A_RecursiveNaive.java) | Recursive (naive) |
| [`B_Memoization.java`](B_Memoization.java) | Memoization |
| [`C_Tabulation.java`](C_Tabulation.java) | Tabulation |

## Key insight

A cell can only be entered from the cell **above** or the cell to the **left**, so the number of paths to it is the sum of those two. An obstacle has 0 paths.

## State, recurrence, base case

- **State:** `dp[r][c]` = number of paths from (0, 0) to (r, c)
- **Recurrence:** `dp[r][c] = dp[r−1][c] + dp[r][c−1]`, or 0 if the cell is an obstacle
- **Base cases:** `dp[0][0] = 1` unless it's an obstacle; first row and first column are 1 **until the first obstacle**, 0 after it
- **Answer:** `dp[m−1][n−1]`

## Space optimisation

Each cell needs the cell above (previous row) and to the left (current row). Two rows of length n, or a **single array**: before updating, `dp[c]` still holds the value from the row above and `dp[c−1]` is already this row's → `dp[c] = dp[c] + dp[c−1]`. O(n).

## Complexity

| Approach | TC | SC |
|---|---|---|
| Naive recursion | O(2^(m+n)) | O(m+n) stack |
| Memoization | O(m·n) | O(m·n) table + O(m+n) stack |
| Tabulation | O(m·n) | O(m·n) |
| Two rows / single array | O(m·n) | O(n) |

Every move goes down or right, so every path has exactly (m−1) + (n−1) steps → depth m + n, not m·n.

## Mistakes I made

1. **Wrote the naive TC as O(2^(m·n)) and the stack as O(m·n).** The robot never visits all cells on one path; depth is m + n. (For 100×100 that's 2¹⁰⁰⁰⁰ vs ~2²⁰⁰.)
2. **Wrote the space-optimised SC as O(m) + O(n).** Only rows of length n are kept → O(n).

## Related

- Pattern: [Space optimisation](../../../../../../../../patterns/space-optimisation.md)
