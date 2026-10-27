package DynamicProgramming.Blind_180.C_Sequence.A_Medium;

public class EditDistance_A_RecursiveNaive {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        return helper(word1, word2, n, m);
    }

    public int helper(String word1, String word2, int n, int m){
        if(n == 0 && m == 0){
            return 0;
        }

        if(n == 0){
            return m;
        }

        if(m == 0){
            return n;
        }

        int currDistance = 0;
        char c1 = word1.charAt(n-1);
        char c2 = word2.charAt(m-1);
        if(c1 == c2){
            currDistance = helper(word1, word2, n-1, m-1);
        }
        else{
            int insert = helper(word1, word2, n, m-1);
            int delete = helper(word1, word2, n-1, m);
            int replace = helper(word1, word2, n-1, m-1);

            currDistance = 1 + Math.min(Math.min(insert, delete), replace);
        }

        return currDistance;
    }
}
