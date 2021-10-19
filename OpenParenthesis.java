
public class OpenParenthesis implements GroupingOperator {

	@Override
	public Integer precedence() {
		return 10;
	}

}
