package main.java.week4_dp.go_upstairs;

//계단은 한 번에 1) 한 계단씩 또는 2) 두 계단씩 오를 수 있다.
//연속된 세개의 계단을 모두 밟아서는 안된다, 시작점은 제외
//마지막 도착 계단은 반드시 밟아야 한다. 반드시 밟아야 하는 계단 : (n-1)또는 (n-2) 인덱스
//각 계단에 쓰여 있는 점수가 주어질 때 이게임에서 얻을 수 있는 총 점수의 최댓값(최적해)을 구하는 프로그램 작성
//입력 : 첫줄 - 계단의 개수 (n), 둘째 줄 ~ n번째 줄 - 아래에 놓인 계단부터 각 계단에 쓰여있는 점수 주어짐
// n <= 300, f(n) <= 10,000
//출력 : 계단 오르기 게임에서 얻을 수 있는 총 점수의 최댓값

/*
* 알고리즘 설계 :
* 반드시 밟아야 하는 계단 n-1 또는 n-2번 인덱스에 이를 수 있게 해야겠다
* 그렇다면 f(n) = f(n-1)+f(n-2) ? '또는'을 의미하는 연산은 덧셈이니까...?
* dp 배열을 만들어서 한 번 풀어볼까?
* 대신 시작점을 의미하는 n = 0은 기본케이스로 정의하고 시작
* */

import java.util.*;

public class Solution {
    static int result = 0;
    static long[] dp;
    static Scanner sc = new Scanner(System.in);
    static final int ONE = 1;
    public static void main(String[] args) {
        int n = sc.nextInt();
        System.out.println(fib(n));
    }

    static long fib(int n){
        dp = new long[n+1];
        dp[0] = 0;
        dp[1] = ONE;

        for (int i = 2; i <= n; i++) {
            //출력값은 fib(n)이 맞아...! 그런데 fib(n)은 fib(n-1)과fib(n-2)로 이루어져있어야 해!
            dp[i] = sc.nextInt();
        }
        for(int i = 2; i<n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }
}
