package Arrays.PrefixAndHashMap.P01_CountSubarrayDivisibleByK;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public long function(int[] a, int k){
        int n = a.length;
        Map<Long,Long> map = new HashMap<>();
        long pf = 0;
        long count = 0;

        map.put(0L, 1L);
        for(int ele : a){
            pf += ele;
            long rem = ((pf % k) + k) % k;
            if(map.containsKey(rem)){
                count += map.get(rem);
            }
            map.put(rem, map.getOrDefault(rem,0L)+1);
        }

        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.function(new int[]{4, 5, 0, -2, -3, 1}, 5) + " (expected 7)");
    }
}
