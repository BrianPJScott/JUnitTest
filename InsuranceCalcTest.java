package ie.atu.sw;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.concurrent.TimeUnit;

class InsuranceCalcTest {

	public static InsuranceCalc testCalc;

	// Although we are requested to include the following annotations, the tests
	// included in this suite did not require three of them.
	// Java garbage collection should clean up any created data structures
	// automatically, and better than I could.

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		testCalc = new InsuranceCalc();
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

	// Test to evaluate the result of the Run() method
	// Should return either the total cost of the insurance where insurable, and -1
	// where not insurable.
	// Takes a list of pair values which translate to Age and Number of Accidents in
	// CSV format.
	@ParameterizedTest
	@CsvSource({ "0, 100", "100, 0", "1, 1", "24, 0", "24, 8", "24, 5", "25, 100", "25, 1", "25, 0", "25, 10", "26, 2",
			"26, 0", "26, 5", "26, 10" })
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	void runNotNegativeTest(int age, int accidents) {

		if (accidents < 0 || accidents > 5) {
			assertTrue(testCalc.Run(age, accidents) == -1);
		} else {
			assertTrue(testCalc.Run(age, accidents) >= 0);
		}

	}

	// Test to evaluate the result of the getAgeSurcharge() method
	// Should return 100 if age is under 25 and 0 otherwise.
	@ParameterizedTest
	@ValueSource(ints = { 0, 1, 5, 20, 25, 26, 27, 50, 100 })
	void getAgeSurchargeTest(int age) {
		int result = testCalc.getAgeSurcharge(age);
		if (age >= 25) {
			assertEquals(result, 0);
		} else {
			assertEquals(result, 100);
		}

		// test list of results
	}

	// Test to evaluate the result of the getAccidentSurcharge() method
	// Should return the matching surcharge for the number of accidents as outlined
	// in the provided program.
	// Will return -1 if number of accidents is non-insurable
	@ParameterizedTest
	@ValueSource(ints = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 50, 100, 1000 })
	void getAccidentSurchargeTest(int accidents) {
		int result = testCalc.getAccidentSurcharge(accidents);
		// test list of results

		switch (accidents) {
		case 0:
			assertEquals(result, 0);
			break;
		case 1:
			assertEquals(result, 50);
			break;
		case 2:
			assertEquals(result, 125);
			break;
		case 3:
			assertEquals(result, 225);
			break;
		case 4:
			assertEquals(result, 375);
			break;
		case 5:
			assertEquals(result, 575);
			break;
		default:
			assertEquals(result, -1);
		}
	}

	// Test to ensure that an illegalArgumentException is thrown by the
	// getAgeSurcharge() method if it is passed a negative argument
	@ParameterizedTest
	@ValueSource(ints = { -1000, -999, -500, -100, -50, -5, -1 })
	void ageNegativeExceptionTest(int ints) {

		assertThrows(IllegalArgumentException.class, () -> {
			testCalc.getAgeSurcharge(ints);
		});
	}

	// Test to ensure that an illegalArgumentException is thrown by the
	// getAccidentSurcharge() method if it is passed a negative argument
	@ParameterizedTest
	@ValueSource(ints = { -1000, -999, -500, -100, -50, -5, -1 })
	void accidentNegativeExceptionTest(int ints) {

		assertThrows(IllegalArgumentException.class, () -> {
			testCalc.getAccidentSurcharge(ints);
		});
	}

	// Test to ensure processing of the getAgeSurcharge() method does not take a
	// long time for multiple inputs
	@ParameterizedTest
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	@ValueSource(ints = { 0, 1, 5, 20, 25, 26, 27, 50, 100 })
	void ageCalcTimingTest(int ints) {

		testCalc.getAgeSurcharge(ints);

	}

	// Test to ensure processing of the getAccidentSurcharge() method does not take
	// a long time for multiple inputs
	@ParameterizedTest
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	@ValueSource(ints = { 0, 1, 5, 20, 25, 26, 27, 50, 100 })
	void accidentCalcTimingTest(int ints) {

		testCalc.getAccidentSurcharge(ints);

	}

	// Test to ensure processing of the calcCost() method does not take a long time
	// for multiple inputs
	@ParameterizedTest
	@CsvSource({ "0, 100", "100, 0", "1, 1", "24, 0", "24, 8", "24, 5", "25, 100", "25, 1", "25, 0", "25, 10", "26, 2",
			"26, 0", "26, 5", "26, 10" })
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	void calcCostTest(int age, int accidents) {

		testCalc.calcCost(age, accidents);

	}

}
