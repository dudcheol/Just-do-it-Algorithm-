package algorithm.swea.D4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _3289_DisJointSet {
    static int n, m;
    static int[] set;

    /**
     * b를 a집합에 포함시킨다.
     * @param a
     * @param b
     */
    static void union(int a, int b) {
        // a의 부모와 b의 부모 찾기
        int aRoot = find(a);
        int bRoot = find(b);

        // aRoot == bRoot : 같은 집합안에 있는 것이므로 합칠필요 없음.
        if (aRoot == bRoot) return;
        // aRoot != bRoot : 다른 집합안에 있으므로 합쳐주는 b의 부모를 a의 부모로 합침.
        set[bRoot] = aRoot;
    }

    /**
     * num이 어떤 수를 부모로하는 집합에 포함되어 있는지 찾는다.
     * @param num
     * @return num이 포함된 집합의 부모 숫자
     */
    static int find(int num) {
        if (set[num] == num) {
            // 부모를 계속 찾다가 찾으려는 수와 부모가 같아질 때
            // 그 숫자가 부모가 된다.
            return num;
        }
        // 계속 부모를 찾아가면서 set 배열을 업데이트한다.
        return set[num] = find(set[num]);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken()); // 입력으로 주어지는 연산의 갯수
            set = new int[n + 1];

            // 집합 초기화 Make-Set
            for (int i = 1; i <= n; i++) {
                set[i] = i;
            }

            sb.append("#").append(tc).append(" ");
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int op = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (op == 0) {
                    // union
                    union(a, b);
                } else {
                    // find
                    sb.append(find(a) == find(b) ? 1 : 0);
                }

            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
