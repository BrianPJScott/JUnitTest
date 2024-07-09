package ie.atu.sw;

public class InsuranceCalc {

	final int BASIC_CHARGE = 500;
	final int AGE_CHARGE = 100;

	int Run(int newAge, int newAccidents) { // Allowing the passing of age and accident parameters makes this method
											// testable by our test suite

		int total = calcCost(newAge, newAccidents);

		return total;						// Although total is not strictly needed by the calling class, it is useful to pass back for the sake of testing 

	}

	int calcCost(int age, int accidents) {

		int total = 0;
		int ageCharge = getAgeSurcharge(age);					// get extra cost due to age
		int accidentCharge = getAccidentSurcharge(accidents);	// get extra costs due to number of accidents

		if (accidentCharge < 0) {								// if the result of the accident surcharge check was -1, the user is uninsurable
			System.out.println("No Insurance!");
			return -1;
		} else {												//Otherwise, return the total cost of insurance
			total = ageCharge + accidentCharge + BASIC_CHARGE;
			System.out.println("Total amount to pay: " + total);
			return total;
		}

	}

	int getAgeSurcharge(int myAge) throws IllegalArgumentException {
		int result = 0;
	
		if (myAge < 0) {										// negative integers are not accepted
			throw new IllegalArgumentException("Only positive arguments accepted");
		} else {
			if (myAge >= 25) {									// no charge for >= 25 yo
				System.out.println("No age surcharge");
				result = 0;
			} else {
				result = AGE_CHARGE;							// +100 for under 25 yo
				System.out.println("Additional surcharge for age: " + result);
			}
		}

		return (result);
	}

	int getAccidentSurcharge(int myAccidents) throws IllegalArgumentException {

		int result = 0;

		if (myAccidents < 0) {									// negative integers are not accepted
			throw new IllegalArgumentException("Only positive arguments accepted");
		} else {

			switch (myAccidents) { // Do not need to handle negative input as we know the input is not negative
			case 0:
				System.out.println("No surcharge for accident(s)");
				result = 0;										// no accidents.. no charge
				break;
			case 1:
				result = 50;									// following charges fall in line with that defined in provided program 
				break;											// in future iterations, would be better to create an enumeration to hold the values
			case 2:
				result = 125;
				break;
			case 3:
				result = 225;
				break;
			case 4:
				result = 375;
				break;
			case 5:
				result = 575;
				break;
			default:
				result = -1;
			}

			if (result > 0) {
				System.out.println("Additional surcharge for accident(s): " + result);
			}
		}
		return (result);
	}

}