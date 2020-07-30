package algorithm.programmers.DFS_BFS;

/* 12:35 시작
 * 1:25 끝
 * n개의 음이 아닌 정수, 이 수를 적절히 더하거나 빼서 타겟 넘버를 만들려고 합니다
 *  -1+1+1+1+1 = 3
	+1-1+1+1+1 = 3
	+1+1-1+1+1 = 3
	+1+1+1-1+1 = 3
	+1+1+1+1-1 = 3
	주어지는 숫자의 개수는 2개 이상 20개 이하입니다.
	각 숫자는 1 이상 50 이하인 자연수입니다.
	타겟 넘버는 1 이상 1000 이하인 자연수입니다.
 */

public class programmers_courses30_lessons43165 {
	static int answer = 0;
	
	static void dfs(int[] numbers, int i, int sum, int target) {
		if(i>=numbers.length-1) {
			if(sum==target) answer++;
			return;
		}
		dfs(numbers, i+1, sum+numbers[i+1], target);
		dfs(numbers, i+1, sum-numbers[i+1], target);
	}
	
	static int solution(int[] numbers, int target) {	
		dfs(numbers, 0, numbers[0], target);
		dfs(numbers, 0, -numbers[0], target);
		
		return answer;
	}

	public static void main(String[] args) {
		int[] numbers = { 1, 1, 1, 1, 1 };
		int target = 3;
		System.out.println(solution(numbers, target));
	}
}
