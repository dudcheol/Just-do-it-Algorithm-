package algorithm.baekjoon;

import java.util.*;
import java.io.*;

public class _16235_나무재테크_addAll풀이 {

	static int N, M, K, map[][];
	static int[] dy = { -1, -1, -1, 0, 1, 1, 1, 0 }; // 8방
	static int[] dx = { -1, 0, 1, 1, 1, 0, -1, -1 }; // 8방
	static LinkedList<Tree> trees;
	static Queue<Tree> deadTrees;
	static int[][] A;

	static class Tree implements Comparable<Tree> {
		int r;
		int c;
		int age;

		public Tree(int x, int y, int age) {
			this.r = x;
			this.c = y;
			this.age = age;
		}

		@Override
		public int compareTo(Tree o) {
			return Integer.compare(this.age, o.age);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		A = new int[N][N];
		trees = new LinkedList <Tree>();
		deadTrees = new LinkedList<Tree>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = 5;
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			trees.add(new Tree(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
					Integer.parseInt(st.nextToken())));
		}

		while (K-- != 0) {
			// 봄
			int[][] dead = new int[N][N];
			for (Iterator<Tree> it = trees.iterator(); it.hasNext();) {
				Tree tree = (Tree) it.next();
				int r = tree.r;
				int c = tree.c;
				int age = tree.age;
				
				if(map[r][c] - age < 0) { // 양분을 충분히 먹을 수 없음 -> 양분을 먹지 못하고 죽음
					dead[r][c] += age/2; // 여름에 죽은 애들이 만들어낼 양분 기록
					it.remove();
				} else {
					map[r][c] -= age;
					tree.age++;
				}
			}
			
			// 가을
			// 나무 번식
			LinkedList<Tree> addTree = new LinkedList<Tree>();
			for (Tree tree : trees) {
				int r = tree.r;
				int c = tree.c;
				if(tree.age % 5 == 0) {
					for (int d = 0; d < 8; d++) {
						int dr = r + dy[d];
						int dc = c + dx[d];
						if(dr<0||dc<0||dr>=N||dc>=N) continue;
						addTree.add(new Tree(dr,dc,1));
					}
				}
			}
			trees.addAll(0, addTree);
			
			// 겨울
			// 땅에 양분 추가
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] += A[i][j] + dead[i][j];
				}
			}
		}
		
		System.out.println(trees.size());
	}

}
