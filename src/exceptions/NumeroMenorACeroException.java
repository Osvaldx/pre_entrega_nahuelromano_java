package exceptions;

public class NumeroMenorACeroException extends RuntimeException {

    public NumeroMenorACeroException() {
        super("ingrese un valor MAYOR a 0!");
    }

}
