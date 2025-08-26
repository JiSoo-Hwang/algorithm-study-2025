# 💡**문제에서 구해야 할 것**

문제 조건 :

- 계단은 한 번에 1) 1계단씩 OR 2) 2계단씩 오를 수 있다.
- 연속된 3개의 계단을 모두 밟아서는 안된다.
- 시작점은 제외
- 마지막 도착 계단은 반드시 밟아야 한다.
- 입력 : 첫줄 - 계단의 개수 (`n`) 둘째 줄~ n 번째 줄 - 아래에 놓은 계단부터 각 계단에 쓰여있는 점

  (`n <= 300, stair(n) <= 10,000` )

- 출력 : 계단 오르기 게임에서 얻을 수 있는 총 점수의 최댓값

# 💡**알고리즘 설계**

반드시 밟아야 하는 계단 n-1 또는 n-2번 인덱스에 이를 수 있게 해야겠다…

그렇다면 f(n) = f(n-1) + f(n-2)? (`미래의 내가 좀 더 생각을 열어보라고 아우성치는 소리`)

그런데 이렇게만 정리하면 “연속된 3개의 계단을 밟을 수 없다”라는 조건을 충족할 수 있을까? (`미래의 내가 거기서 좀 더 생각해보라고 아우성치는 소리`)

dp배열을 만들어서 풀어야 하고, 0번 인덱스는 기본케이스로 정의하고 시작해야지(`그냥 1번 인덱스부터 계산 시키면 될지도…`)

# 💡코드

```
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int[] stair = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            stair[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[n + 1];

        if (n >= 1) dp[1] = stair[1];
        if (n >= 2) dp[2] = stair[1] + stair[2];
        if (n >= 3) dp[3] = Math.max(stair[1] + stair[3], stair[2] + stair[3]);

        for (int i = 4; i <= n; i++) {
            dp[i] = Math.max(dp[i - 2], dp[i - 3] + stair[i - 1]) + stair[i];
        }

        System.out.println(dp[n]);
    }
}
```

# 💡시간복잡도

`O(n)` → 각 계단을 한 번씩만 처리하며, 이전 2~3 단계의 값만 참조하므로 선형 시간

# 💡틀린 이유 & 틀린 부분 수정

접근방식이 틀렸다. 피보나치 수열에서 벗어나지를 못했다ㅠ

“출력값은 dp[n]이 될텐데… dp[n-1]과 dp[n-2]으로 이루어져있어야 해~” (`아니다`)

좀 더 생각을 열어두고 문제를 풀도록 하자…😇