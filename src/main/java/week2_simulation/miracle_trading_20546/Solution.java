package main.java.week2_simulation.miracle_trading_20546;
import java.util.Scanner;

//BNP는 가지고 있는 현금에서 가장 많이 살 수 있는 수량을 주문하는 것
//TIMING은 전량 매수 || 전량 매도로만 이루어지되, 3일 연속 가격이 전일 대비 상승하면 다음날 무조건 가격이 하락한다고 가정한다.
//따라서 현재 소유한 주식의 가격이 3일째 상승한다면 전량 매도한다. (전일 대비 변동이 없으면 상승으로 간주 안함)
//반대로 가격이 전일 대비 하락하는 주식은 다음날 무조건 상승한다고 가정한다.
//준현이와 성민이의 2021년 1월 1일부터 2021년 1월 14일까지의 수익률 겨루기
//준현이의 자산이 더 크다면 "BNP", 성민이의 자산이 더 크다면 "TIMING"출력
//둘의 자산이 같다면 "SAMESAME" 출력

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cash = sc.nextInt();

        int bnpBalance = cash;
        int timingBalance = cash;

        int bnpShares = 0;
        int timingShares = 0;

        int[] prices = new int[14];

        for(int i = 0; i < 14; i++){
            prices[i] = sc.nextInt();
            if(prices[i]>0){
                int shares = bnpBalance / prices[i];
                bnpShares += shares;
                bnpBalance %= prices[i];
            }

            if(i>=2){
                boolean up3 = prices[i-2] < prices[i-1] && prices[i-1] < prices[i];
                boolean down3 = prices[i-2] > prices[i-1] && prices[i-1] > prices[i];

                if(down3 && prices[i]>0){
                   int shares = timingBalance / prices[i];
                   timingShares += shares;
                   timingBalance %= prices[i];
                }else if(up3 && prices[i]>0){
                    timingBalance += timingShares * prices[i];
                    timingShares = 0;
                }
            }
        }

        int bnpResult = bnpBalance + bnpShares * prices[13];
        int timingResult = timingBalance + timingShares * prices[13];

        if(bnpResult > timingResult){
            System.out.println("BNP");
        }else if(bnpResult < timingResult){
            System.out.println("TIMING");
        }else{
            System.out.println("SAMESAME");
        }

    }
}
