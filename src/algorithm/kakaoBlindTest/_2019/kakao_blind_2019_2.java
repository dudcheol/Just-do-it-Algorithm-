package algorithm.kakaoBlindTest._2019;

import java.util.*;

/*
 * 프로그래머스 - 코딩테스트 연습 - 2019 KAKAO BLIND RECRUITMENT - 실패율
 * 시작 23:35
 * 끝  23:59
 * 걸린시간 24분
 * 
 * [배운 것]
 * -Arrays.sort는 매우 유용하다
 * -제공하는 메소드 중 Comparator를 사용할 줄 아는 것은 정렬문제에서 도움이 된다.
 * -배열은  Arrays.sort , 리스트는 Collections.sort
 * -Comparable은 클래스에 implements해서 compareTo하는 것. Comparable을 구현한 클래스의 객체에 구현된 내용에 따라 정렬(기본적으로 제공되는 정렬들은 얘를 쓰고있는거라고 보면됨)
 * -Comparator는 익명클래스로 많이 쓰는데, 객체에 구현되어있는 comparable말고 내가 따로 구현하고 싶은 정렬기준을 만들 수 있음
 */

//실패율 = 스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수 / 스테이지에 도달한 플레이어 수
//전체 스테이지의 개수 N,게임을 이용하는 사용자가 현재 멈춰있는 스테이지의 번호가 담긴 배열 stages
//실패율이 높은 스테이지부터 내림차순으로 스테이지의 번호가 담겨있는 배열을 return
//단, N + 1 은 마지막 스테이지(N 번째 스테이지) 까지 클리어 한 사용자
public class kakao_blind_2019_2 {
	static class Stage {
		int stage;
		double fail;

		Stage(int s, double f) {
			this.stage = s;
			this.fail = f;
		}
	}

	static int[] solution(int N, int[] stages) {
		int[] answer = new int[N];
		int size = stages.length;
		Stage[] s = new Stage[N];

		for (int i = 0; i < N; i++) {
			int currentStageCnt = 0;
			int challenger = 0;
			for (int j = 0; j < size; j++) {
				if (i + 1 == stages[j]) {
					currentStageCnt++;
				}
				if (i + 1 <= stages[j]) {
					challenger++;
				}
			}
			//스테이지에 도달한 유저가 없는 경우 해당 스테이지의 실패율은 0
			double fail=0;
			if(currentStageCnt == 0) fail = 0;
			else fail = (double) currentStageCnt / (double) challenger;
			s[i] = new Stage(i + 1, fail);
		}

		Arrays.sort(s, new Comparator<Stage>() {
			@Override
			public int compare(Stage o1, Stage o2) {
				if (o1.fail > o2.fail) {
					return -1;
				} else if (o1.fail < o2.fail) {
					return 1;
				} else {
					//만약 실패율이 같은 스테이지가 있다면 작은 번호의 스테이지가 먼저 오도록 
					if (o1.stage > o2.stage) {
						return 1;
					} else
						return -1;
				}
			}
		});

		for (int i = 0; i < N; i++) {
			answer[i] = s[i].stage;
		}

		return answer;
	}

	public static void main(String[] args) {
//		int N = 5;
//		int[] stages = { 2, 1, 2, 6, 2, 4, 3, 3 };
		int N = 4;
		int[] stages = { 4,4,4,4,4 };

		for (int n : solution(N, stages)) {
			System.out.print(n + ", ");
		}
	}
}
