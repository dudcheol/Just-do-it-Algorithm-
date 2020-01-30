package baekjoon.BruteForce;

/*
 * 백준 - 가르침
 * 시작: 17:40
 * 끝1: 19:10
 * 끝2: 20:00
 * 시간1: 1시간 30분 - 메모리초과;;
 * 시간2: 2시간 20분 - 성공!!
 * 
 * [메모리 초과가 뜬 이유]
 * - 초반 : String문자열을 매 재귀 순환마다  HashSet으로 변환해주는 작업 -> 메모리초과(자꾸 선언하니까)
 * - 중반 : 재귀 파라미터에 각각 다른 HashSet을 사용하기 위해 매 재귀마다 clone을 통해 HashSet을 복제하고 작업함 -> 메모리초과
 * - 후반 : 결국, 재귀 이후 HashMap에서 넣었던 문자열을 빼는 작업을 통해 하나의 HashMap만을 사용해 메모리초과를 없앴음  
 * 
 * [배운 것]
 * - 반복적인 작업에 지속적인 선언은 메모리 초과를 야기할 수 있으니 주의한다.
 * - Scanner, Println 보다 BufferedReader, BufferedWriter가 성능상 더 빠르다. (속도문제 시 사용)
 * 		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 * - Buffered는 반드시 Exception처리 해주어야 하고, readLine시 문자열로 받기 때문에 숫자일 경우 파싱하는 작업이 필요하다.
 * - 만약, 입력이 4 6 <<이런식이라면 StringTokenizer를 사용해서 nextToken으로 값에 접근하도록 한다.
 * - bw는 bw.flush()로 버퍼에 있는 모든 문자열을 출력할 수 있다.
 */
import java.util.*;
import java.io.*;

public class _1062_Teaching {
	static int K;
	static int N;
	static HashSet<Character>[] word;
	static int answer = 0;

	static void findCanRead(HashSet<Character> str, int cnt, char c) {
		if (cnt > K)
			return;
		if (cnt == K) {
			// words에 있는 알파벳들이 str에 모두 포함되는지 확인한다
			int max = 0;
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
			if (str.contains((char)i))
				continue;
			// 메모리초과 원인!!
//			HashSet<Character> hs = (HashSet<Character>) str.clone();
//			hs.add((char) i);
			str.add((char)i);
			findCanRead(str, cnt + 1, (char) i);
			str.remove((char)i);
		}
		return;
	}

	static HashSet<Character> stringToSet(String word) {
		HashSet<Character> map = new HashSet<>();
		for (char w : word.toCharArray()) {
			map.add(w);
		}
		return map;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		word = new HashSet[N];
		for (int k = 0; k < N; k++) {
			word[k] = new HashSet<>();
			word[k] = stringToSet(br.readLine());
		}

		if (K < 5) {
			bw.write(0 + "");
		} else {
			findCanRead(stringToSet("acint"), 5, (char) ('b' - 1));
			bw.write(answer + "");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
