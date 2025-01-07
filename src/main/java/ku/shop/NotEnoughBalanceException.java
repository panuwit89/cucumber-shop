package ku.shop;

public class NotEnoughBalanceException extends Exception {
    public NotEnoughBalanceException() {}
    public NotEnoughBalanceException(String reason) {
        super(reason);
    }
}