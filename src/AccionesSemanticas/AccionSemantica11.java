package AccionesSemanticas;

import main.*;

public class AccionSemantica11 extends AccionSemantica {

    @Override
    public Token run() {
        System.out.println("[Lexico | Linea " + Lexico.linea + "] Se detectó el token '>='");
        return new Token(Lexico.MAYOR_IGUAL);
    }
}