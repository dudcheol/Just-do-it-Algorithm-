package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _1992_QuadTree {
    static int N;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                map[i][j] = tmp[j];
            }
        }

        System.out.println(quadCompress(N, 0, N - 1, 0, N - 1));
    }

    // 1 2
    // 3 4 의 4로 분할한다고 생각한다
    // return 타입을 String으로!
    private static String quadCompress(int size, int rowStart, int rowEnd, int colStart, int colEnd) {
        if (size == 1) {
            // 굳이 part를 사용할 필요 없이 이 크기는 1이므로 rowStart, colStart가 map의 특정 인덱스가 됨
            return Character.toString(map[rowStart][colStart]);
        }

        // 4분할 실시
        int midr = (rowStart + rowEnd) / 2;
        int midc = (colStart + colEnd) / 2;
        int half = size / 2;
        String part1 = quadCompress(half, rowStart, midr, colStart, midc); // 1
        String part2 = quadCompress(half, rowStart, midr, midc + 1, colEnd); // 2
        String part3 = quadCompress(half, midr + 1, rowEnd, colStart, midc); // 3
        String part4 = quadCompress(half, midr + 1, rowEnd, midc + 1, colEnd); // 4

        // part1.length() == 1이 있는 이유?
        // length가 1보다 큰 경우에는 재귀적으로 쌓여온 문자열들을 가지고 있는 상태이므로 함부로 파트 2,3,4를 제외시킬 수 없다
        if (part1.length() == 1 && part1.equals(part2) && part2.equals(part3) && part3.equals(part4)) {
            return part1;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("(").append(part1).append(part2).append(part3).append(part4).append(")");
            return sb.toString();
        }
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

answer: ((110(0101))(0010)1(0001))
 */