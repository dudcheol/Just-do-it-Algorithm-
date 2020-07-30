package algorithm.APSS.linearDataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Josephus {
    static List findArrive(int n, int k) {
        //첫 번째로 자살하는 병사의 번호가 1
        List list = new LinkedList<Integer>();
        for (int i = 1; i <= n; i++)
            list.add(i);

        int target = 0;
        while (!(list.size() == 2)) {
            if (target > list.size() - 1) {
                target = target % list.size();
            }
            list.remove(target);
            target += (k-1);
        }

        return list;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int TC = Integer.parseInt(br.readLine().trim());

        for (int tc = 0; tc < TC; tc++) {
            st = new StringTokenizer(br.readLine());

            // 3≤N≤1000, 1≤K≤1000
            int n = Integer.parseInt(st.nextToken().trim());
            int k = Integer.parseInt(st.nextToken().trim());

            List answer = findArrive(n, k);
            //두 사람의 번호를 오름차순으로 출력
            Collections.sort(answer);
            System.out.println(answer.get(0) + " " + answer.get(1));
        }
    }
}
