
public class MultiplicationOperator implements ArithmeticOperator {

	@Override
	public double calculate(double firstNumber, double secondNumber) {
		return firstNumber * secondNumber;
	}

	@Override
	public Integer precedence() {
		return 2;
	}
}
