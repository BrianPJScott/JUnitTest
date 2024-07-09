package ie.atu.sw;

import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SelectClasses;

// Class for defining the suite of tests for this project
// Contains 2 test classes, one for each class.

@Suite
@SelectClasses({
	InsuranceCalcTest.class,
	InsuranceProgramTest.class
})

class SuiteRunnerTest {

}
