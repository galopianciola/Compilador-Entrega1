package Errores;

import main.*;

public class Error10 extends AccionSemantica{

    @Override
    public Token run() {
        System.out.println("Error lexico en la linea "+ Lexico.linea +", el lenguaje no reconoce la palabra reservada"+ buffer);
        return null;
    }
}