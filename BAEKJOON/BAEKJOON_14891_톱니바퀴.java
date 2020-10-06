package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BAEKJOON_14891_톱니바퀴 {

	static List<Integer>[] list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st;
		
		list = new ArrayList[4];
		
		for (int i = 0; i <list.length; i++) {
			list[i] = new ArrayList<>();
			String temp = br.readLine();
			for (int j = 0; j < temp.length(); j++) {
				list[i].add(temp.charAt(j)-'0');
			}			
		}
		
		st = new StringTokenizer(br.readLine());
		int orderCnt = Integer.parseInt(st.nextToken());
		for (int i = 0; i <orderCnt; i++) {
			st = new StringTokenizer(br.readLine());
			int index = Integer.parseInt(st.nextToken())-1;
			int dir = Integer.parseInt(st.nextToken());
			
			find(index,dir);
		}
		int total =0;
		for (int i = 0; i < list.length; i++) {
			if(list[i].get(0)==1) {
				total+=Math.pow(2, i);
			}
		}
		System.out.println(total);
	}
	private static void find(int index, int dir) {
		boolean []visited = new boolean[4];
		List<info> result = new ArrayList<>();
		result.add(new info(index,dir));
		Queue<info> queue = new LinkedList<>();
		queue.add(new info(index,dir));
		visited[index]=true;
		
		while(!queue.isEmpty()) {
			info temp = queue.poll();
			
			if(temp.index-1>=0 && !visited[temp.index-1] && list[temp.index-1].get(2)!=list[temp.index].get(6)) {
				queue.add(new info(temp.index-1,temp.dir*-1));
				visited[temp.index-1]=true;
				result.add(new info(temp.index-1,temp.dir*-1));
			}
			if(temp.index+1<4 && !visited[temp.index+1] && list[temp.index+1].get(6)!=list[temp.index].get(2)) {
				queue.add(new info(temp.index+1,temp.dir*-1));
				visited[temp.index+1]=true;
				result.add(new info(temp.index+1,temp.dir*-1));
			}
				
		}
		
		for (int i = 0; i < result.size(); i++) {
			info nf = result.get(i);
			if(nf.dir==1) {
				int temp =list[nf.index].get(list[nf.index].size()-1);
				list[nf.index].remove(list[nf.index].size()-1);
				list[nf.index].add(0, temp);
			}
			else {
				int temp =list[nf.index].get(0);
				list[nf.index].remove(0);
				list[nf.index].add(temp);
			}
		}
		
	}
	
	private static class info {
		int index,dir;

		public info(int index, int dir) {
			super();
			this.index = index;
			this.dir = dir;
		}
		
	}
	private static String src=
			"10010011\n" + 
			"01010011\n" + 
			"11100011\n" + 
			"01010101\n" + 
			"8\n" + 
			"1 1\n" + 
			"2 1\n" + 
			"3 1\n" + 
			"4 1\n" + 
			"1 -1\n" + 
			"2 -1\n" + 
			"3 -1\n" + 
			"4 -1";
}
