package kg.beeline.bankaccountoauth2.endpoint.config;

import kg.beeline.bankaccountoauth2.dto.error.NotFoundErrorResponseDto;
import kg.beeline.bankaccountoauth2.dto.error.OperationNotValidExceptionDto;
import kg.beeline.bankaccountoauth2.dto.error.UnauthorizedErrorResponseDto;
import kg.beeline.bankaccountoauth2.exception.NotFoundException;
import kg.beeline.bankaccountoauth2.exception.OperationNotValidException;
import kg.beeline.bankaccountoauth2.exception.UnAuthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {
    private static final String RESOURCE_NOT_FOUND = "Resource not found";
    private static final String OPERATION_NOT_VALID = "Operation not valid";
    private static final String UNAUTHORIZED = "Unauthorized";

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    NotFoundErrorResponseDto handleNotFoundErrorResponseDto(NotFoundException exception) {
        String message = getMessage(exception, RESOURCE_NOT_FOUND);
        return new NotFoundErrorResponseDto(message);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(OperationNotValidException.class)
    OperationNotValidExceptionDto handleOperationNotValidException(OperationNotValidException exception) {
        String message = getMessage(exception, OPERATION_NOT_VALID);
        return new OperationNotValidExceptionDto(message);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnAuthorizedException.class)
    UnauthorizedErrorResponseDto handleUnauthorizedExceptionException(UnAuthorizedException exception) {
        String message = getMessage(exception, UNAUTHORIZED);

        return new UnauthorizedErrorResponseDto(message);
    }

    private String getMessage(Exception exception, String defaultMessage) {
        String message = defaultMessage;

        if (Objects.nonNull(exception.getLocalizedMessage())) {
            message = exception.getLocalizedMessage();
        }
        if (Objects.nonNull(exception.getMessage())) {
            message = exception.getMessage();
        }

        return message;
    }
}
