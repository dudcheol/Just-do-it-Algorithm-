package baekjoon;

/*
 * 백준 - 1700 - 그리디 - 멀티탭 스케줄링
 * 시간: 5시간... 답보고도 이해못하면서 푼 문제
 * [배운 것]
 * - 발생할 수 있는 '분기'를 모두 생각하고 그 분기별로 처리해야 할 것들을 분명히 한다
 * - 큰 틀(분기)을 먼저 생각해놓고 틀 별로 필요한 처리들을 하는 식으로 하는게 도움이 될 듯 싶다
 * - 그리디 알고리즘 : 현재 알고있는 것으로  가장 최선의 선택을  하는 것
 * 				  이 문제에선 앞으로 멀티탭에 꽂을 물건들이 뭔지 전부 알고있으므로
 * 				  다음에 꽂힐 물건들 중 가장 나중에 꽂힐 물건을 찾으면 되는 문제였다.
 * 				<여기서  주의!!>
 * 				가장 나중에 꽂힐 물건이라고 맨 마지막에 꽂힐 물건이 아니라,
 * 				꽂혀있는 물건들 중 가장 나중에 꽂을 물건을 말하는 것이다!!
 */

import java.util.*;

public class _1700_mulitapScaduling {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int answer = 0;
		int hole = sc.nextInt();
		int size = sc.nextInt();
		int[] use = new int[size];

		for (int i = 0; i < size; i++) {
			use[i] = sc.nextInt();
		}

		// 물건 사용하는 순서를 조회하면서
		// 플러그 리스트 채움
		int fulled = 0;
		// 현재 꽂혀있는 물건을 담은 해시맵 plugNow, value는 대기열의 몇번째에서 등장하는지 나타냄
		HashMap<Integer, Integer> plugNow = new HashMap<>();
		for (int i = 0; i < size; i++) {
			if (plugNow.size() < hole) {
				plugNow.put(use[i], -1);
				System.out.println(use[i]+" -> "+plugNow+" => "+answer);
			} else {
				if (plugNow.containsKey(use[i])) {
					System.out.println(use[i]+" -> "+plugNow+" => "+answer);
					continue;
				} else {
					// 멀티탭이 가득 차 있지 않다면
					if(plugNow.size() < hole) {
						plugNow.put(use[i], -1);
						System.out.println(use[i]+" -> "+plugNow+" => "+answer);
						continue;
					}
					for (int p : plugNow.keySet()) {
						plugNow.put(p, -1);
					}
					// plugNow에 있는 물건들에서 앞으로 꽂혀질 것들 중 가장 나중에 꽂혀질 물건을 찾는다
					for (int j = i+1; j < size; j++) {
						if (plugNow.containsKey(use[j]) && plugNow.get(use[j])==-1) {
							// 몇번째에 등장하는지 value에 넣는다 -> 수가 클수록 나중에 등장
							plugNow.put(use[j], j);
						}
					}
					// value가 -1이면 앞으로 한번도 꽂을일이 없는 물건이므로 그것을 우선적으로 뺀다
					int subValue = -1; // 뺄 물건이 몇번째 등장하는 지 확인
					int find = -1;
					int subIdx = -1;
					for (int p : plugNow.keySet()) {
						if (plugNow.get(p) == -1) {
							subIdx = p;
							break;
						} else {
							find = Math.max(subValue, plugNow.get(p));
							if (find != subValue) { // 더 큰 값이 있다는 뜻
								subValue = find;
								subIdx = p;
							}
						}
					}
					// PlugNow에 나중에 등장할 물건을 멀티탭에서 뺀다
					plugNow.remove(subIdx);
					plugNow.put(use[i], -1);
					answer++;
				}
				System.out.println(use[i]+" -> "+plugNow+" => "+answer);
			}
		}
		System.out.println(answer);
	}
}
