package AccionesSemanticas;

import main.*;

public class AccionSemantica6 extends AccionSemantica {
    @Override
    public Token run() {
        Lexico.cursor--;
        double doble = Double.parseDouble(buffer);

        if ((doble > 2.2250738585272014d - 308 && doble < 1.7976931348623157d + 308) || (doble > -1.7976931348623157d + 308 && doble < -2.2250738585072014d - 308) || (doble == 0.0)) {
            //Alta en la TS
            //Devolver CTE + Punt TS
        }

    }
}