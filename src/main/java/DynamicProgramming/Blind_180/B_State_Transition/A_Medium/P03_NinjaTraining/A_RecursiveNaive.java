package DynamicProgramming.Blind_180.B_State_Transition.A_Medium.P03_NinjaTraining;

public class A_RecursiveNaive {
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

    public static void main(String[] args) {
        A_RecursiveNaive solution = new A_RecursiveNaive();
        System.out.println(solution.ninjaTraining(new int[][]{{10, 40, 70}, {20, 50, 80}, {30, 60, 90}}) + " (expected 210)");
        System.out.println(solution.ninjaTraining(new int[][]{{70, 40, 10}, {180, 20, 5}, {200, 60, 30}}) + " (expected 290)");
        System.out.println(solution.ninjaTraining(new int[][]{{0, 0, 0}, {0, 100, 0}, {0, 1, 0}}) + " (expected 100)");
    }
}
