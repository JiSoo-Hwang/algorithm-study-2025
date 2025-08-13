package main.java.week2_simulation.bingo_2578;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] cs = new int[25];
        int[] mc = new int[25];
        for(int i=0;i<25;i++){
            int csInput = sc.nextInt();
            cs[i] = csInput;
        }
        for(int j=0;j<25;j++){
            int mcInput = sc.nextInt();
            mc[j] = mcInput;
        }



        sc.close();
    }

    public static boolean getHorizontalBingo(){
        for(int i=0;i<25;i++){
            for(int j=0;j<25;j++){
                
            }
        }
        return true;
    }

    public static boolean getVerticalBingo(){
        return true;
    }

    public static boolean getDiagonalBingo(){
        return true;
    }
}
