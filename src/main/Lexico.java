package main;

import AccionesSemanticas.*;
import Errores.*;

import java.util.ArrayList;

public class Lexico {

    private int F = 100;    // Estado final
    private StringBuilder codigoFuente;

    public static char caracter; //caracter que se esta leyendo del codigo fuente
    public static int cursor; //indice del codigo fuente
    public static int linea; //linea que se esta leyendo del codigo fuente
    public static ArrayList<String> palabrasReservadas = new ArrayList<String>();

    //TOKEN SIN ASCII
    public static final int IDE = 257;
    public static final int CTE = 258;
    public static final int MAYOR_IGUAL = 259;
    public static final int MENOR_IGUAL = 260;
    public static final int IGUAL_IGUAL = 261;
    public static final int DISTINTO = 262;
    public static final int COMENT = 263;
    public static final int CADENA = 264;


    // PALABRAS RESERVADAS
    public static final int IF = 265;
    public static final int THEN = 266;
    public static final int ELSE = 267;
    public static final int END_IF = 268;
    public static final int OUT = 269;
    public static final int FUNC = 270;
    public static final int RETURN = 271;
    public static final int UINT = 272;
    public static final int DOUBLE = 273;
    public static final int NI = 274;
    public static final int REF = 276;
    public final static int FOR = 277;
    public final static int UP = 278;
    public final static int DOWN = 279;
    public final static int PROC = 280;


    // ACCIONES SEMANTICAS
    private AccionSemantica as1 = new AccionSemantica1();
    private AccionSemantica as2 = new AccionSemantica2();
    private AccionSemantica as3 = new AccionSemantica3();
    private AccionSemantica as4 = new AccionSemantica4();
    private AccionSemantica as5 = new AccionSemantica5();
    private AccionSemantica as6 = new AccionSemantica6();
    private AccionSemantica as7 = new AccionSemantica7();
    private AccionSemantica as8 = new AccionSemantica8();
    private AccionSemantica as9 = new AccionSemantica9();
    private AccionSemantica as10 = new AccionSemantica10();
    private AccionSemantica as11 = new AccionSemantica11();
    private AccionSemantica as12 = new AccionSemantica12();
    private AccionSemantica as13 = new AccionSemantica13();
    private AccionSemantica as14 = new AccionSemantica14();
    private AccionSemantica as15 = new AccionSemantica15();
    private AccionSemantica as16 = new AccionSemantica16();


    // ERRORES
    private Error1 err1 = new Error1();
    private Error2 err2 = new Error2();
    private Error3 err3 = new Error3();
    private Error4 err4 = new Error4();
    private Error5 err5 = new Error5();
    private Error6 err6 = new Error6();
    private Error7 err7 = new Error7();
    private Error8 err8 = new Error8();
    private Error9 err9 = new Error9();
    private Error11 err11= new Error11();

