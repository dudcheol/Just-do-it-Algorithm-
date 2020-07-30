package algorithm.APSS.recursionAndBruteforce;

/*
 * 알고리즘 문제해결 전략 - 165p - 여행하는 외판원 문제 (완전탐색으로 풀기)
 * (책에는 예제만 있어서 백준문제 품) 백준 - 16991 - 외판원 순회3
 * 시작: 11:50 ~ 13:12 
 */
import java.util.*;
import java.io.*;

public class _165p_TravelingSalesmanProblem {
	static int N;
	static boolean visited[];
	static int[][] cities;
	static int start;
	static double answer = Double.MAX_VALUE;

	static void tsp(int cur, int k, double cost) { // k: 몇번째방문한 곳인지
		if (k == 0) {
			start = cur;
		}
//		int finish = -1;
//		for (boolean visit : visited) {
//			if (!visit) {
//				finish = -1;
//				break;
//			} else {
//				finish = 1;
//			}
//		}

		if(k==N) {
//		if (finish > 0) { // 방문하지 않은 곳이 없다
			answer = Math.min(answer, cost + getCost(cur, start)); 
			/* 계획 세우기 단계에서 계산하는 부분 대충 넘어가지 않는다. 코드로 바로 해버리면 실수를 한다. */
			/* 여기서 매번 실수한다. Math.min의 결과값을 저장하는 변수를 깜빡하지 않도록 하자 */
			return;
		}

		for (int i = 0; i < N; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			tsp(i, k + 1, cost + getCost(cur, i));
			/* tsp의 3번째 인자는 현재 가지고 있는 cost에 다음 여행지와의 거리를 더한 값을 넣는다.
			 * 이때, cost += getCost를 해서  원래 cost값을 건드리면 다다음에 갈 여행지를 선택할때 영향을 받는다.
			 * 이런 사소한 실수때문에 시간이 길어지는 것을 조심하자. */
			visited[i] = false;
		}
	}

	static double getCost(int x, int y) {
		return Math.sqrt(Math.pow(cities[y][0] - cities[x][0], 2) + Math.pow(cities[y][1] - cities[x][1], 2));
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		cities = new int[N][2];
		visited = new boolean[N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			cities[i][0] = Integer.parseInt(st.nextToken());
			cities[i][1] = Integer.parseInt(st.nextToken());
		}

		tsp(0, 0, 0);

		
		System.out.println(answer);
	}
}
