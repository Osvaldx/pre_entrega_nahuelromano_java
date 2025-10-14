package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

import exceptions.NumeroMenorACeroException;

public abstract class InputHelper {

    private static Scanner input = new Scanner(System.in);

    public static int leerInt(String mensaje) {
        boolean numeroInvalido = true;
        int numero = 0;

        do {
            try {
                System.out.print(mensaje);
                numero = input.nextInt();

                if(numero <= 0) {
                    throw new InputMismatchException("[!] Ingrese un valor mayor a 0");
                }
                
                numeroInvalido = false;
            } catch(InputMismatchException ex) {
                System.out.println((ex.getMessage() != null) ? ex.getMessage() : "[!] Ingrese un valor valido");
                input.nextLine();
            }
        } while(numeroInvalido);
        
        input.nextLine();
        return numero;
    }

    public static String leerString(String mensaje) {
        String texto = "";
        boolean textoInvalido = true;

        do {
            System.out.print(mensaje);
            texto = input.nextLine();

            if(texto.trim().isEmpty()) {
                System.out.println("[!] El campo no puede estar vacio");
            } else {
                textoInvalido = false;
            }

        } while(textoInvalido);

        return texto;
    }

    public static Double leerDouble(String mensaje) {
        Double numeroDouble = 0.0;
        boolean numeroInvalido = true;
        
        do {
            try {
                System.out.print(mensaje);
                numeroDouble = input.nextDouble();

                if(numeroDouble <= 0) {
                    throw new NumeroMenorACeroException();
                }

                numeroInvalido = false;
            } catch(InputMismatchException ex) {
                System.out.println((ex.getMessage() != null) ? ex.getMessage() : "[!] Ingrese un número valido");
                input.nextLine();
            }
        } while(numeroInvalido);
        
        input.nextLine();
        return numeroDouble;
    }

}
