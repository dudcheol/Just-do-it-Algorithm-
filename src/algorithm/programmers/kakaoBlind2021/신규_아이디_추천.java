package algorithm.programmers.kakaoBlind2021;

public class 신규_아이디_추천 {

        public String solution(String new_id) {
            String id = new_id;

            id = id.toLowerCase();
            id.replace("!","");
            id.replace("@","");
            id.replace("#","");
            id.replace("*","");


            String answer = "";
            return answer;
        }

    public static void main(String[] args) throws Exception{
        신규_아이디_추천 c = new 신규_아이디_추천();
        String new_id = "...!@BaT#*..y.abcdefghijklm";
        System.out.println(c.solution(new_id));
    }
}
