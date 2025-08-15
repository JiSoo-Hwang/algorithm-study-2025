# 💡**문제에서 구해야 할 것**

문제 조건 :

i번 사람과(세로) j번 사람이 같은 팀에 속했을 때, 팀에 더해지는 능력치는 S(i,j)와 S(j,i)의 합이다.

출력 값은 스타트 팀과 링크 팀의 능력치 차이의 최솟값

# 💡**알고리즘 설계**

일단… 정리해보면

### 1트

0,1,2,3…,N

N+1, N+2,…,2N

2N+1,2N+2,2N+3…,3N

…

(N-1)N+1,…,N^2 개의 인덱스

조합은 총 N(N-1)개 → `이렇게까진 고려할 필요 없을거 같음...`

---

### 2트

Hㅏ… 이거 진짜 어떻게 풀지ㅠㅠㅠㅠ

# 💡코드

```
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] S = new int[N][N];

        int[][] pair = new int[N][N];

        for(int i = 0; i<N; i++){

            for(int j = 0; j<N; j++){

                S[i][j] = sc.nextInt();

                if(i==j){

                    S[i][j] = 0;

                    continue;

                }

                pair[i][j] = S[i][j] + S[j][i];

            //pair의 각 인덱스에 S(i,j)와 S(j,i)의 합이 담겨져있음 

            }

        }
    }
}
```

# 💡시간복잡도

O(N) 2중 for문

# 💡틀린 이유 & 틀린 부분 수정

접근방식이 아예 틀린건 아닌거 같지만… 막혔습니다….