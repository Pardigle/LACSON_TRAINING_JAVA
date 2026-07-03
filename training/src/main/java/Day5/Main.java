package Day5;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Main {
	public static void main(String[] args) {
		try {
			String path = "C:\\Users\\PatrickMichaelLacson\\Downloads\\training\\training\\src\\main\\java\\Day5\\";
			BufferedReader br = new BufferedReader(new FileReader(path + "student.csv"));
			BufferedWriter bw = new BufferedWriter(new FileWriter(path + "student.json"));
			String line;
			String[] headers = br.readLine().split(",");
			
			bw.write("[");
			bw.newLine();
			
			List<Map<String, String>> students = new ArrayList<>();
			
			while ((line = br.readLine()) != null) {
				String[] parsedLine = line.split(",");
				
				Map<String, String> student = new HashMap<>();
				
				for (int column = 0; column < headers.length; column++) {
					student.put(headers[column], parsedLine[column]);
				}
				
				students.add(student);
			}
			
			for (int studentIndex = 0; studentIndex < students.size(); studentIndex++) {
				Map<String, String> student = students.get(studentIndex);

				bw.write("  {");
				bw.newLine();
				
				for (int column = 0; column < headers.length; column++) {
					bw.write(String.format("    \"%s\":\"%s\"", 
							headers[column], 
							student.get(headers[column])));
					if (column + 1 != headers.length) {
						bw.write(",");
					}
					bw.newLine();
				}
				
				bw.write("  }");
				if (studentIndex + 1 != students.size()) {
					bw.write(",");
				}
				
				bw.newLine();
			}
			
			bw.write("]");
			
			bw.close();
			br.close();
			
			System.out.println("File created successfully!");
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		} catch (IOException e) {
			System.out.println("File error: " + e.getMessage());
		}
	}
}
