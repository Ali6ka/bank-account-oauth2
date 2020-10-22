package kg.beeline.bankaccountoauth2.exception;

public class OperationNotValidException extends BankAccountException{
    public OperationNotValidException(String message) {
        super(message);
    }
}
