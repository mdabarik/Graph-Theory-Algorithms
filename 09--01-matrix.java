/*
Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.

Example 1:
Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
Output: [[0,0,0],[0,1,0],[0,0,0]]

Example 2:
Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
Output: [[0,0,0],[0,1,0],[1,2,1]]  */

class Solution {
    class pair {
        int row, col;
        public pair (int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    public int[][] updateMatrix(int[][] mat) {
        
        int m = mat.length, n = mat[0].length;
        Queue<pair> queue = new LinkedList<>();
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (mat[r][c] == 0)
                    queue.add(new pair(r, c));
                else
                    mat[r][c] = -1;
            }
        }
        int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // down, up, right, left
        while (!queue.isEmpty()) {
            pair curr = queue.poll();
            int row = curr.row;
            int col = curr.col;
            for (int i = 0; i < 4; i++) {
                int x = row + direction[i][0];
                int y = col + direction[i][1];
                if (x >= 0 && x < m && y >= 0 && y < n && mat[x][y] == -1) {
                    mat[x][y] = 1 + mat[row][col];
                    queue.add(new pair(x, y));
                }
            }
        }
        return mat;
    }
} // TC: O(m * n), SC: O(m * n)
