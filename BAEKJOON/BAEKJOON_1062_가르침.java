package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BAEKJOON_1062_가르침 {
	static int wordCnt, k , tcnt;
	static List<Character>[]list;
	static List<Character> total;
	static int max = Integer.MIN_VALUE;
	static List<Character> result = new ArrayList<Character>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		wordCnt = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		if (k<5) {
			System.out.println(0);
			return;
		}
		
		k-=5;
		tcnt=0;
		List<Character> not = new ArrayList<Character>();
		not.add('a');
		not.add('n');
		not.add('t');
		not.add('c');
		not.add('i');
		
		list = new ArrayList[wordCnt];
		total =  new ArrayList<Character>();
		
		for (int i = 0; i <wordCnt; i++) {
			list[i] = new ArrayList<>();
		}	
		for (int i = 0; i <wordCnt; i++) {
			String temp = br.readLine();
			for (int j = 0; j < temp.length(); j++) {
				char tempC = temp.charAt(j);
				if (!not.contains(tempC) && !list[i].contains(tempC)) {
					list[i].add(tempC);
				}
			}
			if (list[i].size()<=k) {
				tcnt++;
				for (int j = 0; j <list[i].size(); j++) {
					if (!total.contains(list[i].get(j))) {
						total.add(list[i].get(j));
					}
				}
				
			}
		}
		
		if (total.size()<=k) {
			System.out.println(tcnt);
			return;
		}
		
				
		dfs(0,0);
		if (max==Integer.MIN_VALUE) {
			max=0;
		}
		System.out.println(max);
	
	}
	
	 private static void dfs(int start, int count) {
	        if(count ==k) {
	            int rs = 0;
	            for(int i=0; i<wordCnt; i++) {
	                boolean isTrue = true;
	                for(int j=0; j<list[i].size(); j++) {
	                    if(!result.contains(list[i].get(j))) {
	                        isTrue = false;
	                        break;
	                    }
	                }
	                if(isTrue) {
	                    rs++;
	                }
	            }
	            max = Math.max(max, rs);
	            return;
	        }
	        
	        for(int i=start; i<total.size(); i++) {
	            if(!result.contains(total.get(i))) {
	            	result.add(total.get(i));
	                dfs(i, count+1);
	               result.remove(result.size()-1);
	            }
	        }
	    }

	private static String src="3 6\n" + 
			"antarctica\n" + 
			"antahellotica\n" + 
			"antacartica";
}
