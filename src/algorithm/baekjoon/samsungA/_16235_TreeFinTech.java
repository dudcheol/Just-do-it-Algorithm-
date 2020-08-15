package algorithm.baekjoon.samsungA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 골드 4 - 나무 재테크
 */
public class _16235_TreeFinTech {
    static int n, m, k;
    static int[][] map;
    static int[][] yangboon;
    static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
    static LinkedList<Tree> trees;
    static Queue<Tree> dead;
    static int year;

    static class Tree {
        int x;
        int y;
        int age;

        public Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }
    }

    static void simulate() {
        while (true) {
            if (year == k) return;

            // 봄
            Iterator<Tree> iterator = trees.iterator();
            while (iterator.hasNext()) {
                Tree tree = iterator.next();
                int r = tree.x;
                int c = tree.y;
                int age = tree.age;
                map[r][c] -= age;
                if (map[r][c] < 0) {
                    // 먹지못하고 즉시 죽음
                    dead.offer(tree);
                    iterator.remove();
                    map[r][c] += age;
                } else {
                    tree.age += 1;
                }
            }

            // 여름
            while (!dead.isEmpty()) {
                Tree tree = dead.poll();
                map[tree.x][tree.y] += tree.age / 2;
            }

            // 가을
            for (int i = 0; i < trees.size(); i++) {
                Tree tree = trees.get(i);
                int r = tree.x;
                int c = tree.y;
                if (tree.age % 5 != 0) continue;
                for (int d = 0; d < 8; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (nr < 1 || nc < 1 || nr > n || nc > n) continue;
                    trees.addFirst(new Tree(nr, nc, 1));
                    i++;
                }
            }

            // 겨울
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    map[i][j] += yangboon[i][j];
                }
            }

            year++;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n + 1][n + 1]; // 1부터 시작
        yangboon = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(map[i], 5);
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                yangboon[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        trees = new LinkedList<>();
        dead = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            trees.add(
                    new Tree(
                            Integer.parseInt(st.nextToken()),
                            Integer.parseInt(st.nextToken()),
                            Integer.parseInt(st.nextToken())
                    )
            );
        }

        year = 0;

//        // 나이가 어린 나무순서로 정렬
//        Collections.sort(trees, (o1, o2) -> {
//            return Integer.compare(o1.age, o2.age);
//        });

        simulate();

        System.out.println(trees.size());
    }
}
