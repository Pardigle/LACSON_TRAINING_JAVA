package Day5;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class MalformedLogEntryException extends Exception {
	private static final long serialVersionUID = 1L;
	public MalformedLogEntryException(String message) {
		super(message);
	}
}

public class LogAnalyzer {
	public static void main(String[] args) {
		try {
			String path = "C:\\Users\\PatrickMichaelLacson\\Downloads\\training\\training\\src\\main\\java\\Day5\\";
			BufferedReader br = new BufferedReader(new FileReader(path + "server.log"));
			BufferedWriter bw = new BufferedWriter(new FileWriter(path + "summary.txt"));
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			
			int totalEntries = 0;
			Map<String, Integer> levelTallies = new HashMap<>();
			List<String> errorMessages = new ArrayList<>();
			LocalDateTime earliestTimestamp = null;
			LocalDateTime latestTimestamp = null;
			
			String line;
			while ((line = br.readLine()) != null) {
				validateLogEntry(line);
				
				totalEntries++;
				String[] parsedLine = line.split(" ");
				String rawTimestamp = parsedLine[0] + " " + parsedLine[1];
				LocalDateTime timestamp = LocalDateTime.parse(rawTimestamp.substring(1, rawTimestamp.length() - 1), formatter);
				String level = parsedLine[2].substring(0, parsedLine[2].length() - 1);
				String message = line.substring(line.indexOf(": ") + 1);
				
				if (!levelTallies.containsKey(level)) {
					levelTallies.put(level, 0);
				}
				levelTallies.put(level, levelTallies.get(level) + 1);
				
				if (level.equals("ERROR")) {
					errorMessages.add(message);
				}
				
				if (earliestTimestamp == null || timestamp.isBefore(earliestTimestamp)) {
					earliestTimestamp = timestamp;
				}
				if (latestTimestamp == null || timestamp.isAfter(latestTimestamp)) {
					latestTimestamp = timestamp;
				}
			}
			
			bw.write("Log Summary Report");
			bw.newLine();
			bw.write("-----------------");
			bw.newLine();	
			
			bw.write("Total Entries: " + totalEntries);
			bw.newLine();
			bw.write("INFO: " + Integer.toString(levelTallies.get("INFO")));
			bw.newLine();
			bw.write("WARN: " + Integer.toString(levelTallies.get("WARN")));
			bw.newLine();
			bw.write("ERROR: " + Integer.toString(levelTallies.get("ERROR")));
			bw.newLine();			
			bw.newLine();

			bw.write("Error Messages:");
			bw.newLine();
			for (String message : errorMessages) {
				bw.write(" - " + message);
				bw.newLine();
			}
			bw.newLine();

			
			bw.write("Earliest Timestamp: " + earliestTimestamp.format(formatter));
			bw.newLine();
			bw.write("Latest Timestamp: " + latestTimestamp.format(formatter));
			
			br.close();
			bw.close();
			
			System.out.println("File created successfully!");
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		} catch (IOException e) {
			System.out.println("File error: " + e.getMessage());
		} catch (MalformedLogEntryException e) {
			System.out.println("Entry log error: " + e.getMessage());
		}
	}
	
	public static void validateLogEntry(String logEntry) throws MalformedLogEntryException {
		Set<String> validLevels = new HashSet<>();
		validLevels.add("INFO");
		validLevels.add("WARN");
		validLevels.add("ERROR");
		
		String[] parsedLine = logEntry.split(" ");
		String timestamp = parsedLine[0] + " " + parsedLine[1];
		String level = parsedLine[2];

		if (!timestamp.startsWith("[") || !timestamp.contains("]")) {
			throw new MalformedLogEntryException("Entry must start with \"[\" and contain a \"]\".");
		}
		
		if (!(validLevels.contains(level)
				|| validLevels.contains(level.substring(0, level.length() - 1)))) {
			throw new MalformedLogEntryException("Log entry level must be either INFO, WARN, or ERROR.");
		}
		
		if (level.charAt(level.length() - 1) != ':') {
			throw new MalformedLogEntryException("Log entry level must be followed with a \":\"");
		}
	}
}
