package DynamicProgramming.Blind_180.C_Sequence.A_Medium.P04_NumberOfLongestIncreasingSubsequence;

public class C_Tabulation {
    static class Pair{
        int length;
        int count;

        Pair(int length, int count){
            this.length = length;
            this.count = count;
        }
    }

    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        Pair best = new Pair(0,0);
        Pair[] dp = new Pair[n];

        for(int i=0; i<n; i++){
            Pair maxPair = new Pair(1, 1);

            for(int j=0; j<i; j++){
                if(nums[j] < nums[i]){
                    Pair currPair = dp[j];
                    int currLength = 1 + currPair.length;

                    if(maxPair.length < currLength){
                        maxPair.length = currLength;
                        maxPair.count = currPair.count;
                    }
                    else if(maxPair.length == currLength){
                        maxPair.count += currPair.count;
                    }
                }
            }

            dp[i] = maxPair;

            if(best.length < maxPair.length){
                best.length = maxPair.length;
                best.count = maxPair.count;
            }
            else if(best.length == maxPair.length){
                best.count += maxPair.count;
            }
        }

        return best.count;
    }

    public static void main(String[] args) {
        C_Tabulation solution = new C_Tabulation();
        System.out.println(solution.findNumberOfLIS(new int[]{1, 3, 5, 4, 7}) + " (expected 2)");
        System.out.println(solution.findNumberOfLIS(new int[]{2, 2, 2, 2, 2}) + " (expected 5)");
        System.out.println(solution.findNumberOfLIS(new int[]{1, 2, 4, 3, 5, 4, 7, 2}) + " (expected 3)");
    }
}
