package bavaria.hightech.exceptions;

@SuppressWarnings("serial")
public class NotEnoughMoneyException extends Exception {
	
	public NotEnoughMoneyException() {
		super();
	}
	
	public NotEnoughMoneyException(String s) {
		super(s);
	}
	
}