package main.java.week4_DP.quit_job2_15486;
/*
* 오늘부터 N+1일째 되는 날 퇴사
* 하루에 하나씩 서로 다른 사람의 상담 배정됨
* T = 상담을 완료하는데 걸리는 시간
* P = 상담을 했을 때 받을 수 있는 금액
* 상담을 하는데 필요한 기간은 1일보다 클 수 있기 때문에 모든 상담을 할 수는 없다.
* N+1일째에는 회사에 없기 때문에 근무일수를 초과하는 일정의 상담은 받을 수 없다.
* 상담을 적절히 했을 때, 백준이가 얻을 수 있는 최대 수익을 구하는 프로그램
* 시간 제한 : 2초, 메모리 제한 512
* 1<=N<=1500000
* 1<=T<=50 1<=P<=1000
* 입력 : N, N개의 줄에 T와 P가 공백으로 구분되어서 주어지며, 1일부터 N일까지 순서대로 주어진다.
* */
/*
* 알고리즘 설계 : N+1 크기의 dp배열에서 N번째 배열에서 T는 1만 허용된다.
* N-i>=T 선택할 수 있는 부분, 이것과 선택하지 않았을 때의 가격 비교 -> 큰 것 취하고 저장하기 price +1
* N-i<T인 경우에는 continue;
* dp[0] 반환
* */
import java.io.*;
import java.util.*;
public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] T = new int[N+1];
        int[] P = new int[N+1];
        int[] dp = new int[N+1];

        for(int i =1; i<=N; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine().trim());
            T[i] = Integer.parseInt(st1.nextToken());
            P[i] = Integer.parseInt(st1.nextToken());
        }

        for(int j = N; j>=1; j--){
            int skip = dp[N+1-j];
            if(N+1-j>=T[j]){

            }
        }
    }
}
