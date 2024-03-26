package programmers_lv2;

import java.util.ArrayList;
import java.util.List;

public class Solution78 {
    public int solution(String str1, String str2) {
        int answer = 0;

        // ���ڿ� �빮�ڷ� �� �����ϱ�
        str1.toUpperCase();
        str2.toUpperCase();
        
        List<String> str1List = getElement(str1);
        List<String> str2List = getElement(str2);
        
        return answer;
    }

	// ���ڿ��� �� ���ڷ� �ڸ� ���Ҹ� �ִ� ����Ʈ
	public List<String> getElement(String str) {
		List<String> strList = new ArrayList<String>();

		for (int i = 0; i < str.length() - 1; i++) {
			int str1Num = (int) str.charAt(i);
			int str2Num = (int) str.charAt(i + 1);

			if ((64 < str1Num && str1Num < 91) || (96 < str1Num && str1Num < 123)) {
				if ((64 < str2Num && str2Num < 91) || (96 < str2Num && str2Num < 123)) {
					strList.add(String.valueOf((char) str1Num).concat(String.valueOf((char) str2Num)));
				}
			}
		}
		return strList;
	}




	private int jacquard(List<String> strList1, List<String> strList2) {
		if (strList1.size() == 0 && strList2.size() == 0) {
			return 65536;
		}
		int min = 0;
		int max = strList1.size() + strList2.size();
		// ���� Ŭ ���� ���� �� �ٸ���
		for (String str : strList1) {
			for (int i = 0; i < strList2.size(); i++) {
				if (str.equals(strList2.get(i))) {
					// ���� ���ٸ�
					min++;
					System.out.println("str : "+str);
					System.out.println("strList2.get(i) : "+strList2.get(i));
					strList2.remove(i);
					// �ش� ���� �ش��� �����Ƿ� ������
					break; // ���� ���ҷ�
				}
			}
		}
		max = max - min;
		double result = (double) min / max;
		return (int) (result * 65536);
	}
}