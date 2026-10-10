package DynamicProgramming.Blind_180.State_Transition.A_Medium;

public class NinjaTraining_A_RecursiveNaive {
    public int ninjaTraining(int[][] matrix) {
        int no_of_days = matrix.length;
        return helper(matrix, no_of_days - 1, -1);
    }

    public int helper(int[][] matrix, int day, int banned_activity){
        if(day == 0){
            int max_points = 0;
            for(int activity=0; activity<3; activity++){
                if(activity != banned_activity){
                    max_points = Math.max(max_points, matrix[day][activity]);
                }
            }
            return max_points;
        }

        int max_points = 0;
        for(int activity=0; activity<3; activity++){
            if(activity != banned_activity){
                int curr_points = matrix[day][activity] + helper(matrix, day-1, activity);
                max_points = Math.max(curr_points, max_points);
            }
        }

        return max_points;
    }
}
