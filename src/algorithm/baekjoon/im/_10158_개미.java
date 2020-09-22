package algorithm.baekjoon.im;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _10158_개미 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int w = Integer.parseInt(st.nextToken()); // 맵 크기
		int h = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");
		int p = Integer.parseInt(st.nextToken()); // 개미위치
		int q = Integer.parseInt(st.nextToken());

		int t = Integer.parseInt(br.readLine()); // 개미가 움직일 시간

		int dy = 1;
		int dx = 1;

		int time = 0;

		t %= w * h;

		while (true) {
			if (time == t)
				break;

			p += dy;
			q += dx;
			if (p < 0 || p > w) {
				p -= dy;
				dy *= -1;
				p += dy;
			}
			if (q < 0 || q > h) {
				q -= dx;
				dx *= -1;
				q += dx;
			}

			time++;
		}
		System.out.println(p + " " + q);
	}

}
