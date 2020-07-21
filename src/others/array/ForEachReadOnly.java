package others.array;

import java.util.Arrays;

public class ForEachReadOnly {
    public static void main(String[] args) {
        /* ForEach문은 Read Only이다. */
        int N = 6;

        int[] resultArray = new int[5];

        for (int i = 0; i < resultArray.length; i++) {
            resultArray[i] = (int) (Math.random() * N) + 1;
        }

        for (int x : resultArray) {
            System.out.println(x);
        }

        Arrays.fill(resultArray, 0);
        System.out.println("-----------------");

        int[] resultArray2 = new int[5];

        // ForEach를 사용해서 값을 저장할 수 없다.
        // READ ONLY !!
        for (int x : resultArray) {
            // 왜?
            // x는 resultArray의 값을 가져와서 저장시키는 개념이기 때문이다.
            // resultArray[0..last]에 값을 할당하는 것이 아니라, 그곳에 있는 값을 가져와서 x라는 로컬변수에 쓰는 방식이므로
            // 아래의 방법은 그냥 x라는 로컬변수에 값을 저장하는 것 뿐이다.
            x = (int) (Math.random() * N) + 1;
        }

        for (int x : resultArray) {
            System.out.println(x);
        }

        System.out.println("------------------");

        String[] students = {"홍길동", "유재석", "강호동", "이승기"};

        for (String student : students)
            System.out.println(student);
    }
}
