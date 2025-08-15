package main.java.week2_simulation.quit_job_14501;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int workDays = sc.nextInt();
        int[] T = new int[workDays];
        int[] P = new int[workDays];

        for(int i = 0; i < workDays; i++) {
            T[i] = sc.nextInt();
            P[i] = sc.nextInt();
        }

        int[] dp = new int[workDays+1];
        for(int j = workDays-1; j >= 0; j--){
            //마지막 날 인덱스(j)에 업무 소요일 추가를 한다면 T[j]값은 1이어야 한다
            int next = j + T[j];
            //상담 가능하면 '한다' 선택값, 불가능하면 0
            //dp[next]는 이 업무를 선택한 뒤에 도달하게 될 일차 슬롯
            // (예 : 2일차 때(1번 인덱스) + 5일 걸리는 업무를 선택해서 dp의 6번째 인덱스로 이동
            int take = next<=workDays?P[j]+dp[next]:0;
            //일을 하기를 선택하지 않았을 때의 누적 수익(직전 선택 소환)
            int skip = dp[j+1];

            //일을 하기를 선택했을 때와 선택하지 않았을 때 값 비교해서 큰 값 선택
            dp[j] = Math.max(take, skip);
        }

        System.out.println(dp[0]);
    }
}
