package AccionesSemanticas;

import Errores.Error10;
import main.*;

public class AccionSemantica3 extends AccionSemantica {
    //PALABRAS RESERVADAS

    @Override
    public Token run() {
        Lexico.cursor--; //vuelvo atras en el codigo fuente para devolver el ultimo caracter leido

        if (Lexico.palabrasReservadas.contains(buffer)) { //Buscar en tabla de Palabras Reservadas
            System.out.println("[Lexico | Linea " + Lexico.linea + "] Se detecto un token de palabra reservada -> " + buffer);
            return new Token(this.getID(buffer)); //devolver el id (onda nro del token) de la palabra reservada.
        }

        return new Error10().run(); //si no se retorno el token antes, entonces es un error pq no es una palabra reservada
    }

    public int getID(String palabra) {
        switch (palabra) {
            case "IF":
                return Lexico.IF;
            case "THEN":
                return Lexico.THEN;
            case "ELSE":
                return Lexico.ELSE;
            case "END_IF":
                return Lexico.END_IF;
            case "OUT":
                return Lexico.OUT;
            case "UINT":
                return Lexico.UINT;
            case "DOUBLE":
                return Lexico.DOUBLE;
            case "NI":
                return Lexico.NI;
            case "REF":
                return Lexico.REF;
            case "FOR":
                return Lexico.FOR;
            case "UP":
                return Lexico.UP;
            case "DOWN":
                return Lexico.DOWN;
            case "PROC":
                return Lexico.PROC;
        }
        return 0;
    }
}
