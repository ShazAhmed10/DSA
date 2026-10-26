package DynamicProgramming.Blind_180.C_Sequence.A_Medium;

public class NumberOfLongestIncreasingSubsequence_A_RecursiveNaive {
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

        for(int i=0; i<n; i++){
            Pair currPair = helper(nums, i);

            if(best.length < currPair.length){
                best.length = currPair.length;
                best.count = currPair.count;
            }
            else if(best.length == currPair.length){
                best.count += currPair.count;
            }
        }

        return best.count;
    }

    public Pair helper(int[] nums, int i){
        Pair maxPair = new Pair(1, 1);

        for(int j=0; j<i; j++){
            if(nums[j] < nums[i]){
                Pair currPair = helper(nums, j);
                currPair.length += 1;

                if(maxPair.length < currPair.length){
                    maxPair.length = currPair.length;
                    maxPair.count = currPair.count;
                }
                else if(maxPair.length == currPair.length){
                    maxPair.count += currPair.count;
                }
            }
        }

        return maxPair;
    }
}
