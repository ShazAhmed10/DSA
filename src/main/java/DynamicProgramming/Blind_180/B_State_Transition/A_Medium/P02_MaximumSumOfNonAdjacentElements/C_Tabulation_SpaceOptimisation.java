package DynamicProgramming.Blind_180.B_State_Transition.A_Medium.P02_MaximumSumOfNonAdjacentElements;

public class C_Tabulation_SpaceOptimisation {
    public int nonAdjacent(int[] nums) {
        int n = nums.length;

        int prev2 = 0;
        int prev = nums[0];

        for(int i=2; i<=n; i++){
            int pick = nums[i-1] + prev2;
            int not_pick = prev;

            int curr = Math.max(pick, not_pick);

            prev2 = prev;
            prev = curr;
        }

        return prev;
    }

    public static void main(String[] args) {
        C_Tabulation_SpaceOptimisation solution = new C_Tabulation_SpaceOptimisation();
        System.out.println(solution.nonAdjacent(new int[]{1, 2, 4}) + " (expected 5)");
        System.out.println(solution.nonAdjacent(new int[]{2, 1, 4, 9}) + " (expected 11)");
    }
}
