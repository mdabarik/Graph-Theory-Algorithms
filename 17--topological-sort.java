/* 
URL: https://practice.geeksforgeeks.org/problems/topological-sort/1
Topological sort
Given a Directed Acyclic Graph (DAG) with V vertices and E edges, Find any Topological Sorting of that Graph.
*/

/*------------ DFS Algorithm -------------*/
class Solution {
    static void dfs(ArrayList<ArrayList<Integer>> adj, boolean[] visited, Stack<Integer> stack, int node) {
        visited[node] = true;
        for (Integer nbr : adj.get(node)) {
            if (visited[nbr] == false) {
                dfs(adj, visited, stack, nbr);
            }
        }
        stack.push(node);
    }
    static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < V; i++) {
            if (visited[i] == false) {
                dfs(adj, visited, stack, i);
            }
        }
        int[] top_sort = new int[V];
        for (int i = 0; i < V; i++) {
            top_sort[i] = stack.pop();
        }
        return top_sort;
    }
}
// TC: O(V + E), SC: O(V)

/*------------ DFS Algorithm (Kahn's Algorithm) -------------*/
class Solution
{
    static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) 
    {
        // kahn's algorithm
        int[] indegree = new int[V];
        int[] top_sort = new int[V];
        for (ArrayList<Integer> list : adj) {
            for (int node : list) {
                indegree[node]++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }
        int index = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (Integer nbr : adj.get(curr)) {
                indegree[nbr]--;
                if (indegree[nbr] == 0) {
                    queue.add(nbr);
                }
            }
            top_sort[index] = curr;
            index++;
        }
        return top_sort;
    }
}
