package algorithm.APSS.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 전위순회 : 부모 - 왼 - 오
 * 중위순회 : 왼 - 부모 - 오
 * 후위순회 : 왼 - 오 - 부모
 */
public class Traversal {
    // 한 줄에 해당 트리의 후위 순회했을 때 노드들의 방문 순서를 출력
    static void solution(List<Integer> pre, List<Integer> mid) {
        if (pre.size() == 0 && mid.size() == 0) return;
        // 루트를 찾는다
        // 루트는 전위의 가장 처음
        int root = pre.get(0);
        int midRootPos = mid.indexOf(root);

        //mid는 0~midRootPos-1 왼쪽 서브트리이고, midRootPos+1~끝까지 오른쪽 서브트리
        List<Integer> lmid = mid.subList(0, midRootPos);
        List<Integer> rmid = mid.subList(midRootPos + 1, mid.size());

        //pre는 lmid와 rmid의 사이즈만큼으로 잘라내면됨
        List<Integer> lpre = pre.subList(1, lmid.size()+1);
        List<Integer> rpre = pre.subList(lmid.size() + 1, pre.size());

        solution(lpre, lmid);
        solution(rpre, rmid);

        System.out.print(root + " ");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int TC = Integer.parseInt(br.readLine());

        // 각 노드는 1000 이하의 자연수로 번호 매겨져 있으며, 한 트리에서 두 노드의 번호가 같은 일은 없습니다.
        for (int tc = 0; tc < TC; tc++) {
            int n = Integer.parseInt(br.readLine()); // 1 <= n <= 100
            List<Integer> pre = new ArrayList<>(); // 전위
            List<Integer> mid = new ArrayList<>(); // 중위

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                pre.add(Integer.parseInt(st.nextToken()));
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                mid.add(Integer.parseInt(st.nextToken()));
            }

            solution(pre, mid);
        }
    }
}
