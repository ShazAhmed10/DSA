package Graphs;

import java.util.LinkedList;
import java.util.Queue;

class Triplet{
    int row;
    int col;
    int dist;

    Triplet(int row, int col, int dist){
        this.row = row;
        this.col = col;
        this.dist = dist;
    }
}

public class MinimumKnights {
    int[] x = {-2, -2, -1, -1, +2, +2, +1, +1};
    int[] y = {-1, +1, -2, +2, -1, +1, -2, +2};

    public int function(int n, int[] input_row, int[] input_col){
        boolean[][] board = new boolean[n][n];
        Queue<Triplet> queue = new LinkedList<>();

        queue.add(new Triplet(input_row[0],input_col[0],0));
        board[input_row[0]][input_col[0]] = true;

        while(!queue.isEmpty()){
            Triplet curr = queue.remove();

            if(curr.row == input_row[1] && curr.col == input_col[1]){
                return curr.dist;
            }

            for(int i=0; i<8; i++){
                int r = curr.row + x[i];
                int c = curr.col + y[i];
                int d = curr.dist + 1;
                if(r >= 0 && r < n && c >= 0 && c < n) {
                    board[r][c] = true;
                    queue.add(new Triplet(r,c,d));
                }
            }
        }

        return -1;
    }
}
