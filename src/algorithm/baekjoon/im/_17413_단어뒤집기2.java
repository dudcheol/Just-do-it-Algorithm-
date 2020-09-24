package algorithm.baekjoon.im;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _17413_단어뒤집기2 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] s = br.readLine().toCharArray();

		StringBuilder res = new StringBuilder();
		StringBuilder tmp = new StringBuilder();

		boolean isWord = true;
		for (int i = 0; i < s.length; i++) {
			switch (s[i]) {
			case '<':
				if (tmp.length() != 0) {
					res.append(tmp.reverse());
					tmp.setLength(0);
				}
				isWord = false;
				res.append('<');
				break;
			case '>':
				isWord = true;
				res.append('>');
				break;
			case ' ':
				if (isWord && tmp.length() != 0) {
					res.append(tmp.reverse());
					tmp.setLength(0);
				}
				res.append(' ');
				break;
			default:
				if (isWord) {
					tmp.append(s[i]);
				} else {
					res.append(s[i]);
				}
			}
		}
		if (tmp.length() != 0) {
			res.append(tmp.reverse());
		}
		System.out.println(res);
	}

}
