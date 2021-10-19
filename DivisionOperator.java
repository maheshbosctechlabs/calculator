
public class DivisionOperator implements ArithmeticOperator {

	@Override
	public double calculate(double firstNumber, double secondNumber) {
		if (secondNumber == 0.0) {
			throw new UnsupportedOperationException("Cannot divide by zero");
		}
		return firstNumber / secondNumber;
	}

	@Override
	public Integer precedence() {
		return 2;
	}

}
