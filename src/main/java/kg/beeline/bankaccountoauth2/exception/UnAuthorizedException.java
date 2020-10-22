package kg.beeline.bankaccountoauth2.exception;

public class UnAuthorizedException extends BankAccountException {
    public UnAuthorizedException(String message) {
        super(message);
    }
}
