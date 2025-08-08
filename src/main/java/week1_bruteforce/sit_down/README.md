# 💡**문제에서 구해야 할 것**

문제 조건 :

영학이의 패가 입력으로 주어졌을 때 이길 수 있는 확률

영학이가 이기는 경우의 수/모든 경우의 수

출력값은 0.000 형식이 되어야 함

# 💡**알고리즘 설계**

1. 모든 경우의 수 구하기
2. 영학이가 이기는 경우의 수 구하기
    1. 상대방이 땡인 경우
        1. 영학이도 땡인 경우
            1. 영학이의 숫자가 상대방보다 클 때
    2. 상대방이 끗인 경우
        1. 영학이는 땡인 경우
        2. 영학이도 끗인 경우
            1. 영학이의 끗숫자가 상대방보다 클 때
3. 2단계에서 구한 값을 1단계에서 구한 값으로 나눈다.
4. 결과값을 printf(“%.3f%n”,result)로 구한다.

# 💡코드

```java
//내가 풀어내지 못한... 모범 답안
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int y1 = sc.nextInt();
        int y2 = sc.nextInt();
        int[] cards = new int[11]; // 1~10, 각 2장
        for (int i = 1; i <= 10; i++) cards[i] = 2;

        // 영학이의 카드 제거
        cards[y1]--;
        cards[y2]--;

        int win = 0;
        int total = 0;

        for (int i = 1; i <= 10; i++) {
            for (int j = i; j <= 10; j++) {
                if (i == j && cards[i] < 2) continue; // 같은 카드 2장 필요
                if (i != j && (cards[i] == 0 || cards[j] == 0)) continue;

                // 경우의 수 세기
                int count = (i == j) ? (cards[i] * (cards[i] - 1)) / 2 : cards[i] * cards[j];

                int yScore = (y1 == y2) ? 100 + y1 : (y1 + y2) % 10;
                int oScore = (i == j) ? 100 + i : (i + j) % 10;

                if (yScore > oScore) win += count;
                total += count;
            }
        }

        System.out.printf("%.3f\n", (double) win / total);
    }
}
```

# 💡시간복잡도

이 문제의 입력은 `두 개의 정수(영학이의 카드)`로 고정되어 있고,

가능한 카드 조합도 **`1~10 중 2장을 뽑는 조합`**이므로 **`상수 범위 내`**이다.

- 카드 종류: 1~10
- 각 카드 2장씩
- 총 카드 수: 20장 → 영학이 카드 2장 제외 시 18장 → 18C2 = **153가지** 상대방 조합 가능

하지만 실제 코드에서는 중복 제거와 카드 수량 확인을 위해 **1~10 범위의 숫자 2개를 중첩 루프로 순회**하며 경우의 수를 세는 방식이기 때문에:

> 시간 복잡도: O(1) (상수 시간)
>

이유:

- 입력 크기와 관계없이 항상 **10x10의 범위 안**에서 돌아간다.
- 브루트포스처럼 보이지만, 입력의 수가 작기 때문에 충분히 빠르게 동작한다...고 한다(어렵다ㅠㅠ)

# 💡 틀린 이유 & 틀린 부분 수정

### 접근방식이 틀림

1. 입력된 카드 외의 조합을 정확히 구성하지 못했다

   ### 1. **상대방 카드 중복 제거 안 됨**

    - `for(int i = 0; i < 20; i++)`

      → 이건 카드 번호가 아니라 인덱스 느낌인데, **카드 숫자 1~10을 2장씩 있는 구조로 표현한 게 아님**

    - 실제 상대방이 가질 수 있는 조합은 **영학이의 두 장을 제외한 카드 18장 중 2장 뽑기**

      → 18C2 = 153가지

    - 이걸 반영하지 않고 `(i, j)` 범위를 단순하게 `0~19`, `1~18` 등으로 돌리면 중복, 잘못된 조합이 발생함

2. 카드 분포 고려 누락
    - 문제에서는 `같은 숫자 카드가 두 장 있다는 점`이 중요하다.
    - 예를 들어, 영학이가 뽑은 카드의 숫자는 상대방이 뽑을 확률이 줄어든다는 점을 고려해야 함.

3. 끗 계산 로직 오류

    ```java
    if(i+j-10==0)
    ```

   "i+j가 10이면 0끗"이라는 건데, **끗수는 `(i + j) % 10`** 으로 계산해야 정확함.

4. 승부 로직이 잘못 구성되어있다.

내가 작성한 오답…

```java
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input1 = sc.nextInt();
        int input2 = sc.nextInt();
        int win = 0;
        int lose = 0;
        for(int i = 0; i < 20; i++) {//-> 이건 카드 번호가 아니라 인덱스 느낌인데, 
														        //**카드 숫자 1~10을 2장씩 있는 구조로 표현한 게 아님**
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
```