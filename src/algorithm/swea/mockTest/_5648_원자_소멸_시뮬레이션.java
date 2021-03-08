package algorithm.swea.mockTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

// 20:00~ 중간에 5분
public class _5648_원자_소멸_시뮬레이션 {
    private static int N;
    private static double[] dy = {0.5,-0.5,0,0};
    private static double[] dx = {0,0,-0.5,0.5};

    private static class Dot{
        double x;
        double y;
        int d;
        int k;

        public Dot(double x, double y, int d, int k) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.k = k;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= TC; test_case++) {
            N = Integer.parseInt(br.readLine());
            List<Dot> dots = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine()," ");
                dots.add(new Dot(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
            }

            // 시뮬레이션
            // 4000 번 수행
            int cnt=0;
            int sum=0;
            while (!dots.isEmpty() && cnt++ < 4000){
                // 해당방향 이동
                HashMap<String, Integer> map = new HashMap<>();
                for(int i=dots.size()-1;i>=0;i--){
                    Dot dot = dots.get(i);
                    dot.x = dot.x+dx[dot.d];
                    dot.y = dot.y+dy[dot.d];
                    String key = String.valueOf(dot.x)+String.valueOf(dot.y);
                    map.put(key, map.getOrDefault(key, 0)+1);
                }
                int e=0;
                for(int i=dots.size()-1;i>=0;i--){
                    Dot dot = dots.get(i);
                    String key = String.valueOf(dot.x)+String.valueOf(dot.y);
                    if (map.get(key) >= 2){
                        e+=dot.k;
                        dots.remove(i);
                    }
                }
                sum+=e;
            }

            sb.append('#').append(test_case).append(' ').append(sum).append('\n');
        }
        System.out.print(sb);
    }
}
