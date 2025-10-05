package Arrays.PrefixAndHashMap;

import java.util.HashMap;
import java.util.Map;

public class CountSubarrayDivisibleByK {
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
}
