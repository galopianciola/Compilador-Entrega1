package Errores;

import main.*;

public class Error6 extends AccionSemantica {

    @Override
    public Token run() {
        System.out.println("Error de compilación en la linea " + Lexico.linea + ": se esperaba '+' ó '-' y llegó el cáracter " + Lexico.caracter);
    }
}