package DynamicProgramming.Blind_180.B_State_Transition.A_Medium.P03_NinjaTraining;

public class C_Tabulation {
    public int ninjaTraining(int[][] matrix) {
        int no_of_days = matrix.length;
        int[][] dp = new int[no_of_days][4];

        for(int banned_activity=0; banned_activity<4; banned_activity++){
            int max_points = 0;
            for(int activity=0; activity<3; activity++){
                if(activity != banned_activity){
                    max_points = Math.max(max_points, matrix[0][activity]);
                }
            }
            dp[0][banned_activity] = max_points;
        }

        for(int day=1; day<no_of_days; day++){
            for(int banned_activity=0; banned_activity<4; banned_activity++){
                int max_points = 0;
                for(int activity=0; activity<3; activity++){
                    if(activity != banned_activity){
                        int curr_points = matrix[day][activity] + dp[day-1][activity];
                        max_points = Math.max(curr_points, max_points);
                    }
                }
                dp[day][banned_activity] = max_points;
            }
        }

        return dp[no_of_days - 1][3];
    }

    public static void main(String[] args) {
        C_Tabulation solution = new C_Tabulation();
        System.out.println(solution.ninjaTraining(new int[][]{{10, 40, 70}, {20, 50, 80}, {30, 60, 90}}) + " (expected 210)");
        System.out.println(solution.ninjaTraining(new int[][]{{70, 40, 10}, {180, 20, 5}, {200, 60, 30}}) + " (expected 290)");
        System.out.println(solution.ninjaTraining(new int[][]{{0, 0, 0}, {0, 100, 0}, {0, 1, 0}}) + " (expected 100)");
    }
}
