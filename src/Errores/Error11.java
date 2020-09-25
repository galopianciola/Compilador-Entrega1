package Errores;

import main.*;

public class Error11 extends AccionSemantica{

    @Override
    public Token run() {
        System.out.println("Error lexico en la linea "+ Lexico.linea +", se esperaba un salto de linea al terminar el comentario");
        return null;
    }
}