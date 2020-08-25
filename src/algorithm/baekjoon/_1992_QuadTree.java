package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _1992_QuadTree {
    static int N;
    static int[][] map;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                map[i][j] = tmp[j] - '0';
            }
        }

        quadCompress(N, 1, 0, N - 1, 0, N - 1);

        String answer = sb.toString();
        while (answer.contains("(1111)")) {
            answer = answer.replace("(1111)", "1");
        }

        while (answer.contains("(0000)")) {
            answer = answer.replace("(0000)", "0");
        }

        System.out.println(answer);
    }

    // 1 2
    // 3 4 의 4로 분할한다고 생각한다
    private static void quadCompress(int size, int part, int rowStart, int rowEnd, int colStart, int colEnd) {
        if (size == 1) {
            int ret = -1;
            if (part == 1) ret = map[rowStart][colStart];
            else if (part == 2) ret = map[rowStart][colEnd];
            else if (part == 3) ret = map[rowEnd][colStart];
            else if (part == 4) ret = map[rowEnd][colEnd];
            sb.append(ret);
            return;
        }
        sb.append("(");
        // 4분할 실시
        int midr = (rowStart + rowEnd) / 2;
        int midc = (colStart + colEnd) / 2;
        quadCompress(size / 2, 1, rowStart, midr, colStart, midc); // 1
        quadCompress(size / 2, 2, rowStart, midr, midc + 1, colEnd); // 2
        quadCompress(size / 2, 3, midr + 1, rowEnd, colStart, midc); // 3
        quadCompress(size / 2, 4, midr + 1, rowEnd, midc + 1, colEnd); // 4
        sb.append(")");
    }
}

/*
8
11110000
11110000
00011100
00011100
11110000
11110000
11110011
11110011
 */