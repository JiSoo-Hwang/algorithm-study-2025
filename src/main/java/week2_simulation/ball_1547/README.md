# 💡**문제에서 구해야 할 것**

문제 조건 : 
M(입력1)번의 스왑이 진행되고, M번의 스왑을 할 때마다 x(입력2 * M)번째 y(입력3 * M)번째 컵 위치를 맞바꾼다.
M번의 스왑 후 공이 들어있는 컵의 번호를 출력한다.

# 💡**알고리즘 설계**

버블 정렬 알고리즘을 참고해서 풀었다.

# 💡코드

```
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
```

# 💡시간복잡도

O(N) 반복문 한개 씩만 돌리기 때문!

# 💡 틀린 이유 & 틀린 부분 수정

처음에 인덱스 숫자 때문에 컴파일 오류 났어서 1씩 빼서 해결했다.

# 💡 다른 풀이 or 기억할정보

배열로 스왑을 시뮬레이션해도 되지만, 공의 현재 위치만 정수 하나로 추적하는 방식으로 쉽게 풀 수 있다.

```
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();     // 스왑 횟수
        int pos = 1;              // 공의 시작 위치(1번 컵)

        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            if (pos == x) pos = y;
            else if (pos == y) pos = x;
        }
        System.out.println(pos);
    }
}
```

### 기억할 정보

`Scanner.nextInt()`는 공백/개행을 동일하게 취급해서, 

두 수가 한 줄에 있든 줄바꿈으로 넘어가든 상관없이 잘 읽는다

지금 작성한 방식 그대로면 입력 형식 문제 없긴 하지만…!

속도만 신경 쓴다면 `BufferedReader + StringTokenizer`로 바꿀 수도 있다!
