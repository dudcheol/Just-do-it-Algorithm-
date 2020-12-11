package algorithm.programmers.kakaoBlind2018;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class 방금그곡 {
    static class Music {
        int playtime;
        String name;
        String playStr;

        Music(int p, String n, String ps) {
            this.playtime = p;
            this.name = n;
            this.playStr = ps;
        }
    }

    public static String solution(String m, String[] musicinfos) {
        String answer = "";
        int msize = musicinfos.length;
        ArrayList<Music> akbos = new ArrayList<Music>();
        m = convert(m);

        for (int i = 0; i < msize; i++) {
            StringTokenizer st = new StringTokenizer(musicinfos[i], ",");
            String[] start = st.nextToken().split(":");
            String[] end = st.nextToken().split(":");
            // System.out.println(start[0]+start[1]+","+end[0]+end[1]);
            int playtime = (Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1])) - (Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]));
            // System.out.println(playtime);
            String name = st.nextToken();
            String akbo = convert(st.nextToken());
            StringBuilder sb = new StringBuilder();
            // sb.append(akbo);
            // int limit = 0;
            int akbosize = akbo.length();
            // if(playtime < akbosize) limit = akbosize;
            // else limit = playtime;
            for (int k = 0; k < playtime; k++) sb.append(akbo.charAt(k % akbosize));
            String playakbo = sb.toString();
            // System.out.println(playakbo);
            akbos.add(new Music(playtime, name, playakbo));
        }

        akbos.sort((o1, o2) -> {
            return -Integer.compare(o1.playtime, o2.playtime);
        });

        for (Music akbo : akbos) {
            if (akbo.playStr.contains(m)) {
                // System.out.println(akbo.playtime);
                return akbo.name;
            }
        }

        return "(None)";
    }

    public static String convert(String origin) {
        origin = origin.replace("C#", "c");
        origin = origin.replace("D#", "d");
        origin = origin.replace("F#", "f");
        origin = origin.replace("G#", "g");
        origin = origin.replace("A#", "a");
        return origin;
    }

    public static void main(String[] args) {
        String m = "ABCDEFG";
        String[] musicinfos = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};
        System.out.println(solution(m, musicinfos));
    }
}
