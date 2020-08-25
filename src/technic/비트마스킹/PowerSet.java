package technic.비트마스킹;

import java.util.Scanner;

public class PowerSet {
    static int N;
    static int[] numbers;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = sc.nextInt();
        }
        int caseCount = 1 << N;
        generateSubset(caseCount);
    }

    private static void generateSubset(int caseCount) {
        // 공집합을 빼고싶다면 flag = 1부
        for (int flag = 0; flag < caseCount; flag++) {
            // flag의 각 비트자리를 확인하여 원소 선택 유/무를 판단
            for (int j = 0; j < N; j++)
                if ((flag & (1 << j)) != 0)
                    System.out.print(numbers[j] + " ");
            System.out.println();
        }
    }
}
