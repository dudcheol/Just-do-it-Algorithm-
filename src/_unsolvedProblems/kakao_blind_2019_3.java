package _unsolvedProblems;

import java.util.*;

/*
 * 프로그래머스 - 코딩테스트 연습 - 2019 KAKAO BLIND RECRUITMENT - 후보키
 * 시작 : 14:40
 * 끝 : 16:00 모르겠음
 * 
 * 다시도전
 * 시작  : 09:50
 * 끝 : 
 * 걸린시간 : 
 * 
 * [배운것]
 * HashSet을 이용한 유일성 판단 (배열을 Set에 전부 넣고 난 후 배열의 길이와 Set의 길이가  같은지 판단)  
 * 
 * "완전탐색" 공부하고 풀자
 * "가능한 모든 조합만들기" 공부하고 풀자 - https://bcp0109.tistory.com/15
 */

//유일성(uniqueness) : 릴레이션에 있는 모든 튜플에 대해 유일하게 식별되어야 한다.
//최소성(minimality) : 유일성을 가진 키를 구성하는 속성(Attribute) 중 하나라도 제외하는 경우 유일성이 깨지는 것을 의미한다. 
//					 즉, 릴레이션의 모든 튜플을 유일하게 식별하는 데 꼭 필요한 속성들로만 구성되어야 한다.

public class kakao_blind_2019_3 {
	private static int answer;
	private static List<String> subset;

	private static void powerSet(List<Integer> rl, int k, boolean[] include) {
		if (k == rl.size()) {
			String sub = "";
			for (int i = 0; i < rl.size(); i++) {
				if (include[i])
					sub += (rl.get(i).toString() + " ");
			}
			subset.add(sub);
			return;
		}
		include[k] = false;
		powerSet(rl, k + 1, include);
		include[k] = true;
		powerSet(rl, k + 1, include);
	}

	public static int solution(String[][] relation) {
		answer = 0;
		int totalCardi = relation[0].length;
		int totalTuple = relation.length;
//		List[] rl = new ArrayList[totalCardi];
		List<Integer> rl = new ArrayList<>();

		for (int i = 0; i < totalCardi; i++) {
//			rl[i] = Arrays.asList(relation[i]);
			rl.add(i);
		}

		// 1. 모든 부분집합을 구함
		subset = new ArrayList<>();
		powerSet(rl, 0, new boolean[totalCardi]);
		System.out.println(subset);

		Collections.sort(subset, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if (o1.length() == o2.length()) {
					return o1.compareTo(o2);
				}
				return Integer.compare(o1.length(), o2.length());
			}
		});
		System.out.println(subset);

		// 2. 부분집합들 중 유일성을 만족하는 것들 구함
		List<String> unique = new ArrayList<>();
		for (String s : subset) {
			if (s.isEmpty())
				continue;
			String[] idxs = s.split(" "); // 집합에 속해잇던 인덱스들
			HashSet<String> set = new HashSet<>();

			for (int j = 0; j < totalTuple; j++) {
				String hap = "";
				for (int k = 0; k < idxs.length; k++) {
					int idx = Integer.parseInt(idxs[k]);
					hap += relation[j][idx];
				}
				set.add(hap);
			}

			// 유일성 확인
			if (set.size() == totalTuple) {
				unique.add(s);
			}
		}
		System.out.println(unique);

		// 3. 유일성을 만족하는 것들 중 최소성에 위배되는 애들 제거
		// 이미 가지고 있는 작은 부분집합을 큰 부분집합이 가지고 있을때 그것은 최소성 X
		List<String> minimal = new ArrayList<>();
		if (!unique.isEmpty())
			minimal.add(unique.get(0));
		for (String uni : unique) {
			boolean isMin = true;
			int cnt = 0;
			int minSize = 0;
			for (String min : minimal) {
				String[] mins = min.split(" ");
				minSize = mins.length;
				for (int i = 0; i < mins.length; i++) {
					if (uni.contains(mins[i])) {
						cnt++;
					}
				}
				if (cnt == minSize) {
					isMin = false;
					break;
				}
			}
			if (isMin)
				minimal.add(uni);
		}

		System.out.println(minimal);

		return minimal.size();
	}

	public static void main(String[] args) {
		String[][] relation = { { "100", "ryan", "music", "2" }, { "200", "apeach", "math", "2" },
				{ "300", "tube", "computer", "3" }, { "400", "con", "computer", "4" }, { "500", "muzi", "music", "3" },
				{ "600", "apeach", "music", "2" } };

		System.out.println(solution(relation));
	}
}
