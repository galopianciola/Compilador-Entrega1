package Errores;
import main.*;

public class Error8 extends AccionSemantica{

    @Override
    public void run() {
        System.out.println("Error de compilación en la linea "+linea+": no esta permitido un salto de línea antes de un '-' "+caracter);
    }
}