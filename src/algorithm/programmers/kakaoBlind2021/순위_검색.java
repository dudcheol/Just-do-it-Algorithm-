package algorithm.programmers.kakaoBlind2021;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 순위_검색 {

    private class Info{
        String lang;
        String job;
        String career;
        String food;
        int score;

        public Info(String lang, String job, String career, String food, int score) {
            this.lang = lang;
            this.job = job;
            this.career = career;
            this.food = food;
            this.score = score;
        }
    }

    public int[] solution(String[] info, String[] query) {
        List<Info> list = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();

        // info 파싱
        for(String i : info){
            StringTokenizer st = new StringTokenizer(i, " ");
            list.add(new Info(st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),Integer.parseInt(st.nextToken())));
        }

        // query 파싱
        for (String q : query){
            StringTokenizer st = new StringTokenizer(q, " ");
            String lang = st.nextToken();
            st.nextToken();
            String job = st.nextToken();
            st.nextToken();
            String career = st.nextToken();
            st.nextToken();
            String food = st.nextToken();
            int score = Integer.parseInt(st.nextToken());

            int cnt=0;
            for(Info i : list){
                if (lang.equals("-") || lang.equals(i.lang)){
                    if (job.equals("-") || job.equals(i.job)){
                        if (career.equals("-") || career.equals(i.career)){
                            if (food.equals("-") || food.equals(i.food)){
                                if (i.score >= score){
                                    cnt++;
                                }
                            }
                        }
                    }
                }
            }
            ans.add(cnt);
        }

        int[] answer = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            answer[i] = ans.get(i);
        }

        return answer;
    }

    public static void main(String[] args) throws Exception{
        순위_검색 c = new 순위_검색();

        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};

        for(int i : c.solution(info, query)){
            System.out.println(i);
        }
    }
}
