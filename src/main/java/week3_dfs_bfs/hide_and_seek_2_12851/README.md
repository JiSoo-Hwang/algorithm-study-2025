# 💡**문제에서 구해야 할 것**
문제 조건 :

- 시작점 N에서 목표점 K까지 이동
- 가능한 연산은 -1, +1, *2 (모두 1초)
- 입력값 : 수빈이의 위치 N과 동생의 위치 K
- 출력값 :
    - N에서  K까지 도착하는 데에 필요한 `최소` 시간 → 여기까진 ‘숨바꼭질1(1697)’과 동일
    - 그 최소 시간으로 도달하는 방법의 수 → 이 문제에서 추가로 구해야 하는 것

# 💡**알고리즘 설계**

Step 1 : 문제를 그래프로 볼 것

공간 = 0 ~ 100,000 사이의 모든 정수 위치

간선 = x -1, x +1, x*2 (모두 비용이 1로 동일함)

Step 2 : “최소 시간” → 최단 거리

- 모든 간선의 비용이 1이기 때문에 BFS(너비 우선 탐색)을 쓰면 처음 도달한 시점이 곧 최단 거리인 것이 보장됨
- 최소 시간 = BFS로 도달했을 때의 레벨(깊이 `depth`)

Step 3: “방법의 수” → 경로 개수 카운팅

- 같은 노드에 여러 최단 경로가 합쳐질 수 있으므로, 각 노드에
    - `distance[node]` = 최단 거리
    - `path[node]` = 그 최단 거리로 도달하는 경로 수

      를 기록한다


- 1697번 숨바꼭질1과의 차이점은 `int visited[]` 를 `int[] distance` 와 `int[] path` 배열로 나눠서 관리한다는 것


Step 4: `distance / path` 갱신 룰 만들기

- 어떤 노드(nextStep)에 대해 :
    - 처음 도달했을 때 (`distance[nextStep] == -1` )
        - BFS 특성상 이때 최단 거리가 확정됨
        - `distance[nextStep] = distance[current] + 1`
        - `path[nextStep] = path[current]` (current까지 오는 모든 경로를 그대로 가져오기)
    - 이미 도달했지만, 같은 최단 거리일 때 (`distance[nextStep] == distance[current] +1`)
        - 다른 경로를 통해 또 최단으로 도달한 것
        - `path[nextStep] += path[current]` (경로 수 누적)
    - 더 깊은 레벨로 들어간다면 무시

Step 5: `K`를 발견했을 때 종료하는 조건

BFS는 레벨 순으로 탐색을 하는 방식이기 때문에 `K` 를 처음 발견한 순간의 거리 = 최단 거리

하지만 같은 레벨인 큐에 남아있는 노드들도 `K` 로 이어질 수 있고, 모든 방법의 수를 찾아야 하기 때문에

- `minDistance` = 처음 K를 발견했을 때의 거리 기록
- 그 레벨에서는 끝까지 탐색하고
- 더 깊은 레벨로 들어가고자 할 때 (`distance > minDistance` ) 탐색 중단하고 반복문에서 벗어날 것

Step 6 : 답을 도출

- BFS 종료 후 출력할 것
    - `distance[K]` 최소시간
    - `path[K]` 최소시간으로 도달하는 경로의 수

# 💡코드

```
import java.io.*;
import java.util.*;

public class Solution_ans {
    static final int MAX = 100000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if(N >= K){ //N과 K가 같거나, N이 K보다 커서 계속 빼는 방법(한 가지)만 있는 경우
            System.out.println(N-K);
            System.out.println(1);
            return;
        }

        int[] distance = new int[MAX+1];
        int[] path = new int[MAX+1];
        Arrays.fill(distance, -1); // 아직 방문 안함을 '-1'로 초기화

        Deque<Integer> queue = new ArrayDeque<>();
        distance[N] = 0;
        path[N] = 1; // 시작점에 "도달"하는 방법은 1가지(아무 것도 안하기)
        queue.add(N);

        int minDistance = -1; // K를 처음 발견한 거리 (역시 아직 도착 안했으니 '-1'로 초기화)

        while(!queue.isEmpty()){
            int current = queue.poll();

            // K의 최단거리 레벨 "끝까지" 처리했고, 지금 뽑은 노드의 깊이가 그것보다 크면 빠져나가기
            if(minDistance != -1 && distance[current] > minDistance) break;

            int nextDistance = distance[current] + 1;
            int[] nextSteps = {current-1, current+1, current * 2};

            for(int nextStep : nextSteps ){

                if(nextStep < 0 || nextStep > MAX) continue; // 유효하지 않은 이동은 건너뛰기

                if(distance[nextStep] ==-1) { //처음 방문하는 경우
                    distance[nextStep] = nextDistance;
                    path[nextStep] = path[current];
                    queue.add(nextStep);
                } else if(distance[nextStep] == nextDistance){ //같은 레벨에 있을 때
                    path[nextStep] += path[current]; // 방법 추가
                }
            }

            if(current == K){
                minDistance = distance[current]; //여기서 break하면 안되고 여기서 가능한 경로를 모두 카운팅해야 함
            }
        }
        System.out.println(distance[K]);
        System.out.println(path[K]);
    }
}

```

