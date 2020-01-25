package baekjoon;

/*
 * 백준 - 1700 - 그리디 - 멀티탭 스케줄링
 * 시작: 14:45
 * 끝: 15:50 (못풀었음)
 * 시간:
 */

import java.util.*;

public class _1700_mulitapScaduling {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int answer = 0;
		int hole = sc.nextInt();
		int size = sc.nextInt();
		int[] use = new int[size];
		HashMap<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < size; i++) {
			use[i] = sc.nextInt();
		}

		HashMap<Integer, Boolean> m = new HashMap<>();
		// 물건의 종류를 알아냄
		for (int i = 0; i < size; i++) {
			m.put(use[i], false);
		}

		System.out.println(m);

		// 물건 사용하는 순서를 조회하면서
		// 플러그 리스트 채움
		int fulled = 0;
		for (int i = 0; i < size; i++) {
			if (fulled < hole) {
				if (!(m.get(use[i]))) {
					m.put(use[i], true);
					fulled++;
					System.out.println(m);
				}
			}else {
				if(m.get(use[i])) {
					continue;
				} else {
					List<Integer> goPlug = new ArrayList<>(); // 플러그에 꽂힐 물건 중 이미 꽂혀있는 것들 리스트
					int k=0;
					for(int j=i;j<size;j++) {
						if(m.get(use[j])) {
							if(goPlug.contains(use[j])) {
								continue;
							} else {
								goPlug.add(use[j]);
							}
							if(goPlug.size()>=hole) {
								break;
							}
						}
						k++;
					}
					if(goPlug.isEmpty()) {
						answer+=k;
						continue;
					}
					// goPlug에 없는 것을 플러그에서 빼야함
					for(int _m : m.keySet()) {
						if(m.get(_m)) {
							if(!goPlug.contains(_m)) {
								m.put(use[_m], false);
							}
						}
					}
//					m.put(use[goPlug.get(goPlug.size()-1)], false);
					m.put(use[i], true);
					answer++;
					goPlug.clear();
				}
				System.out.println(m);
			}
		}
		System.out.println(answer);
	}
}
