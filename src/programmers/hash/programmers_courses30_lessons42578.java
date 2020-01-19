package programmers.hash;

import java.util.HashMap;
import java.util.Iterator;

public class programmers_courses30_lessons42578 {
	static int solution(String[][] clothes) {
		HashMap<String, Integer> map = new HashMap<>();

		// 해시에 키,값 형태로 옷 종류별 개수 저장
		for (int i = 0; i < clothes.length; i++) {
			String currentKey = clothes[i][1];
			if (map.containsKey(currentKey)) {
				map.replace(currentKey, map.get(currentKey) + 1);
			} else {
				map.put(currentKey, 1);
			}
		}	
		
		int answer = 1;
		
		for(int val : map.values()) {
			answer *= (val+1);
		}
		
		return answer-1;

//		Iterator<String> mapIter = map.keySet().iterator();
//
//		int speciesCnt = map.size();
////		System.out.println("speciesCnt:" + speciesCnt);
//		int result = 0;
//
//		// n개 중 2..max 선택
//		for (int i = 2; i <= speciesCnt; i++) {
//			int temp = nCr(speciesCnt, i);
//			// 한 종류에 여러개가 있다면 그 만큼 경우의 수가 늘어나므로 곱해준다
//			while (mapIter.hasNext()) {
//				temp *= map.get(mapIter.next());
//				System.out.println(temp);
//			}
//			mapIter = map.keySet().iterator();
//			result += temp;
//			System.out.println("temp = "+temp);
//			System.out.println("result = "+result);
//		}
//
//		// 각각 하나씩만 입었을 경우 더함
//		result += clothes.length;
	}

	// 조합 공식 사용 nCr = n! / r!(n-r)!
	static int nCr(int n, int r) {
 		if (n - r == 0)
			return 1;
		return factorial(n, n) / (factorial(r, r) * factorial(n - r, n - r));
	}

	static int factorial(int n, int result) {
		if (n - 1 <= 0)
			return result;
		return factorial(n - 1, result * (n - 1));
	}

	public static void main(String[] args) {
//		String[][] clothes = { { "1", "a" }, { "4", "b" },{ "5", "c" }, {"2","a"},{"3","a"},{"6","c"}};
//		String[][] clothes = { { "1", "a" }, { "2", "b" },{ "3", "a" }};
		String[][] clothes = { { "1", "a" }, { "2", "b" },{ "3", "c" }};

		System.out.println(solution(clothes));
	}
}
