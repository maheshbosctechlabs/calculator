import java.util.HashMap;
import java.util.Map;

public class OperatorFactory {
	private static final Map<String, Operator> operatorMapping = new HashMap<>();

	static {
		operatorMapping.put("+", new AdditionOperator());
		operatorMapping.put("*", new MultiplicationOperator());
		operatorMapping.put("-", new SubtractionOperator());
		operatorMapping.put("/", new DivisionOperator());
		
		operatorMapping.put("(", new OpenParenthesis());
        operatorMapping.put(")", new CloseParenthesis());
	}

	public Operator getOperator(String symbol) {
		if (!operatorMapping.containsKey(symbol)) {
			return null; // TODO: Throw Exception here
		}
		return operatorMapping.get(symbol);
	}
}
