/*
Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1 and return them in any order.

The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).

Example 1:
Input: graph = [[1,2],[3],[3],[]]
Output: [[0,1,3],[0,2,3]]
Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.

Example 2:
Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]] */

/*-----------DFS Algorithm------------*/
class Solution {
    public void dfs(int[][] graph, List<List<Integer>> list, List<Integer> path, int node) {
        if (graph.length - 1 == node) {
            list.add(new ArrayList<>(path));
            return;
        }
        for (Integer adj : graph[node]) {
            if (!path.contains(adj)) {
                path.add(adj);
                dfs(graph, list, path, adj);
                path.remove(path.size() - 1);
            }
        }
    }
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(0);
        dfs(graph, list, path, 0);
        return list;
    }
}


/*-----------BFS Algorithm------------*/
class Solution {
    public void bfs(int[][] graph, List<List<Integer>> list, int src, int tar) {
        Queue<List<Integer>> queue = new LinkedList<>();
        List<Integer> path = new ArrayList<>();
        path.add(src);
        queue.add(path);
        while (!queue.isEmpty()) {
            path = queue.poll();
            int last = path.get(path.size() - 1);
            if (last == tar) {
                list.add(path);
                continue;
            }
            for (Integer adj : graph[last]) {
                if (!path.contains(adj)) {
                    List<Integer> newPath = new ArrayList<>(path);
                    newPath.add(adj);
                    queue.add(newPath);
                }
            }
        }
    }
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> list = new ArrayList<>();
        int target = graph.length - 1;
        bfs(graph, list, 0, target);
        return list;
    }
}
