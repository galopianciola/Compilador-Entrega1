package AccionesSemanticas;

import Errores.Error9;
import main.*;

public class AccionSemantica5 extends AccionSemantica {
    @Override
    public Token run() {
        int nro = Integer.parseInt(buffer);

        if ((nro > 0) && (nro < (Math.pow(2,16) - 1))) {
            Main.tSimbolos.agregarSimbolo(buffer, Lexico.IDE);
            return new Token(Lexico.IDE, buffer);
        }
        Token e9 = new Error9().run();
        return null;
            /*Alta en la TS
              Devolver CTE + Punt TS.
            */
        }

    }

