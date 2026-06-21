 import java.util.*;

public class ValidSudoku {

    public static boolean isValidSudoku(char[][] board) {
        HashSet<Character>[] rows = new HashSet[9];
        HashSet<Character>[] cols = new HashSet[9];
        HashSet<Character>[] boxes = new HashSet[9];

        // initialize sets
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                char val = board[i][j];

                if (val == '.') continue;

                int boxIndex = (i / 3) * 3 + (j / 3);

                // check duplicate
                if (rows[i].contains(val) ||
                    cols[j].contains(val) ||
                    boxes[boxIndex].contains(val)) {
                    return false;
                }

                // add value
                rows[i].add(val);
                cols[j].add(val);
                boxes[boxIndex].add(val);
            }
        }

        return true;
    }

    public static void main(String[] args) {

        char[][] board = {
            {'1','2','.','.','3','.','.','.','.'},
            {'4','.','.','5','.','.','.','.','.'},
            {'.','9','8','.','.','.','.','.','3'},
            {'5','.','.','.','6','.','.','.','4'},
            {'.','.','.','8','.','3','.','.','5'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','.','.','.','.','.','2','.','.'},
            {'.','.','.','4','1','9','.','.','8'},
            {'.','.','.','.','8','.','.','7','9'}
        };

        System.out.println(isValidSudoku(board)); // true
    }
}
