package Errores;

import main.*;

public class Error2 extends AccionSemantica {

    @Override
    public Token run() {
        System.out.println("Error lexico en la linea " + Lexico.linea + ": se esperaba un dígito, '_' ó '.' y llegó otro carácter");
        return null;
    }
}