# 💡**문제에서 구해야 할 것**

구해야 할 것 : 2×n 크기의 직사각형을 1×2, 2×1 타일로 채우는 방법의 수

입력 : 첫째 줄에 n이 주어진다. (1 ≤ n ≤ 1,000)
출력 : 첫째 줄에 2×n 크기의 직사각형을 채우는 방법의 수를 10,007로 나눈 나머지를 출력한다.

# 💡**알고리즘 설계**

n = 1 방법 1
n = 2 방법 2
n = 3 방법 3
n = 4 방법 5
n ... 방법 (n-1)+(n-2) ->근데 이렇다는걸 어떻게 확신할 수 있지?

# 💡코드

```
import java.io.*;
public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        int MOD = 10007;

        if (n == 1) { System.out.println(1); return; }
        if (n == 2) { System.out.println(2); return; }

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
        }
        System.out.println(dp[n]);
    }
}
```

# 💡시간복잡도

`O(N)` → for문 하나

# 💡틀린 이유 & 틀린 부분 수정

시간초과, 메모리초과

1트 :

```
import java.io.*; 
import java.util.*; 
public class Main{ 
	
	public static void main(String[] args) throws IOException{ 
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		int n = Integer.parseInt(st.nextToken());
		long[] dp = new long[n+1];
	
		dp[1] = 1; dp[2] = 2;
	
			for(int i=3; i<=n; i++){ 
			dp[i] = dp[i-1]+dp[i-2]; 
			} 
	
		System.out.println(dp[n]%10007);
	
		}
	}
```

1. **n=1일 때 배열 범위 초과**
- `dp[2] = 2;` 를 항상 세팅해서, `n=1`이면 `dp` 길이가 2(인덱스 0~1)라 `dp[2]`에서 터짐
- 모듈로를 매 스텝마다 안 해서 오버플로
- 11726의 n 최댓값(=1000)에서 값이 `long` 범위를 훨씬 넘음.

  끝에만 `% 10007` 하면 이미 중간에 오버플로가 난 상태라 결과가 틀림.

  매 번 더할 때 모듈로 해야 함.