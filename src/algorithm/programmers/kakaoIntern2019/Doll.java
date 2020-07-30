package algorithm.programmers.kakaoIntern2019;

import java.util.Stack;

/**
 * 24분
 * 게임 화면의 격자의 상태가 담긴 2차원 배열 board와 인형을 집기 위해 크레인을 작동시킨 위치가 담긴 배열 moves가 매개변수로 주어질 때,
 * 크레인을 모두 작동시킨 후 터트려져 사라진 인형의 개수를 return 하도록 solution 함수를 완성해주세요.
 */
public class Doll {

    static int solution(int[][] board, int[] moves) {
        int answer = 0;
        int size = board[0].length;

        Stack<Integer> st = new Stack<>();

        for (int move : moves) {
            int pos = move - 1;

            for (int i = 0; i < size; i++) {
                if (board[i][pos] == 0) continue;
                if (!st.isEmpty() && st.peek() == board[i][pos]) {
                    st.pop();
                    answer+=2;
                    board[i][pos] = 0;
                    i = size;
                } else {
                    st.push(board[i][pos]);
                    board[i][pos] = 0;
                    i = size;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 0, 0}, {0, 0, 1, 0, 3}, {0, 2, 5, 0, 1}, {4, 2, 4, 4, 2}, {3, 5, 1, 3, 1}};
        int[] moves = {1, 5, 3, 5, 1, 2, 1, 4};

        System.out.println(solution(board, moves));
    }
}
