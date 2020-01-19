package programmers.bruteForce;

/*
 * 프로그래머스 - 코딩테스트 연습 - 완전탐색 - 카펫
 * 시작: 23:20
 * 끝: 23:42
 * 시간: 22분
 * [잘한 것]
 * - 손으로 풀어보면서 수학적으로 접근한 것 매우 잘했음!
 */

public class _42842_carpet {
	static int[] solution(int brown, int red) {
		int[] answer = {,};
		
		// red의 공약수 구하기
		for(int i=1;i<=Math.sqrt(red);i++) {
			if(red%i==0) {
				//공약수
				int x = red/i + 2;
				int y = i + 2;
				if(x*y-red==brown) {
					answer = new int[]{x, y};
					break;
				}
			}
		}
		
		return answer;
	}

	public static void main(String[] args) {
//		int brown = 10;
//		int red = 2;
		int brown = 8;
		int red = 1;
		for (int s : solution(brown, red)) {
			System.out.print(s+", ");
		}
	}
}
