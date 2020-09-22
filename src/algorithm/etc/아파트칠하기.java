package algorithm.etc;

public class 아파트칠하기 {
	static int[] color;
	static int answer;

	public static void main(String[] args) {

		color = new int[8];

		f(0, 0, 0);

		System.out.println(answer);
	}

	private static void f(int k, int yCnt, int bCnt) {
		if (k == 8) {
			answer++;
			return;
		}

		// 현재 시점 k에서 다음에 올 수 있는 색을 구하자
		if (yCnt == 0) {
			// 노랑, 파랑 가능
			f(k + 1, 1, 0);
			if (bCnt == 0) // 단, 이전이 파랑이 아니어야 함
				f(k + 1, 0, 1);
		}

		else if (yCnt == 1) {
			// 노랑, 파랑 가능
			f(k + 1, 2, 0);
			f(k + 1, 0, 1);
		}

		else if (yCnt == 2) {
			// 파랑 가능
			f(k + 1, 0, 1);
		}
	}

}
