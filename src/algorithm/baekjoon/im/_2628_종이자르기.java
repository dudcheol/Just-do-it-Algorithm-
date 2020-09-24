package algorithm.baekjoon.im;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _2628_종이자르기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int garo = Integer.parseInt(st.nextToken());
		int sero = Integer.parseInt(st.nextToken());

		int cutCnt = Integer.parseInt(br.readLine());
		int[] cutGaro = new int[cutCnt];
		int[] cutSero = new int[cutCnt];

		Arrays.fill(cutGaro, -1);
		Arrays.fill(cutSero, -1);

		int gidx = 0;
		int sidx = 0;
		for (int i = 0; i < cutCnt; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int dir = Integer.parseInt(st.nextToken());
			int cut = Integer.parseInt(st.nextToken());

			if (dir == 0) { // 가로로 자름
				cutGaro[gidx++] = cut;
			} else { // 세로로 자름
				cutSero[sidx++] = cut;
			}
		}

		Arrays.sort(cutGaro);
		Arrays.sort(cutSero);

		// 0,0부터 구간이 가장 긴 애의 길이를 찾자
		int garoMax = 0;
		int pre = 0;
		for (int i = 0; i < cutCnt; i++) {
			if (cutGaro[i] == -1)
				continue;
			garoMax = Math.max(garoMax, cutGaro[i] - pre);
			pre = cutGaro[i];
		}
		garoMax = Math.max(garoMax, sero - pre);

		int seroMax = 0;
		pre = 0;
		for (int i = 0; i < cutCnt; i++) {
			if (cutSero[i] == -1)
				continue;
			seroMax = Math.max(seroMax, cutSero[i] - pre);
			pre = cutSero[i];
		}
		seroMax = Math.max(seroMax, garo - pre);

		System.out.println(garoMax * seroMax);
	}

}
