package DynamicProgramming.Blind_180.B_State_Transition.B_Hard.P01_UniquePathsII;

public class A_RecursiveNaive {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row_length = obstacleGrid.length;
        int col_length = obstacleGrid[0].length;

        return helper(obstacleGrid, row_length-1, col_length-1);
    }

    public int helper(int[][] obstacleGrid, int row, int col){
        if(row == 0 && col == 0 && obstacleGrid[row][col] != 1){
            return 1;
        }

        if(row < 0 || col < 0 || obstacleGrid[row][col] == 1){
            return 0;
        }

        int way_1 = helper(obstacleGrid, row-1, col);
        int way_2 = helper(obstacleGrid, row, col-1);

        return way_1 + way_2;
    }

    public static void main(String[] args) {
        A_RecursiveNaive solution = new A_RecursiveNaive();
        System.out.println(solution.uniquePathsWithObstacles(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}) + " (expected 2)");
        System.out.println(solution.uniquePathsWithObstacles(new int[][]{{0, 1}, {0, 0}}) + " (expected 1)");
        System.out.println(solution.uniquePathsWithObstacles(new int[][]{{1}}) + " (expected 0)");
    }
}
