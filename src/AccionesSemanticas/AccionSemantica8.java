package AccionesSemanticas;

import main.*;

public class AccionSemantica8 extends AccionSemantica {

    @Override
    public Token run() {
        Lexico.cursor--;
        System.out.println("Error lexico. Se descarto un '%' en la linea " + Lexico.linea);
        return null;
    }
}