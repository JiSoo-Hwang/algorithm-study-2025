package main.java.week1_bruteforce.decomposition_sum;

import java.util.Scanner;
//256 = 245 + 2 + 4 + 5 245는 256의 생성자
//216 = 198 + 1 + 9 + 8 198은 216의 생성자
//input = outcome + ..+..+.. outcome은 input의 생성자
//어떤 자연수 N이 있을 때, 그 자연수 N의 분해합은 N과 N을 이루는 각 자리수의 합을 의미한다.
//어떤 자연수 M의 분해합이 N인 경우, M을 N의 생성자라 한다.
//어떤 자연수의 경우에는 생성자가 없을 수도 있다. 생성자가 없는 경우는 언제일까? 한 자리수
//반대로 생성자가 여러 개인 자연수도 있을 수 있다.

public class DecompositionSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        int result = 0;

        for(int i =1;i<input;i++){
            int sum = i;
            int temp = i;

            while(temp>0){
                sum+=temp%10;
                temp/=10;
            }

            if(sum==input){
                result = i;
                break;
            }
        }
        System.out.println(result);
    }
}
