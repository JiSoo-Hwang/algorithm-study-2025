# 💡**문제에서 구해야 할 것**

문제 조건 : “연결된 집”이란 상,하,좌,우로 다른 집이 있는 경우를 말한다 (대각선은 포함 ❌)

입력값 : 지도의 크기 (N*N) 5≤N≤25, N줄에는 N개의 자료가 입력됨

출력 : 0) 총 단지수 1) 각 단지에 속하는 “집의 수”, 2) 오름차순으로 정렬하여 출력

# 💡**알고리즘 설계**

어제 풀었던 1012번 문제 참고해서 풀이…

4방향 벡터 활용해서 탐색하기

# 💡코드

```
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
```

# 💡시간복잡도

DFS 탐색 자체는 `O(N^2)`, 정렬 포함하면 최악의 경우 `O(N^2 logN)`

# 💡틀린 이유 & 틀린 부분 수정

ex) 접근방식이 틀림, 시간초과, 메모리초과

어제 1012번 풀이를 많이 참고해서 풀었지만, 문제는 DFS가 방문표시만 하고 크기를 반환하거나 누적하고 있지 않아서 ‘**단지 수**’만 세고 있음. 단지 시작 지점마다 크기를 세서 리스트에 담은 뒤에 정렬 및 출력해야 함

```
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
		        //띄어쓰기 없이 한 줄 형식 입력이라 문자로 파싱해야 함
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                aptMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int complex = 0; //단지수
        for(int r = 0; r < N; r++){
           for(int c = 0; c < N; c++){
               boolean isBuilt = (aptMap[r][c]==1);
               boolean notVisited = (!visited[r][c]);
               if(isBuilt && notVisited){
                   complex++;
                    dfs(r, c);
               }
           }
        }
        out.append(complex).append("\n");
    }

    static void dfs(int r, int c){
        visited[r][c] = true;

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
                    dfs(nr, nc);
                }
            }

        }
    }
}
```

# 💡다른 풀이 or 기억할정보

`string.charAt(j) - '0';`  - 문자로 된 숫자를 정수로 바꿔주는 방식