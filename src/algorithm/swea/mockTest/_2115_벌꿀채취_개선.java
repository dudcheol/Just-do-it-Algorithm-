package algorithm.swea.mockTest;

import java.util.*;
import java.io.*;

public class _2115_벌꿀채취_개선 {
	
	static int N,M,C,map[][],max;
	static boolean[][] p1Visited;
	private static int[][] maxMap;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= TC; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			maxMap = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			sb.append('#').append(test_case).append(' ').append(getMaxBenefit()).append('\n');

		}
		System.out.print(sb);
		
	}

	private static int getMaxBenefit() {
		makeMaxMap();
		return processCombination();
	}

	private static void makeMaxMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N-M; j++) {
				makeMaxSubSet(i, j, 0, 0, 0);
			}
		}
	}
	
	private static void makeMaxSubSet(int i, int j, int cnt, int sum, int powSum) {
		if(sum > C) return;
		if(cnt == M) {
			if(maxMap[i][j-M] < powSum) maxMap[i][j-M] = powSum;
			return;
		}
		// 선택
		makeMaxSubSet(i, j+1, cnt+1, sum+map[i][j], powSum+map[i][j]*map[i][j]);
		// 비선택
		makeMaxSubSet(i, j+1, cnt+1, sum, powSum);
	}

	private static int processCombination() {
		int max = 0, aBenefit = 0, bBenefit = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N-M; j++) { // 일꾼 A 선택
				aBenefit = maxMap[i][j];
				// 일꾼 B 선택
				// 같은행
				bBenefit = 0;
				for (int j2 = j+M; j2 <= N-M; j2++) {
					if(bBenefit < maxMap[i][j2]) bBenefit = maxMap[i][j2];
				}
				// 다른행
				for (int i2 = i+1; i2 < N; i2++) {
					for (int j2 = 0; j2 <= N-M; j2++) {
						if(bBenefit < maxMap[i2][i2]) bBenefit = maxMap[i2][i2];
					}
				}
				if(max < aBenefit + bBenefit) max = aBenefit + bBenefit;
			}
		}
		return max;
	}
}
