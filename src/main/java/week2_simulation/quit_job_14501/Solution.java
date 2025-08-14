package main.java.week2_simulation.quit_job_14501;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int days = sc.nextInt();
        int[] counselTimeArr = new int[days];
        int[] pricesArr = new int[days];

        for(int i = 0; i < days; i++) {
            counselTimeArr[i] = sc.nextInt();
            pricesArr[i] = sc.nextInt();
        }

        //int profit = 0;//이익은 숫자 하나로는 안됨 (dp 배열 (크기 days + 1)) 생성
        int[] dp = new int[days+1];

        for(int j = days-1; j>=0 ;j-- ) {
            int next = j + counselTimeArr[j];

            int take=(next<=days)?pricesArr[j]+dp[next]:0;

            int skip = dp[j+1];

            dp[j] = Math.max(take, skip);
        }

        System.out.println(dp[0]);
    }
}
