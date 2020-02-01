package SWExpertAcademy.D4;

/*
 * 시작 : 10:35
 * 제한시간: 1시간30분 - 실패
 * 끝 : 
 * 
 * [못 푼 이유]:문제자체를 이해를 못함
 * 태영이는 N개의 다이아몬드를 가지고 있다. 각 다이아몬드 크기는 1 이상 10000 이하의 자연수로 나타낼 수 있다.
 * 태영이는 N개의 다이아몬드 중 몇 개를 골라, 애인에게 선물로 주려고 한다.
	=> 몇 개 고른 것이 한 묶음이 된다.
 * 한편, 태영이는 고른 다이아몬드의 크기가 뒤죽박죽이면 애인이 좋아하지 않을 것이라고 생각하여,
	=> 뒤죽박죽인 것은 선물하지 않는다.
 * 고른 다이아몬드 중 크기 차가 K 이하인 것들을 묶음으로 가져가려고 한다. (단, 묶음은 여러 묶음일 수 있다.)
실수	=> 나는 여기서, 한 묶음에 2개가 들어있고 그 차이가 K이하인 것들의 수가 답이라고 생각했다. 근데  완전히 착각이었다.
	=> 한 묶음에는 여러개 다이아몬드가 들어갈 수 있고, 그 차이들이 다 K이하여야 하는 것.
 * 태영이가 애인에게 선물할 수 있는 다이아몬드의 최대 개수는 얼마인가?
 	=> 태영이가 선물한 묶음에 들어있는 다이아몬드의 수는?
 * 
 * [배운 것]
 * - 문제를 충분히 이해하지 않은 상태에서 풀이를 시작하면 엉뚱한 풀이가 될 수 있다
 */

import java.util.*;
import java.io.*;

public class _9088_Diamond {

//	static void recursion(int l) {
//		// 리턴조건
//		if (l > N) {
//			return;
//		}
//		// 할 작업
//		if (l != 0 && l == N) {
//			// num에 들어있는 순서로 차를 구한다
//			int temp = 0;
//			for (int i = 0; i < N; i += 2) {
//				if (i >= N || i + 1 >= N)
//					break;
//				if (Math.abs(num[i] - num[i + 1]) <= K) {
//					temp += 2;
//				}
//			}
//			answer = Math.max(answer, temp);
//			return;
//		}
//
//		// 재귀
//		for (int i = 0; i < N; i++) {
//			if (visited[i])
//				continue;
//			visited[i] = true;
//			num[l] = dia[i];
//			recursion(l + 1);
//			visited[i] = false;
//		}
//	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		int answer = 0;
		int i = 1;
		for (int tc = 0; tc < TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // N개의 줄
			int K = Integer.parseInt(st.nextToken()); // 다이아몬드의 크기 차가 K 이하인 것들을 묶음으로 가져감
			int[] dia = new int[N];
			
			for (int n = 0; n < N; n++) {
				dia[n] = Integer.parseInt(br.readLine());
			}

			Arrays.sort(dia);

			for (int j = 0; j < N - 1; j++) {
				if (N - j < answer) { // 묶음안에 있는 수보다 적은 횟수의 반복이 남았다면 끝낸다
					break;
				}
				int max = 1;
				for (int k = j + 1; k < N; k++) {
					if ( dia[k] - dia[j] <= K) {
						max++;
					} else {
						break;
					}
				}
				answer = Math.max(answer, max);
			}

//			num = new int[N];
//			visited = new boolean[N];
//			for (int n = 0; n < N; n++) {
//				dia[n] = Integer.parseInt(br.readLine());
//			}

			System.out.println("#" + i + " " + answer);
			i++;
		}

		br.close();
	}
}
