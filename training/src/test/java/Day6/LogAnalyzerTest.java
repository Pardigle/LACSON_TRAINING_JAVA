package Day6;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class LogAnalyzerTest {

	private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	
	@BeforeEach
	void setUp() {
		System.setOut(new PrintStream(outputStream));
	}
	
    @AfterEach
    public void restoreSystemOut() throws IOException {
        System.setOut(originalOut);
        Path path = Path.of("resources/summary.txt");
        Files.deleteIfExists(path);
    }
	
	/**
	 * File is generated and correct.
	 * 
	 * Checks whether file is created and equal to expected output.
	 * Checks whether the print output is correct.
	 */
	@Test
	void exec001() throws IOException {
		String expectedFile = Files.readString(Path.of("training/src/test/resources/Day6/exec001/summary.txt"));
		String file = "training/src/test/resources/Day6/exec001/server.log";
		LogAnalyzer.main(new String[] {file});
		String summaryFile = Files.readString(Path.of("resources/summary.txt"));
		assertAll(
			() -> assertEquals(expectedFile, summaryFile),
			() -> assertEquals("Analysis complete. Summary written to summary.txt" + System.lineSeparator(), outputStream.toString())
		);
	}

	/**
	 * One line does not start with a "[".
	 * 
	 * Checks whether print output is correct.
	 */
	@Test
	void exec002() throws MalformedLogEntryException {
		String file = "training/src/test/resources/Day6/exec002/server.log";
		LogAnalyzer.main(new String[] {file});
		assertEquals("Skipping malformed line: 2024-05-10 09:00:00] INFO: Server started successfully" 
			+ System.lineSeparator() 
			+ "Analysis complete. Summary written to summary.txt" 
			+ System.lineSeparator(), 
			outputStream.toString()
		);
	}
	
	/**
	 * One line does not contain a "]".
	 * 
	 * Checks whether print output is correct.
	 */
	@Test
	void exec003() throws MalformedLogEntryException {
		String file = "training/src/test/resources/Day6/exec003/server.log";
		LogAnalyzer.main(new String[] {file});
		assertEquals("Skipping malformed line: [2024-05-10 09:00:00 INFO: Server started successfully" 
			+ System.lineSeparator() 
			+ "Analysis complete. Summary written to summary.txt" 
			+ System.lineSeparator(), 
			outputStream.toString()
		);
	}
	
	/**
	 * One line level does not belong to INFO, WARN, ERROR.
	 * 
	 * Checks whether print output is correct.
	 */
	@Test
	void exec004() throws MalformedLogEntryException {
		String file = "training/src/test/resources/Day6/exec004/server.log";
		LogAnalyzer.main(new String[] {file});
		assertEquals("Skipping malformed line: [2024-05-10 09:00:03] WARNING: Configuration file loaded" 
			+ System.lineSeparator() 
			+ "Analysis complete. Summary written to summary.txt" 
			+ System.lineSeparator(), 
			outputStream.toString()
		);
	}

	/**
	 * One line does not contain a semi-colon after the level.
	 * 
	 * Checks whether print output is correct.
	 */
	@Test
	void exec005() throws MalformedLogEntryException {
		String file = "training/src/test/resources/Day6/exec005/server.log";
		LogAnalyzer.main(new String[] {file});
		assertEquals("Skipping malformed line: [2024-05-10 09:00:00] INFO Server started successfully" 
			+ System.lineSeparator() 
			+ "Analysis complete. Summary written to summary.txt" 
			+ System.lineSeparator(), 
			outputStream.toString()
		);
	}
	
	/**
	 * File is not found.
	 * 
	 * Checks whether print output is correct.
	 * Checks whether there is no file made.
	 */
	@Test
	void exec006() throws FileNotFoundException, NoSuchFileException {
		String file = "training/src/test/resources/Day6/exec006/server.log";
		LogAnalyzer.main(new String[] {file});
		Executable searchOutputFile = () -> Files.readString(Path.of("resources/summary.txt"));
		assertAll(
			() -> assertEquals("Log file not found." + System.lineSeparator(), 
					outputStream.toString()),
			() -> assertThrows(NoSuchFileException.class, searchOutputFile)
		);

	}
	
	/**
	 * server.log file cannot be read correctly.
	 * 
	 * Checks whether print output is correct.
	 * Checks whether there is no file made.
	 */
	@Test
	void exec007() throws IOException, NoSuchFileException {
	    String path = "training/src/test/resources/Day6/exec007/server.log";
	    FileChannel.open(Path.of(path), StandardOpenOption.WRITE).lock();
	    LogAnalyzer.main(new String[] { path });
	    Executable searchOutputFile = () -> Files.readString(Path.of("resources/summary.txt"));
	    assertAll(
	    	() -> assertEquals("Error reading file." + System.lineSeparator(), 
	                 outputStream.toString()),
	    	() -> assertThrows(NoSuchFileException.class, searchOutputFile)
	    );
	}
	
	/**
	 * summary.txt file cannot be written correctly.
	 * 
	 * Checks whether print output is correct.
	 */
	@Test
	void exec008() throws IOException {
	    String readPath = "training/src/test/resources/Day6/exec008/server.log";
	    String writePath = "resources/summary.txt";
	    Files.createFile(Path.of(writePath));
	    FileChannel.open(Path.of(writePath), StandardOpenOption.WRITE).lock();
	    LogAnalyzer.main(new String[] {readPath});
	    assertTrue(outputStream.toString().contains("Error writing summary file."));
	}
	
	/**
	 * LogAnalyzer constructor must be public.
	 * 
	 * Checks whether LogAnalyzer can be called outside of its directory.
	 */
	@Test
	void exec009() {
		LogAnalyzer testAnalyzer = new LogAnalyzer();
		assertNotNull(testAnalyzer);
	}
}
