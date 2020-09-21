package main;
import java.util.Hashtable;

public class TablaSimbolos {
    private static Hashtable<String,String> tSimbolos;


    public TablaSimbolos(){
        tSimbolos = new Hashtable<String,String>();
    }

    public void agregarSimbolo(String lexema,String tipo){
        if (tSimbolos.containsKey(lexema))
            System.out.println("El identificador "+lexema+" ya existe");
        else
            tSimbolos.put(lexema,tipo);
    }


    public String getLexema(String s){
        if (tSimbolos.containsKey(s))
            return tSimbolos.get(s);
        else
            return null;
    }

    public boolean existeLexema(String s){
        return(tSimbolos.containsKey(s));
    }
}
