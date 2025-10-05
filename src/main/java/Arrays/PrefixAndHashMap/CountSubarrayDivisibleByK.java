package Arrays.PrefixAndHashMap;

import java.util.HashMap;
import java.util.Map;

public class CountSubarrayDivisibleByK {
    //the solution follows the arithmetic equation ie, if we consider pf[i] = sum[0...i] = a & pf[j] = sum[0...j] = b where i<j
    //(a - b) % k = 0 => a % k - b % k = 0 => a % k = b % k
    public static long function(int[] a, int k){
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
    /*
    //test case
    int[] a = {4, 5, 0, -2, -3, 1};
    int k = 5;
    System.out.print(CountSubarrayDivisibleByK.function(a,k));
     */
}
