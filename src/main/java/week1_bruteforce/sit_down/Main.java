package main.java.week1_bruteforce.sit_down;
import java.util.Scanner;
import java.util.ArrayList;
//강한 족보의 패를 가진 사람이 이긴다. 두 참가자가 같은 족보의 패를 가졌다면 비긴다.
//패가 입력으로 주어졌을 때 이길 수 있는 확률 구하기
//두 패가 모두 같고, 패의 숫자가 높을 수록 강한 족보
//그 다음은 두 패를 더했을 때 일의 자리 수가 높을 수록 강한 족보

public class Main {

    public static void main(String[] args) {
        //case 1. 영학이가 뽑는 패의 모든 경우의 수를 구한다
        //case 2. 영학이가 이기는 경우의 수를 구한다
        //case 2를 case 1로 나눈다.
        //나눈 값을 %.3f\n%으로 출력한다.

        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt(); //영학이 카드1
        int b = sc.nextInt(); //영학이 카드2

        // 1) 카드 덱 구성(1~10 각 2장씩)
        ArrayList<Integer> deck = new ArrayList<>();
        for(int i=1;i<11;i++){
            deck.add(i);
            deck.add(i);
        }

        // 2-1) 영학이 카드 제거 (제거 함수 remove 따로 구성)
        removeOne(deck,a);
        removeOne(deck,b);

        // 2-2) 영학이 카드 제어 (제어 함수 따로 구성하지 않는 방식)
        //deck.remove(Integer.valueOf(a));
        //deck.remove(Integer.valueOf(b));

        // 3) 점수 계산 함수로 땡인 경우와 끗인 경우 점수 계산
        int yScore = score(a,b);

        // 영학이가 이길 경우의 수와 총 경우의 수 초기화
        int win = 0;
        int total = 0;

        // 4) 상대방이 카드를 뽑는 모든 조합 열거
        for(int j=0;j<deck.size()-1;j++){
            for(int k=j+1;k<deck.size();k++){
                int opp1 = deck.get(j);//상대방이 뽑은 카드 1
                int opp2 = deck.get(k);//상대방이 뽑은 카드 2
                int oppScore = score(opp1,opp2);//상대방이 뽑은 카드로 점수 계산

                if(yScore>oppScore){//영학이의 점수가 상대방보다 높은 경우
                    win++;//이기는 경우 더하기
                }

                total++;//이 루프의 모든 경우의 수 계산
            }
        }

        // 5) 비율 출력
        double result = (double)win/total;
        System.out.printf("%.3f%n",result);
    }

    // 카드 한 장 제거
    public static void removeOne(ArrayList<Integer>deck,int card){
        for(int i=0;i<deck.size();i++){
            if(deck.get(i)==card){
                deck.remove(i);
                return;
            }
        }
    }

    // 점수 : 땡은 100 + 랭크, 끗인 경우 끗수 반환
    public static int score(int a,int b){
        if(a==b){
            return 100+a;
        }else{
            return (a+b)%10;
        }
    }
}
