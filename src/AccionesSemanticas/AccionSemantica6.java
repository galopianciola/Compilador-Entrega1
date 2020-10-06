package AccionesSemanticas;

import Errores.Error9;
import main.*;

public class AccionSemantica6 extends AccionSemantica {
    //CONSTANTES DOUBLE

    @Override
    public Token run() {
        Lexico.cursor--;

        Double doble = Double.parseDouble( buffer.replace('d','e'));

        if ((doble > 2.2250738585272014e-308 && doble < 1.7976931348623157e+308) || (doble > -1.7976931348623157e+308 && doble < -2.2250738585072014e-308) || (doble == 0.0)) {
            Main.tSimbolos.agregarSimbolo(buffer, Lexico.CTE_DOUBLE);
            System.out.println("[Lexico | Linea " + Lexico.linea + "] Se detecto un token de constante double -> " + buffer);
            return new Token(Lexico.CTE_DOUBLE, buffer);
        }

        return new Error9().run(); // fuera de rango
    }
}