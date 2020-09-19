package Errores;
import main.*;

public class Error1 extends AccionSemantica{

    @Override
    public void run() {
        System.out.println("Error de compilación en la linea "+linea+": el carácter "+Main.caracter+" no es válido");
    }
}