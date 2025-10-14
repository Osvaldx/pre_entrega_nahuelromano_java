package exceptions;

public class StockInsuficienteException extends RuntimeException {

    public StockInsuficienteException() {
        super("Stock insuficiente!");
    }
}
