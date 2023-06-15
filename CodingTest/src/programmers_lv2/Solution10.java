package CodingTest.src.programmers_lv2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 1. 시작 기준으로 오름차순 정렬 2. stack 생성 3. 현재 작업 중 과제 vs 새 과제 비교
 */

public class Solution10 {
	static class Assignment {
		private String name;
		private int start;
		private int time;

		public Assignment(String name, String start, String time) {
			this.name = name;
			this.start = start;
			this.time = time;
		}

		public int timeToMinute(String start) {
			String[] arr = start.split(":");
			int hour = Integer.parseInt(arr[0]) * 60;
			int minute = Integer.parseInt(arr[1]);
			return hour + minute;
		}
	}

	public String[] solution(String[][] plans) {
		Assignment[] arr = new Assignment[plans.length];

		for (int i = 0; i < arr.length; i++) {
			Assignment ass = new Assignment(plans[i][0], plans[i][1], plans[i][2]);
			arr[i] = ass;
		}

		Arrays.sort(arr, (o1, o2) -> { // *** 시작 시간 기준으로 정렬 
			return o1.start - o2.start;
		});
		
		
		Stack<Assignment> stack = new Stack<>(); // 진행중인 과제 
		List<String> ans = new ArrayList<String>(); // 정답을 담을 리스트 
		
		int curTime = -1; // 현재시간초기화
		
		for (int i = 0; i < arr.length; i++) {
			if(stack.isEmpty()) {// 과제 없는 경우 
				stack.push(arr[i]);
				continue;
			}
			// 진행중인 과제와 새로운 과제가 있는 경우 
			Assignment curAss = stack.peek();
			Assignment newAss = arr[i];
			
			// 새로운 과제의 시작시간과 진행중 과제의 종료시간 비교 
			curTime = curAss.start;
			
			// 현재 과제 시작 시간 + 작업시간 <= 새로운 과제 시작 시간 
			if(curTime+curAss.time<=newAss.start) {
				recursivePop(stack, newAss, curTime, ans);
			} else {
				// 현재 작업 중단하고 작업한 시간 갱신 및 새 작업 시작
				curAss.time -= newAss.start-curTime;
			}
			stack.push(newAss);
		}
		
		while(!stack.isEmpty()) {
			ans.add(stack.pop().name);
		}
		
		return ans.toArray(new String[0]);
		
	}

	private void recursivePop(Stack<Assignment> stack, Assignment newAss, int curTime, List<String> ans) {
		if(stack.isEmpty()) {
			return;
		}
		
		Assignment curAss = stack.peek();
		// 진행중 과제 
		
		if(curTime+curAss.time<=newAss.start) {
			ans.add(stack.pop().name);
			// 진행중인 과제 계속함 
			recursivePop(stack, newAss, curTime+curAss.time, ans);
			// 진행중과제 + 시간 
		}else {
			curAss.time -= newAss.start-curTime;
		}
	}
}