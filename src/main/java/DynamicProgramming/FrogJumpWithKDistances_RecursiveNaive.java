package DynamicProgramming;

public class FrogJumpWithKDistances_RecursiveNaive {
    public int frogJump(int[] heights, int k) {
        int n = heights.length;
        int overallMin = Integer.MAX_VALUE;
        return helper(n,heights,k);
    }

    public int helper(int n, int[] heights, int k){
        if(n == 1){
            return 0;
        }

        int currMin = Integer.MAX_VALUE;
        for(int i=1; i<=k; i++){
            if(n > i){
                currMin = Math.min(currMin, helper(n-i, heights, k) + Math.abs(heights[n-1] - heights[n-1-i]));
            }
            else {
                break;
            }
        }

        return currMin;
    }
}
