package technic.이진검색;

import java.util.Arrays;

public class BinarySearch {

    static int[] val = {3, 11, 15, 20, 21, 29, 45, 59, 65, 72};

    public static void main(String[] args) {
        System.out.println(Arrays.toString(val));
        System.out.println(binarySearch(21) + "번째 인덱스에 있습니다.");
        System.out.println(binarySearch(11, 0, val.length - 1) + "번째 인덱스에 있습니다.");
        System.out.println(Arrays.binarySearch(val, 20) + "번째 인덱스에 있습니다.");
    }

    private static int binarySearch(int target) {
        // 중앙값 찾아서 target과 비교
        int middle = 0, start = 0, end = val.length - 1;

        while (start <= end) {
            middle = (start + end) / 2;

            if (val[middle] == target) { // 찾음
                return middle;
            } else if (val[middle] < target) { // target이 더 큼
                start = middle + 1; // middle과 비교했더니 target이 더 크다는 결과나 나왔으므로 middle 다음 값을 찾는다
            } else { // target이 더 작음
                end = middle - 1;
            }
        }

        return -1; // target을 찾지 못함
    }

    private static int binarySearch(int target, int start, int end) {

        if (start <= end) {
            int middle = (start + end) / 2;// 중앙값 찾아서 target과 비교
            if (val[middle] == target) { // 찾음
                return middle;
            } else if (val[middle] < target) { // target이 더 큼
                return binarySearch(target, middle + 1, end); // middle과 비교했더니 target이 더 크다는 결과나 나왔으므로 middle 다음 값을 찾는다
            } else { // target이 더 작음
                return binarySearch(target, start, middle - 1);
            }
        }
        return -1; // target을 찾지 못함

    }
}
