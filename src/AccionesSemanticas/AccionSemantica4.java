package AccionesSemanticas;

import main.*;

public class AccionSemantica4 extends AccionSemantica {

    @Override
    public Token run() {
        Lexico.cursor--;

        if (buffer.length() > 20) {
            buffer.substring(0, 19);
            System.out.println("Warning: en la linea "+ Lexico.linea +" se truncó el identificador a 20 caracteres");
        }
        if (!Main.tSimbolos.existeLexema(buffer)) {
            Main.tSimbolos.agregarSimbolo(buffer, Lexico.IDE);
        }
        return new Token(Lexico.IDE, buffer);
    }
}