    private int[][] transiciones = {
            //L  l  d  .  %  <  >  =  "  !  +  -  _  u  i  bt  d ot \n  $
            //0  1  2  3  4  5  6  7  8  9 10  11 12 13 14 15 16 17 18 19
            {1, 2, 3, 6, 11, 13, 14, 15, 17, 16, F, F, -1, 2, 2, 0, 2, F, 0, F},//0
            {1, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F},//1
            {F, 2, 2, F, F, F, F, F, F, F, F, F, 2, 2, 2, F, 2, F, F, F},//2
            {-1, -1, 3, 7, -1, -1, -1, -1, -1, -1, -1, -1, 4, -1, -1, -1, -1, -1, -1, F},//3
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 5, -1, -1, -1, -1, -1, F},//4
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, F, -1, -1, -1, -1, F},//5
            {-1, -1, 7, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, F},//6
            {F, F, 7, F, F, F, F, F, F, F, F, F, F, F, F, F, 8, F, F, F},//7
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 9, 9, -1, -1, -1, -1, -1, -1, -1, F},//8
            {-1, -1, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, F},//9
            {F, F, 10, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F},//10
            {F, F, F, F, 12, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F},//11
            {12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 0, F},//12
            {F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F},//13
            {1, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F},//14
            {F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F},//15
            {-1, -1, -1, -1, -1, -1, -1, F, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, F},//16
            {17, 17, 17, 17, 17, 17, 17, 17, F, 17, 17, 18, 17, 17, 17, 17, 17, 17, -1, F},//17
            {17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 18, 17, 17, 17, 17, 17, 17, 17, F},//18
    };

    private AccionSemantica[][] acciones = {
            // L    l    d    .    %    <    >    =    "    !    +    -    _   'u'  'i'  bl   'd'  ot   \n  $
            // 0    1    2    3    4    5    6    7    8    9   10   11   12   13   14   15   16   17   18  19
            {as1, as1, as1, as1, null, null, null, null, as1, null, as7, as7, err1, as1, as1, null, as1, as7, null, null},//0
            {as2, as3, as3, as3, as3, as3, as3, as3, as3, as3, as3, as3, as3, as3, as3, as3, as3, as3, as3, as3},//1
            {as4, as2, as2, as4, as4, as4, as4, as4, as4, as4, as4, as4, as2, as2, as2, as4, as2, as4, as4, as4},//2
            {err2, err2, as2, as2, err2, err2, err2, err2, err2, err2, err2, err2, null, err2, err2, err2, err2, err2, err2},//3
            {err3, err3, err3, err3, err3, err3, err3, err3, err3, err3, err3, err3, err3, null, err3, err3, err3, err3, err3},//4
            {err4, err4, err4, err4, err4, err4, err4, err4, err4, err4, err4, err4, err4, err4, as5, err4, err4, err4, err4},//5
            {err5, err5, as2, err5, err5, err5, err5, err5, err5, err5, err5, err5, err5, err5, err5, err5, err5, err5, err5},//6
            {as6, as6, as2, as6, as6, as6, as6, as6, as6, as6, as6, as6, as6, as6, as6, as6, as2, as6, as6, as6},//7
            {err6, err6, err6, err6, err6, err6, err6, err6, err6, err6, as2, as2, err6, err6, err6, err6, err6, err6, err6},//8
            {err5, err5, as2, err5, err5, err5, err5, err5, err5, err5, err5, err5, err5, err5, err5, err5, err5, err5, err5},//9
            {as6, as6, as2, as6, as6, as6, as6, as6, as6, as6, as6, as6, as6, as6, as6, as6, as6, as6, as6, as6},//10
            {as8, as8, as8, as8, null, as8, as8, as8, as8, as8, as8, as8, as8, as8, as8, as8, as8, as8, as8},//11
            {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, err11},//12
            {as10, as10, as10, as10, as10, as10, as10, as9, as10, as10, as10, as10, as10, as10, as10, as10, as10, as10, as10},//13
            {as12, as12, as12, as12, as12, as12, as12, as11, as12, as12, as12, as12, as12, as12, as12, as12, as12, as12, as12},//14
            {as14, as14, as14, as14, as14, as14, as14, as13, as14, as14, as14, as14, as14, as14, as14, as14, as14, as14, as14},//15
            {err7, err7, err7, err7, err7, err7, err7, as15, err7, err7, err7, err7, err7, err7, err7, err7, err7, err7, err7},//16
            {as2, as2, as2, as2, as2, as2, as2, as2, as16, as2, as2, as2, as2, as2, as2, as2, as2, as2, err8},//17
            {as2, as2, as2, as2, as2, as2, as2, as2, as2, as2, as2, as2, as2, as2, as2, as2, as2, as2, as2} //18
    };

    public Lexico(StringBuilder codigoFuente) {
        linea = 1;
        cursor = 0;
        this.codigoFuente = codigoFuente;

        caracter = codigoFuente.charAt(cursor);

        palabrasReservadas.add("IF");
        palabrasReservadas.add("THEN");
        palabrasReservadas.add("ELSE");
        palabrasReservadas.add("END_IF");
        palabrasReservadas.add("OUT");
        palabrasReservadas.add("FUNC");
        palabrasReservadas.add("RETURN");
        palabrasReservadas.add("UINT");
        palabrasReservadas.add("DOUBLE");
        palabrasReservadas.add("NI");
        palabrasReservadas.add("REF");
        palabrasReservadas.add("FOR");
        palabrasReservadas.add("UP");
        palabrasReservadas.add("DOWN");
        palabrasReservadas.add("PROC");
    }

    public Token getToken() {
        int estadoActual = 0;
        int columna = -1;
        Token token = null;

        while ((caracter != '$') && (cursor<codigoFuente.length())) { // mientras no llego al final del codigo
            caracter = codigoFuente.charAt(cursor);
            cursor++;
            columna = getColumna(caracter);
            if (columna != -1) { // si no es un caracter invalido
                if (acciones[estadoActual][columna] != null) // si hay una AS
                    token = acciones[estadoActual][columna].run(); //ejecuto la AS correspondiente

                estadoActual = transiciones[estadoActual][columna]; // transicion de estado siempre
                //TODO:deberiamos preguntar si token no es null? por tema errores.
                if (estadoActual == F)//si estoy en final (tengo un token listo para devolver)
                    return token;
                   /* if (caracter == '$') {
                        //return new Token('$');
                    } else {
                        return token;
                    }*/
                else if (estadoActual == -1) {
                    cursor--;
                    return token;//estadoActual = 0;//DEBERIA IR A FINALo al inicio?
                }
            } else { // error por caracter invalido
                return new Error1().run();
            }
            if (caracter == '\n')
                linea++;
        }
        return new Token('$');
    }

    private int getColumna(char caracter) {
        if (caracter == 117)
            return 13; // 'u'
        if (caracter == 105)
            return 14; // 'i'
        if (caracter == 100)
            return 16; // 'd'
        if ((caracter >= 65) && (caracter <= 90))
            return 0; // LETRAS mayusculas
        if ((caracter >= 97) && (caracter <= 122))
            return 1; // letras minusculas
        if ((caracter >= 48) && (caracter <= 57))
            return 2; // digitos
        if (caracter == 46)
            return 3; // .
        if (caracter == 37)
            return 4; // %
        if (caracter == 60)
            return 5; // <
        if (caracter == 62)
            return 6; // >
        if (caracter == 61)
            return 7; // =
        if (caracter == 34)
            return 8; // "
        if (caracter == 33)
            return 9; // !
        if (caracter == 43)
            return 10; // +
        if (caracter == 45)
            return 11; // -
        if (caracter == 95)
            return 12; // _
        if ((caracter == 9) || (caracter == 32))
            return 15; // >blanco o tab
        if (caracter == 42 || caracter == 44 || caracter == 47 || caracter == 41 || caracter == 40 || caracter == 123 || caracter == 125 || caracter == 58 || caracter == 59)
            return 17; // 'otro'
        if (caracter == 10)
            return 18; // 'nueva linea'
        if (caracter == 36)
            return 19; // $
        return -1; //caracter no valido
    }
}
