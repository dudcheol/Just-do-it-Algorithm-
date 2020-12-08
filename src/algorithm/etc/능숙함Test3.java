package algorithm.etc;

public class 능숙함Test3 {
    // Q. 한 문자열 입력 후, 순차 탐색 후 역순 탐색하며 출력합시다.
    static String input;
    static int N;
    public static void main(String[] args) {
        input = "ASADADAS";
        N = input.length();

        recursion(0);
    }

    private static void recursion(int k) {
        if (k==N) {
            System.out.println();
            return;
        }
        System.out.print(input.charAt(k));
        recursion(k+1);
        System.out.print(input.charAt(k));
    }
}
