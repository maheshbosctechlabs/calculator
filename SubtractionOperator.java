
public class SubtractionOperator implements ArithmeticOperator {

	@Override
	public double calculate(double firstNumber, double secondNumber) {
		return firstNumber - secondNumber;
	}
	
	@Override
	public Integer precedence() {
		return 1;
	}
}
