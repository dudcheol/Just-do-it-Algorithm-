package kakaoBlindTest._2019;

import java.util.LinkedList;
import java.util.Queue;

import com.sun.xml.internal.ws.policy.spi.AbstractQNameValidator;

/*
 * 프로그래머스 - 코딩테스트 연습 - 2019 KAKAO BLIND RECRUITMENT - 무지의 먹방 라이브
 * 시작: 20:55
 * 끝 :
 * 걸린시간:
 * 
 * [배운 것]
 * 효율성 테스트인 문제는 효율을 꼭 생각해서 풀어야 한다.
 */

public class kakao_blind_2019_4 {
	// 무지는 1번 음식부터 먹기 시작하며, 회전판은 번호가 증가하는 순서대로 음식을 무지 앞으로 가져다 놓는다.
	// 마지막 번호의 음식을 섭취한 후에는 회전판에 의해 다시 1번 음식이 무지 앞으로 온다.
	// 무지는 음식 하나를 1초 동안 섭취한 후 남은 음식은 그대로 두고, 다음 음식을 섭취한다. 다음 음식이란, 아직 남은 음식 중 다음으로
	// 섭취해야 할 가장 가까운 번호의 음식을 말한다.
	// 회전판이 다음 음식을 무지 앞으로 가져오는데 걸리는 시간은 없다고 가정한다.
	// 만약 더 섭취해야 할 음식이 없다면 -1을 반환
//	static class Food {
//		int name;
//		int time;
//
//		Food(int n, int t) {
//			this.name = n;
//			this.time = t;
//		}
//	}

	static int solution(int[] food_times, long k) {
		int answer = 0;
		int fSize = food_times.length;
		int mok = (int) (k / fSize);
		int rest = (int) (k - (fSize * mok));
		int use = mok * fSize;

		int i = 0;
		
		while (mok!=0) {
			if (food_times[i] == 0) {
				i++;
				continue;
			}
			int sub = food_times[i] - mok;
			if (sub < 0) {
				food_times[i] = 0;
				use -= sub;
			} else {
				food_times[i] = sub;
				use -= mok;
			}
			
			if (i == fSize - 1) {
				i = 0;
			} else {
				i++;
			}
			if (use == 0) {
				break;
			}
		}

		int zeroCnt=0;
		while (true) {
			if(rest == 0) {
				answer = i+1;
				break;
			}
			if(zeroCnt == fSize) {
				answer = -1;
				break;
			}
			
			if (food_times[i] == 0) {
				zeroCnt++;
			} else {
				food_times[i] -= 1;
				rest--;
			}
			
			if (i == fSize - 1) {
				i = 0;
			} else {
				i++;
			}
		}
		
		return answer;

//		Queue<Food> q = new LinkedList<>();
//		for (int i=0;i<food_times.length;i++) {
//			q.offer(new Food(i+1, food_times[i]));
//			ftSum += food_times[i];
//		}
//		// 시뮬레이션 : 0초부터 시작
//		while (true) {
//			if (ftSum < k) {
//				answer = -1;
//				break;
//			}
//			if (time == k) {
//				if (q.isEmpty())
//					answer = -1;
//				else
//					answer = q.peek().name;
//				break;
//			}
//			Food polled = q.poll();
//			polled.time--;
//			if (polled.time > 0) {
//				q.offer(polled);
//			}
//			time++;
//		}

//		return answer;
	}

	public static void main(String[] args) {
		int[] food_times = { 0 };
		long k = 1;

		System.out.println(solution(food_times, k));
	}
}
