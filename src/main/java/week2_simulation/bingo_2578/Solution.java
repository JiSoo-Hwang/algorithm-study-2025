package main.java.week2_simulation.bingo_2578;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] cs = new int[25]; //철수의 빙고판
        int[] mc = new int[25]; //사회자가 부르는 숫자 순서

        for(int i=0;i<25;i++){
            cs[i] = sc.nextInt();
        }

        // 루프 순서를 "호출 순서 → 보드 탐색"으로 바꿈
        for(int j=0;j<25;j++){
            mc[j] = sc.nextInt();
        }

        // 해당 숫자를 보드에서 찾아 "한 칸만" 마킹하고 즉시 break
        for(int k=0;k<25;k++){
            int called = mc[k];
            for(int l=0;l<25;l++){
                if(cs[l]==called){
                    cs[l] = -1; //마킹값을 -1로 (원래 숫자 1과 충돌 방지)
                    break;
                }
                if (isBingo(cs)){
                    System.out.println(l);
                    return;
                }
            }
        }
        sc.close();
    }

    // isBingo: '한 줄이라도'가 아니라 '3줄 이상 완성'이면 true
    public static boolean isBingo(int[] cs) {
        int lines = 0;

        // 가로 5줄
        for (int r = 0; r < 5; r++) {
            boolean ok = true;
            for (int c = 0; c < 5; c++) {
                if (cs[r * 5 + c] != -1) { ok = false; break; }
            }
            if (ok) lines++;
        }

        // 세로 5줄
        for (int c = 0; c < 5; c++) {
            boolean ok = true;
            for (int r = 0; r < 5; r++) {
                if (cs[r * 5 + c] != -1) { ok = false; break; }
            }
            if (ok) lines++;
        }

        // 대각선 2줄
        boolean okDiag1 = true; // 좌상→우하
        for (int i = 0; i < 5; i++) {
            if (cs[i * 5 + i] != -1) { okDiag1 = false; break; }
        }
        if (okDiag1) lines++;

        boolean okDiag2 = true; // 우상→좌하
        for (int i = 0; i < 5; i++) {
            if (cs[i * 5 + (4 - i)] != -1) { okDiag2 = false; break; }
        }
        if (okDiag2) lines++;

        return lines >= 3;
    }
}
