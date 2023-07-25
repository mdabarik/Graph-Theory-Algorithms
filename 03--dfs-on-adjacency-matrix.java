/*
https://practice.geeksforgeeks.org/problems/depth-first-traversal-for-a-graph/1
*/

import java.util.*;

public class Main {
    public static void dfs(int[][] matrix, boolean[][] visited, int r, int c) {
        int row = matrix.length, col = matrix[0].length;
        if (r < 0 || r >= row || c < 0 || c >= col || visited[r][c]) return;
        System.out.print(matrix[r][c] + " ");
        visited[r][c] = true;
        // down, top, right, left
        dfs(matrix, visited, r + 1, c);
        dfs(matrix, visited, r - 1, c);
        dfs(matrix ,visited, r, c + 1);
        dfs(matrix, visited, r, c - 1);

    }
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        dfs(matrix, visited, 0, 0); // 1 4 7 8 5 2 3 6 9 
    }
}
