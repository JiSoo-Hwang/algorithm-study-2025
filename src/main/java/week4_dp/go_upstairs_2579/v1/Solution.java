package main.java.week4_dp.go_upstairs_2579.v1;

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
            //출력값은 fib(n)이 맞아...!
            //그런데 fib(n)은 fib(n-1)과fib(n-2)로 이루어져있어야 해!
            dp[i] = sc.nextInt();
        }
        for(int i = 2; i<n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }
}
