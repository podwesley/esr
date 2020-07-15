package com.algaworks.algafood.exception;

public class AlgaFoodResultadoVazioException extends RuntimeException {

    private static final long serialVersionUID = 129737869999338069L;

    public AlgaFoodResultadoVazioException(String message) {
        super(message);
    }

    public AlgaFoodResultadoVazioException(String message, Throwable cause) {
        super(message, cause);
    }
}
