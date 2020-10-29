package algorithm.swea.mockTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _4014_활주로건설_교수님풀이 {

	private static int N, X, map[][], count;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= TC; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			count = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			process();
			sb.append('#').append(test_case).append(' ').append(count).append('\n');
		}
		System.out.print(sb);
	}
	
	private static void process() {
		for (int i = 0; i < N; i++) {
			// 하나의 반복문으로 교차진행하는 방법도 상관없음
			if(makeRoadByRow(i)) ++count;
			if(makeRoadByCol(i)) ++count;
		}
	}
	
	private static boolean makeRoadByRow(int i) {
		
		int beforeHeight, size; // 이전 칸의 높이, 평탄한 지형의 길이
		beforeHeight = map[i][0];
		size = 1;
		for (int j = 1; j < N; j++) {
			// 1. 이전칸과 현재칸의 높이가 같은지
			if(beforeHeight == map[i][j]) {
				++size;
			
			// 2. 현재칸이 이전칸보다 높이가 1 높은 경우 (오르막 경사로 설치 가능한지 체크)
			} else if(beforeHeight + 1 == map[i][j]) {
				if(size < X) return false; // 활주로 건설 불가
				beforeHeight++;
				size = 1;
				
			// 3. 현재칸이 이전칸보다 높이가 1 낮은 경우 (내리막 경사로 설치 가능한지 체크)
			} else if(beforeHeight - 1 == map[i][j]) {
				int count = 0;
				for (int k = j; k < N; k++) {
					if(map[i][k] != beforeHeight-1) break;
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
	
	private static boolean makeRoadByCol(int i) {
		
		int beforeHeight, size; // 이전 칸의 높이, 평탄한 지형의 길이
		beforeHeight = map[0][i];
		size = 1;
		for (int j = 1; j < N; j++) {
			// 1. 이전칸과 현재칸의 높이가 같은지
			if(beforeHeight == map[j][i]) {
				++size;
			
			// 2. 현재칸이 이전칸보다 높이가 1 높은 경우 (오르막 경사로 설치 가능한지 체크)
			} else if(beforeHeight + 1 == map[j][i]) {
				if(size < X) return false; // 활주로 건설 불가
				beforeHeight++;
				size = 1;
				
			// 3. 현재칸이 이전칸보다 높이가 1 낮은 경우 (내리막 경사로 설치 가능한지 체크)
			} else if(beforeHeight - 1 == map[j][i]) {
				int count = 0;
				for (int k = j; k < N; k++) {
					if(map[k][i] != beforeHeight-1) break;
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