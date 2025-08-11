package main.java.week2_simulation.ball_1547;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int swap = sc.nextInt();
        int[] arr = new int [3];
        arr[0]=1;
        for(int m = 0; m<swap; m++){
            int x= sc.nextInt();
            int y= sc.nextInt();
            int temp = arr[x-1];
            arr[x-1] = arr[y-1];
            arr[y-1] = temp;
        }

        for(int k =0; k<arr.length;k++){
            if(arr[k]==1){
                System.out.println(k+1);
                break;
            }
        }
    }
}
