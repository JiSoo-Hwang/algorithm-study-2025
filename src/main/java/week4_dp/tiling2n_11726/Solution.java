package main.java.week4_DP.tiling2n_11726;
/*
* 2×n 크기의 직사각형을 1×2, 2×1 타일로 채우는 방법의 수를 구하는 프로그램을 작성하시오.
* 아래 그림은 2×5 크기의 직사각형을 채운 한 가지 방법의 예이다.
* 입력 : 첫째 줄에 n이 주어진다. (1 ≤ n ≤ 1,000)
* 출력 : 첫째 줄에 2×n 크기의 직사각형을 채우는 방법의 수를 10,007로 나눈 나머지를 출력한다.
* */
/*
* n = 1 방법 1
* n = 2 방법 2
* n = 3 방법 3
* n = 4 방법 5
* n ... 방법 (n-1)+(n-2) ->근데 이렇다는걸 어떻게 확신할 수 있지?
* */
import java.io.*;
import java.util.*;
public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[] dp  = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++){
            dp[i] = dp[i-1]+dp[i-2];
        }

        System.out.println(dp[n]%10007);
    }
}
