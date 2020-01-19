package programmers.DFS_BFS;

/*
 * 20:00 시작 
 * 22:50 60점
 * 23:12 100점 - 완료 - 힌트봄 ㅜㅜ
 * begin에서 target으로 변환하는 가장 짧은 변환 과정을 찾으려고 합니다.
 *  1. 한 번에 한 개의 알파벳만 바꿀 수 있습니다.
	2. words에 있는 단어로만 변환할 수 있습니다.
 *  최소 몇 단계의 과정을 거쳐 begin을 target으로 변환할 수 있는지 return
 *제한사항
	각 단어는 알파벳 소문자로만 이루어져 있습니다.
	각 단어의 길이는 3 이상 10 이하이며 모든 단어의 길이는 같습니다.
	words에는 3개 이상 50개 이하의 단어가 있으며 중복되는 단어는 없습니다.
	begin과 target은 같지 않습니다.
	변환할 수 없는 경우에는 0를 return 합니다.
 */

public class programmers_courses30_lessons43163 {
	static boolean[] marked;
	static int answer = 0;

	static void dfs(char[] changes, char[] targets, char[][] words, int needSameTarget) {
		int findOneDiffer = 0;
		int findSameTarget = 0;
		boolean changeNeedSameTarget = true;
		char[] select;
		while (changeNeedSameTarget) {
			for (int i = 0; i < words.length; i++) {
				if (needSameTarget == changes.length + 1)
					return;
				if (marked[i])
					continue;
				for (int j = 0; j < changes.length; j++) {
					if (words[i][j] != changes[j]) {
						findOneDiffer++;
					}
				}
				if (findOneDiffer == 1) {
					select = words[i];
					for (int k = 0; k < select.length; k++) {
						if (select[k] == targets[k]) {
							findSameTarget++;
						}
					}
					if (findSameTarget == needSameTarget) {
						marked[i] = true;
						answer++;
						dfs(select, targets, words, needSameTarget + 1);
						return;
					}
				}
				findOneDiffer = 0;
				findSameTarget = 0;
			}
			needSameTarget -= 1;
			if (needSameTarget == 0) {
				answer = 0;
				return;
			}
		}
		return;
	}

	static int solution(String begin, String target, String[] words) {
		marked = new boolean[words.length];
		char[] begins = begin.toCharArray();
		char[] targets = target.toCharArray();
		char[][] words_c = new char[words.length][target.length()];
		int impossible = 0;
		for (int i = 0; i < words.length; i++) {
			if (words[i].equals(target))
				impossible++;
			words_c[i] = words[i].toCharArray();
		}

		// begin과 target이 얼마나 같은지 알아야함
		int sameCnt = 0;
		for (int i = 0; i < begins.length; i++) {
			if (begins[i] == targets[i])
				sameCnt++;
		}

		// begin과 target이 이미 sameCnt만큼은 같으므로 words에서 선택된 문자열이 target과 비교했을때 최소한
		// sameCnt+1만큼은 같아야함
		int needSameTarget = sameCnt + 1;

		if (impossible == 0)
			return 0;
		dfs(begins, targets, words_c, needSameTarget);

		return answer;
	}

	public static void main(String[] args) {
		String begin = "hot";
		String target = "lot";
		String[] words = { "hot", "dot", "dog", "lot", "log" };

		System.out.println(solution(begin, target, words));
	}
}
