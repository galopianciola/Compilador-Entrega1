package AccionesSemanticas;
import main.*;


public class AccionSemantica2 extends AccionSemantica {

    @Override
    public Token run() {
        buffer = buffer + Lexico.caracter;
        return null;
    }
}
