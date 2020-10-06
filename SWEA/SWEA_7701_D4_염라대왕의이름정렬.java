package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SWEA_7701_D4_염라대왕의이름정렬 {

	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb= new StringBuilder(); 
		
		int TC = Integer.parseInt(st.nextToken());
		for (int t = 1; t <=TC; t++) {			
			Set<String> hastSet = new HashSet();
			sb.append("#").append(t).append("\n");
			st = new StringTokenizer(br.readLine());
			int nameCnt = Integer.parseInt(st.nextToken());
			
			for (int i = 0; i <nameCnt; i++) {
				String s = br.readLine();
				hastSet.add(s);
			}
			List<String> temp = new ArrayList<>(hastSet);
			Collections.sort(temp, new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					Integer n1 = o1.length();
					Integer n2 = o2.length();
					if(n1==n2) {
						return o1.compareTo(o2);
					}
					return n1.compareTo(n2);
				}
			});
			
			
			for (String string : temp) {
				sb.append(string).append("\n");
			}
		}System.out.println(sb);

	}
	
	private static class Name {
		String name;
		
		public Name(String name) {
			super();
			this.name = name;
		}
	}
	private static String src="2\n" + 
			"5\n" + 
			"nazeeee\n" + 
			"name\n" + 
			"iseeeee\n" + 
			"ho\n" + 
			"seok\n" + 
			"12\n" + 
			"s\n" + 
			"a\n" + 
			"m\n" + 
			"s\n" + 
			"u\n" + 
			"n\n" + 
			"g\n" + 
			"j\n" + 
			"j\n" + 
			"a\n" + 
			"n\n" + 
			"g";
}
