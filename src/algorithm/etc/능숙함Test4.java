package algorithm.etc;

public class 능숙함Test4 {
    // Q. 주사위 N개를 던졌을 때 나올 수 있는 눈의 경우의 수
    static int N, selected[], cnt;
    static boolean visited[];

    public static void main(String[] args) {
        N = 3;
        cnt = 0;
        selected = new int[N];
        visited = new boolean[7];
        dice(0);
        System.out.println("총 갯수 : " +cnt);
    }

    private static void dice(int k) {
        if (k==N){
            cnt++;
            for (int i = 0; i < N; i++)
                System.out.print(selected[i]);
            System.out.println();
            return;
        }
        for (int i = 1; i <= 6; i++) {
            selected[k] = i;
            dice(k+1);
        }
    }
}
