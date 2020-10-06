package Errores;

import main.*;

public class Error5 extends AccionSemantica {

    @Override
    public Token run() {
        System.out.println("Error léxico: Linea " + Lexico.linea + " se esperaba un dígito y llegó el carácter " + Lexico.caracter);
        return null;
    }
}