package algorithm.kakaoBlindTest._2019;

import java.util.*;

/*
 * 프로그래머스 - 코딩테스트 연습 - 2019 KAKAO BLIND RECRUITMENT - 후보키
 * 
 * [배운것]
 * HashSet을 이용한 유일성 판단 (배열을 Set에 전부 넣고 난 후 배열의 길이와 Set의 길이가  같은지 판단)
 * HashSet으로 부분집합 여부, 차집합, 합집합 등 집합연산이 가능하다!! 많이 필요하다면 Set배열을 만들어서 비교하는 작업이  필요~~!!
 * "완전탐색" 공부하고 풀자 -> 멱집합만들기(어떤 집합에 존재하는 모든 부분집합 구하기)
 *
 * [고쳐야할 것]
 * 나는 코드가 진행되는 과정 중에 다른 문제를 해결하려는 경향이 있다.
 * 하지만 이렇게하면 복잡해져서 더 어려워질 수 있다.
 * 한 코드에서는 한가지 작업만해서 다음 작업은 다음 코드에서 진행하는 식으로 하면 덜 복잡해진다.
 * 이 문제같은 경우, 3파트로 나눠서 1) 모든 부분집합 구하기, 2) 부분집합 중 유일성인 것들만 찾은 유일성집합 찾기, 3) 유일성집합 중 최소성집합찾기
 * 로 풀면 되는데, 나는 1)의 과정에서 2와  3까지 해버릴려고 하니까 상당히 복잡해지는거다.
 * 문제를 풀 때, 큰 단락을 정해서 풀어보자!!!!! 그리고 굳이 한꺼번에 안해도 되는 작업은 나눠서 해보자~~!!!!
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
//		List<String> minimal = new ArrayList<>();
		HashSet<String>[] set = new HashSet[unique.size()];
		int i = 0;
		for (String m : unique) {
			String[] mAry = m.split(" ");
			set[i] = new HashSet<>();
			for (String st : mAry) {
				set[i].add(st);
			}
			i++;
		}

		List<HashSet<String>> answer = new ArrayList<>();

		for (int k = 0; k < set.length; k++) {
			if(answer.isEmpty()) {
				answer.add(set[k]);
				continue;
			}
			int cnt=0;
			for(HashSet hs : answer) {
				if(!set[k].containsAll(hs)) {
					cnt++;
				}
			}
			if(cnt==answer.size()) {
				answer.add(set[k]);
			}
		}

		System.out.println(answer);
//		if (!unique.isEmpty())
//			minimal.add(unique.get(0));
//		for (String uni : unique) {
//			boolean isMin = true;
//			int cnt = 0;
//			int minSize = 0;
//			for (String min : minimal) {
//				String[] mins = min.split(" ");
//				minSize = mins.length;
//				for (int i = 0; i < mins.length; i++) {
//					if (uni.contains(mins[i])) {
//						cnt++;
//					}
//				}
//				if (cnt == minSize) {
//					isMin = false;
//					break;
//				}
//			}
//			if (isMin)
//				minimal.add(uni);
//		}

//		System.out.println(minimal);

//		return minimal.size();
		return answer.size();
	}

	public static void main(String[] args) {
		String[][] relation = { { "100", "ryan", "music", "2" }, { "200", "apeach", "math", "2" },
				{ "300", "tube", "computer", "3" }, { "400", "con", "computer", "4" }, { "500", "muzi", "music", "3" },
				{ "600", "apeach", "music", "2" } };

		System.out.println(solution(relation));
	}
}
