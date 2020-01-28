package kakaoBlindTest._2020;

/*
 * 코딩테스트 연습 - 2020 KAKAO BLIND RECRUITMENT - 괄호 변환
 * 강의 보면서 풂
 * 
 * [배운 것]
 * 재귀를 구현할 때, return에 재귀를 넣는 방법을 사용하기도 한다.
 * 
 */

public class _60058_parenthesisTransformation {
	private static int pos=0;
    private static boolean isRightString(String str){
        boolean isRight = true;
        int left=0,right=0;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='(')
                left++;
            else
                right++;
            
            if(left<right){
                isRight = false;
            }
            
            if(left==right){
                pos=i+1;
                return isRight;
            }
        }
        return true;
    }
    
	public static String solution(String p) {
        if(p.length()==0) return "";
        
        boolean isRight = isRightString(p);
        String u = p.substring(0,pos);
        String v = p.substring(pos,p.length());
        
        if(isRight){
            return u+solution(v);
        }
        
        String answer = "(" + solution(v) + ")";
        for(int i=1;i<u.length()-1;i++){
            if(u.charAt(i)=='(')
                answer+=')';
            else
                answer+='(';
        }
        return answer;
    }
	
	public static void main(String[] args) {
//		String p ="(()())()";
//		String p =")(";
		String p ="()))((()";
		System.out.println(solution(p));
	}
}
