package Errores;

import main.*;

public class Error2 extends AccionSemantica {

    @Override
    public Token run() {
        System.out.println("Error de compilación en la linea " + Lexico.linea + ": se esperaba un dígito, '_' ó punto y llegó el carácter " + Lexico.caracter);
    }
}