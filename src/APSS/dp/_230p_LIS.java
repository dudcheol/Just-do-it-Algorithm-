package APSS.dp;

/*
 * 알고리즘 문제해결 전략 - 230p - 최대 증가 부분 수열
 * 시작~끝: 09:40~ 1) 10:45 .. 결국 교재 참고 ㅠㅠ
 * 걸린시간: 1) 1시간5분 - 오답(문제를 잘못이해함)
 * - 틀렸다! -
 *	[오답 원인]
	1. 처음엔 문제를 잘못이해했음. 문제에서 주어지는 예제를 반드시 숙지할 것!!
	2. 교재를 보지 않고 진행했던 나만의 풀이방법이 나쁘지 않았지만, 내가 푼 풀이에 대한 깊은 이해가 부족해서 틀렸다고 생각한다
	구현했던 lis(a(인덱스),b(카운트))는  "a 인덱스로 시작했을 때 가능한 최대증가부분수열"만을 구할 뿐이다.
	구해야 하는 답은 nums배열에 있는 모든 수를 이용한 최대증가부분수열을 구하는 것이므로 각 원소마다 시작점을 두어 계산한 후 그 중 가장 큰 값을 찾았어야 했다.
	
 *	[다른 사람 코드 참고 -> 내가 취했던 접근 되돌아보기 -> 나는 왜 이 풀이를 떠올리지 못했나?] 
	- 위 문제점을 인식은 하고 있었지만 해결방법이 생각나질 않았는데, 꼭 재귀 내에서 해결하려고만 했다. 그러지말고 재귀를 처음 호출하는 부분에서 반복하여도 된다는 것을 알았다.
	- 문제를 조각내어 풀어나간다는 생각자체를 못하고 있다. 조각낸 문제들이 모두 같은 행동을 수행한다는 것을 유념
	- cache는 재귀를 진행해가면서 저장하는게 아니라 재귀가 리턴되면서 저장하는 것이고, 그 때의 상태트리 깊이는 가장 밑일 것이다.
	상태트리 가장 밑에서 구한 답을 캐시에 저장해놓고, 리턴해서 위로 올라온 상태에서 또 다시 왔던 곳을 진입해야되는 일이 생길때 미리 계산해둔 것을 사용하여
	재계산을 막음으로써 효율을 올리는 것이다
 */

import java.util.*;
import java.io.*;

public class _230p_LIS {
	static int n;
	static int[] nums;
	static int[] cache;

	static int lis(int start) {
		// 문제를 조각내서 생각하자!
		// 캐시확인
		if (cache[start] != -1)
			return cache[start];

		cache[start] = 1;
		for (int next = start + 1; next < n; next++) {
			if (nums[start] < nums[next]) {
				cache[start] = Math.max(cache[start], lis(next) + 1); // start보다 크므로 다음 원소로 넘어가되 현재 원소의 갯수를 카운트함
			}
		}
		return cache[start];
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < TC; tc++) {
			n = Integer.parseInt(br.readLine());
			nums = new int[n];
			cache = new int[n];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.fill(cache, -1);

			int answer = 0;
			for (int i = 0; i < n; i++) {
				answer = Math.max(lis(i), answer);
			}
			System.out.println(answer);
		}
	}
}
