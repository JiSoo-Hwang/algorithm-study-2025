package main.java.week4_dp.quit_job2_15486;

import java.io.*;
import java.util.*;
public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] T = new int[N];
        int[] P = new int[N];
        int[] dp = new int[N+1];

        for(int i =0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        //역순으로 백트래킹 : skip은 그대로 두는 것, 선택한다면 dp[N-j]
        for(int j = N-1; j>=0; j--){
            int next = j + T[j];
            int take = next <= N?P[j]+dp[next]:0;
            int skip = dp[j+1];
            dp[j] = Math.max(take,skip);

        }
        System.out.println(dp[0]);
    }
}
