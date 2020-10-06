package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;



public class SWEA_5658_보물상자비밀번호 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(st.nextToken());
		for (int t = 1; t <=TC; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			 List<String> result = new ArrayList<>();
			 String input = br.readLine();
	         StringBuilder list = new StringBuilder(input);	            
	         list.append(input.substring(0,N/4-1));
	         System.out.println(list);
	 
	         for (int i = 0; i <list.length()-(N/4-1); i++) {
				String temp = list.substring(i,i+N/4);
				if(!result.contains(temp))
					result.add(temp);
	         }
	         Collections.sort(result,new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					// TODO Auto-generated method stub
					return o2.compareTo(o1);
				}
			});
	         
	         sb.append(Integer.parseInt(result.get(K-1), 16)).append("\n");
	         
	         
	             
	        }
	        System.out.println(sb);
	}

	
	private static String src="5\n" + 
			"12 10\n" + 
			"1B3B3B81F75E\n" + 
			"16 2\n" + 
			"F53586D76286B2D8\n" + 
			"20 14\n" + 
			"88F611AE414A751A767B\n" + 
			"24 16\n" + 
			"044D3EBA6A647B2567A91D0E\n" + 
			"28 11\n" + 
			"8E0B7DD258D4122317E3ADBFEA99";
}
