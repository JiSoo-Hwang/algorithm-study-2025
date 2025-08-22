package main.java.week3_DFS_BFS.hide_and_seek_1697;
//수빈이가 동생을 찾을 수 있는 가장 빠른 시간
//입력값 : 수빈이의 위치 N 동생일 있는 위치 K N과 K는 정수
//N(0<=N<=100000) (0<=K<=100000)
//위치가 X라면 수빈이의 걷는 속도 1초당 X-1 or X+1
//순간이동시 2*X

import java.io.*;
import java.util.*;
public class Solution {

    static int MAX = 100000;//N과 K의 최대 값에 따라 최대 범위 설정

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new LinkedList<>();//BFS를 위한 que
        int[] visited = new int[MAX+1];//방문 여부 체크를 위한 배열

        queue.add(N);
        visited[N]=1; //시작 위치 방문

        while (!queue.isEmpty()) {
            int current = queue.poll(); //
            if(current == K){
                System.out.println(visited[current]-1);
                return;
            }

            if(current -1 >= 0 && visited[current - 1]==0){
                visited[current-1] = visited[current] + 1;
                queue.add(current - 1);
            }

            if(current +1 < MAX && visited[current + 1]==0){
                visited[current + 1] = visited[current] + 1;
                queue.add(current + 1);
            }

            if(current * 2 < MAX && visited[current * 2]==0){
                visited[current * 2] = visited[current] + 1;
                queue.add(current * 2);
            }
        }
    }
}
