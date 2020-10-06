package AccionesSemanticas;

import Errores.Error9;
import main.*;

public class AccionSemantica5 extends AccionSemantica {
    //CONSTANTES UINT

    @Override
    public Token run() {
        Lexico.caracter = Lexico.codigoFuente.charAt(Lexico.cursor);
        Integer nro = Integer.parseInt(AccionSemantica.buffer);

        if ((nro >= 0) && (nro <= (Math.pow(2,16) - 1))) {
            Main.tSimbolos.agregarSimbolo(buffer, Lexico.CTE_UINT);
            System.out.println("[Lexico | Linea " + Lexico.linea + "] Se detecto un token de constante UINT -> " + buffer);
            return new Token(Lexico.CTE_UINT, buffer);
        }
        return new Error9().run(); // fuera de rango
        }

    }

