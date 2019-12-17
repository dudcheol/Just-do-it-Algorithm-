package just_do_it_Algorithm;

import java.util.*;

public class programmers_courses30_lessons42583 {

	/*
	 * 11;30 ~ 1:10
	 *  트럭은 1초에 1만큼 움직이며, 다리 길이는 bridge_length이고 다리는 무게 weight까지 견딥니다. *** 트럭이
	 * 다리에 완전히 오르지 않은 경우, 이 트럭의 무게는 고려하지 않습니다.
	 */

	static class Truck {
		int move;
		int weight;

		Truck(int m, int w) {
			this.move = m; // 다리 위의 위치
			this.weight = w;
		}
	}

	public static int solution(int bridge_length, int weight, int[] truck_weights) {
		int answer = 0; // 걸리는 초
		Stack<Truck> passing = new Stack<>(); // 다리를 건너는 트럭
		Stack<Truck> wait = new Stack<>(); // 대기 트럭

		// 대기 트럭에 트럭을 넣음
		for (int i = truck_weights.length - 1; i >= 0; i--) {
			wait.push(new Truck(0, truck_weights[i]));
		}

		int currentWeight = 0;
		// 다리를 건너는 트럭과 대기트럭이 없으면 반복문 종료
		currentWeight += wait.peek().weight;
		passing.push(wait.pop());
		while (!passing.isEmpty() || !wait.isEmpty()) {
			// 시간은 1초씩 증가
			answer++;

			// 다리 안에 있는 모든 트럭은 1씩 움직인다
			if (!passing.isEmpty()) {
				for (int i = 0; i < passing.size(); i++) {
					passing.elementAt(i).move++;
				}

				// 다리에 있는 트럭 중 가장 앞에 있는 애가 bridge_length만큼 갔는지 확인
				if (passing.elementAt(0).move == bridge_length) {
					currentWeight -= passing.elementAt(0).weight;
					passing.remove(0);
				}

				// 트럭을 더 건너게 해도 되면 passing에 대기트럭 추가
				if (!wait.isEmpty() && currentWeight <= weight) {
					if (currentWeight + wait.peek().weight <= weight) {
						currentWeight += wait.peek().weight;
						passing.push(wait.pop());
					}
				}
			}
		}

		return answer + 1;
	}

	public static void main(String[] args) {
//		int bridge_length = 2;
//		int weight = 10;
//		int[] truck_weights = { 7, 4, 5, 6 };

		int bridge_length = 100;
		int weight = 100;
		int[] truck_weights = { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 };

		System.out.println(solution(bridge_length, weight, truck_weights));
	}
}
