package main.java.week3_DFS_BFS.connected_components_11724;
import java.io.*;
import java.util.*;

public class Solution {

    static List<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];

        for(int i = 0; i <= N; i++) graph[i] = new ArrayList<>();

        for(int j = 0; j < M; j++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        visited = new boolean[N + 1];
        int count = 0;

        for(int k = 1; k <= N; k++){
            if(!visited[k]){
                dfs(k);
                count++;
            }
        }
        System.out.println(count);
    }

    //임력 값이 크지 않은 케이스에 한해 재귀 사용
    static void dfs(int cur){
        visited[cur] = true; // 지금 노드 방문 처리
        for(int next : graph[cur]){
            if(!visited[next]){ // 아직 방문하지 않은 곳이 있으면
                dfs(next); // 그쪽으로 이동
            }
        }
    }
}
