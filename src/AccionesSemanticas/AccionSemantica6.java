package AccionesSemanticas;

import Errores.Error9;
import main.*;

public class AccionSemantica6 extends AccionSemantica {
    @Override
    public Token run() {
        Lexico.cursor--;
        double doble = Double.parseDouble(buffer);

        if ((doble > 2.2250738585272014d - 308 && doble < 1.7976931348623157d + 308) || (doble > -1.7976931348623157d + 308 && doble < -2.2250738585072014d - 308) || (doble == 0.0)) {
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