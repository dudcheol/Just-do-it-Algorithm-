package just_do_it_Algorithm;

import java.util.Arrays;

public class programmers_courses30_lessons42577 {
	public static void main(String[] args) {
		String[] phone_book = { "119", "1219", "97674223", "13195524421", "9767" };

		System.out.println(solution(phone_book));
	}

	public static boolean solution(String[] phone_book) {
		boolean answer = true;

		Arrays.sort(phone_book);
		for (String num : phone_book) {
			System.out.println(num);
		}

		String compareNum = phone_book[0];
		for (int i = 1; i < phone_book.length; i++) {
			if (compareNum.length() > phone_book[i].length())
				continue;

			if (compareNum.equals(phone_book[i].substring(0, compareNum.length()))) {
				return false;
			} else {
				compareNum = phone_book[i];
			}
		}
		return true;
	}
}
