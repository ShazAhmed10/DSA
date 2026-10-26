package DynamicProgramming.Blind_180.C_Sequence.A_Medium;

public class NumberOfLongestIncreasingSubsequence_C_Tabulation {
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
}
