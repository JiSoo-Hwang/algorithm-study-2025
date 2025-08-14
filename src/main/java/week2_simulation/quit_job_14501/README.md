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

- 주어지는 일수를 최대한 채우고
- 가격이 최대한 큰 수를 골라야 함
- 상담기간(N) 크기의 배열
1. for loop를 사용하되, i++를 사용하는 대신 T만큼 이동시키는 방법…?

   → 첫날에 돈을 짜게 주면 어떡함…?

   → out of bounds 오류는 어떻게 방지하지?

2. N에서 일수를 빼가면서 카운팅(N-i가 0이상일 때까지만 카운트)

   → 돈은 어떻게 계산함?

   `int profit에 누적시키기`


# 💡코드

```
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int days = sc.nextInt();
        int[] counselTimeArr = new int[days];
        int[] pricesArr = new int[days];

        for(int i = 0; i < days; i++) {
            counselTimeArr[i] = sc.nextInt();
            pricesArr[i] = sc.nextInt();
        }

        //int profit = 0;//이익은 숫자 하나로는 안됨 (dp 배열 (크기 days + 1)) 생성
        int[] dp = new int[days+1];

        for(int j = days-1; j>=0 ;j-- ) {
            int next = j + counselTimeArr[j];
            
            int take=(next<=days)?pricesArr[j]+dp[next]:0;
            
            int skip = dp[j+1];
            
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