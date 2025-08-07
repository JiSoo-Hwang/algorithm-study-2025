package main.java.week1_bruteforce.eureka_theory;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase =  sc.nextInt();
        int[] results = new int[testCase];

        for(int i = 0; i < testCase; i++) {
            int flag =0;
            int num =  sc.nextInt();
            int index = 0;
            while(index*(index+1)<1000){
                index++;
            }
            int[] triNums = new int[index];
            for(int t=1; t<=index; t++){
                triNums[t-1] = t*(t+1)/2;
            }
            for(int j =0; j < triNums.length; j++) {
                for(int k =0; k < triNums.length; k++) {
                    for(int l =0; l < triNums.length; l++) {
                        if(triNums[j]+triNums[k]+triNums[l] == num) {
                            flag = 1;
                            break;
                        }
                    }
                }
            }
            results[i] = flag;
        }
        for(int num : results) {
            System.out.println(num);
        }
    }
}
