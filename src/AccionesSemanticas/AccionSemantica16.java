package AccionesSemanticas;

import main.*;

public class AccionSemantica16 extends AccionSemantica {
    @Override
    public Token run() {
        Lexico.cursor++;
        buffer = buffer + Lexico.caracter;
        buffer = buffer.replace("-", "");
        buffer = buffer.replace("\n", "");
        Main.tSimbolos.agregarSimbolo(buffer, Lexico.CADENA);
        return new Token(Lexico.CADENA, buffer);
        //Alta en la TS
        //Devolver ID + Puntero TS
    }


}