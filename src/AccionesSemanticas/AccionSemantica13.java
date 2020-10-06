package AccionesSemanticas;

import main.*;

public class AccionSemantica13 extends AccionSemantica {

    @Override
    public Token run() {
        System.out.println("[Lexico | Linea " + Lexico.linea + "] Se detectó el token '=='");
        return new Token(Lexico.IGUAL_IGUAL);
    }
}