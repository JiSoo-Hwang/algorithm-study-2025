package main.java.week4_dp.fibonacci_sequence_2748;
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
