package Errores;

import main.*;

public class Error9 extends AccionSemantica {

    @Override
    public Token run() {
        System.out.println("Warning en la linea " + Lexico.linea + ": el identificador excedió el tamaño máximo, el tamaño se truncó a 20 caracteres");
    }
}