package DynamicProgramming.Blind_180.State_Transition.B_Hard;

import java.util.Arrays;

public class UniquePathsII_B_Memoization {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row_length = obstacleGrid.length;
        int col_length = obstacleGrid[0].length;
        int[][] dp = new int[row_length][col_length];

        for(int[] arr : dp){
            Arrays.fill(arr, -1);
        }

        return helper(obstacleGrid, row_length-1, col_length-1, dp);
    }

    public int helper(int[][] obstacleGrid, int row, int col, int[][] dp){
        if(row == 0 && col == 0 && obstacleGrid[row][col] != 1){
            return dp[0][0] = 1;
        }

        if(row < 0 || col < 0 || obstacleGrid[row][col] == 1){
            if(row >= 0 && col >= 0){
                dp[row][col] = 0;
            }
            return 0;
        }

        if(dp[row][col] != -1){
            return dp[row][col];
        }

        int way_1 = helper(obstacleGrid, row-1, col, dp);
        int way_2 = helper(obstacleGrid, row, col-1, dp);

        return dp[row][col] = way_1 + way_2;
    }
}
