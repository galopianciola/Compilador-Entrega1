package Errores;
import main.*;

public class Error4 extends AccionSemantica{

    @Override
    public void run() {
        System.out.println("Error de compilación en la linea "+linea+": se esperaba una 'i' y llegó el carácter "+caracter);
    }
}