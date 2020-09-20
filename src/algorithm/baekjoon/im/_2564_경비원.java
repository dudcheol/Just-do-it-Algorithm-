package algorithm.baekjoon.im;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2564_경비원 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] opposite = { 0, 2, 1, 4, 3 };
		int answer = 0;

		int garo = Integer.parseInt(st.nextToken());
		int sero = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(br.readLine());

		int[][] store = new int[n + 1][3];

		for (int i = 0; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			store[i][2] = Integer.parseInt(st.nextToken());
			store[i][1] = Integer.parseInt(st.nextToken());
			switch (store[i][2]) {
			case 1: // 북 (위)
				store[i][0] = sero;
				break;
			case 2: // 남 (아래)
				store[i][0] = 0;
				break;
			case 3: // 서 (왼)
				store[i][0] = store[i][2];
				store[i][1] = 0;
				break;
			case 4: // 동 (오른)
				store[i][0] = store[i][2];
				store[i][1] = garo;
				break;
			}
		}
		
		// n번째는 동근
		int dongY = store[n][0];
		int dongX = store[n][1];
		int dongDir = store[n][2];

		for (int i = 0; i < n; i++) {
			int storeY = store[i][0];
			int storeX = store[i][1];
			int storeDir = store[i][2];
			int dist = 0;

			if (opposite[storeDir] == dongDir) { // 동근과 반대방향에 있다면
				if (storeDir == 1 || storeDir == 2) { // 북,남 관계라면
					int dist1 = 0;// 두가지 방향으로 계산할 수 있음
					int dist2 = 0;
					// 동근이가 왼쪽으로 갔을 때
					dist1 += sero; // 세로값 + 각자의 가로값
					dist1 += (storeX + dongX);
					// 동근이가 오른쪽으로 갔을 때
					dist2 += sero;
					dist2 += (Math.abs(garo - storeX) + Math.abs(garo - dongX));
					dist = Math.min(dist1, dist2);
				} else { // 서,동 관계라면
					int dist1 = 0;
					int dist2 = 0;
					// 동근이가 아래로 갔을 때
					dist1 += garo;// 가로값 + 각자의 세로값
					dist1 += (storeY + dongY);
					// 동근이가 위로 갔을 때
					dist2 += garo;// 가로값 + 각자의 세로값
					dist2 += (Math.abs(sero - storeY) + Math.abs(sero - dongY));
					dist = Math.min(dist1, dist2);
				}
			} else { // 반대방향이 아니라면
				// 각자의 거리 계산
				dist = (Math.abs(dongY - storeY) + Math.abs(dongX - storeX));
			}

			answer += dist;
		}

		System.out.println(answer);
	}

}
/*
10 5 
4 
1 4 
3 2 
2 8 
1 10 
2 3

 */
