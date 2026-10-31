package DynamicProgramming.A2Z.OneDimension_DP.A_Medium.P01_FrogJump;

public class C_Tabulation_SpaceOptimisation {
    public int frogJump(int[] heights) {
        int n = heights.length;
        int prev = 0;
        int prev2 = 0;

        for(int i=2; i<=n; i++){
            int way_1 = prev + Math.abs(heights[i-1] - heights[i-2]);
            int way_2 = Integer.MAX_VALUE;
            if(i > 2){
                way_2 = prev2 + Math.abs(heights[i-1] - heights[i-3]);
            }
            int curr = Math.min(way_1, way_2);
            prev2 = prev;
            prev = curr;
        }

        return prev;
    }

    public static void main(String[] args) {
        C_Tabulation_SpaceOptimisation solution = new C_Tabulation_SpaceOptimisation();
        System.out.println(solution.frogJump(new int[]{2, 1, 3, 5, 4}) + " (expected 2)");
        System.out.println(solution.frogJump(new int[]{7, 5, 1, 2, 6}) + " (expected 9)");
    }
}
