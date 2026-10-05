package DynamicProgramming.Easy;

import java.util.*;

public class NinjaTraining_B_Memoization {
    public int ninjaTraining(int[][] matrix) {
        int no_of_days = matrix.length;
        int[][] dp = new int[no_of_days][4];
        for(int i=0; i<no_of_days; i++){
            Arrays.fill(dp[i], -1);
        }
        return helper(matrix, no_of_days - 1, 3, dp);
    }

    public int helper(int[][] matrix, int day, int banned_activity, int[][] dp){
        if(day == 0){
            int max_points = 0;
            for(int activity=0; activity<3; activity++){
                if(activity != banned_activity){
                    max_points = Math.max(max_points, matrix[day][activity]);
                }
            }
            return dp[day][banned_activity] = max_points;
        }

        if(dp[day][banned_activity] != -1){
            return dp[day][banned_activity];
        }

        int max_points = 0;
        for(int activity=0; activity<3; activity++){
            if(activity != banned_activity){
                int curr_points = matrix[day][activity] + helper(matrix, day-1, activity, dp);
                max_points = Math.max(curr_points, max_points);
            }
        }

        return dp[day][banned_activity] = max_points;
    }
}
