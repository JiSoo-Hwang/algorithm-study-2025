# 💡**문제에서 구해야 할 것**

문제 조건 : 1) 합이 100이 되는 2) 7명의 난쟁이의 신장을 3) 오름차순으로 출력해야 한다.

# 💡**알고리즘 설계**

```
1. 정수 배열1에 각 인덱스에 난쟁이들의 키를 담기
2. 0으로 초기화한 정수 sum에 배열1의 각 인덱스 값을 더하기
3. sum에서 배열1의 두 인덱스 값을 뺀 결과가 100일 때
4. 정수 배열2에 앞서 얘기한 두 인덱스를 제외한 인덱스들을 담기
5. 배열2를 오름차순 정렬 후 출력하기
```

# 💡코드

```
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
```

# 💡시간복잡도

| 단계 | 연산내용 | 시간복잡도                          |
| --- | --- |--------------------------------|
| 이중 반복문 | 두 난쟁이 제거 조합 탐색 | O(n²) (n = 9) → 36번 반복         |
| 배열 복사 및 정렬 | 두 명을 제외한 7명을 복사 후 정렬 | O(n) + O(n log n) ≈ O(n log n) |
| 전체 시간복잡도 | 이중 반복 내에서 정렬까지 포함 | O(n² * log n)                  |

# 💡틀린 이유 & 틀린 부분 수정

1트 실패 :

잘못된 접근 방식

일곱 난쟁이 신장의 합이 ‘정확히’ 100이 되어야 한다는 것을 간과하고

냅다 오름차순 정렬하고 앞에 7개만 출력시켰다.

```
import java.util.Arrays;
import java.util.Scanner;

int [] inputArr = new int [9];
        for (int i = 0; i < inputArr.length; i++) {
            inputArr[i] = sc.nextInt();
        }
        Arrays.sort(inputArr);

        int [] dwarfArr = new int [7];

        for (int i=0;i<dwarfArr.length;i++) {
            dwarfArr[i] = inputArr[i];
        }

        for(int num:dwarfArr){
            System.out.println(num);
        }
```

2트 실패 :

여전히 잘못된 접근 방식 (아무리 급해도 문제를 제대로 읽자…)

일곱 난쟁이 신장의 합이 **`‘100 미만’이 아니라 ‘정확히 100’`** 이어야 한다는 것을 간과하고 문제를 풀었다.

```
import java.util.Arrays;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        int[] inputArr = new int[9];
        int sum = 0;
        for(int i=0;i<inputArr.length;i++){
            sum+=inputArr[i];
        }
        int[] dwarfArr = new int[9];
        for(int i=0;i<inputArr.length;i++){
            for(int j=1;j<inputArr.length;j++){
                if(sum-inputArr[i]-inputArr[j]<100){
                    for(int k=0;k<dwarfArr.length;k++){
                        if(k!=i && k!=j){
                         dwarfArr[k]=inputArr[k];   
                        }
                    }
                Arrays.sort(dwarfArr);
                for(int n=0;n<dwarfArr.length;n++){
                    if(dwarfArr[n]!=0){
                        System.out.println(dwarfArr[n]);
                    }
                }
                return;
              }
           }
        }
        
    }
    
}
```

3트 실패 :

이중 반복문이 잘못되었다.

j는 1이 아닌 i+1부터 시작해야 “9명 중 2명을 고르는 조합 수”가 된다.

```
import java.util.Arrays;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[] inputArr = new int[9];
        int sum = 0;
        for(int i=0;i<inputArr.length;i++){
            sum+=inputArr[i];
        }
        int[] dwarfArr = new int[9];
        for(int i=0; i<inputArr.length-1;i++){
            for(int j=1;j<inputArr.length;j++){
                if(sum-inputArr[i]-inputArr[j]==100){
                    for(int k=0;k<inputArr.length;k++){
                        if(k!=i && k!=j){
                            dwarfArr[k]=inputArr[k];
                        }
                    }
                    Arrays.sort(dwarfArr);
                    for(int n=0;n<dwarfArr.length;n++){
                        if(dwarfArr[n]!=0){
                            System.out.println(dwarfArr[n]);
                        }
                    }
                    return;
                }
            }
        }
    }
}
```

# 💡다른 풀이 or 기억할정보

배열 복사는 하되, 굳이 크기가 9인 배열을 두개를 만들어서, 출력할 때 0인지 검증하는 절차를 추가하지 않아도 되었다.

```
//(중략)
int[] answer = new int[7];
                    int index = 0;
                    for (int k = 0; k < 9; k++) {
                        if (k != i && k != j) {
                            answer[index++] = inputArr[k];
                        }
                    }
                    Arrays.sort(answer);
                    for (int a : answer) {
                        System.out.println(a);
                    }
```