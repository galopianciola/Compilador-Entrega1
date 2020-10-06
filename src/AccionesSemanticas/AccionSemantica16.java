package AccionesSemanticas;

import main.*;

public class AccionSemantica16 extends AccionSemantica {
    //CADENA DE CARACTERES

    @Override
    public Token run() {
        buffer = buffer + Lexico.caracter;
        buffer = buffer.replace("-", "");
        buffer = buffer.replace("\n", "");
        Main.tSimbolos.agregarSimbolo(buffer, Lexico.CADENA);
        System.out.println("[Lexico | Linea " + Lexico.linea + "] Se detectó un token de cadena de caracteres");
        return new Token(Lexico.CADENA, buffer);
    }


}