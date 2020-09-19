package Errores;
import main.*;
public class Error7 extends AccionSemantica {

    @Override
    public void run() {
        System.out.println("Error de compilación en la linea " + linea + ": se esperaba una '=' y llegó el cáracter " + caracter);
    }
}