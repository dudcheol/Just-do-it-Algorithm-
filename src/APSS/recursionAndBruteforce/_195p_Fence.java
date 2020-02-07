package APSS.recursionAndBruteforce;

/*
 * 알고리즘 문제해결 전략 - 195p - 울타리 잘라내기
 * - 회고! -
 *	[나는 왜 이 풀이를 떠올리지 못했나?] 
	
 */

import java.util.*;
import java.io.*;

public class _195p_Fence {
	static int N;
	static int[] h;

	static int findLargestSquare(int l, int r) {
		/* 기저사례: 더이상 쪼갤 수 없는 순간 (1개만 남은 경우) */
		if (l == r)
			return h[l];
		int mid = (l + r) / 2;
		/* 왼쪽에서 찾기 */
		int resL = findLargestSquare(l, mid);
		/* 오른쪽에서 찾기 */
		int resR = findLargestSquare(mid + 1, r);
		/* 양쪽에서 얻은 직사각형 크기 중 더 큰 것을 선택 */
		int ret = Math.max(resL, resR);

		/* 가운데서부터 왼쪽, 오른쪽으로 하나씩 늘려가며 가장 큰 직사각형 찾기 */
		int _l = mid;
		int _r = mid + 1;
		int height = Math.min(h[_l], h[_r]); /* 둘 중 작은걸 선택해야 직사각형이 만들어짐 */

		/*
		 * _r이 r보다 작은 동안에 _l이 l과 같으면 h[_l-1]과 h[_r+1]을 비교할 필요없이 오른쪽으로 가면 된다. _r이 r과 같아지면
		 * 오른쪽으로 가야 한다. _l이 l보다 큰 동안에는 왼쪽으로 갈지 오른쪽으로 갈지를 어디가 더 높은가를 기준으로 판단하기때문에 비교해주어야
		 * 한다.
		 */
//		while (true) {
//			if (_l > l && _r < r) {
//				if (h[_l - 1] > h[_r + 1]) {
//					_l -= 1;
//					height = Math.min(height, h[_l]);
//				} else {
//					_r += 1;
//					height = Math.min(height, h[_r]);
//				}
//			} else if (_r < r) { // 오른쪽으로만 간다
//				_r+=1;
//				height = Math.min(height, h[_r]);
//			} else if (_l > l) { // 왼쪽으로만 간다
//				_l-=1;
//				height = Math.min(height, h[_l]);
//			} else { // 더이상 갈 수 없다
//				break;
//			}
//			ret = Math.max(ret, (_r - _l + 1) * height);
//		}
//		return ret;

		while (_l > l && _r < r) {
			/*
			 * _r이 r보다 작은 동안에 _l이 l과 같으면 h[_l-1]과 h[_r+1]을 비교할 필요없이 오른쪽으로 가면 된다. _r이 r과 같아지면
			 * 오른쪽으로 가야 한다. _l이 l보다 큰 동안에는 왼쪽으로 갈지 오른쪽으로 갈지를 어디가 더 높은가를 기준으로 판단하기때문에 비교해주어야
			 * 한다.
			 */
			if (_r < r && (_l == l || h[_l - 1] < h[_r + 1])) {
				_r++;
//				if (h[_r] == 0) {
//					_r = r;
//					continue;
//				}
				height = Math.min(height, h[_r]);
			} else {
				_l--;
//				if (h[_l] == 0) {
//					_l = l;
//					continue;
//				}
				height = Math.min(height, h[_l]);
			}
			ret = Math.max(ret, (_r - _l + 1) * height);
		}
		return ret;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			h = new int[N];
			for (int i = 0; i < h.length; i++) {
				h[i] = Integer.parseInt(st.nextToken());
			}
			System.out.println(findLargestSquare(0, N - 1));
		}
	}
}
