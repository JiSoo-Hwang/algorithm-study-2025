# 💡**문제에서 구해야 할 것**

문제 조건 :

- 방향 없는 그래프
- 구해야 하는 것 `연결 요소의 개수`
- 조건
    - 정점의 개수 N (1≤N≤1000)
    - 간선의 개수 M (0≤M≤N(N-1)/)
    - 둘째 줄부터 M개의 줄에 간선의 양 끝점 u와 v가 주어진다
    - (1 ≤ u, v ≤ N, u는 v와 다른 수)
    - 같은 간선은 한 번만 주어진다


# 💡**알고리즘 설계**

탐색한 노드 `visited[]`  라는 boolean 배열을 두어, 방문한 노드를 `true`로 표시한다

아직 방문하지 않은 노드를 만나면 DFS 시작:

- DFS는 현재 노드에서 인접한 노드를 따라가며, 방문하지 않은 노드를 재귀적으로 방문한다.
- 방문한 노드는 `visited[next] = true` 로 마킹한다.

DFS를 한 번 실행할 때마다 하나의 연결 요소를 모두 방문하게 되므로 count++;

# 💡코드

```
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

```

# 💡시간복잡도

- 점점 방문 → `O(V)`
- 간선 확인 → `O(E)`
- 시간 복잡도 = `O(V+E)`

  (V: 정점 수, E: 간선 수)


# 💡틀린 이유 & 틀린 부분 수정

DFS와 BFS… 개념을 이해한 것 같으면서도 코드로 구현하려니 아직 많이 어려운 것 같습니다…

연습만이 살 길…⭐️

# 💡다른 풀이 or 기억할정보

`BFS` 방식으로도 한 번 풀어볼 것!