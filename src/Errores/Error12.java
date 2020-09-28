package Errores;

import main.AccionSemantica;
import main.Lexico;
import main.Token;

public class Error12 extends AccionSemantica {

    @Override
    public Token run() {
        System.out.println("Error lexico en la linea " + Lexico.linea + ". La cadena no se cerro correctamente.");
        return null;
    }
}
