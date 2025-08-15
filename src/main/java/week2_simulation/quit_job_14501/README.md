# 💡**문제에서 구해야 할 것**

문제 조건 :

오늘부터 N+1일째 되는 날 퇴사 (부럽다…)

하루에 하나씩 서로 다른 사람의 상담

상담을 완료하는데 걸리는 기간 T, 상담을 했을 때 받을 수 있는 금액 P

입력값:

- 첫 줄 : N일
- N 개의 줄 : T(기간) P(가격)

상담기간에 백준이가 얻을 수 있는 최대 수익

# 💡**알고리즘 설계**

- 상태 정의 : `dp[i]` = i번째 날부터 마지막 날(N-1)까지 얻을 수 있는 최대 수익

  (배열 크기는 `N+1` , 그리고 `dp[N] = 0` = “근무일 끝난 다음날은 더 벌 것 없음”)

- 의사결정 (두 선택)
   1. take : i일 상담을 수행할 수 있을 때만
      - 유효조건 : `i + T[i] <= N`
      - 값 : `P[i] + dp[i + T[i]]`
      - 유효하지 않으면 0(혹은 아주 작은 값으로 처리 `Integer.*MIN_VALUE*;` )
   2. skip : i일 상담을 건너뜀
      - 값 : `dp[i + 1]`
- 점화식 : `dp[i] = max( take, skip )`
- 반복 순서 : 경계 실수 방지를 위해 뒤에서 앞으로 : `for (i = N-1; i >=0; i--)
- 정답은 `dp[0]` 에 할당 및 출력

# 💡코드

```
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int workDays = sc.nextInt();
        int[] T = new int[workDays];
        int[] P = new int[workDays];

        for(int i = 0; i < workDays; i++) {
            T[i] = sc.nextInt();
            P[i] = sc.nextInt();
        }

        int[] dp = new int[workDays+1];
        for(int j = workDays-1; j >= 0; j--){
            //마지막 날 인덱스(j)에 업무 소요일 추가를 한다면 T[j]값은 1이어야 한다
            int next = j + T[j];
            //상담 가능하면 '한다' 선택값, 불가능하면 0
            //dp[next]는 이 업무를 선택한 뒤에 도달하게 될 일차 슬롯
            // (예 : 2일차 때(1번 인덱스) + 5일 걸리는 업무를 선택해서 dp의 6번째 인덱스로 이동
            int take = next<=workDays?P[j]+dp[next]:0;
            //일을 하기를 선택하지 않았을 때의 누적 수익(직전 선택 소환)
            int skip = dp[j+1];
            
            //일을 하기를 선택했을 때와 선택하지 않았을 때 값 비교해서 큰 값 선택
            dp[j] = Math.max(take, skip);
        }

        System.out.println(dp[0]);
    }
}
```

# 💡시간복잡도

O(N) for loop 한 개

# 💡틀린 이유 & 틀린 부분 수정

접근방식이 완전히 틀린…건 아닌데 어떻게 구현할지 막혀버림…

### 1트

접근 방식 :

- 주어지는 일수를 최대한 채우고 (`그리디 함정1`)
- 수익이 최대한 큰 수를 골라야 함 (`그리디 함정2` )
- 상담기간(N) 크기의 배열
1. for loop를 사용하되, i++를 사용하는 대신 T만큼 이동시키는 방법…?

   → `첫날에 돈을 짜게 주면 어떡함…?`

   → `out of bounds 오류는 어떻게 방지하지?`

   수정 : N에서 일수를 빼가면서 카운팅(N-i가 0이상일 때까지만 카운트)

   → 돈은 어떻게 계산함?

   int profit에 누적시키기

   (❌`DP로 접근해야 하고, 하나의 정수만으로 계산하기에는 적절하지 않음`)

   → 일수를 빼가면서 카운팅한다면 반드시 다음으로 들어오는 의뢰를 선택해야 하는가?

   `No.  int skip 로 해당 의뢰를 선택했을 때의 누적 수의 계산`

   → 건너뛸지 말지 판단은 어떤 기준으로 해야 하지?

   업무를 수행했을 때 이익이 더 크다는 판단이 생겼을 때?

   → 이 판단 로직은 어떻게?

   매번 일하기를 선택했을 때(`take`)와 vs 건너뛰었을 때(`skip`)를 비교해서 큰 값을 선택해야 함.

   `take` 값은 일하기를 선택했을 때 주어지는 수입 + 소요 일수 이후 dp 배열 인덱스값에 할당된 역순으로 계산했던 누적 수익

   `skip` 값은 다음 날부터의 누적 수익

````
import java.util.Scanner;

public class Solution {
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);

        int days = sc.nextInt();
        int[] counselTimeArr = new int[days];
        int[] pricesArr = new int[days];

        for(int i = 0; i < days; i++) {
            int counselTime = sc.nextInt();
            counselTimeArr[i] = counselTime;
            int price = sc.nextInt();
            pricesArr[i] = price;
        }

        int profit = 0;

        for(int j = 0; ;j++ ) { //for loop를 어떻게 완성시켜야 할지 모르겠다...
            if(days-j-1>counselTimeArr[j]){ //이렇게 하면 최대한 얻을 수 있는 profit을 보장할 수는 없을거 같은데...
                profit += pricesArr[j];
            }
        }
    }
}
````