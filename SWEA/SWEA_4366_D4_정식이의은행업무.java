package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_4366_D4_정식이의은행업무 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(st.nextToken());
		for (int t = 1; t <=TC; t++) {
			sb.append("#").append(t).append(" ");
			
			StringBuilder binary = new StringBuilder(br.readLine());
			List<Integer> bi = new ArrayList<>();
			bi.add(Integer.parseInt(binary.toString(),2));
			StringBuilder tenary = new StringBuilder(br.readLine());			
			List<Integer> te = new ArrayList<>();
			te.add(Integer.parseInt(tenary.toString(),3));
			
			for (int i = 0; i < binary.length(); i++) {
				if (binary.charAt(i)=='0') {
					binary.setCharAt(i, '1');
					bi.add(Integer.parseInt(binary.toString(),2));
					binary.setCharAt(i, '0');
				}else {
					binary.setCharAt(i, '0');
					bi.add(Integer.parseInt(binary.toString(),2));
					binary.setCharAt(i, '1');
				}
			}
			for (int i = 0; i < tenary.length(); i++) {
				if (tenary.charAt(i)=='0') {
					tenary.setCharAt(i, '1');
					te.add(Integer.parseInt(tenary.toString(),3));
					tenary.setCharAt(i, '2');
					te.add(Integer.parseInt(tenary.toString(),3));
					tenary.setCharAt(i, '0');
				}else if(tenary.charAt(i)=='1') {
					tenary.setCharAt(i, '0');
					te.add(Integer.parseInt(tenary.toString(),3));
					tenary.setCharAt(i, '2');
					te.add(Integer.parseInt(tenary.toString(),3));
					tenary.setCharAt(i, '1');
				}else {
					tenary.setCharAt(i, '0');
					te.add(Integer.parseInt(tenary.toString(),3));
					tenary.setCharAt(i, '1');
					te.add(Integer.parseInt(tenary.toString(),3));
					tenary.setCharAt(i, '2');
				}
			}
			
			for (int i = 0; i < bi.size(); i++) {
				if(te.contains(bi.get(i))) {
					sb.append(bi.get(i)).append("\n");
					break;
				}
			}
			
			
		}
		System.out.println(sb);
	}
	private static String src="1\n" + 
			"1010\n" + 
			"212";
}
