package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _16987_계란으로계란치기_다른풀이 {

	private static int[] S; // 내구도
	private static int[] W; // 무게
	private static int N;
	private static int cnt;
	private static int max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		S = new int[N];
		W = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			S[i] = Integer.parseInt(st.nextToken());
			W[i] = Integer.parseInt(st.nextToken());
		}

		dfs(0);

		System.out.println(max);
		br.close();
	}

	private static void dfs(int d) {
		if (d == N)
			return;
		
		for (int i = 0; i < N; i++) {
			if (d == i)
				continue; // 자신은 스킵
			if (S[i] <= 0)
				continue; // 다음 계란(깨짐)
			
			if (S[d] <= 0) { // 현재 계란(깨짐)
				dfs(d + 1);
			} else { // 현재 들고있는 계란이 깨져있지 않음
				S[d] -= W[i];
				S[i] -= W[d];
				if(S[d] <= 0) cnt++;
				if(S[i] <= 0) cnt++;
				
				max = Math.max(max, cnt);
				dfs(d + 1);
				
				if(S[d] <= 0) cnt--;
				if(S[i] <= 0) cnt--;
				S[d] += W[i];
				S[i] += W[d];
			}
		}
	}

}
