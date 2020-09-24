package technic.동적계획법;

import java.util.Arrays;
import java.util.Scanner;

public class LISTest2 { // O(nlogn) // 교수님say: 꼭 알아두세요!!

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		int[] LIS = new int[N]; // 각 LIS의 길이를 만족하는 맨 끝에오는 최소값을 저장

		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}

		int size = 0; /// lis 길이
		for (int i = 0; i < N; i++) {
			// binarySearch - O(logN)
			int temp = Arrays.binarySearch(LIS, 0, size, arr[i]); // arr[i] : 탐색 키
													   // ==> 찾으면 인덱스
													   // ==> 못찾으면 음수값으로 자신이 삽입될 위치 반환 : -삽입위치-1
			// 중복값이 없다면 temp : 음수값
			temp = Math.abs(temp) - 1; // 삽입 위치 환산
			LIS[temp] = arr[i];
			
			if(temp == size) { // 맨뒤에 추가하는 상황
				++size;
			}
		}
		System.out.println(size);
		
	}

}
