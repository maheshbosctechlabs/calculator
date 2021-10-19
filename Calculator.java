
public class Calculator {
	public static void main(String[] args) {
		
		String input="10 * -6 + (10 + 10)";
		InfixToPostfixConverter converter = new InfixToPostfixConverter(new StringFormatter());
		ExpressionEvalutor notation = new PostfixExpressionEvalutor();
		
		
		System.out.print(notation.evalute(converter.infixToPostfix(input)));
		
	}
}
