package algorithm.swea.D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class _1251_하나로 {
	static int N;
	static Island[] islands;
	static double E;
	static ArrayList<Edge> list;
	static int[] set;

	static class Island {
		int num;
		int y;
		int x;

		public Island(int num) {

			this.num = num;
		}
	}

	static class Edge implements Comparable<Edge> {
		Island from;
		Island to;
		double cost;

		public Edge(Island from, Island to, double cost) {

			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			if (this.cost > o.cost) {
				return 1;
			} else if (this.cost < o.cost) {
				return -1;
			} else
				return 0;
		}
	}

	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= TC; test_case++) {
			double answer = 0;
			N = Integer.parseInt(br.readLine());
			islands = new Island[N];
			set = new int[N];

			for (int i = 0; i < N; i++) {
				islands[i] = new Island(i);
				set[i] = i;
			}

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				islands[i].x = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				islands[i].y = Integer.parseInt(st.nextToken());
			}
			
			E = Double.parseDouble(br.readLine());

			// 간선 정보 채우기 (거리)
			list = new ArrayList<Edge>();
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					double cost = E * (Math.pow(Math.abs(islands[i].x - islands[j].x),2) + Math.pow(Math.abs(islands[i].y - islands[j].y),2));
					list.add(new Edge(islands[i], islands[j], cost));
				}
			}

			// 간선 순서로 정렬
			Collections.sort(list);
			int cnt = 0;
			for (int i = 0; i < list.size(); i++) {
				if (cnt == N - 1) /*이거 까먹고 있었다*/
					break;
				Edge cur = list.get(i);

				if (union(cur.from.num, cur.to.num)) {
					answer += cur.cost;
					cnt++;
				}
			}
			
			answer = Math.round(answer);

			sb.append('#').append(test_case).append(' ').append((long)answer).append('\n');
		}
		System.out.print(sb);
	}

	private static int find(int x) {
		if (set[x] == x) {
			return x;
		}
		return set[x] = find(set[x]);
	}

	private static boolean union(int x, int y) {
		int xRoot = find(x);
		int yRoot = find(y);

		if (xRoot == yRoot) {
			return false;
		}

		set[yRoot] = xRoot;
		return true;
	}

}

/*
#1 10000
#2 180000
#3 1125000
#4 1953913
#5 27365366
#6 337122
#7 711268755613
#8 280157
#9 521568761
#10 34
#11 375890356686
#12 68427157
#13 21404
#14 16620885
#15 4776395492
#16 54860981981
#17 24236206202
#18 132410
#19 12876964085
#20 7016649393
 */
