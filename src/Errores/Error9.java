package Errores;

import main.*;

public class Error9 extends AccionSemantica {
    @Override
    public Token run() {
        System.out.println("Error lexico en la linea " + Lexico.linea + ", la constante se encuentra fuera de rango ");
        return null;
    }
}
