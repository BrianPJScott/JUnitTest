package ie.atu.sw;

import java.util.Scanner;

public class InsuranceProgram {

	// This program has been re-written with testing primarily in mind.
	// Many of the design choices were made to help demonstrate the requirements in the testing exercise.
	
	// Program entry point
	public static void main(String[] args) {

		InsuranceCalc myCalc = new InsuranceCalc();
		Scanner myScanner = getScanner();
		
		int age = getAge(myScanner);
		int accidents = getAccidents(myScanner);
		
		myCalc.Run(age, accidents);

		myScanner.close();
		
	}
	
	// getScanner method created to be able to test for issues with Scanner creation
	static Scanner getScanner() {
		Scanner newScanner;

		newScanner = new Scanner(System.in);

		return newScanner;
	}

	// getAge method will throw exception in the event it is passed a null argument
	static int getAge(Scanner myScanner) throws NullPointerException {

		int result = -1;

		if (myScanner != null) {					// Testing the following block code only adds value if testing the Scanner
													// utility for I/O which is not in scope for this project
			while (result < 0) { // Ensures result is not negative
				System.out.print("Enter your age: ");
				result = myScanner.nextInt();

			}
		} else {
			throw new NullPointerException();
		}

		return (result);

	}

	// getAccidents method will throw exception in the event it is passed a null argument
	static int getAccidents(Scanner myScanner) throws NullPointerException {

		int result = -1;

		if (myScanner != null) {					// Testing the following block code only adds value if testing the Scanner
													// utility for I/O which is not in scope for this project
			while (result < 0) { // Ensures result is not negative
				System.out.print("How many accidents did you have? ");
				result = myScanner.nextInt();
			}
		} else {
			throw new NullPointerException();
		}
		return (result);

	}
}