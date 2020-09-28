package AccionesSemanticas;

import Errores.Error9;
import main.*;

public class AccionSemantica5 extends AccionSemantica {
    @Override
    public Token run() {
        Lexico.caracter = Lexico.codigoFuente.charAt(Lexico.cursor);
        Integer nro = Integer.parseInt(AccionSemantica.buffer);
        //a
        if ((nro >= 0) && (nro <= (Math.pow(2,16) - 1))) {
            Main.tSimbolos.agregarSimbolo(buffer, Lexico.IDE);
            return new Token(Lexico.CTE, buffer);
        }
        Token e9 = new Error9().run();
        return null;
            /*Alta en la TS
              Devolver CTE + Punt TS.
            */
        }

    }

