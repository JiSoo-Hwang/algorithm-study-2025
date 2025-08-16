package main.java.week2_simulation.operator_insertion_14888;
import java.io.*;
import java.util.*;

public class Solution {
    static int N;
    static int[] nums;
    static int[] ops = new int[4]; // +, -, *, /
    static int maxVal = Integer.MIN_VALUE;
    static int minVal = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());
        nums = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) ops[i] = Integer.parseInt(st.nextToken());

        dfs(1, nums[0]); // 첫 수에서 시작, 다음 인덱스 = 1
        System.out.println(maxVal);
        System.out.println(minVal);
    }

    static void dfs(int idx, int cur) {
        if (idx == N) {
            maxVal = Math.max(maxVal, cur);
            minVal = Math.min(minVal, cur);
            return;
        }
        for (int op = 0; op < 4; op++) {
            if (ops[op] == 0) continue;
            ops[op]--;
            int next = apply(cur, nums[idx], op);
            dfs(idx + 1, next);
            ops[op]++; // 백트래킹 복구
        }
    }

    // op: 0:+, 1:-, 2:*, 3:/
    static int apply(int a, int b, int op) {
        switch (op) {
            case 0: return a + b;
            case 1: return a - b;
            case 2: return a * b;
            case 3:
                // 문제 규칙: 0을 기준으로 버림(자바 int 나눗셈과 동일)
                if (b == 0) return 0; // (문제상 b=0은 주어지지 않지만 안전장치)
                // a/b 가 음수일 때의 동작도 자바는 이미 truncate toward 0
                return a / b;
            default: throw new IllegalArgumentException();
        }
    }
}
