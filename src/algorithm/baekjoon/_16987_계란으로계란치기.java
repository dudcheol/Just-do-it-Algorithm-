package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _16987_계란으로계란치기 {

	private static class Egg implements Comparable<Egg> {
		int num;
		int power;
		int weight;

		public Egg(int num, int power, int weight) {
			this.num = num;
			this.power = power;
			this.weight = weight;
		}

		@Override
		public int compareTo(Egg o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	private static int N;
	private static Egg[] eggs;
	private static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		eggs = new Egg[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			eggs[i] = new Egg(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		dfs(0, 0, 0);

		System.out.println(answer);
	}

	private static void dfs(int cur, int cnt, int tryCnt) {
		answer = Math.max(answer, cnt);
		
		if (tryCnt == N) {
			return;
		}

		if (eggs[cur].power <= 0) { // 현재 집은 계란이 깨져있으면 오른쪽 계란으로
			dfs(cur + 1, cnt, tryCnt + 1);
			return;
		}

		for (int i = 0; i < N; i++) {
			if (cur == i || eggs[i].power <= 0) // 현재 집고있는 계란 or 집었는데 이미 깨진 계란이면 건너뜀
				continue;

			Egg curTmp = new Egg(eggs[cur].num, eggs[cur].power, eggs[cur].weight);
			Egg targetTmp = new Egg(eggs[i].num, eggs[i].power, eggs[i].weight);
			eggs[cur].power -= eggs[i].weight;
			eggs[i].power -= eggs[cur].weight;

			if (eggs[cur].power <= 0) { // 손에 든 계란이 깨졌다면 들었던 계란의 오른쪽 계란을 집음
				if (eggs[i].power <= 0) {
					dfs(cur + 1, cnt + 2, tryCnt + 1); // 계란 둘 다 깨짐
				} else {
					dfs(cur + 1, cnt + 1, tryCnt + 1); // 현재 든 계란만 깨짐
				}
			} else { // 깨지지 않았다면 들고있는 계란 계속 들고서 계란깨기 진행
				if (eggs[i].power <= 0) {
					dfs(cur + 1, cnt + 1, tryCnt + 1); // 타겟 계란은 깨짐
				} else {
					dfs(cur + 1, cnt, tryCnt + 1); // 둘 다 안깨짐
				}
			}

			eggs[cur] = curTmp;
			eggs[i] = targetTmp;
		}
	}

}
