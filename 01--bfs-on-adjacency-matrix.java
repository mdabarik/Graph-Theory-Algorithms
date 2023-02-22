import java.util.*;

public class Main {
    static class pair {
        int first, second;
        public pair(int f, int s) {
            first = f;
            second = s;
        }
    }
    public static void bfs(int[][] matrix, boolean[][] visited, int r, int c) {
        int row = matrix.length, col = matrix[0].length;
        Queue<pair> queue = new LinkedList<>();
        queue.add(new pair(r, c));
        visited[r][c] = true;
        int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // down, top, right, left
        while (!queue.isEmpty()) {
            pair curr = queue.poll();
            int first = curr.first;
            int second = curr.second;
            System.out.print(matrix[first][second] + " ");
            for (int i = 0; i < 4; i++) {
                int adjX = first + direction[i][0];
                int adjY = second + direction[i][1];
                if (adjX < 0 || adjX >= row || adjY < 0 || adjY >= col || visited[adjX][adjY]) continue;
                queue.add(new pair(adjX, adjY));
                visited[adjX][adjY] = true;
            }
        }
    }
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        bfs(matrix, visited, 0, 0);
    }
}
