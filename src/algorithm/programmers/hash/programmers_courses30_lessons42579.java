package algorithm.programmers.hash;

import java.util.*;

public class programmers_courses30_lessons42579 {
	static int[] solution(String[] genres, int[] plays) {
		HashMap<Integer, Integer> playsMap = new HashMap<>();
		HashMap<Integer, Integer> keyIsPlaysMap = new HashMap<>();
		HashMap<String, List<Integer>> genresMap = new HashMap<>();
		HashMap<String, Integer> findPlaysRank = new HashMap<>();

		// key:고유번호 , value:재생횟수 int[]
		for (int i = 0; i < genres.length; i++) {
			playsMap.put(i, plays[i]);
		}
		System.out.println("playsMap:" + playsMap);

		// key:재생횟수 , value:고유번호 int[]
		for (int i = 0; i < genres.length; i++) {
			keyIsPlaysMap.put(plays[i], i);
		}
		System.out.println("keyIsPlaysMap:" + keyIsPlaysMap);

		// key:장르, value:고유번호
		List<Integer> id = new ArrayList<>();
		for (int i = 0; i < genres.length; i++) {
			if (genresMap.containsKey(genres[i])) {
				List<Integer> temp = genresMap.get(genres[i]);
				temp.add(i);
				genresMap.put(genres[i], temp);
			} else {
				List<Integer> temp = new ArrayList<>();
				temp.add(i);
				genresMap.put(genres[i], temp);
			}
		}
		System.out.println("genresMap:" + genresMap);

		// 재생 수가 많은 순으로 장르 정렬
		for (int i = 0; i < genres.length; i++) {
			if (findPlaysRank.containsKey(genres[i])) {
				findPlaysRank.put(genres[i], findPlaysRank.get(genres[i]) + plays[i]);
			} else {
				findPlaysRank.put(genres[i], plays[i]);
			}
		}
		System.out.println("findPlaysRank:" + findPlaysRank);

		// 장르별 재생수 저장된 배열 genrePlayAry
		int[] genrePlayAry = new int[findPlaysRank.size()];
		int k = 0;
		for (String genre : findPlaysRank.keySet()) {
			genrePlayAry[k] = findPlaysRank.get(genre);
			k++;
		}
		// genrePlayAry 오름차순 정렬
		Arrays.sort(genrePlayAry);

		ArrayList<Integer> answerList = new ArrayList<>();
		HashMap<Integer, int[]> compareMap = new HashMap<>();
		// 재생수가 많은 장르부터 차례로 순회
		for (int i = genrePlayAry.length - 1; i >= 0; i--) {
			System.out.println("genrePlayAry[i] = " + genrePlayAry[i]);
			// 재생수가 genrePlay인 장르 찾기
			for (String genre : findPlaysRank.keySet()) {
				if (genrePlayAry[i] == findPlaysRank.get(genre)) {
					// 해당 장르에서 두 곡 뽑아내기
					List<Integer> compareList = genresMap.get(genre);
					System.out.println(genre + "'s compareList:" + compareList);
					
					// 고유번호를 가지고있는 배열을 만들어서 재생수가 큰 수대로 정렬
					int[] idNums = new int[compareList.size()];
					int l=0;
					for(int idNum : compareList) {
						idNums[l] = idNum;
						l++;
						System.out.println(idNum);
					}
					// 버블정렬
					int temp;
					for(int p=idNums.length-1; p>0;p--) {
						for(int q=0; q<p; q++) {
							System.out.println(playsMap.get(idNums[q]));
							System.out.println(playsMap.get(idNums[q+1]));
							if(playsMap.get(idNums[q]) < playsMap.get(idNums[q+1])) {
								temp = idNums[q];
								idNums[q] = idNums[q+1];
								idNums[q+1] = temp;
							}
						}
					}
					
					for(int idNum : idNums)System.out.print(idNum+" ");
					System.out.println();
					
					// 두 곡만 수록한다. 한 곡만 있으면 한곡만 수록한다.
					int answerSize = idNums.length < 2 ? 1 : 2;
					for(int m=0;m<answerSize;m++) {
						answerList.add(idNums[m]);
					}
				}
			}
		}

		// answerList 배열로 만듬
		int[] answer = new int[answerList.size()];
		int l = 0;
		for (int ans : answerList) {
			answer[l] = ans;
			l++;
		}

		System.out.println("----------------------");
		return answer;
	}

	public static void main(String[] args) {
		String[] genres = { "classic", "pop", "classic", "classic", "pop" };
		int[] plays = { 500, 600, 150, 800, 2500 };

		int[] answers = solution(genres, plays);
		for (int answer : answers)
			System.out.print(answer + " ");
	}
}
