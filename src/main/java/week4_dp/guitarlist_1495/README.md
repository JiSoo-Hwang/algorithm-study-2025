# 💡**문제에서 구해야 할 것**

N개의 곡을 순서대로 연주할 때, 시작 볼륨`S`에서 매 곡마다 정해진 변화량`V[i]`만큼**증가 또는 감소**시킬 수 있다. 모든 순간의 볼륨은`0..M`범위를 벗어나면 안 된다.

N곡을 모두 연주한**이후 가능한 볼륨들 중 최댓값**을 구하라. (불가능하면`-1`)

문제 조건 :

- 입력:`N S M`(1 ≤ N ≤ 100, 0 ≤ S ≤ M ≤ 1000), 다음 줄에`V[1..N]`
- 매 곡마다 현재 볼륨`x`에서`x+V[i]`또는`x-V[i]`로 이동 가능(범위 밖은 불가)
- 목표: i=N 이후 가능한 볼륨의 최댓값 (없으면 -1)

# 💡**알고리즘 설계**

- 상태 정의:`dp[i][v] ∈ {false, true}`
    - i: 0..N (연주한 곡의 개수)
    - v: 0..M (현재 볼륨)
- 시작값:`dp[0][S] = true`
- 전이: i에서 i+1로
    - `dp[i][v] == true`이면
        - `nv1 = v + V[i+1]`가`0..M`이면`dp[i+1][nv1] = true`
        - `nv2 = v - V[i+1]`가`0..M`이면`dp[i+1][nv2] = true`
- 정답:`max { v | dp[N][v] == true }`(없으면 -1)

# 💡코드

```
import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] V = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) V[i] = Integer.parseInt(st.nextToken());

        // dpCurr[v] : i곡 연주 후 볼륨 v 가능 여부
        boolean[] dpCurr = new boolean[M + 1];
        dpCurr[S] = true; // 초기 상태

        for (int i = 1; i <= N; i++) {
            boolean[] dpNext = new boolean[M + 1];
            for (int v = 0; v <= M; v++) {
                if (!dpCurr[v]) continue;

                int up = v + V[i];
                if (up <= M) dpNext[up] = true;

                int down = v - V[i];
                if (down >= 0) dpNext[down] = true;
            }
            dpCurr = dpNext; // 롤링
        }

        int ans = -1;
        for (int v = M; v >= 0; v--) {
            if (dpCurr[v]) { ans = v; break; }
        }
        System.out.println(ans);
    }
}
```

# 💡시간복잡도

- 시간: 각 단계에서 가능한 볼륨들을 확인(최대 M+1개) →**`O(N·M)`**
- 공간: 1차원 불리언 2개(롤링) →**`O(M)`**

  (2차원 풀로 만들면 `O(N·M)`)

# 💡다른 풀이 or 기억할정보

- **BitSet 최적화(Java)**

  `BitSet cur, next`를 쓰면 한 단계 전이를 비트 연산으로 빠르게 할 수 있다.

  예:`next = (cur << V[i]) OR (cur >>> V[i])`와 유사한 느낌(범위 체크는 필요). 실전 Java에선 직접 shift는 어렵지만,`BitSet`의`get(from, to)`/`shift`유틸을 작성해 응용 가능.

- **BFS 관점**

  상태를`(i, v)`로 보고 레벨이 곡 인덱스 i인**층별 BFS**로 생각해도 동일. 각 레벨에서 가능한 v들을 큐/집합으로 관리하면 DP와 같은 결과.