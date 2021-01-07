package algorithm.jungol.Intermediate_Coder.분할정복;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1092_제곱수_출력 {
    private static final int D = 20091024;
    private static int X,Y;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        System.out.println(pow(X,Y));
    }

    private static long pow(int x, int y) {
        if (y==0){
            return 1;
        }
        if (y==1){
            return x;
        }
        int mid = y/2;
        /**
         * 실수1
         * y가 짝수일 경우, pow 연산을 한번 더 하게된다. ex) 2^10 이면 2^5, 2^5 를 구하기 위한 작업을 2번이나 하게 됨
         * 숫자가 작으면 영향이 적겠지만, 숫자가 매우 클 경우 수행시간에 치명적일 수 있다 (재귀이므로)
         *
         * 실수2
         * (A * B) mod C = (A mod C * B mod C) mod C
         */
        if (y%2==0){
            long ret = pow(x, mid)%D;
            return (ret*ret)%D;
        } else {
            return (pow(x, mid)%D * pow(x, y-mid)%D) % D;
        }
    }
}
