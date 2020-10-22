package kg.beeline.bankaccountoauth2.exception;

import lombok.Getter;

@Getter
public class BankAccountException extends Exception {

    private String message;

    public BankAccountException(String message) {
        this.message = message;
    }
}
