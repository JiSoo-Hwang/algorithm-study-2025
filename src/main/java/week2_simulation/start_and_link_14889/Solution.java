package main.java.week2_simulation.start_and_link_14889;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] S = new int[N][N];

        int[][] pair = new int[N][N];

        for(int i = 0; i<N; i++){

            for(int j = 0; j<N; j++){

                S[i][j] = sc.nextInt();

                if(i==j){

                    S[i][j] = 0;

                    continue;

                }

                pair[i][j] = S[i][j] + S[j][i];

            //pair의 각 인덱스에 S(i,j)와 S(j,i)의 합이 담겨져있음

            }

        }
    }
}
