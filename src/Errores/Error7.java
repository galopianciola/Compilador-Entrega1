package Errores;

import main.*;

public class Error7 extends AccionSemantica {

    @Override
    public Token run() {
        System.out.println("Error de compilación en la linea " + Lexico.linea + ": se esperaba una '=' y llegó el cáracter " + Lexico.caracter);
    }
}