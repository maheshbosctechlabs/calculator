import java.text.DecimalFormat;
import java.util.*;

public class PostfixExpressionEvalutor implements ExpressionEvalutor {

	private OperatorFactory operatorFactory = new OperatorFactory();

	private static String INVALID_EXPRESSION = "You didn't enter a valid expression";

	@Override
	public String evalute(Queue<String> postfixExp) {

		Stack<String> evaluation = new Stack<String>();
		String currentToken;

		String operandA, operandB;
		double opA, opB;
		String result;

		DecimalFormat decimalFormat = new DecimalFormat("0.00");

		while (postfixExp.isEmpty() == false) {

			currentToken = postfixExp.remove();

			// if the token is an operand, push it onto the stack
			if (operatorFactory.getOperator(currentToken) == null) {
				evaluation.push(currentToken);
			} else {

				operandA = operandB = result = "";
				opA = opB = 0;

				try {
					operandA = evaluation.pop();
					operandB = evaluation.pop();
				} catch (EmptyStackException e) {
					return INVALID_EXPRESSION;
				}

				try {
					opA = Double.parseDouble(operandA);
					opB = Double.parseDouble(operandB);
				} catch (NumberFormatException e) {
					return INVALID_EXPRESSION;
				}

				ArithmeticOperator arithmeticOperator = (ArithmeticOperator) operatorFactory.getOperator(currentToken);

				result = Double.toString(arithmeticOperator.calculate(opB, opA));

				evaluation.push(result);
			}
		}

		// the final result is the last number of the stack
		String finalResult = evaluation.pop();

		try {
			Double finalResultDouble = Double.parseDouble(finalResult);
			finalResult = decimalFormat.format(finalResultDouble);
		} catch (NumberFormatException e) {
			System.out.println("Number Format Exception: " + e.getMessage());
			return INVALID_EXPRESSION;
		}

		// if the stack is now empty, the processing was successful
		if (evaluation.isEmpty() == true) {
			return finalResult;
		} else {
			return INVALID_EXPRESSION;
		}

	}
}