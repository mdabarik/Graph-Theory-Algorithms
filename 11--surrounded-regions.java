/*
Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example: 
Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
Explanation: Notice that an 'O' should not be flipped if:
- It is on the border, or
- It is adjacent to an 'O' that should not be flipped.
The bottom 'O' is on the border, so it is not flipped.
The other three 'O' form a surrounded region, so they are flipped. */

class Solution {
    public void dfs(char[][] board, int r, int c) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != 'O')
            return;
        board[r][c] = '#';
        // down, up, right, left
        dfs(board, r + 1, c);
        dfs(board, r - 1, c);
        dfs(board, r, c + 1);
        dfs(board, r, c - 1);
    }
    public void solve(char[][] board) {
        int row = board.length, col = board[0].length;
        
        for (int c = 0; c < col; c++) { // first row & last row
            if (board[0][c] == 'O') 
                dfs(board, 0, c);
            if (board[row - 1][c] == 'O')
                dfs(board, row - 1, c);
        }
       
        for (int r = 0; r < row; r++) {  // first col & last col
            if (board[r][0] == 'O')
                dfs(board, r, 0);
            if (board[r][col - 1] == 'O')
                dfs(board, r, col - 1);
        }
        // 1. replace unsurronded region 'O' with '#'
        // 2. replace remaining 'O' with 'X'
        // 3. replace '#' with 'O'
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (board[r][c] == 'O')
                    board[r][c] = 'X';
                if (board[r][c] == '#')
                    board[r][c] = 'O';
            }
        }
    }
} 
