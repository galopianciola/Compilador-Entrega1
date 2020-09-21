package AccionesSemanticas;

import main.*;

public class AccionSemantica5 extends AccionSemantica {
    @Override
    public Token run() {
        int nro = Integer.parseInt(buffer);

        if ((nro > 0) && (nro < (2 ^ 16 - 1))) {
            /*Alta en la TS
              Devolver CTE + Punt TS.
            */
        }

    }
}
