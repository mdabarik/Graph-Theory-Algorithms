/* ULR: https://www.pramp.com/challenge/Y56aZmaj9Ptmd9wV9xvL
Shortest Cell Path
In a given grid of 0s and 1s, we have some starting row and column sr, sc and a target row and column tr, tc. Return the length of the shortest path from sr, sc to tr, tc that walks along 1 values only.

Each location in the path, including the start and the end, must be a 1. Each subsequent location in the path must be 4-directionally adjacent to the previous location.

It is guaranteed that grid[sr][sc] = grid[tr][tc] = 1, and the starting and target positions are different.

If the task is impossible, return -1.

Examples:

input:
grid = [[1, 1, 1, 1], [0, 0, 0, 1], [1, 1, 1, 1]]
sr = 0, sc = 0, tr = 2, tc = 0
output: 8
(The lines below represent this grid:)
1111
0001
1111

grid = [[1, 1, 1, 1], [0, 0, 0, 1], [1, 0, 1, 1]]
sr = 0, sc = 0, tr = 2, tc = 0
output: -1
(The lines below represent this grid:)
1111
0001
1011 */

import java.io.*;
import java.util.*;

class Solution {
  static class pair {
      int row;
      int col;
      public pair (int row, int col) {
          this.row = row;
          this.col = col;
      }
  } // https://www.pramp.com/challenge/Y56aZmaj9Ptmd9wV9xvL
	static int shortestCellPath(int[][] grid, int sr, int sc, int tr, int tc) {
		  boolean[][] visited = new boolean[grid.length][grid[0].length];
      Queue<pair> queue = new LinkedList<>();
      queue.add(new pair(sr, sc));
      visited[sr][sc] = true;
      int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // down, up, right, left
      int level = 0;
      while (!queue.isEmpty()) {
          int size = queue.size();
          while (size != 0) {
              pair curr = queue.poll();
              int row = curr.row;
              int col = curr.col;
              for (int i = 0; i < 4; i++) {
                  int x = row + direction[i][0];
                  int y = col + direction[i][1];
                  if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && visited[x][y] == false && grid[x][y] == 1) {
                      if (x == tr && y == tc) {
                          return level + 1;
                      }
                      queue.add(new pair(x, y));
                      visited[x][y] = true;
                  }
              }
              size--;
          }
          level++;
      }
      return -1;
	}
  

	public static void main(String[] args) {
	    int[][] grid = {
        {1, 1, 1, 1},
        {0, 1, 0, 1},
        {1, 1, 1, 1}
      };
      System.out.println(shortestCellPath(grid, 0, 0, 2, 0));
	}
}
