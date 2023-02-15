import java.util.*;

public class Main {
    public static void dfs(List<List<Integer>> graph, boolean[] visited, List<Integer> list, int v) {
        list.add(v);
        visited[v] = true;
        List<Integer> adj = graph.get(v);
        for (Integer curr : adj) {
            if (visited[curr]) continue;
            dfs(graph, visited, list, curr);
        }
    }
    public static List<Integer> dfsHelper(List<List<Integer>> graph) {
        boolean[] visited = new boolean[graph.size()];
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < graph.size(); i++) {
            if (visited[i]) continue;
            dfs(graph, visited, list, i);
        }
        return list;
    }
  
    public static void main(String[] args) {
        List<List<Integer>> graph = new ArrayList<>();
        graph.add(new ArrayList<>(){{add(1); add(3); }});
        graph.add(new ArrayList<>(){{add(0); add(2); add(4); }});
        graph.add(new ArrayList<>(){{add(1); add(5); }});
        graph.add(new ArrayList<>(){{add(0); add(6); }});
        graph.add(new ArrayList<>(){{add(1); add(7); }});
        graph.add(new ArrayList<>(){{add(2); add(8); }});
        graph.add(new ArrayList<>(){{add(3); add(7); }});
        graph.add(new ArrayList<>(){{add(4); add(6); add(8); }});
        graph.add(new ArrayList<>(){{add(5); add(7); }});
        graph.add(new ArrayList<>());
        graph.add(new ArrayList<>());
        /*
            0 -> 1, 3
            1 -> 0, 2, 4
            2 -> 1, 5
            3 -> 0, 6
            4 -> 1, 7
            5 -> 2, 8
            6 -> 3, 7
            7 -> 4, 6, 8
            8 -> 5, 7
            9 ->
            10 ->

            graph::
            0 --- 1 --- 2
            .     .     .
            .     .     .
            3     4     5
            .     .     .
            .     .     .
            6 --- 7 --- 8

            9

            10
        */
        for (Integer i : dfsHelper(graph))
            System.out.print(i + " ");
    }
}
