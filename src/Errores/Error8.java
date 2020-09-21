package Errores;

import main.*;

public class Error8 extends AccionSemantica {

    @Override
    public Token run() {
        System.out.println("Error de compilación en la linea " + Lexico.linea + ": no esta permitido un salto de línea antes de un '-' " + Lexico.caracter);
    }
}