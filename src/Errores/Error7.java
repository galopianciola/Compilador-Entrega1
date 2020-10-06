package Errores;

import main.*;

public class Error7 extends AccionSemantica {

    @Override
    public Token run() {
        System.out.println("Error léxico: Linea " + Lexico.linea + " se esperaba un '=' después del '!' y llegó otro caracter");
        return null;
    }
}