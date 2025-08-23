package main.java.week3_DFS_BFS.hide_and_seek_2_12851;
//숨바꼭질 1과 같은 조건들에 추가로 가장 빠른 시간으로 찾는 방법의 가지수 구하기
import java.util.*;
import java.io.*;
public class Solution {
    static final int MAX = 100000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if(N >= K){ //N과 K가 같거나, N이 K보다 커서 계속 빼는 방법(한 가지)만 있는 경우
            System.out.println(N-K);
            System.out.println(1);
            return;
        }

        int[] distance = new int[MAX+1];
        int[] path = new int[MAX+1];
        Arrays.fill(distance, -1); // 아직 방문 안함을 '-1'로 초기화

        Deque<Integer> queue = new ArrayDeque<>();
        distance[N] = 0;
        path[N] = 1; // 시작점에 "도달"하는 방법은 1가지(아무 것도 안하기)
        queue.add(N);

        int minDistance = -1; // K를 처음 발견한 거리 (역시 아직 도착 안했으니 '-1'로 초기화)

        while(!queue.isEmpty()){
            int current = queue.poll();

            // K의 최단거리 레벨 "끝까지" 처리했고, 지금 뽑은 노드의 깊이가 그것보다 크면 빠져나가기
            if(minDistance != -1 && distance[current] > minDistance) break;

            int nextDistance = distance[current] + 1;
            int[] nextSteps = {current-1, current+1, current * 2};

            for(int nextStep : nextSteps ){

                if(nextStep < 0 || nextStep > MAX) continue; // 유효하지 않은 이동은 건너뛰기

                if(distance[nextStep] ==-1) { //처음 방문하는 경우
                    distance[nextStep] = nextDistance;
                    path[nextStep] = path[current];
                    queue.add(nextStep);
                } else if(distance[nextStep] == nextDistance){ //같은 레벨에 있을 때
                    path[nextStep] += path[current]; // 방법 추가
                }
            }

            if(current == K){
                minDistance = distance[current]; //여기서 break하면 안되고 여기서 가능한 경로를 모두 카운팅해야 함
            }
        }
        System.out.println(distance[K]);
        System.out.println(path[K]);
    }
}
