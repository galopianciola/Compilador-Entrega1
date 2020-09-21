package main;

import java.io.*;

public class Main{

    private static BufferedReader codigo;
    public static TablaSimbolos tSimbolos = new TablaSimbolos();

    private static StringBuilder getCodigo(BufferedReader ubicacion){
        StringBuilder buffer = new StringBuilder();

        try{
            codigo = new BufferedReader(new FileReader(ubicacion.readLine()));
            String readLine;

            while ((readLine = codigo.readLine())!= null) {
                buffer.append(readLine + "\n");
            }

            buffer.append("$");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    public static void main(String[]args) {
        String direccion = "/Users/macbook/IdeaProjects/Compilador/pruba.txt";
        InputStream is = new ByteArrayInputStream(direccion.getBytes());

        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        StringBuilder codigo = new StringBuilder(getCodigo(br));

        int linea = 0;
        for (int i = 0; i<codigo.length();i++){
            if (codigo.charAt(i) == '\n')
                linea++;
        }
        System.out.println("cant lineas = " + linea);
    }
}