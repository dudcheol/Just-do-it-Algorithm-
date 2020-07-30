package algorithm.programmers.skillCheck.level2;

public class skill_test_level2_q1_try3 {
	static boolean[] visited;
	static int answer;

	static int solution(int[] nums) {
		
		visited = new boolean[nums.length];
		sumSosu(0, nums, 0, 0);

		return answer;
	}
	
	static void sumSosu(int k, int[] nums, int res, int idx) {
		if(k==3) {
			if(isSosu(res)) {
				answer++;
			}
			return;
		}
		
		for(int i=idx;i<nums.length;i++) {
			if(visited[i]) continue;
			visited[i]=true;
			sumSosu(k+1, nums, res+nums[i], i);
			visited[i]=false;
		}
	}
	
	static boolean isSosu(int res) {
		int cnt=0;
		for(int i=2;i<res;i++) {
			if(res%i==0)cnt++;
		}
		
		if(cnt==0) return true;
		else return false;
	}

	public static void main(String[] args) {
		int[] nums = {1,2,7,6,4};
		
		System.out.println(solution(nums));
	}
}
