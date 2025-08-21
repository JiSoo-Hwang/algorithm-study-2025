package main.java.week3_DFS_BFS.apartment_numbering_2667;
import java.io.*;
import java.util.*;

public class Solution {
    static int N;
    static int[][] aptMap;
    static boolean[][] visited;
    static final int[] dr = {-1,1,0,0};
    static final int[] dc = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        aptMap = new int[N][N];
        visited = new boolean[N][N];

        for(int i = 0; i < N; i++){
            String line = br.readLine().trim();
            for(int j = 0; j < N; j++){
                aptMap[i][j] = line.charAt(j) - '0';
            }
        }

        int complex = 0; //단지수
        List<Integer> sizes = new ArrayList<>();

        for(int r = 0; r < N; r++){
           for(int c = 0; c < N; c++){
               if(aptMap[r][c] == 1 && !visited[r][c]){
                   //단지 하나를 DFS로 전부 방문하면 크기를 반환받음
                   int size = dfs(r, c);
                   complex++;
                   sizes.add(size);
               }
           }
        }
        Collections.sort(sizes);
        out.append(complex).append("\n");
        for(int s : sizes)out.append(s).append("\n");

        System.out.println(out);
    }

    static int dfs(int r, int c){
        visited[r][c] = true;
        int size = 1;

        for(int d = 0; d < 4; d++){
            int nr = r + dr[d];
            int nc = c + dc[d];

            boolean reachButtomRow = (nr < 0);
            boolean reachTopRow = (nr >= N);
            boolean reachBottomCol = (nc < 0);
            boolean reachTopCol = (nc >= N);

            if(!reachButtomRow && !reachTopRow && !reachBottomCol && !reachTopCol){
                boolean isBuilt = (aptMap[nr][nc]==1);
                boolean notVisited = !visited[nr][nc];
                if(isBuilt && notVisited){
                    size += dfs(nr, nc);
                }
            }
        }
        return size;
    }
}
