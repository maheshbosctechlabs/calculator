import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class InfixToPostfixConverter {

	private OperatorFactory operatorFactory = new OperatorFactory();

	private Formatter formatter;
	
	
	public InfixToPostfixConverter(Formatter formatter) {
		this.formatter = formatter;
	}

	public Queue<String> infixToPostfix(String currentLine) {

		// Format the string
		currentLine = formatter.format(currentLine);

		Queue<String> outputQueue = new LinkedList<String>();
		Stack<String> pendingOperators = new Stack<String>();

		String[] infixExpression = currentLine.split(" ");

		for (int i = 0; i < infixExpression.length; i++) {

			if (operatorFactory.getOperator(infixExpression[i]) == null) {
				outputQueue.add(infixExpression[i]);
			} else {

				// if the operand is a close parenthesis, keep popping and enqueue-ing
				// characters from the stack
				// until you reach the end of the stack or an open parenthesis
				if (infixExpression[i].equals(")")) {
					while (!pendingOperators.isEmpty() && pendingOperators.peek().equals("(") == false) {

						outputQueue.add(pendingOperators.pop());
					}
					// to get rid of the open parenthesis
					pendingOperators.pop();
				} else {
					// if the character is an operand but is not a close parenthesis
					// keep popping off the stack until you reach an operator of lower precedence
					while (!pendingOperators.isEmpty() && pendingOperators.peek().equals("(") == false) {

						if (precedence(pendingOperators.peek()) >= precedence(infixExpression[i])) {
							outputQueue.add(pendingOperators.pop());
						} else {
							break;
						}
					}

					pendingOperators.add(infixExpression[i]);
				}
			}
		}

		// if the stack still contains characters, enqueue them onto the output queue
		while (pendingOperators.isEmpty() == false) {
			outputQueue.add(pendingOperators.pop());
		}

		return outputQueue;
	}

	// method that determines the precedence of operators
	private int precedence(String operator) {

		Operator op = operatorFactory.getOperator(operator);

		if (op != null) {
			return op.precedence();
		}

		return -1;

	}

}