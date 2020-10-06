package Errores;

import main.AccionSemantica;
import main.Lexico;
import main.Token;

public class Error12 extends AccionSemantica {

    @Override
    public Token run() {
        System.out.println("Error léxico: Linea " + Lexico.linea + " la cadena no se cerro correctamente.");
        return null;
    }
}
