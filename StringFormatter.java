
public class StringFormatter implements Formatter {

	// used to check if the character is an operator or not
	private String operators = "()/*%+-";

	private boolean isOperator(char ch) {
		return operators.indexOf(ch) != -1;
	}

	@Override
	public String format(String input) {

		input = input.replaceAll(" ", "");

		StringBuilder result = new StringBuilder();

		for (int i = 0; i < input.length(); i++) {

			// if the character is a minus sign that is not at start of the line
			if (operators.indexOf(input.charAt(i)) == 6 && i != 0) {
				// if there is no operand before or after the minus sign, it is truly a minus
				// sign and spaces are appended before and after it
				if (!isOperator(input.charAt(i - 1)) && !isOperator(input.charAt(i + 1))) {
					result.append(" ");
					result.append(input.charAt(i));
					result.append(" ");
				} else {
					// if the previous character is a number
					if (!isOperator(input.charAt(i - 1))) {
						result.append(" ");
						result.append(input.charAt(i));
					} else {
						// if the next character is a number it is a negative sign
						if (!isOperator(input.charAt(i + 1))) {
							result.append(" ");
							result.append(input.charAt(i));
						} else {

							if (isOperator(input.charAt(i - 1)) && isOperator(input.charAt(i + 1))) {
								result.append(" ");
								result.append(input.charAt(i));
								result.append(" ");
							}
						}
					}
				}
			} else {
				// if the minus sign is at the beginning of the line (in which case it is a
				// negative sign)
				if (operators.indexOf(input.charAt(i)) == 6 && i == 0) {
					result.append(input.charAt(i));
				} else {
					// if the character is an operator that is not at the beginning of the line
					if (isOperator(input.charAt(i)) && i != 0) {
						result.append(" ");
						result.append(input.charAt(i));
						result.append(" ");
					} else {
						// if the character is an operator at the beginning of the line
						if (isOperator(input.charAt(i))) {
							result.append(input.charAt(i));
							result.append(" ");
						} else {
							// otherwise the character is an operand and no space is needed
							result.append(input.charAt(i));
						}
					}

				}
			}
		}

		input = result.toString();

		// eliminates any double spaces
		input = input.replaceAll("  ", " ");

		return input;
	}

}
