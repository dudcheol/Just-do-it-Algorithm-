package algorithm.swea.mockTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _4014_활주로건설_교수님풀이_행열반전 {

	private static int N, X, map[][], count;
	private static int[][] rotateMap;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= TC; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			rotateMap = new int[N][N];
			count = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					rotateMap[j][i] = map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			process();
			sb.append('#').append(test_case).append(' ').append(count).append('\n');
		}
		System.out.print(sb);
	}
	
	private static void process() {
		for (int i = 0; i < N; i++) {
			if(makeRoad(map[i])) ++count;
			if(makeRoad(rotateMap[i])) ++count;
		}
	}
	
	private static boolean makeRoad(int[] road) {
		int beforeHeight, size; // 이전 칸의 높이, 평탄한 지형의 길이
		beforeHeight = road[0];
		size = 1;
		for (int j = 1; j < N; j++) {
			// 1. 이전칸과 현재칸의 높이가 같은지
			if(beforeHeight == road[j]) {
				++size;
			
			// 2. 현재칸이 이전칸보다 높이가 1 높은 경우 (오르막 경사로 설치 가능한지 체크)
			} else if(beforeHeight + 1 == road[j]) {
				if(size < X) return false; // 활주로 건설 불가
				beforeHeight++;
				size = 1;
				
			// 3. 현재칸이 이전칸보다 높이가 1 낮은 경우 (내리막 경사로 설치 가능한지 체크)
			} else if(beforeHeight - 1 == road[j]) {
				int count = 0;
				for (int k = j; k < N; k++) {
					if(road[k] != beforeHeight-1) break;
					count++; // 이전칸의 높이와 1차이 낮은 연속된 평탄화 지형의 길이를 카운트
				}
				if(count < X) return false;
				j += X - 1; // 경사로 다음 칸의 위치로 제어
				beforeHeight--;
				size = 0;
				
			// 4. 높이가 2이상 차이나는 경우
			} else {
				return false;
			}
		}
		return true;
	}
}