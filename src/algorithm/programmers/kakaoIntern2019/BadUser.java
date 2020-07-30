package algorithm.programmers.kakaoIntern2019;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Pattern;

/**
 * 1시간 45분
 * 이벤트 응모자 아이디 목록이 담긴 배열 user_id와 불량 사용자 아이디 목록이 담긴 배열 banned_id가 매개변수로 주어질 때,
 * 당첨에서 제외되어야 할 제재 아이디 목록은 몇가지 경우의 수가 가능한 지 return 하도록 solution 함수를 완성해주세요.
 */
public class BadUser {
    static ArrayList<String> candiList = new ArrayList<>();
    static int banIdSize;
//    static boolean[] visited;
    static String[] banRegx;
    static HashSet<String> visited = new HashSet<>();
    static ArrayList<HashSet<String>> alreadyDone = new ArrayList<>();

    static int solution(String[] user_id, String[] banned_id) {
        int answer = 0;

        HashSet<String> ans = new HashSet<>();
        banRegx = new String[banned_id.length];

        int pos = 0;
        for (String ban : banned_id) {
            // banned_id 정규식으로 바꾸자
            String regx = "^";
            for (char c : ban.toCharArray()) {
                if (c != '*')
                    regx += c;
                else {
                    regx += '.';
                }
            }
            regx += '$';
            /*
              굳이 이렇게 할 필요 없이,
              banned_id[i] = banned_id[i].replace("*", "."); 를 해도 됐다.
              참고) .대신 [\\w]를 했으면 더 섬세한 코드
             */

            // user_id 중 regx와 일치하는 문자 찾기
            int findMatches = 0;
            for (String user : user_id) {
                if (Pattern.matches(regx, user)) {
                    ans.add(user);
                }
            }
            banRegx[pos] = regx;
            pos++;
        }

        candiList = new ArrayList<>(ans);
        banIdSize = banned_id.length;
//        visited = new boolean[candiList.size()];

        return getBanCnt(0, 0);
    }

    static int getBanCnt(int k, int compareBanId) {
        if (k == candiList.size() || compareBanId == banIdSize) {
            for (HashSet<String> ad : alreadyDone){
                if (visited.containsAll(ad)){ /* A.containsAll(B) => B는 A의 부분집합인가? */
                    return 0;
                }
            }
            alreadyDone.add(new HashSet<>(visited)); /* 깊은복사, 얕은복사 조심!! */
            return 1;
        }

        int ret = 0;

        /* 오래걸린 이유 :
        * 중복되는 부분을 제거하는 방법을 생각하는 것이 오래걸렸다.
        * 이 문제의 경우 '기저'부분에서 중복을 제거할 수 있다는 것을 배웠으니 다음엔 바로 해결할 수 있도록 하자
        */
        for (int i = 0; i < candiList.size(); i++) {
            String candidate = candiList.get(i);
            if (visited.contains(candidate)) continue;
            if (Pattern.matches(banRegx[compareBanId], candiList.get(i))) {
                visited.add(candidate);
                ret += getBanCnt(k + 1, compareBanId + 1);
                visited.remove(candidate);
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"fr*d*", "abc1**"};

        String[] user_id2 = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id2 = {"*rodo", "*rodo", "******"};

        String[] user_id3 = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id3 = {"fr*d*", "*rodo", "******", "******"};


//        System.out.println(solution(user_id, banned_id));
//        System.out.println(solution(user_id2, banned_id2));
        System.out.println(solution(user_id3, banned_id3));
    }
}
