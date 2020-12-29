package algorithm.jungol.Beginner_Coder.여러가지;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class _2259_참외밭 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());

        // 북남 - NS, 서동 - WE
        ArrayList<Integer> ns = new ArrayList<>();
        ArrayList<Integer> we = new ArrayList<>();
        ArrayList<Integer> all = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int dir = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            if (dir==1||dir==2){
                ns.add(num);
            } else {
                we.add(num);
            }
            all.add(num);
        }
        Collections.sort(ns);
        Collections.sort(we);
        int maxArea = ns.get(ns.size()-1)*we.get(we.size()-1);
        int minArea = 0;

        int pre = all.get(0);
        int idx=1;
        while (true){
            if(pre * all.get(idx)==maxArea){
                int idx1=(idx+2)%6;
                int idx2=(idx+3)%6;
                minArea = all.get(idx1)*all.get(idx2);
                break;
            } else {
                pre = all.get(idx);
                idx=(idx+1)%6;
            }
        }

        System.out.println((maxArea-minArea)*K);
    }
}
