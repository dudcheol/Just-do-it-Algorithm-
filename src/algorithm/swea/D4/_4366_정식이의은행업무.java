package algorithm.swea.D4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _4366_정식이의은행업무 {
	static char[] bin;
	static int binSize;
	static char[] third;
	static int thrSize;
	static char[][] bAry = { { '1' }, { '0' } };
	static char[][] tAry = { { '1', '2', '0' }, { '0', '2', '1' }, { '0', '1', '2' } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= TC; test_case++) {

			bin = br.readLine().toCharArray();
			binSize = bin.length;
			third = br.readLine().toCharArray();
			thrSize = third.length;

			// 경우의 수를 놓고 생각한다
			// 한자리만 잘못 기억 하는 것이므로 한자리씩 바꾼다음 기저에서 검사한다

			long res = 0;
			loop: for (int i = 0; i < binSize; i++) {
				bin[i] = bAry[bin[i] - '0'][0];
				res = parseDec(bin, 2);

				for (int j = 0; j < third.length; j++) {
					int cur = third[j] - '0';
					long res2 = 0;
					third[j] = tAry[cur][0];
					res2 = parseDec(third, 3);
					if (res == res2)
						break loop;

					third[j] = tAry[cur][1];
					res2 = parseDec(third, 3);
					if (res == res2)
						break loop;

					third[j] = tAry[cur][2];
				}

				bin[i] = bAry[bin[i] - '0'][0];
			}

			sb.append('#').append(test_case).append(' ').append(res).append('\n');
		}
		System.out.print(sb);
	}

	private static long parseDec(char[] target, int jinsu) {
		long ret = 0;
		int idx = 0;
		for (int i = target.length - 1; i >= 0; i--) {
			ret += Math.pow(jinsu, idx++) * (target[i] - '0');
		}
		return ret;
	}

}
