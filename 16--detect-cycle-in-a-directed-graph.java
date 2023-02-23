/* 
URL: https://practice.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1
Detect cycle in a directed graph
Given a Directed Graph with V vertices (Numbered from 0 to V-1) and E edges, check whether it contains any cycle or not.
*/

/*---------- DFS Algorithm------------*/
class Solution {
    boolean dfs(ArrayList<ArrayList<Integer>> adj, boolean[] visited, boolean[] dfsVisited, int node) {
        visited[node] = true;
        dfsVisited[node] = true;
        for (Integer nbr : adj.get(node)) {
            if (visited[nbr] == false) {
                if (dfs(adj, visited, dfsVisited, nbr) == true) {
                    return true;
                }
            } else if (dfsVisited[nbr] == true) {
                return true;
            }
        }
        dfsVisited[node] = false;
        return false;
    }
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];
        boolean[] dfsVisited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (visited[i] == false) {
                if (dfs(adj, visited, dfsVisited, i) == true) {
                    return true;
                }
            }
        }
        return false;
    }
}

/*---------- BFS Algorithm (Kahn's Algorithm) ------------*/
class Solution { // kahn's algorithm
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] indegree = new int[V];
        for (ArrayList<Integer> list : adj) {
            for (int node : list) {
                indegree[node]++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int node = 0; node < indegree.length; node++) {
            if (indegree[node] == 0) {
                queue.add(node);
            }
        }
        int count = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (Integer nbr : adj.get(curr)) {
                indegree[nbr]--;
                if (indegree[nbr] == 0) {
                    queue.add(nbr);
                }
            }
            count++;
        }
        if (count == V) {
            return false;
        }
        return true;
    }
}
