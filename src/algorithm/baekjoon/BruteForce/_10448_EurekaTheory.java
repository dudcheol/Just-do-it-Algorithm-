package algorithm.baekjoon.BruteForce;

/*
 * 10448 - 유레카 이론
 * 시작: 14:30
 * 끝: 16:00
 * 시간: 1시간 30분... <실수로인해 잡아먹은 시간이 너무 크다!!
 * 
 * [ 고쳐야할 점 ]
 * - 문제를 정확히 파악하고 설계한 후  코드로 옮겨야 한다
 * - 이 문제같은 경우 막연히 삼각수 세개를 더한 값을 구한다고 생각해서 '연속된'세개의 합만 구하다보니 미궁에 빠진 문제다...
 *   제발 설계를 먼저 해보고 코드로 옮기자 ㅠㅠ 이런 실수는 너무 치명적이다
 */

import java.util.*;

public class _10448_EurekaTheory {
	static int num[];

	static void triCheck(List<Integer> samgak) {
//		int[] t = new int[3];
		// 연속한 것이 아니다!!
//		t[0] = n * (n + 1) / 2;
//		t[1] = (n + 1) * (n + 2) / 2;
//		t[2] = (n + 2) * (n + 3) / 2;

//		if (t[0] + t[0] + t[0] > 1000)
//			return;

		for (int i = 0; i < samgak.size(); i++) {
			for (int j = 0; j < samgak.size(); j++) {
				for (int k = 0; k < samgak.size(); k++) {
					if (samgak.get(i) + samgak.get(j) + samgak.get(k) > 1000)
						continue;
					num[samgak.get(i) + samgak.get(j) + samgak.get(k)] = 1;
//					int T = t[i] + t[j] + t[k];
//					if (T > 1000)
//						continue;
//					if (num[T] == 1)
//						continue;
//					System.out.println("T" + (n + i)+"("+t[i]+")" + " + " + "T" + (n + j)+"("+t[j]+")" + " + " + "T" + (n + k) +"("+t[k]+")" + " = " + T);
//					num[T] = 1;
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		List<Integer> nl = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			nl.add(sc.nextInt());
		}

		// 삼각수를 구한다
		List<Integer> samgak = new ArrayList<>();
		for (int i = 1; i < 45; i++) {
			samgak.add(i * (i + 1) / 2);
		}

//		System.out.println(samgak);

		num = new int[1001];
		Arrays.fill(num, 0);

		triCheck(samgak);

		for (int _nl : nl) {
			System.out.println(num[_nl]);
		}
	}
}
