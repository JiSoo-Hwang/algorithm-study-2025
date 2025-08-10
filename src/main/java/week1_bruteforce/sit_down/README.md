# 💡**문제에서 구해야 할 것**

문제 조건 :

영학이의 패가 입력으로 주어졌을 때 이길 수 있는 확률

영학이가 이기는 경우의 수/모든 경우의 수

출력값은 0.000 형식이 되어야 함

# 💡**알고리즘 설계**

### 1트…

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

---

### 2트…(성공)

1. 리스트로 카드 덱 구성
2. 영학이가 뽑은 카드를 리스트에서 제거하는 함수 만들기

   (별도 함수 작성 없이`deck.remove(Integer.valueOf(y1));` 이렇게 작성도 가능)

   (영학이가 같은 숫자의 카드를 뽑으면 해당 함수/메서드 두 번 실행해야 함)

3. 땡인 경우, 끗인 경우 각각 점수를 계산하는 함수 만들기
4. 상대방이 남은 카드로 2장의 카드를 뽑는 조합을 2중 for문으로 표현하고,
    1. 매번 상대방이 뽑은 카드 숫자로 계산한 점수를 영학이가 뽑은 카드 숫자로 계산한 점수와 비교,

       영학이의 점수가 큰 경우 win (이기는 경우의 수) +1

    2. 루프를 돌 때마다 무조건 total (모든 경우의 수) +1

# 💡코드

```
import java.util.Objects;
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

```

# 💡시간복잡도

이 문제의 입력은 `두 개의 정수(영학이의 카드)`로 고정되어 있고,

가능한 카드 조합도**`1~10 중 2장을 뽑는 조합`**이므로**`상수 범위 내`**이다.

- 카드 종류: 1~10
- 각 카드 2장씩
- 총 카드 수: 20장 → 영학이 카드 2장 제외 시 18장 → 18C2 =**153가지**상대방 조합 가능

하지만 실제 코드에서는 중복 제거와 카드 수량 확인을 위해**1~10 범위의 숫자 2개를 중첩 루프로 순회**하며 경우의 수를 세는 방식이기 때문에:

> 시간 복잡도: O(1)(상수 시간)
>

이유:

- 입력 크기와 관계없이 항상**10x10의 범위 안**에서 돌아간다.
- 브루트포스처럼 보이지만, 입력의 수가 작기 때문에 충분히 빠르게 동작한다...고 한다(어렵다ㅠㅠ)

# 💡틀린 이유 & 틀린 부분 수정

### 접근방식이 틀림

1. 입력된 카드 외의 조합을 정확히 구성하지 못했다

### 1트…

### 1.**상대방 카드 중복 제거 안 됨**

- `for(int i = 0; i < 20; i++)`

  → 이건 카드 번호가 아니라 인덱스 느낌인데,**카드 숫자 1~10을 2장씩 있는 구조로 표현한 게 아님**

- 실제 상대방이 가질 수 있는 조합은**영학이의 두 장을 제외한 카드 18장 중 2장 뽑기**

  → 18C2 = 153가지

- 이걸 반영하지 않고`(i, j)`범위를 단순하게`0~19`,`1~18`등으로 돌리면 중복, 잘못된 조합이 발생함

1. 카드 분포 고려 누락
    - 문제에서는 `같은 숫자 카드가 두 장 있다는 점`이 중요하다.
    - 예를 들어, 영학이가 뽑은 카드의 숫자는 상대방이 뽑을 확률이 줄어든다는 점을 고려해야 함.

2. 끗 계산 로직 오류

    ```
    if(i+j-10==0)
    ```

   "i+j가 10이면 0끗"이라는 건데,**끗수는`(i + j) % 10`**으로 계산해야 정확함.

3. 승부 로직이 잘못 구성되어있다.

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

---

### 2트…

```java
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        ArrayList<Integer> deck = new ArrayList<>();

        for(int i=0;i<11;i++){// ⚠️오답 : 덱에 0이 들어감 (1..10이어야 함)
            deck.add(i);
            deck.add(i);
        }

        removeOne(deck,a);
        removeOne(deck,b);

        int win = 0;

        for(int j=0;j<deck.size()-1;j++){
            for(int k=j+1;k<deck.size();k++){
                if(a==b){//영학이가 땡일 때
                    if(Objects.equals(deck.get(j), deck.get(k))){
                        if(deck.get(j)<a){
                            win++;
                        }
                    }
                    // ⚠️오답 : 영학이가 땡일 대, 상대가 끗인 모든 경우를 승리로 포함 안함
                }else{//영학이가 끗일 때
                    if((deck.get(j)+deck.get(k))%10<(a+b)%10){//상대방도 끗일 때
                    // ⚠️오답 : 상대방이 땡인 경우 방어가 안되어있음
                        win++;
                    }
                }
            }
        }
		//⚠️오답 : 총 경우의 수를 구하지 못함 (어디에 끼워넣어야할지 몰?루겠어서...)
		//⚠️오답 : 출력문 없음

    }

    public static void removeOne(ArrayList<Integer>deck,int card){
        for(int i=0;i<deck.size();i++){
            if(deck.get(i)==card){
                deck.remove(i);
                return;
            }
        }
    }
```

# 💡다른 풀이 or 기억할정보

앉았다 문제는 직전에 유레카 이론 문제를 풀 때 사용한 for문과 다르게 접근해야 한다.

유레카 이론 문제에서 검토했던 2중 for문은

중복을 허용하는/허용하지 않는 상태에서 중복 가능한 2개/서로 다른 2개를 선택 하는 경우의 수

영학이가 2장의 카드를 뽑고, 남아있는 18장의 “실물 카드”를 인덱스 0~17로 놓고,

서로 다른 두 장(i<j)을 모두 센 루프.

같은 랭크의 서로 다른 두 장의 카드든, 영학이가 뽑아가서 1장만 남은 카드든,

다 18 장의 개별의 실물 카드로 녹아있기 때문에,

아래 반복문은 유효함.

```
 ArrayList<Integer> deck = new ArrayList<>();
 for(int i=1;i<11;i++){
            deck.add(i);
            deck.add(i);
 }
```

한편, 리스트 없이 단순히 18이라는 숫자만 하드코딩하면

그 카드가 어떤 숫자인지, 몇 장씩 남았는지를 알 수 없어서

게임 규칙(땡 가능 여부, 1장 남은 경우 처리)을 직접 적용할 수 없다.

```
int total = 0;
for(int i = 0; i < 18; i++){
	for(int j = i + 1; j < 18; j++){
		total++;
	}
}
```

---

### 랭크만 계산하는 풀이 방식

```
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