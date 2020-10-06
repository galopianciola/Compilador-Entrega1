package AccionesSemanticas;

import main.*;

public class AccionSemantica12 extends AccionSemantica {

    @Override
    public Token run() {
        Lexico.cursor--;
        System.out.println("[Lexico | Linea " + Lexico.linea + "] Se detectó el token '>'");
        return new Token('>');
    }
}