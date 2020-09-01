package algorithm.swea.D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
R*3 + C*2 + S*5 = 31
R*2 + C*4 + S*7 = 45 // R = 45/2 - C*2 - S*7/2
R*7 + C*5 + S*3 = 48
연립방정식의 해법 : 1. 소거법(변수의 계수를 맞춰서 각 항을 빼줌), 2. 대입법

하지만
1 <= R, C, S <= 2000 정수, 범위가 유한하다, 적음, 완전탐색 가능
R C S의 모든 순열을 구해보자 (동일한 값이 나올 수 있으니까 중복순열)
 */
public class _3378_스타일리쉬_들여쓰기_풀이 {

    private static int[][] m;
    private static int[][] dap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= TC; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int p = Integer.parseInt(st.nextToken()); // 스타일리쉬를 마스터한 사람의 코드 줄 수 1 <= p <= 10
            int q = Integer.parseInt(st.nextToken()); // 스타일리쉬를 사용한 '나'의 코드 줄 수 1 <= p <= 10
            // RCS 모든 순열을 구해서, 스타일리쉬 코드에 답으로 사용할 수 있는지 확인 후, 내 코드에 적용하겠음
            // 소스코드를 매번(8000번) 읽어서 분석하는 것이 아니라, 분석한 데이터만 저장해서 재활용
            // 소스코드의 한 줄 .점들여쓰기, 소, 중, 대괄호
            m = new int[p][4]; // 소스코드의 한 줄 [0].들여쓰기, [1]소, [2]중, [3]대
            for (int i = 0; i < p; i++) {
                String line = br.readLine(); // 한줄

                // . 의 개수 세기
                int index = 0;
                while (line.charAt(index) == '.') {
                    index++;
                }
                m[i][0] = index; // . 개수
                if (i > 0) {
                    m[i][1] = m[i - 1][1]; // 누적하기 위하여 이전 칸의 값을 가져옴
                    m[i][1] = m[i - 1][2];
                    m[i][1] = m[i - 1][3];
                }

                // 점이 끝난 다음 소스코드의 위치가 index~끝
                // 여는 괄호의 개수 - 닫는 괄호의 개수
                for (int j = 0; j < line.length(); j++) {
                    switch (line.charAt(j)) {
                        case '(':
                            m[i][1]++;
                            break;
                        case ')':
                            m[i][1]--;
                            break;
                        case '{':
                            m[i][2]++;
                            break;
                        case '}':
                            m[i][2]--;
                            break;
                        case '[':
                            m[i][3]++;
                            break;
                        case ']':
                            m[i][3]--;
                            break;
                    }
                }
            } // end of for 마스터 스타일리쉬 코드분석

//            for (int i = 0; i < m.length; i++) {
//                System.out.println(Arrays.toString(m[i]));
//            }

            //내 소스코드 분석
            dap = new int[q][4];
            for (int i = 0; i < q; i++) {
                String line = br.readLine();
                int index = 0;
                // .의 개수는 없는 상태임
                if (i > 0) {
                    dap[i][1] = dap[i - 1][1]; // 누적하기 위해서 이전행의 데이터 복붙
                    dap[i][2] = dap[i - 1][2];
                    dap[i][3] = dap[i - 1][3];
                }
                for (int j = 0; j < line.length(); j++) {
                    switch (line.charAt(j)) {
                        case '(':
                            dap[i][1]++;
                            break;
                        case ')':
                            dap[i][1]--;
                            break;
                        case '{':
                            dap[i][2]++;
                            break;
                        case '}':
                            dap[i][2]--;
                            break;
                        case '[':
                            dap[i][3]++;
                            break;
                        case ']':
                            dap[i][3]--;
                            break;
                    }
                }
            } // end of for 내 소스코드 분석

            // dap[i][0] : 들여쓰기 .의 개수를 기록하자 : 초기값을 사용하지 않는 숫자로 넣기 -2, 답이 유일하지 않으면 -1
            for (int i = 0; i < q; i++) {
                dap[i][0] = -2; // 초기화
            }

            // 중복순열 만들기
            for (int R = 1; R <= 20; R++) {
                for (int C = 1; C <= 20; C++) {
                    for (int S = 1; S <= 20; S++) {
                        if (check(R, C, S)) { // R, C, S 의 중복순열 쌍이 마스터 코드에 답으로 쓸 수 있는지 체크
                            cal(R, C, S); // 내 소스코드의 들여쓰기를 계산해보
                        }
                    }
                }
            }

            sb.append('#').append(test_case).append(" 0"); // 첫번째 줄은 항상 0
            for (int i = 1; i < dap.length; i++) {
                sb.append(' ').append(dap[i][0]);
            }
            sb.append('\n');
        } // end of for test_case
        System.out.print(sb);
    } // end of main

    /** 내 코드에서 R,C,S 값으로 .의 개수를 구하기 */
    private static void cal(int R, int C, int S) {
        // dap[i][0] : 들여쓰기 .의 개수를 기록하자 : 초기값을 사용하지 않는 숫자로 넣기 -2, 답이 유일하지 않으면 -1
        for (int i = 1; i < dap.length; i++) {
            int x = dap[i - 1][1] * R + dap[i - 1][2] * C + dap[i - 1][3] * S;

            if (dap[i][0] == -2){ // 답을 구한적이 없으면, 바로 저장함
                dap[i][0] = x;
            } else if (dap[i][0] != x) { // 답을 구한적이 있는데, 기존의 구했던 답과 다르면 (답이 유일하지 않다는 의미) -1
                dap[i][0] = -1; // 유일하지 않음
            }
        }
    }

    /** 스타일리쉬 마스터 코드에서 R,C,S 가 답이 되는지 체크 */
    private static boolean check(int R, int C, int S) {
        for (int i = 1; i < m.length; i++) {
            // 현재 행의 점의 개수는 이전 행까지의 누적된 괄호의 개수로 연산한 결과여야 한다
            if (m[i][0] != m[i - 1][1] * R + m[i - 1][2] * C + m[i - 1][3] * S) {
                return false; // 한줄이라도 다르면 안됨
            }
        }
        return true;
    }
} // end of class
