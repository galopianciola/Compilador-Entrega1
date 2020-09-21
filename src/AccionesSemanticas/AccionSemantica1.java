package AccionesSemanticas;
import main.*;

public class AccionSemantica1 extends AccionSemantica {

    @Override
    public Token run() {
        buffer = Character.toString(Lexico.caracter);
        return null;
    }
}
