package AccionesSemanticas;

import Errores.Error9;
import main.*;

public class AccionSemantica6 extends AccionSemantica {
    @Override
    public Token run() {
        Lexico.cursor--;

        Double doble = Double.parseDouble( buffer.replace('d','e'));

        if ((doble > 2.2250738585272014e-308 && doble < 1.7976931348623157e+308) || (doble > -1.7976931348623157e+308 && doble < -2.2250738585072014e-308) || (doble == 0.0)) {
            Main.tSimbolos.agregarSimbolo(buffer, Lexico.DOUBLE);
            return new Token(Lexico.DOUBLE, buffer);
        }
        Token e9 = new Error9().run();
        return null;
            /*Alta en la TS
              Devolver CTE + Punt TS.
            */
    }
}