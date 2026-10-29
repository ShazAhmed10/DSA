package DynamicProgramming.Blind_180.C_Sequence.B_Hard;

public class WildcardMatching_A_RecursiveNaive {
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();

        return helper(s, p, n, m);
    }

    public boolean helper(String s, String p, int n, int m){
        if(n == 0 && m == 0){
            return true;
        }

        if(n == 0){
            if(p.charAt(m-1) == '*'){
                return helper(s, p, n, m-1);
            }
            return false;
        }

        if(m == 0){
            return false;
        }

        char c1 = s.charAt(n-1);
        char c2 = p.charAt(m-1);
        boolean curr = false;
        if(c1 == c2 || c2 == '?'){
            curr = helper(s, p, n-1, m-1);
        }
        else{
            if(c2 == '*'){
                curr = helper(s, p, n, m-1) || helper(s, p, n-1, m);
            }
        }

        return curr;
    }
}
