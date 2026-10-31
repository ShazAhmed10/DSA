package DynamicProgramming.Blind_180.D_Interval.B_Hard.P01_MatrixChainMultiplication;

public class A_RecursiveNaive {
    public int matrixMultiplication(int[] nums) {
        int n = nums.length;
        return helper(nums, 0, n-1);
    }

    public int helper(int[] nums, int left, int right){
        if(left+1 == right){
            return 0;
        }

        int currMinMultiplyCost = Integer.MAX_VALUE;
        for(int i=left+1; i<right; i++){
            int currLeftCost = helper(nums, left, i);
            int currRightCost = helper(nums, i, right);
            int currCost = nums[i] * nums[left] * nums[right];
            int currTotalCost = currLeftCost + currRightCost + currCost;

            currMinMultiplyCost = Math.min(currTotalCost, currMinMultiplyCost);
        }

        return currMinMultiplyCost;
    }

    public static void main(String[] args) {
        A_RecursiveNaive solution = new A_RecursiveNaive();
        System.out.println(solution.matrixMultiplication(new int[]{10, 15, 20, 25}) + " (expected 8000)");
        System.out.println(solution.matrixMultiplication(new int[]{4, 2, 3}) + " (expected 24)");
        System.out.println(solution.matrixMultiplication(new int[]{10, 15, 20, 25, 30}) + " (expected 15500)");
        System.out.println(solution.matrixMultiplication(new int[]{5, 10}) + " (expected 0)");
    }
}
