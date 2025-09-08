package sep7_2025;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class NQueens {
    //time complexity O(N! + N^2 * number of solutions)
    //same code to count number of solutions
    // to note if we are just counting number of solution - we dont need the board state

    int size;
    HashSet<Integer> isColVisited;
    HashSet<Integer> isLeftDiagonal;
    HashSet<Integer> isRightDiagonal;

    boolean isSafe(int i, int j){
        if(isColVisited.contains(j) || isRightDiagonal.contains(i+j) || isLeftDiagonal.contains(j-i))
            return false;
        else
            return true;
    }
    List<String> generateSolution(char[][] board){
        List<String> result = new ArrayList<>();
        for(int i=0;i<size;i++){
            StringBuilder stringBuilder = new StringBuilder();
            for(int j=0;j<size;j++){
                stringBuilder.append(board[i][j]);
            }
            result.add(stringBuilder.toString());
        }
        return result;
    }
    int backtrack(int row, char[][] board, List<List<String>> list){
        if(row==size){
            list.add(generateSolution(board));
            return 1;
        }
        int count = 0;
        for(int j=0;j<size;j++){
            if(isSafe(row,j)){
                board[row][j] = 'Q';
                isColVisited.add(j);
                isRightDiagonal.add(row+j);
                isLeftDiagonal.add(j-row);
                count += backtrack(row+1,board, list);
                board[row][j] = '.';
                isColVisited.remove(j);
                isRightDiagonal.remove(row+j);
                isLeftDiagonal.remove(j-row);
            }
        }

        return count;
    }

    public static void main(String[] args){
        NQueens nQueens = new NQueens();
        nQueens.size = 4;
        nQueens.isRightDiagonal = new HashSet<>();
        nQueens.isLeftDiagonal = new HashSet<>();
        nQueens.isColVisited = new HashSet<>();
        char[][] board = new char[nQueens.size][nQueens.size];
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board.length;j++){
                board[i][j] = '.';
            }
        }
        List<List<String>> result = new ArrayList<>();
        System.out.println(nQueens.backtrack(0,board, result));
        System.out.println(result);
    }
}
