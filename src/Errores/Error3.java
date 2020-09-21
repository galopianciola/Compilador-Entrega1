package Errores;

import main.*;

public class Error3 extends AccionSemantica {

    @Override
    public Token run() {
        System.out.println("Error de compilación en la linea " + Lexico.linea + ": se esperaba una 'u' y llegó el carácter " + Lexico.caracter);
    }
}