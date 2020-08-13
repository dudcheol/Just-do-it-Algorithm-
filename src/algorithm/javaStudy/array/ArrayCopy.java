package algorithm.javaStudy.array;

public class ArrayCopy {
    public static void main(String[] args) {
        /* 배열의 값 복사 메소드 */
        String[] students = {"홍길동", "유재석", "강호동", "이승기"};

        String [] students3 = new String[5];
        System.arraycopy(students, 0, students3, 0, 4);
        students3[4] = "신동엽";

        for (String student : students3)
            System.out.println(student);

        /* Array is Immutable */
        // 배열은 변경할 수 없다!
        // 변경이 필요한 경우, 새로 작성하는 것이 일반적으로 유리하다.
        // 계속해서 바꿀바에 충분히 큰 배열을 선언하여 사용하는 것이 좋다.
    }
}
