package algorithm.jungol.Intermediate_Coder.자료구조;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class _1214_히스토그램 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        Stack<Integer> s = new Stack<>();
        int[] arr = new int[n];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            while (!s.isEmpty() && arr[s.peek()] > arr[i]){
                int k = s.pop();
                int width = i;
                if (!s.isEmpty()) width -= s.peek()+1;
                int area = arr[k]*width;
                max = Math.max(max, area);
            }
            s.push(i);
        }

        while (!s.isEmpty()){
            int k = s.pop();
            int width = n;
            if (!s.isEmpty()) width -= s.peek()+1;
            int area = arr[k]*width;
            max = Math.max(max, area);
        }

        System.out.println(max);
    }
}

/*
7 2 1 4 5 1 3 3
7 2 1 4 5 2 3 3
 */