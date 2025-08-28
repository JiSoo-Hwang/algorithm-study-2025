# 💡**문제에서 구해야 할 것**

문제 조건 :

`X%3==0` 이라면 `X/=3` , `X%2==0` 이라면 `X/=2`, 그 외에는 `X-=1`

(`/3` ,  `/2` , `-1` ) 세 연산을 사용해서 1을 만드는 횟수의 최솟값 출력하기

# 💡**알고리즘 설계**

이전 상태 최소값 + 1의 점화식

`-1` 연산 : `dp[n]=dp[n-1]+1`

`-2` 연산 : `if(n%2==0) dp[n] = Math.min(dp[n], dp[n/2]+1)`

`-3` 연산 : `if(n%3==0) dp[n] = Math.min(dp[n], dp[n/3]+1`

# 💡코드

```
import java.util.Scanner;

public class Solution{
	public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
	        int N = sc.nextInt();
	
	        int[] dp = new int[N + 1];
	        dp[1] = 0; // base case
	
	        for (int i = 2; i <= N; i++) {
	            dp[i] = dp[i - 1] + 1;
	            if (i % 2 == 0) dp[i] = Math.min(dp[i], dp[i / 2] + 1);
	            if (i % 3 == 0) dp[i] = Math.min(dp[i], dp[i / 3] + 1);
	        }
	
	        System.out.println(dp[N]);
	    }
}
```

# 💡시간복잡도

`O(N)` → for 문 1번