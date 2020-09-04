package algorithm.swea.D4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _4366_정식이의은행업무 {
	static char[] bin;
	static int binSize;
	static char[] third;
	static int thrSize;
	static char[] bAry = { '1', '0' };
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

			int res = 0;
			loop: for (int i = 0; i < binSize; i++) {
				bin[i] = bAry[bin[i] - '0'];
				res = changeBinToDec();

				for (int j = 0; j < third.length; j++) {
					int cur = third[j] - '0';
					int res2 = 0;
					third[j] = tAry[cur][0];
					res2 = changeThrToDec();
					if (res == res2)
						break loop;

					third[j] = tAry[cur][1];
					res2 = changeThrToDec();
					if (res == res2)
						break loop;

					third[j] = tAry[cur][2];
				}

				bin[i] = bAry[bin[i] - '0'];
			}

			sb.append('#').append(test_case).append(' ').append(res).append('\n');
		}
		System.out.print(sb);
	}

	private static int changeBinToDec() {
		int ret = 0;
		int val = 1;
		for (int i = bin.length - 1; i >= 0; i--) {
			if (bin[i] == '1') {
				ret += val;
			}
			val *= 2;
		}
		return ret;
	}

	private static int changeThrToDec() {
		int ret = 0;
		int val = 1;
		for (int i = third.length - 1; i >= 0; i--) {
			if (third[i] == '1') {
				ret += val;
			} else if (third[i] == '2') {
				ret += (val * 2);
			}
			val *= 3;
		}
		return ret;
	}

}
