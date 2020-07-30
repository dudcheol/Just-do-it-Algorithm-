package algorithm.others.array;

public class Memory {
    public static void main(String[] args) {
        /* Local Variables vs Array in Memory */
        int i = 3; // 기본형 - 스택
        char c = 'A'; // 기본형 - 스택

        // 실제로 Object가 만들어지는 공간 = Heap
        int[] intArr = new int[5]; // 참조형 - 값은 힙에 만들어지고 그곳의 주소(Memory Address)를 스택에 넣음. 왜? 가변적이기 때문
        intArr[i] = 27; // 참조형 - 힙에 값 생성 -> 스택에는 주소

        char[] charArr = new char[2];
        charArr[1] = 'A';

        String str1 = "Hello"; // Heap 공간 내의 다른 위치에 저장됨
        String str2 = new String("Hello");
        String str3 = "Hello"; // str1에서 생성했던 "Hello"객체를 재사용함 (즉, str1과 str3는 같은 객체가 됨)
        String str4 = new String("Hello"); // new로 인해 새로운 Object를 생성하므로 주소값을 새로 받음
    }
}
