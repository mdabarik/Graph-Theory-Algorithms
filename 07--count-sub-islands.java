/*
https://leetcode.com/problems/count-sub-islands/
*/

/*
You are given two m x n binary matrices grid1 and grid2 containing only 0's (representing water) and 1's (representing land). An island is a group of 1's connected 4-directionally (horizontal or vertical). Any cells outside of the grid are considered water cells.

An island in grid2 is considered a sub-island if there is an island in grid1 that contains all the cells that make up this island in grid2.

Return the number of islands in grid2 that are considered sub-islands.

Example 1:
Input: grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
Output: 3
Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
The 1s colored red in grid2 are those considered to be part of a sub-island. There are three sub-islands.

Input: grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
Output: 2 
Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
The 1s colored red in grid2 are those considered to be part of a sub-island. There are two sub-islands. */

class Solution {
    public static boolean dfs(int[][] grid1, int[][] grid2, int r, int c, int m, int n) {
        if (r < 0 || r >= m || c < 0 || c >= n || grid2[r][c] != 1)
            return true;
        if (grid1[r][c] != grid2[r][c])
            return false;
        grid2[r][c] = -1;
        // down, up, right, left
        boolean down = dfs(grid1, grid2, r + 1, c, m, n);
        boolean up = dfs(grid1, grid2, r - 1, c, m, n);
        boolean right = dfs(grid1, grid2, r, c + 1, m, n);
        boolean left = dfs(grid1, grid2, r, c - 1, m, n);
        return down && up && right && left;
    }
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int row = grid1.length, col = grid1[0].length;
        int count = 0;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (grid2[r][c] == 1) {
                    if (dfs(grid1, grid2, r, c, row, col) == true) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
} // TC: O(m * n), SC: O(m * n)
