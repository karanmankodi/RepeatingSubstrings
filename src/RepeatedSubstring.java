import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

/**
 * 
 */

/**
 * @author karanmankodi
 *
 */
public class RepeatedSubstring {

	public static boolean containsAllSpaces(StringBuilder sb) {
		int i = 0;
		char c = ' ';
		while (i < sb.length() && sb.charAt(i) == c) {
			i++;
		}
		if (i == sb.length()) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * @param args - file path as String
	 * input = file path as String
	 * output = longest repeated substring printed on console
	 */
	public static void main(String[] args) {
		File f = new File(args[0]);
		FileReader fr;
		BufferedReader br;
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			TreeMap<Integer, StringBuilder> subStrings = new TreeMap<Integer, StringBuilder>(); 
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				// starting with a fresh treemap for each line.
				subStrings.clear();
				for(int outer = 0; outer< currentLine.length(); outer++) {
					for (int inner=outer+1; inner< currentLine.length(); inner++) {
						if (currentLine.charAt(outer) != currentLine.charAt(inner)) {
							continue;
						} else {
							int innerStart = inner;
							StringBuilder tempsb = new StringBuilder();
							tempsb.append(currentLine.charAt(inner));
							
							int firstComparisonPointer = outer;
							int secondComparisonPointer = inner;
							firstComparisonPointer++;
							secondComparisonPointer++;
							while (secondComparisonPointer < currentLine.length() && firstComparisonPointer < innerStart && currentLine.charAt(firstComparisonPointer) == currentLine.charAt(secondComparisonPointer)) {
								tempsb.append(currentLine.charAt(secondComparisonPointer));
								firstComparisonPointer++;
								secondComparisonPointer++;
							}
							if (!(subStrings.containsKey(tempsb.length()))) {
								if (!(containsAllSpaces(tempsb))){
									subStrings.put(tempsb.length(), tempsb);

								} 
							}
						}
					}
				}
				if (subStrings.isEmpty()){
					System.out.println("NONE");
				} else {
					System.out.println(subStrings.get(subStrings.lastKey()));
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println ("File not found. Please specify the correct file path.");
		} catch (IOException e) {
			System.out.println ("IO Exception");
		} 

	}

}
