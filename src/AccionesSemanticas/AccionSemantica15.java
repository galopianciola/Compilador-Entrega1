package AccionesSemanticas;

import main.*;

public class AccionSemantica15 extends AccionSemantica {

    @Override
    public Token run() {
        System.out.println("[Lexico | Linea " + Lexico.linea + "] Se detectó el token '!='");
        return new Token(Lexico.DISTINTO);
    }


}

