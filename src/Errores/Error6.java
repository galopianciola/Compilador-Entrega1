package Errores;

import main.*;

public class Error6 extends AccionSemantica {

    @Override
    public Token run() {
        System.out.println("Error léxico: Linea " + Lexico.linea + " se esperaba '+' ó '-' y llegó otro caracter");
        return null;
    }
}