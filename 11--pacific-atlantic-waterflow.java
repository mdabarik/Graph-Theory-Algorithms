/*
There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.

The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).

The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.

Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.

Example: 
Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
Explanation: The following cells can flow to the Pacific and Atlantic oceans, as shown below:
[0,4]: [0,4] -> Pacific Ocean 
       [0,4] -> Atlantic Ocean
[1,3]: [1,3] -> [0,3] -> Pacific Ocean 
       [1,3] -> [1,4] -> Atlantic Ocean
[1,4]: [1,4] -> [1,3] -> [0,3] -> Pacific Ocean 
       [1,4] -> Atlantic Ocean
[2,2]: [2,2] -> [1,2] -> [0,2] -> Pacific Ocean 
       [2,2] -> [2,3] -> [2,4] -> Atlantic Ocean
[3,0]: [3,0] -> Pacific Ocean 
       [3,0] -> [4,0] -> Atlantic Ocean
[3,1]: [3,1] -> [3,0] -> Pacific Ocean 
       [3,1] -> [4,1] -> Atlantic Ocean
[4,0]: [4,0] -> Pacific Ocean 
       [4,0] -> Atlantic Ocean
Note that there are other possible paths for these cells to flow to the Pacific and Atlantic oceans. */

class Solution {
    public static void dfs(int[][] matrix, boolean[][] ocean, int r, int c, int curr) {
        if (r < 0 || r >= matrix.length || c < 0 || c >= matrix[0].length) return;
        if (matrix[r][c] < curr || ocean[r][c] == true) return;
        ocean[r][c] = true;
        // down, up, right, left
        dfs(matrix, ocean, r + 1, c, matrix[r][c]);
        dfs(matrix, ocean, r - 1, c, matrix[r][c]);
        dfs(matrix, ocean, r, c + 1, matrix[r][c]);
        dfs(matrix, ocean, r, c - 1, matrix[r][c]);
    }
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> list = new ArrayList<>();
        int row = heights.length, col = heights[0].length;
        boolean[][] pacific = new boolean[row][col];
        boolean[][] atlantic = new boolean[row][col];
        int MIN = Integer.MIN_VALUE;
        // first row & last row
        for (int c = 0; c < col; c++) {
            dfs(heights, pacific, 0, c, MIN);
            dfs(heights, atlantic, row - 1, c, MIN);
        }
        // first col & last col
        for (int r = 0; r < row; r++) {
            dfs(heights, pacific, r, 0, MIN);
            dfs(heights, atlantic, r, col - 1, MIN);
        }
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (pacific[r][c] == true && atlantic[r][c] == true) {
                    list.add(Arrays.asList(r, c));
                }
            }
        }
        return list;
    }
}

/*
    1, 2, 2, 3, 5
    3, 2, 3, 4, 4
    2, 4, 5, 3, 1
    6, 7, 1, 4, 5
    5, 1, 1, 2, 4
*/
