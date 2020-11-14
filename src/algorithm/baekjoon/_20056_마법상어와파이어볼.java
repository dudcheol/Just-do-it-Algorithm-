package algorithm.baekjoon;

import java.util.*;
import java.io.*;

// m질량, d방향, s속력, rc r행c열
// 1번부터 시작
public class _20056_마법상어와파이어볼 {

	static int N, M, K, visited[][];
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int[][] mergeDir = {{0,2,4,6},{1,3,5,7}};
	private static Map[][] map;

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
	}
	
	static class Map{
		int m;
		int s;
		ArrayList<Integer> ds;
		
		public Map() {
			this.ds = new ArrayList<>();
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visited = new int[N][N];
		map = new Map[N][N];

		Queue<Fire> q = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new Map();
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			q.offer(new Fire(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1,
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())));
		}

		while (!q.isEmpty()) {
			if(K-- == 0) break;
			initMap();
			visited = new int[N][N];
			
			int size = q.size();
			while (size-- != 0) {

				Fire f = q.poll();

				int nr = f.r;
				int nc = f.c;
				for (int i = 0; i < f.s; i++) {
					nr = dirConverter(nr + dr[f.d]);
					nc = dirConverter(nc + dc[f.d]);
				}
				map[nr][nc].m += f.m;
				map[nr][nc].s += f.s;
				map[nr][nc].ds.add(f.d);
				visited[nr][nc]++;
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					Map cur = map[i][j];
					if(visited[i][j] >= 2) {
						int m = cur.m/5;
						if(m==0) continue;
						int s = cur.s/visited[i][j];
						int d = 0;
						int dssize = cur.ds.size();
						
						int oddcnt = 0;
						int evencnt = 0;
						for (int k = 0; k < dssize; k++) {
							if(cur.ds.get(k)%2==0) {
								oddcnt++;
							}else {
								evencnt++;
							}
						}
						if(oddcnt != visited[i][j] && evencnt != visited[i][j])
							d = 1;
						
						for (int k = 0; k < 4; k++) {
							q.offer(new Fire(i,j,m,s,mergeDir[d][k]));
						}
					} else if(visited[i][j] == 1) {
						q.offer(new Fire(i,j,cur.m,cur.s,cur.ds.get(0)));
					}
				}
			}
		}
		
		int answer = 0;
		while(!q.isEmpty()) {
			answer += q.poll().m;
		}
		System.out.println(answer);
	}

	private static int dirConverter(int d) {
		if(d<0) {
			return N-1;
		}
		if(d>=N) {
			return d=0;
		}
		return d;
	}

	private static void initMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j].m = 0;
				map[i][j].s = 0;
				map[i][j].ds.clear();
			}
		}
	}

}
