package AccionesSemanticas;
import main.*;

public class AccionSemantica4 extends AccionSemantica{

    @Override
    public void run() {
        Main.cursor--;

        if (buffer.length() > 20){
            buffer.substring(0,19);
            //todo:largar warning indicando la linea
        } else {
            /*Buscar en tabla de Símbolos.
                    Si está,
                        devolver el identificador.
                    Si no,
                        Alta en la TS
                        Devolver ID + Puntero TS
            */

        }
    }
}
