package com.algaworks.algafood.exception;

public class AlgaFoodRestricaoException extends RuntimeException {

    private static final long serialVersionUID = -4516484045771691072L;

    public AlgaFoodRestricaoException(String message) {
        super(message);
    }

    public AlgaFoodRestricaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
