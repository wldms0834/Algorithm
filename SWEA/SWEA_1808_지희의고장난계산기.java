package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1808_지희의고장난계산기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		List<Integer> numbers;
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int TC =Integer.parseInt(st.nextToken());
		for (int t = 1; t <=TC; t++) {
			numbers = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i <10; i++) {
				if(Integer.parseInt(st.nextToken())==1) numbers.add(i);
			}		
			int X = Integer.parseInt(br.readLine());
			
			for (int i = 0; i < 1<<numbers.size(); i++) {
				List<Integer> result = new ArrayList<>();
				for (int j = 0; j <numbers.size(); j++) {
					if((i&1<<j)>0) {
						result.add(numbers.get(j));
					}
				}
				
				for (int j = 0; j < result.size(); j++) {
					//if()
				}
				System.out.println(result);
			}
		}

	}
	private static String src ="3\n" +  
			"0 1 1 0 0 1 0 0 0 0\n" + 
			"60\n" + 
			"1 1 1 1 1 1 1 1 1 1\n" + 
			"128\n" + 
			"0 1 0 1 0 1 0 1 0 1\n" + 
			"128";
}
