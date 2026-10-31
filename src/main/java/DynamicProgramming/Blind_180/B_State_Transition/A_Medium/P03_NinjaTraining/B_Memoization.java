package DynamicProgramming.Blind_180.B_State_Transition.A_Medium.P03_NinjaTraining;

import java.util.*;

public class B_Memoization {
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

    public static void main(String[] args) {
        B_Memoization solution = new B_Memoization();
        System.out.println(solution.ninjaTraining(new int[][]{{10, 40, 70}, {20, 50, 80}, {30, 60, 90}}) + " (expected 210)");
        System.out.println(solution.ninjaTraining(new int[][]{{70, 40, 10}, {180, 20, 5}, {200, 60, 30}}) + " (expected 290)");
        System.out.println(solution.ninjaTraining(new int[][]{{0, 0, 0}, {0, 100, 0}, {0, 1, 0}}) + " (expected 100)");
    }
}
