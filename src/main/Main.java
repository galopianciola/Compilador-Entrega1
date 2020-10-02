package main;

import java.io.*;
import Parser.*;

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

            buffer.deleteCharAt(buffer.length()-1);
            //System.out.println(buffer);
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
        //-------------- CARGA DE ARCHIVO --------------
        String direccion = "test.txt";
        InputStream is = new ByteArrayInputStream(direccion.getBytes());

        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        StringBuilder codigo = new StringBuilder(getCodigo(br));

        int linea = 0;
        for (int i = 0; i<codigo.length();i++){
            if (codigo.charAt(i) == '\n')
                linea++;
        }
        //System.out.println("cant lineas = " + linea);

        //-------------- ///// // /////// --------------

        Lexico l1 = new Lexico(codigo);

        Parser p = new Parser(l1);
        p.run();

     /*  Token token = l1.getToken();
        if (token != null){
            System.out.println(token.getId());
            System.out.println(token.getLexema());
        }


        Token token2 = l1.getToken();
        if (token2 != null){
            System.out.println(token2.getId());
            System.out.println(token2.getLexema());
        }
        /*Token token3 = l1.getToken();
        if (token3 != null){
            System.out.println(token3.getId());
            System.out.println(token3.getLexema());
        }*/
    }
}