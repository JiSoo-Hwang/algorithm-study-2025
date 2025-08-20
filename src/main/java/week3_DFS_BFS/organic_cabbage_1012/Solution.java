package main.java.week3_DFS_BFS.organic_cabbage_1012;
import java.io.*;
import java.util.*;

//어떤 배추에 배추흰지렁이가 한 마리라도 살고 있으면, 인접(상하좌우)한 다른 배추도 보호받을 수 있음
//서로 인접해있는 배추들이 몇 군데에 퍼져있는지 조사하면 총 몇 마리의 지렁이가 필요한지 알 수 있음
//구해야 할 것 : 각 테스트 케이스에 대해 필요한 최소의 배추흰지렁이 마리 수
//입력 : 첫 줄 - 테스트 케이스 수 T
//      다음 줄 - 배추를 심은 배추밭의 가로길이 M, 세로길이 N, 배추의 위치 개수 K
//      K줄 - 배추의 위치 X(0<=X<=M-1), Y(0<=Y<=N-1)
//배추의 위치는 겹치지 않음
//출력 : 각 테스트 케이스에 대해 필요한 최소의 배추 흰지렁이 마리수
//알고리즘 설계 : T는 그래프의 개수? 각각 독립되어야 함
//             가로 길이는... 최장 너비? 세로 길이는 계층 수, 즉 최장 깊이?
//언제 흰지렁이 카운트를 해야 할까?
//각 입력 값을 DFS 기법으로 탐색해서(...?)

public class Solution {
    static int M, N, K;
    static int[][] farm;
    static boolean[][] visited;
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int test = 0; test < T; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(st.nextToken()); //가로 (columns)
            N = Integer.parseInt(st.nextToken()); //세로 (rows)
            K = Integer.parseInt(st.nextToken()); //배추의 위치 개수

            farm = new int[N][M];
            visited = new boolean[N][M];


            for(int k = 0; k < K; k++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()); //가로 (columns)
                int y = Integer.parseInt(st.nextToken()); //세로 (rows)
                farm[y][x] = 1;
            }

            int worms = 0;
            for(int r = 0; r < N; r++){
                for(int c = 0; c < M; c++){
                    boolean hasCabbage = (farm[r][c] == 1);
                    boolean notVisited = (!visited[r][c]);
                    if(hasCabbage && notVisited){
                        worms++;
                        dfs(r, c);
                    }
                }
            }
            out.append(worms).append("\n");
        }
        System.out.println(out);
    }

    static void dfs(int r, int c){
        visited[r][c] = true;

        for(int d = 0; d < 4; d++){
            int nr = r + dr[d];
            int nc = c + dc[d];

            boolean reachButtomRow = (nr < 0);
            boolean reachTopRow = (nr >= N);
            boolean reachButtomCol = (nc < 0);
            boolean reachTopCol = (nc >= M);

            if(!reachButtomRow && !reachTopRow && !reachButtomCol && !reachTopCol){
                boolean notVisted = !visited[nr][nc];
                boolean hasCabbage = (farm[nr][nc] == 1);
                if(notVisted && hasCabbage){
                    dfs(nr, nc);
                }
            }
        }
    }
}
