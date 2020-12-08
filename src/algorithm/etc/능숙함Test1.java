package algorithm.etc;

public class 능숙함Test1 {
    // Q. nums 배열에 있는 문자 중 가장 많이 등장하는 문자를 출력한다
    public static void main(String[] args) {
        char[] nums = {'A','S','A','D','A','D','A','S'};
        int[] num = new int['Z'+1];
        int max = 0;
        int idx = 0;

        for (int i = 0; i < nums.length; i++) {
            num[nums[i]]++;
            if (max < num[nums[i]]){
                max = num[nums[i]];
                idx = nums[i];
            }
        }

        System.out.println((char)idx);
    }
}
