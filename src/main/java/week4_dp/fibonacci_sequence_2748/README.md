# 💡**문제에서 구해야 할 것**

문제 조건 :  0번째 피보나치수는 0, 1번째 피보나치수는 1, 그 다음 2번째 부터는 바로 앞 두 피보나치 수 (`f(n-1)`, `f(n-2)`)의 합이 된다.

구해야 할 것 :

입력 : n

출력: n 번째 피보나치 수

# 💡**알고리즘 설계**

0번째 피보나치수와 1번째 피보나치수 기본 케이스가 주어졌고,

그 다음 2번째부터는 바로 앞 두 피보나치 수의 합 f(n) = f(n-1) + f(2);

단, 순수 재귀 방식으로 작성하면 시간 초과가 되기 때문에, Bottom-up 방식 + 큰 수 `long` 배열로 피보나치수를 반환하는 함수 만들 것

# 💡코드

```
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(fib(n));
    }

    static long fib(int n){

        long[] dp  = new long[n+1];

        dp[0] = 0;
        dp[1] = 1;

        if(n<=1) {
            return n;
        }else{
            for(int i = 2; i <= n; i++){
                dp[i] = dp[i-1] + dp[i-2];
            }
        }

        return dp[n];
    }
}
```

# 💡시간복잡도

`O(n)` → 반복문 실행

# 💡틀린 이유 & 틀린 부분 수정

1트는 시간초과, 2트는 메모리초과

(드디어 좀 덜 부끄러운 오답을 내고 있다ㅠㅠ…)

1트 (시간 초과)

```
public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(fib(n));
    }

    static int fib(int n){

        int result;
        if(n<=1) {
            result = n;
        }else{
            result = fib(n-1)+fib(n-2); //순수 재귀 , 중복 계산되는 부분에 대한 메모이제이션이 없음
        }

        return result;
    }
}
```

2트 (메모리 초과)

```
public class Solution{

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(fib(n));
    }
    
    static int fib(int n){
        
        if(n<=1){
            return n;
        }
        
        int[] dp = new int[n+1]; // int의 최대값보다 더 큰 피보나치 수도 허용하는 자료형 long 사용할 것
        
        dp[0] = 0;
        dp[1] = 1;
        
        for(int i = 2; i <= n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
}
```