# 💡**문제에서 구해야 할 것**

문제 조건 : 수빈이가 동생을 찾을 수 있는 가장 빠른 시간

수빈이의 위치가 X라면 이동 수단(?)은 X-1, X+1, 2*X

# 💡**알고리즘 설계**

BFS 탐색을 할 때 `Queue` 사용

시작 위치를 queue에 제일 먼저 더하고, 수빈이의 “현재위치”를 의미할 `int current`에 할당

`int[] visited`로 탐색한 위치 기록해서 중복 방지

-1, +1, *2 연산 순서대로(작은 연산 순서대로) 유효한 위치(0이상, 최대값인 100,000 이하)고 아직 방문하지 않은 (인덱스 값이 0일 때) 위치라면 `int current`에 해당 연산을 실행, `visited[current]`(소요시간 의미)에 1 더하기

수빈이의 위치값이 동생과 같을 때 `visited[current]`-1 반환

# 💡코드

```
import java.io.*;
import java.util.*;
public class Solution {

    static int MAX = 100000;//N과 K의 최대 값에 따라 최대 범위 설정

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new LinkedList<>();//BFS를 위한 que
        int[] visited = new int[MAX+1];//방문 여부 체크를 위한 배열

        queue.add(N);
        visited[N]=1; //시작 위치 방문

        while (!queue.isEmpty()) {
            int current = queue.poll(); //
            if(current == K){
                System.out.println(visited[current]-1);
                return;
            }

            if(current -1 >= 0 && visited[current - 1]==0){
                visited[current-1] = visited[current] + 1;
                queue.add(current - 1);
            }

            if(current +1 <= MAX && visited[current + 1]==0){
                visited[current + 1] = visited[current] + 1;
                queue.add(current + 1);
            }

            if(current * 2 <= MAX && visited[current * 2]==0){
                visited[current * 2] = visited[current] + 1;
                queue.add(current * 2);
            }
        }
    }
}
```

# 💡시간복잡도

`O(V)` 

# 💡 틀린 이유 & 틀린 부분 수정

유효한 위치 조건 중 최대값 `MAX` 는 포함안함…

1 안 빼고`visited[current]` 출력시킴…