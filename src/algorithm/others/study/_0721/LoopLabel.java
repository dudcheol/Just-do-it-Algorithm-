package algorithm.others.study._0721;

public class LoopLabel {
    public static void main(String[] args) {
        /* Label */
        // here : label
        here:
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (j == 3) break here; // 원래는 바로 위의 for문만 나오지만, label을 통해 break할 위치를 명시해주면 해당 for문이 break됨.
                System.out.println(i + "," + j);
            }
        }
    }
}
