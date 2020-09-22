package algorithm.etc;

public class 아파트칠하기2 {
	static int answer;
	static int[] memo;

	public static void main(String[] args) {

		memo = new int[8 + 1];

		memo[1] = 2;
		memo[2] = 3;
		
		System.out.println(f(8));
	}

	private static int f(int n) {
		if (memo[n] == 0 && n >= 2) {
			memo[n] = f(n - 1) + f(n - 2);
		}
		return memo[n];
	}

}
