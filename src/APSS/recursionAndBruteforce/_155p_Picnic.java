package APSS.recursionAndBruteforce;

/*
 * 알고리즘 문제해결 전략 - 155p - 소풍
 * 시작~끝: 09:40~10:30 (완전히잘못품)
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

public class _155p_Picnic {
	static int n, m;
	static int answer = 0;
	static int[] num;
	static boolean[] visited;
	static int[][] couple;
	static HashSet<Integer> set = new HashSet<>();
	static HashSet<String> temp = new HashSet<>();
	static HashSet<HashSet<String>> answerSet = new HashSet<>();

	static void makeAllCouple(int k) {
		if (k > n / 2 - 1)
			return;
		if (k == n / 2 - 1) {
			String str = "";
			for (int i = 0; i < n / 2; i++) {
				int idx = num[i];
				String make = couple[idx][0] + "" + couple[idx][1];
				str += make;
				set.add(couple[idx][0]);
				set.add(couple[idx][1]);
//				temp.add(make);
			}

			if (set.size() != n) {
				set.clear();
				return;
//				temp.clear();
			}

//			if (!answerSet.contains(temp)) {
//				answerSet.add(temp);
//				System.out.println(str);
//				answer++;
//			}

			answer++;
			System.out.println(str);
			set.clear();
//			temp.clear();

			return;
		}

		for (int i = /* 포인트 */k + 1; i < m; i++) {
			num[k + 1] = i;
			makeAllCouple(k + 1);
		}
	}

//	static boolean compare(int k, String str) {
//		if (k > n / 2)
//			return false;
//		if (str.length() == 0)
//			return true;
//		for (int i = 0; i < couple.length; i++) {
//			String co = couple[i][0] + "" + couple[i][1];
//			if (str.substring(0, 2).equals(co)) {
//				if (compare(k + 1, str.substring(2))) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}

	static boolean[][] areFriends = new boolean[10][10];

	// taken[i] = i번째 학생이 짝을 이미 찾았으면 true, 아니면 false
	static int countPairings(boolean[] taken) {
		// BaseCase : 모든 학생이 짝을 찾았으면 한 가지 방법을 찾았으니 종료한다.
//		boolean finished = true;					 			=> 중복발생
//		for (int i = 0; i < n; i++) {
//			if (!taken[i])
//				finished = false;
//		}
//		if (finished)
//			return 1;
		int firstFree = -1;
		for (int i = 0; i < n; i++) {
			if (!taken[i]) {
				firstFree = i;
				break;
			}
		}
		if (firstFree == -1) // 위 반복문에서 taken이 false인 경우가 없었다는 의미이므로
			return 1;
		int ret = 0;
		// 서로 친구인 두 학생을 찾아 짝을 지어 준다.
//		for (int i = 0; i < n; i++) {						=> 처음부터 다시 다 구하면 중복발생한다
//			for (int j = 0; j < n; j++) {					=> firstFree 이후만 구해서 가장 번호가 빠른 사람의 짝만 구함
		for (int i = firstFree + 1; i < n; i++) {
			if (!taken[i] && areFriends[firstFree][i]) {
				taken[firstFree] = taken[i] = true;
				ret += countPairings(taken);
				taken[firstFree] = taken[i] = false;
			}
		}
		return ret;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		n = 0;
		m = 0;

		for (int tc = 0; tc < TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			num = new int[n / 2];
			visited = new boolean[m];
			Arrays.fill(num, -1);
			couple = new int[m][2];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < m; i++) {
//				couple[i][0] = Integer.parseInt(st.nextToken());
//				couple[i][1] = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				areFriends[x][y] = true;
				areFriends[y][x] = true;
			}

//			/**/Arrays.sort(couple, new Comparator<int[]>() {
//				@Override
//				public int compare(int[] o1, int[] o2) {
//					if (o1[0] == o2[0]) {
//						return Integer.compare(o1[1], o2[1]);
//					}
//					return Integer.compare(o1[0], o2[0]);
//				}
//			});

			boolean[] taken = new boolean[10];

			answer = countPairings(taken);

//			makeAllCouple(taken);

			// 얘는 4명일때만 가능..
//			for (int i = 0; i <= m - 1; i++) {
//				int[] target = couple[i];
//				for (int j = i + 1; j <= m - 1; j++) {
//					if (target[0] == couple[j][0] || target[0] == couple[j][1] || target[1] == couple[j][0]
//							|| target[1] == couple[j][1]) {
//						continue;
//					} else {
//						answer++;
//					}
//				}
//			}

			System.out.println(answer);
			answer = 0;
			for (boolean[] f : areFriends)
				Arrays.fill(f, false);
		}

//		HashMap<Integer, List<Integer>> frd = new HashMap<>();
//
//		for (int tc = 0; tc < TC; tc++) {
//			StringTokenizer st = new StringTokenizer(br.readLine());
//			int n = Integer.parseInt(st.nextToken());
//			int m = Integer.parseInt(st.nextToken());
//			st = new StringTokenizer(br.readLine());
//			for (int i = 0; i < m; i++) {
//				int key = Integer.parseInt(st.nextToken());
//				int value = Integer.parseInt(st.nextToken());
//				if (frd.containsKey(key)) {
//					List<Integer> temp = frd.get(key);
//					temp.add(value);
//					frd.put(key, temp);
//				} else {
//					List<Integer> temp = new ArrayList<Integer>();
//					temp.add(value);
//					frd.put(key, temp);
//				}
//			}
//			
//			for(int i=0;i<3;i++) {
//				for(int j=i+1;j<3;j++) {
//					if()
//				}
//			}
//			
//			for(int key : frd.keySet()) {
//				frd.get(key).clear();
//			}
//		}

		br.close();
	}
}