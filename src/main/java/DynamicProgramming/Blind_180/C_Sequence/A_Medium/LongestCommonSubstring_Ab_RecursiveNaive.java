package DynamicProgramming.Blind_180.C_Sequence.A_Medium;

public class LongestCommonSubstring_Ab_RecursiveNaive {
    public int longestCommonSubstr(String str1, String str2) {
        int best = 0;

        for(int i=0; i<str1.length(); i++){
            for(int j=0; j<str2.length(); j++){
                best = Math.max(best, helper(str1, str2, i, j));
            }
        }

        return best;
    }

    public int helper(String str1, String str2, int i, int j){
        if(i >= str1.length() || j >= str2.length()){
            return 0;
        }

        char a = str1.charAt(i);
        char b = str2.charAt(j);

        int length = 0;
        if(a == b){
            length = 1 + helper(str1, str2, i+1, j+1);
        }

        return length;
    }
}
