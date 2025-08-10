package main.java.week1_bruteforce.sit_down;
import java.util.Scanner;
//강한 족보의 패를 가진 사람이 이긴다. 두 참가자가 같은 족보의 패를 가졌다면 비긴다.
//패가 입력으로 주어졌을 때 이길 수 있는 확률 구하기
//두 패가 모두 같고, 패의 숫자가 높을 수록 강한 족보
//그 다음은 두 패를 더했을 때 일의 자리 수가 높을 수록 강한 족보

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input1 = sc.nextInt();
        int input2 = sc.nextInt();
        int win = 0;
        int lose = 0;
        for(int i = 0; i < 20; i++) {
            for(int j = 1; j < 19; j++) {
                if(i==j){//상대방이 땡일 때
                   if(input1==input2){//영학이도 땡일 때
                       if(i<input1){//영학이의 숫자가 상대방보다 클 때
                           win++;
                       }else{
                           lose++;
                       }
                   }
                }else if(i+j-10==0){//상대방이 0끗일 때
                    if(input1+input2-10!=0){//영학이는 0끗이 아닐 때
                        win++;
                    }else{
                        lose++;
                    }
                }else{//상대방이 n끗이고
                    // 영학이의 끗수가 상대방보다 높을 때
                    if((input1+input2-10)>(i+j-10)) {
                        win++;
                    }else{
                        lose++;
                    }
                }
            }
        }
        double result = (double) win /(win+lose);
        System.out.printf("%.3f%n", result);
    }
}
