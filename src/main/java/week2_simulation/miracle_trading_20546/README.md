# 💡**문제에서 구해야 할 것**

문제 조건 :
- BNP는 가지고 있는 현금에서 가장 많이 살 수 있는 수량을 주문하는 것
- TIMING은 전량 매수 || 전량 매도로만 이루어지되, 3일 연속 가격이 전일 대비 상승하면 다음날 무조건 가격이 하락한다고 가정한다.
- 따라서 현재 소유한 주식의 가격이 3일째 상승한다면 전량 매도한다. (전일 대비 변동이 없으면 상승으로 간주 안함)
- 반대로 가격이 전일 대비 하락하는 주식은 다음날 무조건 상승한다고 가정한다.
- 준현이와 성민이의 2021년 1월 1일부터 2021년 1월 14일까지의 수익률 겨루기
- 준현이의 자산이 더 크다면 "BNP", 성민이의 자산이 더 크다면 "TIMING"출력
- 자산은 1월 14일 각자의 잔액 + (보유 주식 수량 * 1월 14일 주가)

# 💡**알고리즘 설계**

### 1트 (`실패…`)

- 14일의 주가 입력될 때마다 기록 (배열에 추가) ✅
- 준현이 함수는 입력해오는 주가 그대로 매수/매도 진행 ⚠️→ 전략별로 함수를 만들 필요 없었음…
- 성민이 함수는 입력해오는 주가 배열에 저장 🔼→ 그냥 두 전략 모두 하나의 주가 배열 공유 가능….
- 만약 3일 연속 같은 상승/하락 추이가 보이면 매도/매수 진행 ✅
- 이때 필요한 값 : 각자의 보유 주식, 잔액 ✅
- 두 함수의 반환값을 비교해서 결과 출력 ✅

### 2트(`성공!`)

- 14일간의 주가 입력될 때마다 기록 (크기 14인 배열에 추가)
- 준현이는 입력해오는 주가 그대로 매수/매도 진행
- 성민이는 주가 배열이 연속으로 3개 인덱스가 상승 (첫 인덱스 < 두번째 인덱스 < 세번째 인덱스)추이가 보이면 매도 진행, 하락 (첫 인덱스 > 두번째 인덱스 > 세번째 인덱스)추이가 보이면 매수 진행
- 마지막에 두 전략으로 나온 수익을 계산결과 (잔액 + 주식수량 * 마지막날 주가) 비교해서 결과 출력

# 💡코드

```
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cash = sc.nextInt();

        int bnpBalance = cash;
        int timingBalance = cash;

        int bnpShares = 0;
        int timingShares = 0;

        int[] prices = new int[14];

        for(int i = 0; i < 14; i++){
            prices[i] = sc.nextInt();
            if(prices[i]>0){
                int shares = bnpBalance / prices[i];
                bnpShares += shares;
                bnpBalance %= prices[i];
            }

            if(i>=2){
                boolean up3 = prices[i-2] < prices[i-1] && prices[i-1] < prices[i];
                boolean down3 = prices[i-2] > prices[i-1] && prices[i-1] > prices[i];

                if(down3 && prices[i]>0){
                   int shares = timingBalance / prices[i];
                   timingShares += shares;
                   timingBalance %= prices[i];
                }else if(up3 && prices[i]>0){
                    timingBalance += timingShares * prices[i];
                    timingShares = 0;
                }
            }
        }

        int bnpResult = bnpBalance + bnpShares * prices[13];
        int timingResult = timingBalance + timingShares * prices[13];

        if(bnpResult > timingResult){
            System.out.println("BNP");
        }else if(bnpResult < timingResult){
            System.out.println("TIMING");
        }else{
            System.out.println("SAMESAME");
        }

    }
}
```

# 💡시간복잡도

`O(N)` 반복문 한개만 돌리기 때문

기타 내부에서 하는 연산(주식 매수, 매도 여부 판단, 결과 계산)은 모두 상수 시간 `O(1)`

# 💡틀린 이유 & 틀린 부분 수정

접근방식이 틀림

### 1트…

```
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cash = sc.nextInt();
        int bnpBalance = cash;
        int timingBalance = cash;
        int[] bnpStock = new int[14];//보유 주식 수량은 누적값 하나만 관리하면 되기 때문에 굳이 배열로 만들 필요 없음
        int[] timingStock = new int[14];//배열로 만들어야 했던건 주가였음...(14일간 반복해서 입력 받을거잖아요...)
        

        int bnpResult =0 ;
        int timingResult =0;
        for(int i=0;i<14;i++){
            int stockPrice = sc.nextInt();
            bnpStock[i] = getStock(bnpBalance,stockPrice);
            bnpBalance %= bnpStock[i]; // bnpStock[i]가 0 이면 / by zero 오류 발생

            while(i>1){
                if(timingStock[i-1]>timingStock[i-2] && timingStock[i]>timingStock[i-1]){
                    timingStock[i] = getStock(timingBalance,stockPrice);
                    timingBalance %= timingStock[i];// timingStock[i]가 0 이면 / by zero 오류 발생
                } else if (timingStock[i-1]<timingStock[i-2] && timingStock[i]<timingStock[i-1]) {
	                         //왜 주가가 아닌 수량을 비교하고 있죠오...? 주가 배열을 만들어보아요...^^
                    timingBalance = sellStock(timingBalance,timingStock[i-1],stockPrice);
                    timingStock[i] = 0;
                }
            }

            if (i==13){
                bnpResult = bnpBalance + stockPrice * bnpStock[i];
                timingResult = timingBalance + stockPrice * timingStock[i];
            }
        }

        if(bnpResult>timingResult){
            System.out.println("BNP");
        }else if (bnpResult<timingResult){
            System.out.println("TIMING");
        }else{
            System.out.println("SAMESAME");
        }

    }

    public static int getStock(int balance, int stockPrice){
        if(balance>stockPrice){
            return balance/stockPrice;
        }
        return 0;
    }

    public static int sellStock(int balance, int stock, int stockPrice){
        return balance + (stock * stockPrice);
    }

}
```

# 💡다른 풀이 or 기억할정보

회고 : 알고리즘 설계를 좀 더 명확하게 해서, 생성할 변수, 변수의 데이터 타입을 좀 더 확실하게 정해놓고 풀이를 하자