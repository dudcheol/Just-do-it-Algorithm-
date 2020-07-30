package algorithm.APSS.recursionAndBruteforce;

/*
 * 알고리즘 문제해결 전략 - 172p - 순열과 조합 개인적인 공부
 * 시작~끝:
 * 걸린시간:
 * - 맞았다! -
	[간단한 해법]
	[어떤  방식으로 접근했나?]
	[해법을 찾는 데 결정적인 깨달음]
	[다른 해결 방법이 있다면?]
 * - 틀렸다! -
	[오답 원인]
	[다른 사람 코드 참고 -> 내가 취했던 접근 되돌아보기 -> 나는 왜 이 풀이를 떠올리지 못했나?] 
 */
import java.util.*;
import java.io.*;

public class _172p_PermutationAndCombination {
	static int N;
	static BufferedWriter bw;
	static boolean[] visited;

	static void permutation(int n, int[] num) throws Exception {
		if (n == N) { /* BaseCase 함부로 상수로 쓰지 않는다. */
			for (int i = 1; i <= N; i++)
				bw.write(num[i] + "");
			bw.write("\n");
			return;
		}

		for (int i = 1; i <= N; i++) {
			if (visited[i])
				continue;
			/*
			 * visited와 num의 인덱스를 구분하는 표시를 해둬야 겠다. visited의 인덱스는 '사용한 숫자'에 관한 인덱스이므로 for문의
			 * i를, num의 인덱스는 '자릿수'에 관한 인덱스이므로 매개변수 n을 사용해야 함
			 */
			visited[i] = true;
			num[n + 1] = i;
			permutation(n + 1, num);
			visited[i] = false;
		}
		return;
	}

	static void backtrackingCombination(int start, int n) throws Exception {
		if (n == 0) {
			for (int i = 1; i < visited.length; i++) {
				if (visited[i])
					System.out.print(i);
			}
			System.out.println();
			return;
		}

		for (int i = start; i <= N; i++) { // i는 해당 숫자
			visited[i] = true;
			backtrackingCombination(i + 1, n - 1);
			visited[i] = false;
		}
	}

	static void unsortedCombination(List<Integer> picked, int toPick) throws Exception {
		if (toPick == 0) {
			for (int i = 1; i <= N; i++) {
				picked.add(i);
			}
		}

		if (toPick == N) {
			picked.sort(null);
			for (int pic : picked)
				bw.write(pic + "");
			bw.write("\n");
			return; /* 리턴 자꾸 까먹어...! BaseCase는 특히 신중해야함 */
		}

		picked.remove(new Integer(toPick + 1));
		unsortedCombination(picked, toPick + 1);
		picked.add(toPick + 1);
		unsortedCombination(picked, toPick + 1);
	}

	public static void main(String[] args) throws Exception {
		// N(N<=9인 자연수)을 입력받고 N에 대한 순서가 있는 순열, 순서가 없는 순열, 조합을 출력해보자.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N + 1];

		permutation(0, new int[N + 1]);
		bw.write("----------\n");
		bw.flush();
		unsortedCombination(new ArrayList<Integer>(), 0);
		bw.flush();
		bw.write("----------\n");

		Arrays.fill(visited, false);
		backtrackingCombination(1, N);
		bw.flush();
		br.close();
		bw.close();
	}
}
