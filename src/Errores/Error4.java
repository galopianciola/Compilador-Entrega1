package Errores;

import main.*;

public class Error4 extends AccionSemantica {

    @Override
    public Token run() {
        Lexico.cursor--;
        System.out.println("Error lexico en la linea " + Lexico.linea + ": se esperaba una 'i' y llegó el carácter " + Lexico.caracter);
        return null;
    }
}