# 💡시간복잡도

`O(V+E)`

`V` Vertex (정점, 노드) 개수

`E` Edge (간선, 연결선) 개수

# 💡틀린 이유 & 틀린 부분 수정

접근방식이 틀림

이런 테스트 코드로 찾아보려 했슨…

이걸로 “방법의 수”를 찾아낼 수는 없다

(단순히 `K` 에 도착할 때까지 탐색하는 동안 만들어진 엣지 수만큼 기호가 출력된다)

BFS 탐색의 원리를 아직 코드로 제대로 파악하지 못해서 발생한 문제ㅠㅠ

그리고 숨바꼭질 1 문제처럼 단순히 `visited` 로만 체크하고 끝낼 수 없다

`visited`는 거리 `distance` 로, 방법의 수(경로의 수)는 `path` 로 따로 계산해야 한다.

```
//숨바꼭질 1과 같은 조건들에 추가로 가장 빠른 시간으로 찾는 방법의 가지수 구하기
import java.util.*;
import java.io.*;
public class Solution {
    static int MAX = 100000;
    static int N;
    static int K;
    static Queue<Integer> queue;
    static int[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        queue = new LinkedList<>();
        visited = new int[MAX+1];

        queue.add(N);
        visited[N] = 1;
        ArrayList<String> symbol = new ArrayList<>();
        while(!queue.isEmpty()){

            int current = queue.poll();
            if(current == K){
                System.out.println(visited[current]-1);
                System.out.println(symbol);
                return;
            }

            if(current - 1 >= 0 && visited[current-1]==0){
                queue.add(current-1);
                visited[current-1] = visited[current]+1;
                symbol.add("-1");
            }

            if(current +1 <= MAX && visited[current+1]==0){
                queue.add(current+1);
                visited[current+1] = visited[current]+1;
                symbol.add("+1");
            }

            if(current *2 <= MAX && visited[current*2]==0){
                queue.add(current*2);
                visited[current*2] = visited[current]+1;
                symbol.add("*");
            }
        }
        //current에 이르기까지 사용할 수 있는 연산(순서 의미 있음)의 가지수 계산
        // 예) +1 -1 *2
        //    -1 +1 *2
        //    ...
        //    대신 곱셈은 좀 더 순서를 엄격하게 지켜야 할텐데...
    }
```

# 💡다른 풀이 or 기억할정보

1. BFS 기본기
- 모든 간선(이 문제에서는 `-1, +1, *2` 연산) 가중치가 같을 때 최단 거리를 구하는 방법
- `distance[nextStep]` 에 방문여부와 최단 시간 정보 얻기
- `distance[nextStep] == -1` → 아직 도달 못함
- `distance[nextStep]==값` → 최단 거리 확정

2. “최단 경로수”를 구하는 법
- `distance[]` 배열과는 별개로 `path[]` 배열로 해당 위치까지 도달하는 최단 경로의 개수를 기록한다
    - 처음 도달했을 땐 `path[nextStep] = path[current]`
    - 같은 레벨에 다시 도달한 것일 땐 `path[nextStep] += path[current]`

3. BFS의 종료 타이밍
- 목표 K를 처음 만났을 때 바로 종료하지 말고, 같은 레벨에 있는 노드들은 모두 탐색을 끝내야 “최단 경로 수”를 구할 수 있다
- `minDistance` 로 최단 거리 도달했을 때의 레벨을 기억해두고, 이보다 깊은 레벨에서는 탐색을 중단

4. 특수 케이스 `N >= K` (이런 특수 엣지 케이스를 생각해보는게 중요하다)
- 이 경우에는 `-1` 만 반복하는 게 최단 (그냥 K 자체거나, N이 더 크니까 계ㅔㅔㅔ속 빼는 것 밖에 없음)
- 연산 횟수는 `N-K` 가 될 것이고, 경로의 수는 하나 `1`

---

### ArrayDeque vs LinkedList (BFS 큐에서)

- 둘 다  `Queue` 인터페이스 구현체라 `offer` , `poll` 사용 가능


- LinkedList
    - 노드 객체(데이터 + 포인터) 연결 구조
    - 삽입/삭제 `O(1)`(노드 자체를 이미 참고하고 있을 때, 즉 노드 객체를 변수로 가지고 있는 경우)

      (아니라면 노드를 탐색해야 하기 때문에 `O(n)`)

    - 각 노드가 별도 객체 → 메모리 많이 잡아먹음, CPU의 캐시 활용도는 떨어짐


- ArrayDeque
    - 내부적으로 배열을 원형으로 돌려 씀 (양쪽 끝 삽입/삭제 가능)
    - 삽입 / 삭제 보통 `O(1)` 의 시간 복잡도 (배열이 꽉 차서 더 큰 새 배열을 만들어야 할 땐 `O(n)`
    - 메모리 연속적 → 캐시 친화적 → 성능 up 


- BFS처럼  `offer / poll` 만 반복하는 큐에서는 `ArrayDeque` 가 더 빠르고 가볍다.