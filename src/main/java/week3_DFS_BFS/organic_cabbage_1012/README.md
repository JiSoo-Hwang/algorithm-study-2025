# 💡**문제에서 구해야 할 것**

구해야 할 걸 : 각 테스트 케이스에 대해 필요한 최소의 배추흰지렁이 마리 수

문제 조건 :

- 한 배추에 “인접한” 배추란, 상,하,좌,우에 위치한 배추를 의미함
- 서로 인접해있는 배추들이 몇 군데에 퍼져있는지 조사하면 총 몇 마리의 지렁이가 필요한지 알 수 있음

# 💡**알고리즘 설계**

`T` 는 테스트케이스마다 별도의 그래프(격자), 서로 독립됨

가로 `M` 세로 `N` 은 ‘격자 크기’

아직 방문하지 않은 배추 칸을 발견해서 dfs(또는 bfs)를 새로 시작할 때 지렁이 갯수 카운트하기

dfs : 상하좌우 4방향으로 확장하면서 같은 덩어리의 배추를 전부 방문처리한다.

# 💡코드

```
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

```

# 💡시간복잡도

`O(N*M)` 각 칸을 최대 한 번 씩 방문

# 💡틀린 이유 & 틀린 부분 수정

아직 dfs를 코드로 구현하는 것에 익숙하지 않아서 그런지 배열 크기 오류 나고 그래서 고쳐갔습니다…

# 💡다른 풀이 or 기억할정보

- 좌표 주의: 입력이 보통`(x, y)`로**x=열(col), y=행(row)**. 배열 접근은`field[y][x]`.
- 4방향 벡터:`dr = {-1,1,0,0}`,`dc = {0,0,-1,1}`