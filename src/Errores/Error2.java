package Errores;

import main.*;

public class Error2 extends AccionSemantica {

    @Override
    public Token run() {
        Lexico.cursor--;
        System.out.println("Error lexico en la linea " + Lexico.linea + ": se esperaba un dígito, '_' ó punto y llegó el carácter " + Lexico.caracter);
        return null;
    }
}