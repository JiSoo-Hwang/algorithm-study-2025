package main.java.week3_DFS_BFS.dfs_bfs_1260;
import java.io.*;
import java.util.*;

public class Solution {
    static List<Integer>[] g;
    static boolean[] visited;
    static StringBuilder line;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        g = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) g[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            g[a].add(b);
            g[b].add(a);
        }

        // 번호가 작은 정점부터 방문되도록 정렬
        for (int i = 1; i <= N; i++) Collections.sort(g[i]);

        StringBuilder out = new StringBuilder();

        // DFS
        visited = new boolean[N + 1];
        line = new StringBuilder();
        dfs(V);
        out.append(line.toString().trim()).append('\n');

        // BFS
        Arrays.fill(visited, false);
        line = new StringBuilder();
        bfs(V);
        out.append(line.toString().trim());

        System.out.print(out);
    }

    static void dfs(int u) {
        visited[u] = true;
        line.append(u).append(' ');
        for (int v : g[u]) {
            if (!visited[v]) dfs(v);
        }
    }

    static void bfs(int start) {
        Deque<Integer> q = new ArrayDeque<>();
        visited[start] = true;
        q.add(start);
        while (!q.isEmpty()) {
            int u = q.poll();
            line.append(u).append(' ');
            for (int v : g[u]) {
                if (!visited[v]) {
                    visited[v] = true;  // 큐에 넣을 때 방문 체크(중복 방지)
                    q.add(v);
                }
            }
        }
    }
}
