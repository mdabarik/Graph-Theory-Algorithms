/*
URL: https://practice.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1
Detect cycle in an undirected graph
Given an undirected graph with V vertices and E edges, check whether it contains any cycle or not. Graph is in the form of adjacency list where adj[i] contains all the nodes ith node is having edge with.

Example 1:
Input:  
V = 5, E = 5
adj = {{1}, {0, 2, 4}, {1, 3}, {2, 4}, {1, 3}} 
Output: 1
Explanation: 
1->2->3->4->1 is a cycle.

Example 2:
Input: 
V = 4, E = 2
adj = {{}, {2}, {1, 3}, {2}}
Output: 0 */

/*-----------DFS Algorithm------------*/
class Solution {
    public boolean dfs(ArrayList<ArrayList<Integer>> graph, boolean[] visited, int node, int parent) {
        visited[node] = true;
        for (Integer adj : graph.get(node)) {
            if (visited[adj] == false) {
                if (dfs(graph, visited, adj, node) == true) return true;
            } else if (adj == parent) {
                continue;
            } else if (adj != parent) {
                return true;
            }
        }
        return false;
    }
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (visited[i] == false) {
                if (dfs(adj, visited, i, -1) == true) {
                    return true;
                }
            }
        }
        return false;
    }
}


/*-----------BFS Algorithm------------*/
class Solution {
    class pair {
        int node, parent;
        public pair (int node, int parent) {
            this.node = node;
            this.parent = parent;
        }
    }
    public boolean bfs(ArrayList<ArrayList<Integer>> graph, boolean[] visited, int node) {
        Queue<pair> queue = new LinkedList<>();
        queue.add(new pair(node, -1));
        visited[node] = true;
        while (!queue.isEmpty()) {
            pair curr = queue.poll();
            int currNode = curr.node;
            int parent = curr.parent;
            for (Integer adj : graph.get(currNode)) {
                if (visited[adj] == false) {
                    queue.add(new pair(adj, currNode));
                    visited[adj] = true;
                } else if (adj == parent) {
                    continue;
                } else if (adj != parent) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (visited[i] == false) {
                if (bfs(adj, visited, i) == true) {
                    return true;
                }
            }
        }
        return false;
    }
}
