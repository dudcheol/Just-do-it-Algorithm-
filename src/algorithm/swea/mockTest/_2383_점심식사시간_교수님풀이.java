package algorithm.swea.mockTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class _2383_점심식사시간_교수님풀이 {
    private static int N, cnt, min; // 맵의 크기, 최소시간, 사람의 수
    private static boolean[] selected; // 부분집합 선택을 관리할 배열
    private static int[][] sList; // 계단 정보
    private static final int M=1,W=2,D=3,C=4; // 사람들의 상태 : 이동중, 대기, 계단내려가는중, 완료
    private static ArrayList<Person> pList; // 사람들리스트
    private static class Person implements Comparable<Person>{
        int r,c,downCnt,status,time; // 행좌표,열좌표,내려간계단수,상태,입구도착시간
        public Person(int r, int c) {
            this.r = r;
            this.c = c;
        }
        public void init(){
            downCnt = 0;
            status = M;
            time = 0;
        }
        @Override
        public int compareTo(Person o) {
            return Integer.compare(this.time, o.time);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int TC = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= TC; test_case++) {
            N = Integer.parseInt(br.readLine());
            pList = new ArrayList<Person>();
            sList = new int[2][];
            min = Integer.MAX_VALUE;

            int c=0,k=0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    c = Integer.parseInt(st.nextToken());
                    if (c==1) pList.add(new Person(i, j));
                    else if (c>1) sList[k++] = new int[]{i,j,c}; // 좌표, 계단높이
                }
            }
            cnt = pList.size();
            selected = new boolean[cnt];

            divide(0);
            sb.append('#').append(test_case).append(' ').append(min).append('\n');
        }
        System.out.print(sb);
    }

    private static void divide(int index){ // 부분집합으로 계단 배정하기
        if (index==cnt){
            makeList();
            return;
        }
        selected[index] = true;
        divide(index+1);
        selected[index] = false;
        divide(index+1);
    }

    private static void makeList(){ // 계단의 배정된 상황에 따라 각각의 사람리스트 생성
        ArrayList<Person> aList = new ArrayList<Person>();
        ArrayList<Person> bList = new ArrayList<Person>();
        for (int i = 0; i < cnt; i++) {
            Person p = pList.get(i);
            p.init(); // 새로운 객체를 생성하지 않고 시뮬레이션을 돌리기 위해 초기화 작업 진행
            if (selected[i]){
                p.time = Math.abs(p.r-sList[0][0])+Math.abs(p.c-sList[0][1]);
                aList.add(p);
            }else{
                p.time = Math.abs(p.r-sList[1][0])+Math.abs(p.c-sList[1][1]);
                bList.add(p);
            }
        }
        int res = go(aList, bList);
        if (min > res) min = res;
    }

    // 두 사람리스트를 내려가기 처리 후 소요시간 비교해서 모든 사람이 내려가는데 걸리는 시간 결정
    private static int go(ArrayList<Person> aList, ArrayList<Person> bList){
        int timeA=0,timeB=0;
        if (aList.size()>0) timeA = processDown(aList, sList[0][2]);
        if (bList.size()>0) timeB = processDown(bList, sList[1][2]);
        return timeA>timeB ? timeA : timeB;
    }

    // 해당리스트의 사람들이 모두 내려가는 처리(소요시간 계산)
    private static int processDown(ArrayList<Person> list, int height){
        Collections.sort(list); // 입구 도착시간이 가장 빠른사람 먼저 처리
        int time = list.get(0).time;
        int size = list.size();
        int ingCnt = 0, cCnt = 0; // 내려가고 있는 사람 수, 다 내려간 사람 수
        Person p;

        while(true){ // 1분의 시간 동안 모든 사람이 수행함
            for (int i = 0; i < size; i++) {
                p = list.get(i);
                if (p.status == C) continue; // 다 내려간 사람이므로 건너뜀

                if (p.time == time){
                    p.status = W;
                } else if (p.status == W && ingCnt < 3){
                    p.status = D;
                    p.downCnt = 1;
                    ingCnt++;
                } else if (p.status == D) {
                    if (p.downCnt < height){
                        p.downCnt++;
                    }else{
                        p.status = C;
                        cCnt++; // 도착한 사람 수 늘림
                        ingCnt--; // 내려가는 중인 사람이 도착했으므로 줄임
                    }
                }
            }
            if (cCnt==size) break; // 모든 사람이 내려갔다면 반복 종료
            time++;
        }
        return time;
    }
}
