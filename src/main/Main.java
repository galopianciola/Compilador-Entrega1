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

    public static void main(String[]args) throws IOException {
        //-------------- CARGA DE ARCHIVO --------------

        InputStreamReader leer = new InputStreamReader(System.in);
        BufferedReader buffer = new BufferedReader(leer);
        System.out.print("Ingrese la direccion del archivo: ");
        String direccion = buffer.readLine();

       // String direccion = "test.txt";
        InputStream is = new ByteArrayInputStream(direccion.getBytes());

        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        StringBuilder codigo = new StringBuilder(getCodigo(br));

        System.out.println("Contenido del archivo: " + codigo);
        //-------------- ///// // /////// --------------

        Lexico l1 = new Lexico(codigo);

        System.out.println("\n********* PARSER.RUN() *********");
        Parser p = new Parser(l1);
        p.run();

        System.out.println("\n********* TABLA DE SIMBOLOS *********");
        tSimbolos.printTablaSimbolos();

    }
}