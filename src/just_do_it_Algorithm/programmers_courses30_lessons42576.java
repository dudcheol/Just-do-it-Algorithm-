package just_do_it_Algorithm;

import java.util.ArrayList;
import java.util.HashMap;

public class programmers_courses30_lessons42576 {
	public static void main(String[] args) {
		String[] participant = {"marina", "josipa", "nikola", "vinko", "filipa"};
		String[] completion = {"josipa", "filipa", "marina", "nikola"};

		String answer = solution(participant, completion);

		System.out.println(answer);
	}

	public static String solution(String[] participants, String[] completions) {
		String answer = "";
		HashMap<String, ArrayList<Boolean>> marathoners = new HashMap<>();

		for (String participant : participants) {
			ArrayList<Boolean> list = new ArrayList<>();
			list.add(true);
			if (marathoners.containsKey(participant)) {
				list = marathoners.get(participant);
				list.add(true);
				marathoners.put(participant, list);
			} else {
				marathoners.put(participant, list);
			}
		}

		for (String completion : completions) {
			if (marathoners.get(completion).size() <= 1) {
				marathoners.remove(completion);
			} else {
				marathoners.get(completion).remove(0);
			}
		}

		for (String participant : participants) {
			if(marathoners.get(participant)!=null && marathoners.get(participant).size()!=0) {
				answer = participant;
				break;
			}
		}

		return answer;
	}
}
