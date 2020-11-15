package algorithm.baekjoon;

import java.util.*;
import java.util.Map.Entry;
import java.io.*;

// m질량, d방향, s속력, rc r행c열
// 1번부터 시작
public class _20056_마법상어와파이어볼 {

	static int N, M, K;
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int[][] mergeDir = { { 0, 2, 4, 6 }, { 1, 3, 5, 7 } };
	private static HashMap<Integer, ArrayList<Fire>> hs;

	static class Fire {
		int r;
		int c;
		int m;
		int s;
		int d;

		public Fire(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Fire [r=" + r + ", c=" + c + ", m=" + m + ", s=" + s + ", d=" + d + "]";
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		hs = new HashMap<Integer, ArrayList<Fire>>();

		Queue<Fire> q = new LinkedList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			q.offer(new Fire(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())));
		}

		while (!q.isEmpty()) {
			if (K-- == 0)
				break;

			int size = q.size();
			hs.clear();
			while (size-- != 0) {
				Fire f = q.poll();

				int nr = (f.r + dr[f.d]*f.s) % N;
				int nc = (f.c + dc[f.d]*f.s) % N;
				if(nr<0)nr = N+nr; // 음수만큼 이동 시 위치찾기 : 이동할 음수값 + 배열의 크기
				if(nc<0)nc = N+nc;

				// nr,nc를 구하기 위한 작업에서 시간지연 발생!! => 너무 많은 반복문
//				for (int i = 0; i < f.s; i++) {
//					nr = dirConverter(nr + dr[f.d]);
//					nc = dirConverter(nc + dc[f.d]);
//				}

				int key = nr * N + nc;
				if(hs.get(key) == null) hs.put(key, new ArrayList<>());
				hs.get(key).add(new Fire(nr, nc, f.m, f.s, f.d));
			}

			for (ArrayList<Fire> cur : hs.values()) {
				int m = 0;
				int s = 0;
				int r = -1;
				int c = -1;
				int d = 0;
				
				for (Fire f : cur) {
					m += f.m;
					s += f.s;
					r = f.r;
					c = f.c;
					d = f.d;
				}
				
				int cnt = cur.size();

				if (cnt >= 2) {
					m /= 5;
					if (m == 0)
						continue;
					s /= cnt;

					d = 0;
					int oddcnt = 0;
					int evencnt = 0;
					for (Fire f : cur) {
						if (f.d % 2 == 0) oddcnt++;
						else evencnt++;
					}
					if (oddcnt != cnt && evencnt != cnt)
						d = 1;

					for (int k = 0; k < 4; k++) {
						q.offer(new Fire(r, c, m, s, mergeDir[d][k]));
					}
				} else if (cnt == 1) {
					q.offer(cur.get(0));
				}
			}
		}

		int answer = 0;
		while (!q.isEmpty()) {
			answer += q.poll().m;
		}
		System.out.println(answer);
	}

	private static int dirConverter(int d) {
		if (d < 0) {
			return N - 1;
		}
		if (d >= N) {
			return d = 0;
		}
		return d;
	}

}
