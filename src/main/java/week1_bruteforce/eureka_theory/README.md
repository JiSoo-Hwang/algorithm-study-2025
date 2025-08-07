# 💡**문제에서 구해야 할 것**

문제 조건 :

첫줄은 테스트 케이스수

입력한 테스트 케이스 값의 개수 만큼 값을 입력할 수 있다.

각 입력값이 정확히 3개의 삼각수의 합으로 표현될 수 있는지를 1과 0으로 출력해야 한다.

# 💡**알고리즘 설계**

1. 테스트 케이스 수 만큼 반복문, 반복문 안에 scanner.nextInt();
2. 0으로 초기화한 정수 index를 가지고 1000 이내의 삼각수를 담을 정수 배열 생성
3. 1000이내의 삼각수를 2번에 생성한 배열에 담기
4. 3중 for문으로 (중복 허용 패턴) 3개의 삼각수의 합이 각 테스트 케이스 값에 해당하면 flag값 1할당 및 반복문 벗어나기
5. 각 테스트 케이스 flag값 배열에 담아 출력

# 💡시간복잡도

1. 삼각수를 미리 구하는 부분

```
while(index*(index+1) < 100) { 
		index++; 
}
for (int t = 1; t <= index; t++) {
    triNums[t - 1] = t * (t + 1) / 2;
}
```

삼각수 최대 개수는 약 44개 (1000보다 작은 삼각수)

상수 시간 `O(1)`

1. 3중 반복문

```
for (int j = 0; j < triNums.length; j++) {
    for (int k = 0; k < triNums.length; k++) {
        for (int l = 0; l < triNums.length; l++) {
            if (triNums[j] + triNums[k] + triNums[l] == num) {
                flag = 1;
                break;
            }
        }
    }
}
```

중첨된 3중 for문으로 `O(N³)`

여기서 N은 삼각수의 개수인 44

`44³ = 85184`회 연산

적은 수준이라 Big-O 표기에 보이는 것 치곤 빠름

1. 첫줄에 입력하는 테스트케이스 수

T개의 입력값에 대해 위 연산을 반복

→ 전체 시간복잡도는 `O(T × N³)`

# 💡틀린 이유 & 틀린 부분 수정

잘못된 접근 방식

```java
import java.util.Scanner;

 public class Main {
   public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase =  sc.nextInt();
        int[] results = new int[testCase];
        int flag =0;//여기다 초기화하면 각 테스트 케이스마다 독립적인 검증이 어렵다
        for(int i = 0; i < testCase; i++) {
            int num =  sc.nextInt();
        int index = 0;
            while(index*(index+1)<100){
                index++;
            }
            int[] triNums = new int[index];
            for(int t=1; t<=index; t++){//처음엔
                triNums[t-1] = t*(t+1)/2;//이 부분도 없어서
            }//빈배열 갖다가 아래서 검증시켰다...ㅠAㅠ
            for(int j =0; j < triNums.length-2; j++) {//잘
                for(int k =1; k < triNums.length-1; k++) {//못
                    for(int l =2; l < triNums.length; l++) {//된 반복문...
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
```

# 💡다른 풀이 or 기억할정보

## 반복문 유형 정리
### 📊 요약 표

| 반복문 유형 | 중복 허용 여부 | 패턴 | 사용 예 |
| --- | --- | --- | --- |
| 2중 for문 (조합) | ❌ 허용 안함 | `for (int i = 0; i < N-1; i++)for (int j = i+1; j < N; j++)` | 서로 다른 2개 선택 (조합) |
| 2중 for문 (중복) | ✅ 허용함 | `for (int i = 0; i < N; i++)for (int j = 0; j < N; j++)` | 중복 가능 2개 선택 |
| 3중 for문 (조합) | ❌ 허용 안함 | `for (int i = 0; i < N-2; i++)for (int j = i+1; j < N-1; j++)for (int k = j+1; k < N; k++)` | 서로 다른 3개 선택 (조합) |
| 3중 for문 (중복) | ✅ 허용함 | `for (int i = 0; i < N; i++)for (int j = 0; j < N; j++)for (int k = 0; k < N; k++)` | 중복 가능 3개 선택 |

---

## 🧠 기억 꿀팁

- `j = i + 1` → **중복 제거용**
- `j = 0` → **중복 허용**
- 중복 허용 문제는 "같은 거 여러 개 써도 돼?" 자문해보기
- 조합 문제는 "같은 조합을 두 번 세지 말자"가 핵심