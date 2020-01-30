package baekjoon.BruteForce;

/*
 * 백준 - 가르침
 * 시작: 17:40
 * 끝: 19:10
 * 시간: 1시간30분 - 메모리초과;;
 * 
 */
import java.util.*;

public class _1062_Teaching {
	static int K;
	static int N;
	static String need = "acint";
	static String[] words;
	static HashSet<Character>[] word;
	static int answer = 0;

	static void findCanRead(HashSet<Character> str, int cnt, char c) {
		if (cnt > K)
			return;
		if (cnt == K) {
			// words에 있는 알파벳들이 str에 모두 포함되는지 확인한다
			int max = 0;
//			for (String w : words) {
//				if (isContains(str, w)) {
//					// 읽을 수 있는 단어
//					max++;
//				}
//			}
			for (HashSet<Character> w : word) {
				if (str.containsAll(w)) {
					// 읽을 수 있는 단어
					max++;
				}
			}
			answer = Math.max(answer, max);
			return;
		}

		for (int i = (int) c + 1; i <= (int) 'z'; i++) {
			if (str.contains(i))
				continue;
			HashSet<Character> hs = (HashSet<Character>) str.clone();
			hs.add((char)i);
			findCanRead(hs, cnt + 1, (char) i);
		}
		return;
	}

	static boolean isContains(String str1, String str2) { // str2가 str1에 포함되나?
		HashSet<Character> map1 = stringToSet(str1);
		HashSet<Character> map2 = stringToSet(str2);

		return map1.containsAll(map2);
	}

	static HashSet<Character> stringToSet(String word) {
		HashSet<Character> map = new HashSet<>();
		for (char w : word.toCharArray()) {
			map.add(w);
		}
		return map;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		words = new String[N];
		for (int i = 0; i < N; i++) {
			words[i] = sc.next();
		}

		int k=0;
		word = new HashSet[N];
		for (String w : words) {
			word[k] = new HashSet<>();
			word[k] = stringToSet(w);
			k++;
		}

		if (K < 5) {
			System.out.println(0);
		} else {
			findCanRead(stringToSet(need), 5, (char) ('b' - 1));
			System.out.println(answer);
		}
	}
}
