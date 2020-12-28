package algorithm.jungol.Beginner_Coder.여러가지;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _1311_카드게임 {

    static class Card implements Comparable<Card>{
        char color;
        int num;

        public Card(char color, int num) {
            this.color = color;
            this.num = num;
        }

        @Override
        public int compareTo(Card o) {
            return Integer.compare(this.num, o.num);
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[] colors = {'R','B','Y','G'};
        Card[] cards = new Card[5];
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            cards[i] = new Card(st.nextToken().charAt(0), Integer.parseInt(st.nextToken()));
        }

        // 같은 색, 같은 숫자가 몇개있는가
        int[] colorCnt = new int['Y'+1];
        int[] numCnt = new int[10];
        for (int i = 0; i < 5; i++) {
            colorCnt[cards[i].color]++;
            numCnt[cards[i].num]++;
        }

        // 가장 많은 같은 색, 같은 숫자 갯수
        int sameColorCnt = 0;
        int sameNumCnt = 0;
        int sameNum = 0;
        for (int i = 0; i < 4; i++) {
            if(sameColorCnt < colorCnt[colors[i]]){
                sameColorCnt = colorCnt[colors[i]];
            }
        }
        for (int i = 1; i < 10; i++) {
            if (sameNumCnt < numCnt[i]){
                sameNumCnt = numCnt[i];
                sameNum = i;
            }
        }

        // 숫자 연속적인가?
        boolean isCon = true;
        Arrays.sort(cards);
        int pre = cards[0].num;
        for (int i = 1; i < 5; i++) {
            if (cards[i].num!=++pre) {
                isCon = false;
                break;
            }
        }

        // 카드 5장이 모두 같은 색이면서 숫자가 연속적일 때, 점수는 가장 높은 숫자에 900을 더한다.
        if (sameColorCnt==5){
            if (isCon) System.out.println(cards[4].num + 900);
            // 5장의 카드 색깔이 모두 같을 때 점수는 가장 높은 숫자에 600을 더한다.
            else System.out.println(cards[4].num + 600);
        }
        // 카드 5장 중 4장의 숫자가 같을 때 점수는 같은 숫자에 800을 더한다.
        else if (sameNumCnt==4){
            System.out.println(sameNum + 800);
        }
        // 카드 5장 중 3장의 숫자가 같고 나머지 2장도 숫자가 같을 때 점수는 3장이 같은 숫자에 10을 곱하고 2장이 같은 숫자를 더한 다음 700을 더한다.
        else if (sameNumCnt==3){
            for (int i = 1; i < 10; i++) {
                if (numCnt[i]==2){
                    System.out.println(sameNum*10 + i + 700);
                    return;
                }
            }
            // 카드 5장 중 3장의 숫자가 같을 때 점수는 같은 숫자에 400을 더한다.
            System.out.println(sameNum + 400);
        }
        // 카드 5장의 숫자가 연속적일 때 점수는 가장 높은 숫자에 500을 더한다.
        else if (isCon){
            System.out.println(cards[4].num + 500);
        }
        // 카드 5장 중 2장의 숫자가 같고 또 다른 2장의 숫자가 같을 때 점수는 같은 숫자 중 큰 숫자에 10을 곱하고 같은 숫자 중 작은 숫자를 더한 다음 300을 더한다.
        else if (sameNumCnt==2){
            for (int i = 1; i < 10; i++) {
                if (i==sameNum)continue;
                if (numCnt[i]==2){
                    System.out.println(Math.max(sameNum, i)*10 + Math.min(sameNum, i) + 300);
                    return;
                }
            }
            // 카드 5장 중 2장의 숫자가 같을 때 점수는 같은 숫자에 200을 더한다.
            System.out.println(sameNum + 200);
        }
        // 위의 어떤 경우에도 해당하지 않을 때 점수는 가장 큰 숫자에 100을 더한다.
        else {
            System.out.println(cards[4].num + 100);
        }
    }
}
