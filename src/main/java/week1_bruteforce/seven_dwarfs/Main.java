package main.java.week1_bruteforce.seven_dwarfs;

import java.util.Arrays;
import java.util.Scanner;
//문제 조건 정리:
//일곱 명의 난쟁이의 키의 합이 100
//아홉 난쟁이의 키가 주어짐
//아홉 개의 줄에 난쟁이들의 키가 주어짐, 키는 100을 넘지 않는 자연수
//아홉 난쟁이의 키는 모두 다름
//가능한 정답이 여러 가지인 경우에는 아무거나 출력한다
//난쟁이의 키를 오릅차순으로 출력한다

//풀이 정리:
//정수 배열1에 각 인덱스에 난쟁이들의 키를 담기
//0으로 초기화한 정수 sum에 배열1의 각 인덱스 값을 더하기
//sum에서 배열1의 두 인덱스 값을 뺀 결과가 100일 때
//정수 배열2에 앞서 얘기한 두 인덱스를 제외한 인덱스들을 담기
// 배열2를 오름차순 정렬 후 출력하기
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int [] inputArr = new int[9];
        int sum = 0;
        for(int i=0;i<inputArr.length;i++){
            inputArr[i] = sc.nextInt();
            sum += inputArr[i];
        }
        int [] dwarfArr = new int[9];
        for(int i=0;i<inputArr.length-1;i++){
            for(int j=i+1;j<inputArr.length;j++){
                if(sum-inputArr[i]-inputArr[j]==100){
                    for(int k=0;k<inputArr.length;k++){
                        if(k!=i &&  k!=j){
                            dwarfArr[k] = inputArr[k];
                        }
                    }
                    Arrays.sort(dwarfArr);
                    for (int n : dwarfArr) {
                        if (n != 0) {
                            System.out.println(n);
                        }
                    }
                    return;
                }
            }
        }
    }
}
