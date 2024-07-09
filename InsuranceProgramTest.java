package ie.atu.sw;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InsuranceProgramTest {

	public static InsuranceProgram testProg;

	// Although we are requested to include the following annotations, the tests
	// included in this suite did not require three of them.
	// Java garbage collection should clean up any created data structures
	// automatically, and better than I could.
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		testProg = new InsuranceProgram();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	// Test that the getScanner method returns a nun-null result
	@Test
	void getScannerTest() {
		assertNotNull(InsuranceProgram.getScanner());
	}

	// Test that the getAge method throws the correct exception when no scanner is passed to it
	@Test
	void getAgeNoScannerExceptionTest() {

		assertThrows(NullPointerException.class, () -> {
			InsuranceProgram.getAge(null);
		});
	}

	// Test that the getAccident method throws the correct exception when no scanner is passed to it
	@Test
	void getAccidentsNoScannerExceptionTest() {

		assertThrows(NullPointerException.class, () -> {
			InsuranceProgram.getAccidents(null);
		});
	}
}
