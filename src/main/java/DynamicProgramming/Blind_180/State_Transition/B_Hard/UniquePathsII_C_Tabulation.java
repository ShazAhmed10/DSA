package DynamicProgramming.Blind_180.State_Transition.B_Hard;

public class UniquePathsII_C_Tabulation {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row_length = obstacleGrid.length;
        int col_length = obstacleGrid[0].length;
        int[][] dp = new int[row_length][col_length];

        if(obstacleGrid[0][0] != 1) dp[0][0] = 1;
        for(int col=1; col<col_length; col++){
            if(dp[0][col-1] != 0 && obstacleGrid[0][col] != 1){
                dp[0][col] = 1;
            }
        }

        for(int row=1; row<row_length; row++){
            if(dp[row-1][0] != 0 && obstacleGrid[row][0] != 1){
                dp[row][0] = 1;
            }
        }

        for(int row=1; row<row_length; row++){
            for(int col=1; col<col_length; col++){
                if(obstacleGrid[row][col] != 1){
                    dp[row][col] = dp[row-1][col] + dp[row][col-1];
                }
            }
        }

        return dp[row_length-1][col_length-1];
    }
}
