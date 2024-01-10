package CodingTest.src.programmers_lv2;

import java.util.LinkedList;

public class Solution75 {
    public int solution(int cacheSize, String[] cities) {
        if(cacheSize == 0){
            return cities.length*5;
        }
        int answer = 0;
        LinkedList<String> cache = new LinkedList<>();
        for (int i = 0; i < cities.length; i++) {
            String s = cities[i].toUpperCase();
            if(cache.remove(s)){
                // �ش� ���ð� �ְ�, ������ true�� ��ȯ��
                // hit �� ��� ����ð� 1
                answer += 1;
                cache.add(s);
            } else{
                // miss �� ��� ����ð� 5
                answer += 5;
                if(cache.size() >= cacheSize){
                    // ���� �����ϰ� �ִ� ĳ�� ������ >= �־��� ĳ�� ������
                    cache.remove(0);
                    // ���� �տ� �ִ� ���� ����
                }
                cache.add(s);
                // �׸��� �űԸ� ���� ����

            }
        }
        return answer;
    }
}