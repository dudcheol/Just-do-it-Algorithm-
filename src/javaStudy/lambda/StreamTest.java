package com.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

// Stream API : Collection 관련 데이터들을 가공해서 처리하는 기능
/* Stream 사용 순서
 * 1. 스트림 생성		2. 중간 연산		3. 최종 연산
 * 스트림생성().중간연산().중간연산().최종연산()
 */
public class StreamTest {

	public static void main(String[] args) {
		List<String> names = Arrays.asList("orange", "pro", "olive", "java");
		Stream<String> str = names.stream();
		str.filter(x -> x.contains("o")).forEach(x -> System.out.println(x));

		List<Integer> ages = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		ages.stream().filter(x -> x > 3).limit(3).forEach(x -> System.out.println(x));
	}

}
