package algorithm.jungol.Intermediate_Coder.분할정복;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _3520_Tutorial_Sort3 {
    private static class Student{
        int id;
        String name;
        int Pi;

        public Student(int id, String name, int pi) {
            this.id = id;
            this.name = name;
            this.Pi = pi;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int N=Integer.parseInt(st.nextToken());
        int Q=Integer.parseInt(st.nextToken());
        Student[] students = new Student[N];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine()," ");
            students[i-1] = new Student(i, st.nextToken(), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(students, (o1,o2)->{
            int res = -Integer.compare(o1.Pi, o2.Pi);
            if (res==0) res = o1.name.compareTo(o2.name);
            if (res==0) res = Integer.compare(o1.id, o2.id);
            return res;
        });
        st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            sb.append(students[Integer.parseInt(st.nextToken())-1].id).append(' ');
        }
        System.out.println(sb);
    }
}
