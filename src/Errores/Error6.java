package Errores;
import main.*;

public class Error6 extends AccionSemantica{

    @Override
    public void run() {
        System.out.println("Error de compilación en la linea "+linea+": se esperaba '+' ó '-' y llegó el cáracter "+caracter);
    }
}