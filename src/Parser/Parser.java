//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 2 "calc.y"
package Parser;
import main.*;
//#line 20 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short IDE=257;
public final static short CTE_UINT=258;
public final static short MAYOR_IGUAL=259;
public final static short MENOR_IGUAL=260;
public final static short IGUAL_IGUAL=261;
public final static short DISTINTO=262;
public final static short CTE_DOUBLE=263;
public final static short CADENA=264;
public final static short IF=265;
public final static short THEN=266;
public final static short ELSE=267;
public final static short END_IF=268;
public final static short OUT=269;
public final static short UINT=270;
public final static short DOUBLE=271;
public final static short NI=272;
public final static short REF=273;
public final static short FOR=274;
public final static short UP=275;
public final static short DOWN=276;
public final static short PROC=277;
public final static short FUNC=278;
public final static short RETURN=279;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    1,    4,    4,    3,    3,    2,    2,
    2,    7,    7,    5,    5,    9,    9,   10,   10,   12,
   12,   12,   12,   12,   12,   12,   12,   12,   12,   11,
   11,   11,   11,   14,   14,   14,   14,   14,   13,   13,
    8,    8,    6,    6,    6,    6,    6,   15,   15,   22,
   22,   22,   22,   22,   22,   22,   22,   22,   22,   22,
   22,   22,   20,   23,   23,   23,   23,   25,   25,   25,
   26,   26,   26,   27,   27,   28,   24,   24,   24,   24,
   24,   24,   21,   21,   16,   16,   16,   29,   29,   29,
   29,   29,   29,   29,   29,   29,   29,   29,   29,   17,
   17,   30,   30,   30,   30,   30,   18,   18,   31,   31,
   31,   19,   19,   33,   33,   33,   33,   32,   32,   32,
   34,   34,   34,   34,
};
final static short yylen[] = {                            2,
    1,    1,    3,    1,    2,    2,    1,    2,    2,    2,
    1,    2,    2,    2,    1,    1,    3,   11,    1,   10,
   10,   10,   10,   10,   10,   10,   10,   10,   10,    1,
    3,    5,    1,    7,    2,    3,    4,    4,    2,    3,
    1,    1,    1,    1,    1,    1,    1,   14,    1,   13,
   13,   13,   13,   13,   13,   13,   13,   13,   13,   13,
   13,   13,    3,    3,    3,    1,    4,    3,    3,    1,
    1,    1,    1,    1,    1,    2,    1,    1,    1,    1,
    1,    1,    1,    1,    8,   12,    1,    7,    7,    7,
    7,    7,    7,    7,   11,   11,   11,   11,   11,    4,
    1,    3,    3,    2,    4,    3,    3,    1,    2,    2,
    2,    4,    1,    3,    3,    3,    3,    3,    5,    1,
    2,    2,    4,    3,
};
final static short yydefred[] = {                         0,
    0,    0,    0,   41,   42,    0,    0,    0,    0,    0,
    0,    1,    0,    0,    4,    0,    0,   11,    0,   15,
   19,   43,   44,   45,   46,   47,   49,   87,  101,  108,
  113,    0,   75,   74,    0,    0,    0,    0,    0,    0,
    0,   70,   71,   72,    0,  120,   73,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    7,    0,    0,    0,
    0,    5,    8,   12,    9,   13,   10,   16,    0,  122,
    0,    0,  116,    0,    0,   76,  121,    0,    0,    0,
    0,    0,    0,  115,    0,    0,    0,   80,   81,   79,
   82,   77,   78,    0,  102,    0,    0,  106,    0,    0,
    0,    0,    0,    0,    0,    0,   33,    0,    3,  117,
  114,    0,  124,  118,    0,  112,    0,    0,   68,   69,
    0,    0,    0,    0,    0,    0,    0,  105,  100,    0,
    0,    0,    0,    0,    0,    0,   39,    0,    0,    0,
    0,   17,   67,  123,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   40,    0,    0,    0,    0,
    0,    0,   36,    0,  119,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   37,   38,    0,   89,   90,   92,   93,
    0,   91,   88,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   85,
    0,   83,   84,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   34,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   22,   23,   25,   28,
    0,   27,   26,   24,   21,   20,   97,   98,    0,   96,
   95,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   18,   86,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   50,   52,   55,   57,
   59,   61,    0,   60,   58,   56,   54,   53,   51,   48,
};
final static short yydgoto[] = {                         11,
   12,   63,   14,   15,   16,   17,   18,   19,   69,   20,
  105,   21,  106,  107,   22,   23,   24,   25,   26,   49,
  214,   27,   50,   94,   41,   42,   43,   44,   28,   29,
   30,   45,   31,   46,
};
final static short yysindex[] = {                        93,
    9,   68,   13,    0,    0,    7,   12,  823,  -51,  226,
    0,    0,    0,  108,    0,   44,   72,    0, -225,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  -44,    0,    0,    2,  -39,  226, -223, -221,  -18,
  -21,    0,    0,    0,   40,    0,    0,  170,   23,  356,
   39,   96,   22,  -49,  -25, -227,    0,  118,  -44,   99,
  -18,    0,    0,    0,    0,    0,    0,    0,    4,    0,
 -206,  226,    0,   46,  -18,    0,    0,  123,  123,  123,
  123,   10, -172,    0,  -37,  -24,  -34,    0,    0,    0,
    0,    0,    0,  226,    0,   47,   50,    0, -166,   75,
 -161, -215,  -10, -156,   61,   25,    0,   63,    0,    0,
    0, -152,    0,    0,   -4,    0,  -21,  -21,    0,    0,
 -148,   53,  823,  823,  133,  823,  -18,    0,    0,   55,
   56,  -36,   57, -145, -154,  -35,    0, -153, -227,   35,
 -151,    0,    0,    0, -137,  143,  169,  179,  204,  241,
  226,  226,  406,  226,  226,    0,   62,   71,  -58,   73,
   98, -227,    0,   74,    0, -139, -129, -127,   67, -124,
 -123,   87,   88,  417,  100,  102,  104,  -92,  -91,   95,
  -88,  -87, -227,    0,    0,  -86,    0,    0,    0,    0,
 -103,    0,    0, -217, -217, -217,  -55, -217, -217, -217,
   32,   54,   58, -101,   59,   64,  130,   65,  282,    0,
  823,    0,    0,  -82,  -80,  -78, -242,  -74,  -73,  -69,
  -68,  823,  823,  823,  300,  823,  823,  823, -227,  823,
  259,  332,  354,  150,  152,  154,  155,   20,  156,  157,
  160,  162,  369,  384,  402,  424,  442,  453,  479,  497,
    0,  520,  -63,   77,  -61,  106,   94,  198,  207,  268,
  -17,  283,  296,  298,  299,  302,    0,    0,    0,    0,
  535,    0,    0,    0,    0,    0,    0,    0,  161,    0,
    0,  823,  823,  823,  823,  823,  464,  823,  823,  823,
  823,  823,    0,    0,  553,  572,  590,  622,  644,  691,
  715,  725,  740,  756,  775,  791,    0,    0,    0,    0,
    0,    0,  813,    0,    0,    0,    0,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    5,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   51,    0,    0,    0,    0,  101,    0,    0,  103,
  865,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  140,    0,    0,    0,    0,    0,    0,  431,    0,    0,
  141,    0,    0,    0,    0,    0,    0,    0,  264,    0,
    0,    0,    0,    0,  290,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  317,    0,    0,    0,
    0,    0,    0,    0,    0,  -33,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  892,  914,    0,    0,
    0,    0,    0,    0,    0,    0,  -41,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  -31,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  -30,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  352,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  -13,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  373,    0,    0,    0,    0,    0,    0,    0,  376,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  388,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    1,   27,    0,    0,    0,    0,  783,    0,    0,
  -43,    0,  360,    0,    0,    0,    0,    0,    0,  921,
 -122,    0,   28,    0,  -16,   -9,    0,    0,    0,    0,
    0,   21,    0,    0,
};
final static int YYTABLESIZE=1190;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         63,
   13,   73,  181,  217,    2,  159,   39,   30,   57,   35,
   31,  101,  108,   71,  103,  237,  125,   63,   39,  211,
   80,  226,  154,  287,   78,   81,   79,   32,   40,   60,
  135,   68,  212,  213,   58,   77,  143,   61,   78,   76,
   79,   72,    4,    5,    7,  102,   54,  112,   36,  113,
  114,   56,   52,   38,    4,    5,   74,  212,  213,  136,
  262,  117,  118,   87,   75,    7,   39,  121,  139,   37,
  119,  120,  215,  216,  218,  219,  220,  221,  162,   95,
   84,   63,   99,   83,  122,  123,  116,  128,  126,   83,
  129,  130,   73,   73,  238,   73,  133,   73,  124,  115,
  137,  138,   65,  141,  142,  286,    9,   48,  144,   73,
  145,  156,   38,  151,  152,  155,    9,  157,  160,  165,
  164,  127,  178,   57,   57,   57,   57,   10,  187,    7,
   67,  179,    9,  182,  186,  132,   98,   10,  188,  111,
  189,  183,   83,  192,  193,  194,  195,    9,   57,  146,
  147,  149,  150,   10,  222,  204,  225,    9,  198,  111,
  199,  109,  200,  209,  210,  201,  202,   38,   10,  205,
  206,  208,    9,  229,  169,  234,  223,  235,   10,  236,
  224,  227,    9,  239,  240,  251,  228,  230,  241,  242,
  257,  191,  258,   10,  259,  260,  263,  264,  104,  110,
  265,  279,  266,   10,  277,   59,  280,  100,    9,   57,
   85,   57,   70,  180,   38,    8,  282,   59,    9,  212,
  213,  153,   57,   57,   57,   57,   57,   57,   57,   10,
   57,   57,   62,   63,   63,  232,  158,  233,   30,   10,
   35,   31,  109,    9,    4,    5,   57,  102,  243,  244,
  245,  247,  248,  249,  250,  148,  252,  254,   32,    4,
    5,    7,  102,   53,   10,   32,   33,  166,   55,    7,
   38,   34,  271,    7,    7,    7,   51,  261,    7,   35,
    9,    7,   57,   57,   57,   57,   57,   57,   57,   57,
   57,   57,   57,  167,    4,    5,   82,  102,    9,   64,
   57,   10,   82,  168,    4,    5,   73,  102,  295,  296,
  297,  298,  299,  301,  302,  303,  304,  305,  306,   10,
  283,    9,   14,    1,   47,   33,  313,   66,  170,  284,
   34,    2,  131,    1,  190,    3,    4,    5,   35,    9,
    6,    2,   10,    7,  278,    3,    4,    5,  107,    1,
    6,   96,  203,    7,  110,   82,  111,    2,  109,   97,
   10,    3,    4,    5,    1,  171,    6,    4,    5,    7,
  102,    9,    2,  281,    1,  103,    3,    4,    5,   47,
   33,    6,    2,  253,    7,   34,    3,    4,    5,    1,
  285,    6,   10,    9,    7,  104,  110,    2,   78,    1,
   79,    3,    4,    5,  231,  288,    6,    2,    9,    7,
   94,    3,    4,    5,   10,   92,    6,   93,  289,    7,
  290,  291,  246,    9,  292,    1,   47,   33,  294,   10,
    6,   29,   34,    2,   99,    1,    0,    3,    4,    5,
   35,    9,    6,    2,   10,    7,   62,    3,    4,    5,
   38,    0,    6,    0,    0,    7,  255,    0,    0,    0,
    1,   38,   10,    9,  174,  140,    0,    0,    2,    0,
    0,    0,    3,    4,    5,  196,    0,    6,  256,    0,
    7,    9,   47,   33,   10,    0,    0,    0,   34,    0,
    0,    0,    9,  267,    0,    0,   35,    1,  161,  163,
    0,    0,   10,    9,    0,    2,    0,    0,  268,    3,
    4,    5,    0,   10,    6,    1,    0,    7,    9,   14,
  184,  185,    0,    2,   10,    0,  269,    3,    4,    5,
    0,    0,    6,    0,    0,    7,    9,    0,    1,   10,
    0,    0,  207,    0,    0,  107,    2,    0,  270,    0,
    3,    4,    5,    0,    0,    6,    1,   10,    7,    9,
    0,    0,    0,    0,    2,    0,  272,    0,    3,    4,
    5,    0,  103,    6,    9,    0,    7,  273,    0,    0,
   10,    0,    0,    0,    0,    0,  300,    0,    1,    0,
    0,    0,    9,    0,    0,   10,    2,    0,    0,    0,
    3,    4,    5,  274,    0,    6,    0,   94,    7,    0,
    1,    9,    0,   10,   88,   89,   90,   91,    2,    0,
    0,  275,    3,    4,    5,    1,    0,    6,   29,    9,
    7,   99,   10,    2,    0,    0,    0,    3,    4,    5,
    1,    0,    6,   62,  276,    7,    0,    0,    2,    0,
   10,    0,    3,    4,    5,    0,    0,    6,    1,  293,
    7,    9,   47,   33,    0,    0,    2,    0,   34,    0,
    3,    4,    5,   47,   33,    6,   35,  307,    7,   34,
    1,    0,   10,    9,    0,    0,    0,   35,    2,    0,
    0,    0,    3,    4,    5,    0,  308,    6,    1,    0,
    7,    0,    0,    0,   10,    0,    2,    0,    0,    1,
    3,    4,    5,    0,  309,    6,    0,    2,    7,    0,
    1,    3,    4,    5,    0,    0,    6,    0,    2,    7,
    9,    0,    3,    4,    5,    1,    0,    6,    0,    0,
    7,    0,    0,    2,    0,    0,  310,    3,    4,    5,
    0,   10,    6,    1,    9,    7,    0,    0,    0,    0,
    0,    2,    0,    0,    9,    3,    4,    5,  311,    0,
    6,    0,    0,    7,    0,   10,    1,    0,    0,    9,
    0,    0,    0,    0,    2,   10,    0,    0,    3,    4,
    5,    1,    0,    6,    0,    9,    7,    0,    0,    2,
   10,    0,    0,    3,    4,    5,    0,    0,    6,    1,
    0,    7,    0,    0,    9,  312,   10,    2,    0,    0,
    0,    3,    4,    5,    0,    0,    6,    0,    1,    7,
    9,    0,    0,    0,    0,   10,    2,  104,  104,  314,
    3,    4,    5,    0,    0,    6,    1,    0,    7,  315,
    0,   10,    9,    0,    2,    0,    0,    0,    3,    4,
    5,    0,    9,    6,  316,    0,    7,    0,    0,    0,
    0,    0,    0,   10,    0,    0,    0,    0,    1,    0,
  317,    0,    0,   10,  134,  104,    2,    0,  104,    0,
    3,    4,    5,    0,    0,    6,    0,    0,    7,  318,
    1,    0,    0,    0,    0,   66,    0,   66,    2,   66,
    0,    0,    3,    4,    5,  319,    0,    6,    0,    0,
    7,  104,  104,   66,   66,    0,   66,    0,    0,    0,
    0,    0,   64,    0,   64,    0,   64,  320,    0,    0,
    0,    0,    0,  104,  104,    0,    0,    1,    0,    0,
   64,   64,    0,   64,   65,    2,   65,    0,   65,    3,
    4,    5,    0,    0,    6,  104,    0,    7,   86,    0,
    0,    1,   65,   65,    0,   65,    0,    0,    0,    2,
    0,    1,    0,    3,    4,    5,    0,   66,    6,    2,
    0,    7,    0,    3,    4,    5,    1,    0,    6,    0,
    0,    7,    0,    0,    2,    0,    0,    0,    3,    4,
    5,  104,    1,    6,   64,    0,    7,    0,    0,    0,
    2,    0,    0,    0,    3,    4,    5,    0,    0,    6,
    0,    1,    7,    0,    0,    0,   65,    0,    0,    2,
    0,    0,    0,    3,    4,    5,    0,    1,    6,    0,
    0,    7,    0,    0,    0,    2,    0,    0,    0,    3,
    4,    5,    0,    0,    6,    0,    0,    7,    0,    1,
    0,  172,  173,  175,  176,  177,    0,    2,    0,    1,
    0,    3,    4,    5,    0,    0,    6,    2,    0,    7,
    0,    3,    4,    5,  197,    0,    6,    0,    0,    7,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   66,    0,    0,   66,   66,   66,   66,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   66,
   66,    0,    0,    0,    0,    0,    0,   64,    0,    0,
   64,   64,   64,   64,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   64,   64,    0,   65,
    0,    0,   65,   65,   65,   65,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   65,   65,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         41,
    0,   41,   61,   59,    0,   41,   58,   41,    8,   41,
   41,   61,   56,   58,   40,  258,   41,   59,   58,  123,
   42,  123,   59,   41,   43,   47,   45,   41,    1,    9,
   41,  257,  275,  276,    8,  257,   41,   10,   43,  263,
   45,   40,  270,  271,   40,  273,   40,   44,   40,  256,
  257,   40,   40,   45,  270,  271,   36,  275,  276,  103,
   41,   78,   79,   41,   37,   61,   58,   58,   44,   61,
   80,   81,  195,  196,  197,  198,  199,  200,   44,   41,
   41,  123,   61,   44,  257,  123,   41,   41,  123,   44,
   41,  258,   42,   43,  217,   45,  258,   47,  123,   72,
  257,   41,   59,   41,  257,  123,   40,   40,  257,   59,
   58,  257,   45,   59,   59,   59,   40,  272,  272,  257,
  272,   94,   61,  123,  124,  125,  126,   61,  268,  125,
   59,   61,   40,   61,   61,   61,   41,   61,  268,   41,
  268,   44,   44,  268,  268,   59,   59,   40,  148,  123,
  124,  125,  126,   61,  123,   61,  258,   40,   59,   59,
   59,   59,   59,  267,  268,  258,  258,   45,   61,  258,
  258,  258,   40,   44,  148,  258,  123,  258,   61,  258,
  123,  123,   40,  258,  258,  229,  123,  123,  258,  258,
   41,  125,   41,   61,   41,   41,   41,   41,   59,   59,
   41,  125,   41,   61,  268,  257,  268,  257,   40,  209,
   41,  211,  257,  272,   45,  123,  123,  257,   40,  275,
  276,  258,  222,  223,  224,  225,  226,  227,  228,   61,
  230,  231,  125,  275,  276,  209,  272,  211,  272,   61,
  272,  272,  125,   40,  270,  271,  246,  273,  222,  223,
  224,  225,  226,  227,  228,  123,  230,  231,  272,  270,
  271,  257,  273,  257,   61,  257,  258,  125,  257,  265,
   45,  263,  246,  269,  270,  271,  264,  258,  274,  271,
   40,  277,  282,  283,  284,  285,  286,  287,  288,  289,
  290,  291,  292,  125,  270,  271,  257,  273,   40,  256,
  300,   61,  257,  125,  270,  271,  256,  273,  282,  283,
  284,  285,  286,  287,  288,  289,  290,  291,  292,   61,
  123,   40,   59,  257,  257,  258,  300,  256,  125,  123,
  263,  265,  258,  257,  268,  269,  270,  271,  271,   40,
  274,  265,   61,  277,  268,  269,  270,  271,   59,  257,
  274,  256,  258,  277,  256,  257,  256,  265,  256,  264,
   61,  269,  270,  271,  257,  125,  274,  270,  271,  277,
  273,   40,  265,  268,  257,   59,  269,  270,  271,  257,
  258,  274,  265,  125,  277,  263,  269,  270,  271,  257,
  123,  274,   61,   40,  277,  256,  256,  265,   43,  257,
   45,  269,  270,  271,  123,  123,  274,  265,   40,  277,
   59,  269,  270,  271,   61,   60,  274,   62,  123,  277,
  123,  123,  123,   40,  123,  257,  257,  258,  268,   61,
    0,   59,  263,  265,   59,  257,   -1,  269,  270,  271,
  271,   40,  274,  265,   61,  277,   59,  269,  270,  271,
   45,   -1,  274,   -1,   -1,  277,  125,   -1,   -1,   -1,
  257,   45,   61,   40,   59,  106,   -1,   -1,  265,   -1,
   -1,   -1,  269,  270,  271,   59,   -1,  274,  125,   -1,
  277,   40,  257,  258,   61,   -1,   -1,   -1,  263,   -1,
   -1,   -1,   40,  125,   -1,   -1,  271,  257,  139,  140,
   -1,   -1,   61,   40,   -1,  265,   -1,   -1,  125,  269,
  270,  271,   -1,   61,  274,  257,   -1,  277,   40,  256,
  161,  162,   -1,  265,   61,   -1,  125,  269,  270,  271,
   -1,   -1,  274,   -1,   -1,  277,   40,   -1,  257,   61,
   -1,   -1,  183,   -1,   -1,  256,  265,   -1,  125,   -1,
  269,  270,  271,   -1,   -1,  274,  257,   61,  277,   40,
   -1,   -1,   -1,   -1,  265,   -1,  125,   -1,  269,  270,
  271,   -1,  256,  274,   40,   -1,  277,  125,   -1,   -1,
   61,   -1,   -1,   -1,   -1,   -1,  123,   -1,  257,   -1,
   -1,   -1,   40,   -1,   -1,   61,  265,   -1,   -1,   -1,
  269,  270,  271,  125,   -1,  274,   -1,  256,  277,   -1,
  257,   40,   -1,   61,  259,  260,  261,  262,  265,   -1,
   -1,  125,  269,  270,  271,  257,   -1,  274,  256,   40,
  277,  256,   61,  265,   -1,   -1,   -1,  269,  270,  271,
  257,   -1,  274,  256,  125,  277,   -1,   -1,  265,   -1,
   61,   -1,  269,  270,  271,   -1,   -1,  274,  257,  125,
  277,   40,  257,  258,   -1,   -1,  265,   -1,  263,   -1,
  269,  270,  271,  257,  258,  274,  271,  125,  277,  263,
  257,   -1,   61,   40,   -1,   -1,   -1,  271,  265,   -1,
   -1,   -1,  269,  270,  271,   -1,  125,  274,  257,   -1,
  277,   -1,   -1,   -1,   61,   -1,  265,   -1,   -1,  257,
  269,  270,  271,   -1,  125,  274,   -1,  265,  277,   -1,
  257,  269,  270,  271,   -1,   -1,  274,   -1,  265,  277,
   40,   -1,  269,  270,  271,  257,   -1,  274,   -1,   -1,
  277,   -1,   -1,  265,   -1,   -1,  125,  269,  270,  271,
   -1,   61,  274,  257,   40,  277,   -1,   -1,   -1,   -1,
   -1,  265,   -1,   -1,   40,  269,  270,  271,  125,   -1,
  274,   -1,   -1,  277,   -1,   61,  257,   -1,   -1,   40,
   -1,   -1,   -1,   -1,  265,   61,   -1,   -1,  269,  270,
  271,  257,   -1,  274,   -1,   40,  277,   -1,   -1,  265,
   61,   -1,   -1,  269,  270,  271,   -1,   -1,  274,  257,
   -1,  277,   -1,   -1,   40,  125,   61,  265,   -1,   -1,
   -1,  269,  270,  271,   -1,   -1,  274,   -1,  257,  277,
   40,   -1,   -1,   -1,   -1,   61,  265,   55,   56,  125,
  269,  270,  271,   -1,   -1,  274,  257,   -1,  277,  125,
   -1,   61,   40,   -1,  265,   -1,   -1,   -1,  269,  270,
  271,   -1,   40,  274,  125,   -1,  277,   -1,   -1,   -1,
   -1,   -1,   -1,   61,   -1,   -1,   -1,   -1,  257,   -1,
  125,   -1,   -1,   61,  102,  103,  265,   -1,  106,   -1,
  269,  270,  271,   -1,   -1,  274,   -1,   -1,  277,  125,
  257,   -1,   -1,   -1,   -1,   41,   -1,   43,  265,   45,
   -1,   -1,  269,  270,  271,  125,   -1,  274,   -1,   -1,
  277,  139,  140,   59,   60,   -1,   62,   -1,   -1,   -1,
   -1,   -1,   41,   -1,   43,   -1,   45,  125,   -1,   -1,
   -1,   -1,   -1,  161,  162,   -1,   -1,  257,   -1,   -1,
   59,   60,   -1,   62,   41,  265,   43,   -1,   45,  269,
  270,  271,   -1,   -1,  274,  183,   -1,  277,   48,   -1,
   -1,  257,   59,   60,   -1,   62,   -1,   -1,   -1,  265,
   -1,  257,   -1,  269,  270,  271,   -1,  123,  274,  265,
   -1,  277,   -1,  269,  270,  271,  257,   -1,  274,   -1,
   -1,  277,   -1,   -1,  265,   -1,   -1,   -1,  269,  270,
  271,  229,  257,  274,  123,   -1,  277,   -1,   -1,   -1,
  265,   -1,   -1,   -1,  269,  270,  271,   -1,   -1,  274,
   -1,  257,  277,   -1,   -1,   -1,  123,   -1,   -1,  265,
   -1,   -1,   -1,  269,  270,  271,   -1,  257,  274,   -1,
   -1,  277,   -1,   -1,   -1,  265,   -1,   -1,   -1,  269,
  270,  271,   -1,   -1,  274,   -1,   -1,  277,   -1,  257,
   -1,  151,  152,  153,  154,  155,   -1,  265,   -1,  257,
   -1,  269,  270,  271,   -1,   -1,  274,  265,   -1,  277,
   -1,  269,  270,  271,  174,   -1,  274,   -1,   -1,  277,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  256,   -1,   -1,  259,  260,  261,  262,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  275,
  276,   -1,   -1,   -1,   -1,   -1,   -1,  256,   -1,   -1,
  259,  260,  261,  262,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  275,  276,   -1,  256,
   -1,   -1,  259,  260,  261,  262,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  275,  276,
};
}
final static short YYFINAL=11;
final static short YYMAXTOKEN=279;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'","'*'","'+'","','",
"'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,"':'","';'",
"'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,"IDE","CTE_UINT","MAYOR_IGUAL","MENOR_IGUAL",
"IGUAL_IGUAL","DISTINTO","CTE_DOUBLE","CADENA","IF","THEN","ELSE","END_IF",
"OUT","UINT","DOUBLE","NI","REF","FOR","UP","DOWN","PROC","FUNC","RETURN",
};
final static String yyrule[] = {
"$accept : programa",
"programa : bloque",
"bloque : sentencia",
"bloque : '{' bloque_sentencias '}'",
"bloque : error_bloque",
"error_bloque : bloque_sentencias '}'",
"error_bloque : '{' bloque_sentencias",
"bloque_sentencias : sentencia",
"bloque_sentencias : bloque_sentencias sentencia",
"sentencia : declaracion ';'",
"sentencia : ejecucion ';'",
"sentencia : error_sentencia",
"error_sentencia : declaracion error",
"error_sentencia : ejecucion error",
"declaracion : tipo lista_de_variables",
"declaracion : procedimiento",
"lista_de_variables : IDE",
"lista_de_variables : lista_de_variables ',' IDE",
"procedimiento : PROC IDE '(' lista_de_parametros ')' NI '=' CTE_UINT '{' bloque_sentencias '}'",
"procedimiento : error_proc",
"error_proc : PROC '(' lista_de_parametros ')' NI '=' CTE_UINT '{' bloque_sentencias '}'",
"error_proc : PROC IDE lista_de_parametros ')' NI '=' CTE_UINT '{' bloque_sentencias '}'",
"error_proc : PROC IDE '(' ')' NI '=' CTE_UINT '{' bloque_sentencias '}'",
"error_proc : PROC IDE '(' lista_de_parametros NI '=' CTE_UINT '{' bloque_sentencias '}'",
"error_proc : PROC IDE '(' lista_de_parametros ')' '=' CTE_UINT '{' bloque_sentencias '}'",
"error_proc : PROC IDE '(' lista_de_parametros ')' NI CTE_UINT '{' bloque_sentencias '}'",
"error_proc : PROC IDE '(' lista_de_parametros ')' NI '=' '{' bloque_sentencias '}'",
"error_proc : PROC IDE '(' lista_de_parametros ')' NI '=' CTE_UINT bloque_sentencias '}'",
"error_proc : PROC IDE '(' lista_de_parametros ')' NI '=' CTE_UINT '{' '}'",
"error_proc : PROC IDE '(' lista_de_parametros ')' NI '=' CTE_UINT '{' bloque_sentencias",
"lista_de_parametros : param",
"lista_de_parametros : param ',' param",
"lista_de_parametros : param ',' param ',' param",
"lista_de_parametros : error_lista_de_parametros",
"error_lista_de_parametros : param ',' param ',' param ',' lista_de_parametros",
"error_lista_de_parametros : param param",
"error_lista_de_parametros : param param param",
"error_lista_de_parametros : param ',' param param",
"error_lista_de_parametros : param param ',' param",
"param : tipo IDE",
"param : REF tipo IDE",
"tipo : UINT",
"tipo : DOUBLE",
"ejecucion : control",
"ejecucion : seleccion",
"ejecucion : salida",
"ejecucion : asignacion",
"ejecucion : invocacion",
"control : FOR '(' IDE '=' CTE_UINT ';' condicion ';' inc_decr CTE_UINT ')' '{' bloque_sentencias '}'",
"control : error_for",
"error_for : FOR IDE '=' CTE_UINT ';' condicion ';' inc_decr CTE_UINT ')' '{' bloque_sentencias '}'",
"error_for : FOR '(' '=' CTE_UINT ';' condicion ';' inc_decr CTE_UINT ')' '{' bloque_sentencias '}'",
"error_for : FOR '(' IDE CTE_UINT ';' condicion ';' inc_decr CTE_UINT ')' '{' bloque_sentencias '}'",
"error_for : FOR '(' IDE '=' ';' condicion ';' inc_decr CTE_UINT ')' '{' bloque_sentencias '}'",
"error_for : FOR '(' IDE '=' CTE_UINT condicion ';' inc_decr CTE_UINT ')' '{' bloque_sentencias '}'",
"error_for : FOR '(' IDE '=' CTE_UINT ';' ';' inc_decr CTE_UINT ')' '{' bloque_sentencias '}'",
"error_for : FOR '(' IDE '=' CTE_UINT ';' condicion inc_decr CTE_UINT ')' '{' bloque_sentencias '}'",
"error_for : FOR '(' IDE '=' CTE_UINT ';' condicion ';' CTE_UINT ')' '{' bloque_sentencias '}'",
"error_for : FOR '(' IDE '=' CTE_UINT ';' condicion ';' inc_decr ')' '{' bloque_sentencias '}'",
"error_for : FOR '(' IDE '=' CTE_UINT ';' condicion ';' inc_decr CTE_UINT '{' bloque_sentencias '}'",
"error_for : FOR '(' IDE '=' CTE_UINT ';' condicion ';' inc_decr CTE_UINT ')' bloque_sentencias '}'",
"error_for : FOR '(' IDE '=' CTE_UINT ';' condicion ';' inc_decr CTE_UINT ')' '{' '}'",
"error_for : FOR '(' IDE '=' CTE_UINT ';' condicion ';' inc_decr CTE_UINT ')' '{' bloque_sentencias",
"condicion : expresion comparador expresion",
"expresion : expresion '+' termino",
"expresion : expresion '-' termino",
"expresion : termino",
"expresion : DOUBLE '(' expresion ')'",
"termino : termino '*' factor",
"termino : termino '/' factor",
"termino : factor",
"factor : cte",
"factor : factor_negado",
"factor : IDE",
"cte : CTE_DOUBLE",
"cte : CTE_UINT",
"factor_negado : '-' CTE_DOUBLE",
"comparador : '<'",
"comparador : '>'",
"comparador : IGUAL_IGUAL",
"comparador : MAYOR_IGUAL",
"comparador : MENOR_IGUAL",
"comparador : DISTINTO",
"inc_decr : UP",
"inc_decr : DOWN",
"seleccion : IF '(' condicion ')' '{' bloque_sentencias '}' END_IF",
"seleccion : IF '(' condicion ')' '{' bloque_sentencias '}' ELSE '{' bloque_sentencias '}' END_IF",
"seleccion : error_if",
"error_if : IF condicion ')' '{' bloque_sentencias '}' END_IF",
"error_if : IF '(' ')' '{' bloque_sentencias '}' END_IF",
"error_if : IF '(' condicion '{' bloque_sentencias '}' END_IF",
"error_if : IF '(' condicion ')' bloque_sentencias '}' END_IF",
"error_if : IF '(' condicion ')' '{' '}' END_IF",
"error_if : IF '(' condicion ')' '{' bloque_sentencias END_IF",
"error_if : IF '(' condicion ')' '{' bloque_sentencias '}'",
"error_if : IF '(' condicion ')' '{' bloque_sentencias '}' '{' bloque_sentencias '}' END_IF",
"error_if : IF '(' condicion ')' '{' bloque_sentencias '}' ELSE bloque_sentencias '}' END_IF",
"error_if : IF '(' condicion ')' '{' bloque_sentencias '}' ELSE '{' '}' END_IF",
"error_if : IF '(' condicion ')' '{' bloque_sentencias '}' ELSE '{' bloque_sentencias END_IF",
"error_if : IF '(' condicion ')' '{' bloque_sentencias '}' ELSE '{' bloque_sentencias '}'",
"salida : OUT '(' CADENA ')'",
"salida : error_salida",
"error_salida : OUT CADENA ')'",
"error_salida : OUT '(' CADENA",
"error_salida : OUT CADENA",
"error_salida : OUT '(' error ')'",
"error_salida : OUT '(' ')'",
"asignacion : IDE '=' expresion",
"asignacion : error_asignacion",
"error_asignacion : IDE expresion",
"error_asignacion : '=' expresion",
"error_asignacion : IDE '='",
"invocacion : IDE '(' parametros ')'",
"invocacion : error_invocacion",
"error_invocacion : '(' parametros ')'",
"error_invocacion : IDE parametros ')'",
"error_invocacion : IDE '(' ')'",
"error_invocacion : '(' parametros error",
"parametros : IDE ':' IDE",
"parametros : parametros ',' IDE ':' IDE",
"parametros : error_parametros",
"error_parametros : ':' IDE",
"error_parametros : IDE IDE",
"error_parametros : parametros IDE ':' IDE",
"error_parametros : IDE ':' error",
};

//#line 213 "calc.y"

private Lexico lexico;
public Parser(Lexico lexico)
{
  this.lexico= lexico;
}

public int yylex(){
   Token token = this.lexico.getToken();
   if(token != null ){
   	int val =token.getId();
   	yyval = new ParserVal(token.getLexema());
   	return val;
   }
   return 0;
}

public void yyerror(String s){
    System.out.println("Parser: " + s);
}
//#line 680 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 5:
//#line 21 "calc.y"
{System.out.println("[Parser | Linea " + Lexico.linea + "] se detectó un bloque de sentencias mal declarado, falta '{'");}
break;
case 6:
//#line 22 "calc.y"
{System.out.println("[Parser | Linea " + Lexico.linea + "] se detectó un bloque de sentencias mal declarado, falta '}'");}
break;
case 9:
//#line 29 "calc.y"
{System.out.println("[Parser | Linea " + Lexico.linea + "] se detectó una sentencia declarativa");}
break;
case 10:
//#line 30 "calc.y"
{System.out.println("[Parser | Linea " + Lexico.linea + "] se detectó una sentencia de ejecución");}
break;
case 12:
//#line 34 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó una sentencia mal declarada, falta ';'");}
break;
case 13:
//#line 35 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó una sentencia mal declarada, falta ';'");}
break;
case 14:
//#line 38 "calc.y"
{System.out.println("[Parser | Linea " + Lexico.linea + "] se detectó una declaracion");}
break;
case 16:
//#line 42 "calc.y"
{System.out.println("[Parser | Linea " + Lexico.linea + "] lei un ID");}
break;
case 18:
//#line 49 "calc.y"
{System.out.println("[Parser | Linea " + Lexico.linea + "]se declaro una PROC");}
break;
case 20:
//#line 54 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un procedimiento mal declarada, falta IDE");}
break;
case 21:
//#line 55 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un procedimiento mal declarada, falta '('");}
break;
case 22:
//#line 56 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un procedimiento mal declarado, falta lista de parametros");}
break;
case 23:
//#line 57 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un procedimiento mal declarado, falta ')'");}
break;
case 24:
//#line 58 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un procedimiento mal declarado, falta NI ");}
break;
case 25:
//#line 59 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un procedimiento mal declarado, '=' despues de NI ");}
break;
case 26:
//#line 60 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un procedimiento mal declarado, falta la CTE_UINT ");}
break;
case 27:
//#line 61 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un procedimiento mal declarado, falta '{' que abre el bloque de sentecias ");}
break;
case 28:
//#line 62 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó una sentencia mal declarada, falta el bloque de sentencias");}
break;
case 29:
//#line 63 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un procedimiento mal declarado, falta '}' que cierra el bloque de sentencias");}
break;
case 34:
//#line 72 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectaron más parametros de los permitidos (3)");}
break;
case 35:
//#line 73 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectaron parametros mal declarados, falta ','");}
break;
case 36:
//#line 74 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectaron parametros mal declarados, falta ','");}
break;
case 37:
//#line 75 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectaron parametros mal declarados, falta ','");}
break;
case 38:
//#line 76 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectaron parametros mal declarados, falta ','");}
break;
case 41:
//#line 83 "calc.y"
{System.out.println("[Parser | Linea " + Lexico.linea + "] lei un tipo UINT");}
break;
case 42:
//#line 84 "calc.y"
{System.out.println("[Parser | Linea " + Lexico.linea + "] lei un tipo DOUBLE");}
break;
case 48:
//#line 94 "calc.y"
{ System.out.println("[Parser | Linea " + Lexico.linea + "] lei un FOR");}
break;
case 50:
//#line 98 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta '('");}
break;
case 51:
//#line 99 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta id ");}
break;
case 52:
//#line 100 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta '='");}
break;
case 53:
//#line 101 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta 'CTE_UINT'");}
break;
case 54:
//#line 102 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta ';'");}
break;
case 55:
//#line 103 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta la condición");}
break;
case 56:
//#line 104 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta ';'");}
break;
case 57:
//#line 105 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta la palabra UP o DOWN");}
break;
case 58:
//#line 106 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta 'CTE_UINT'");}
break;
case 59:
//#line 107 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta ')'");}
break;
case 60:
//#line 108 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta '{'");}
break;
case 61:
//#line 109 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta el bloque de sentencias");}
break;
case 62:
//#line 110 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta '}'");}
break;
case 64:
//#line 115 "calc.y"
{ System.out.println("[Parser | Linea " + Lexico.linea + "] se realizo una suma");}
break;
case 65:
//#line 116 "calc.y"
{ System.out.println("[Parser | Linea " + Lexico.linea + "] se realizo una resta");}
break;
case 67:
//#line 118 "calc.y"
{ System.out.println("[Parser | Linea " + Lexico.linea + "] se realizo una conversion");}
break;
case 68:
//#line 121 "calc.y"
{ System.out.println("[Parser | Linea " + Lexico.linea + "] se realizo una multiplicacion");}
break;
case 69:
//#line 122 "calc.y"
{ System.out.println("[Parser | Linea " + Lexico.linea + "] se realizo una division");}
break;
case 73:
//#line 128 "calc.y"
{System.out.println("[Parser | Linea " + Lexico.linea + "] se leyó un identificador");}
break;
case 74:
//#line 131 "calc.y"
{System.out.println("[Parser | Linea " + Lexico.linea + "] se leyó una cte double");}
break;
case 75:
//#line 132 "calc.y"
{System.out.println("[Parser | Linea " + Lexico.linea + "] se leyó una cte uint");}
break;
case 85:
//#line 150 "calc.y"
{System.out.println("[Parser | Linea " + Lexico.linea + "] se leyó un IF");}
break;
case 86:
//#line 151 "calc.y"
{System.out.println("[Parser | Linea " + Lexico.linea + "] se leyó un IF con ELSE");}
break;
case 88:
//#line 155 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un IF mal declarado, falta '('");}
break;
case 89:
//#line 156 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un IF mal declarado, falta la condicion");}
break;
case 90:
//#line 157 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un IF mal declarado, falta ')'");}
break;
case 91:
//#line 158 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un IF mal declarado, falta '{'");}
break;
case 92:
//#line 159 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un IF mal declarado, falta el bloque de sentencias");}
break;
case 93:
//#line 160 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un IF mal declarado, falta '}'");}
break;
case 94:
//#line 161 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un IF mal declarado, falta el END_IF o ELSE");}
break;
case 95:
//#line 162 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un IF mal declarado, falta el ELSE");}
break;
case 96:
//#line 163 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un IF mal declarado, falta '{'");}
break;
case 97:
//#line 164 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un IF mal declarado, falta el bloque de sentencias del ELSE");}
break;
case 98:
//#line 165 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un IF mal declarado, falta '}'");}
break;
case 99:
//#line 166 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un IF mal declarado, falta el END_IF");}
break;
case 100:
//#line 171 "calc.y"
{System.out.println("[Parser | Linea " + Lexico.linea + "] se realizó un OUT");}
break;
case 102:
//#line 175 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " falta '(' en el OUT de cadena");}
break;
case 103:
//#line 176 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " falta ')' en el OUT de cadena");}
break;
case 104:
//#line 177 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " faltan '(' y ')' en el OUT de cadena");}
break;
case 105:
//#line 178 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " entre '(' y ')' no hay una cadena");}
break;
case 106:
//#line 179 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " falta cadena entre los parentesis en el OUT");}
break;
case 107:
//#line 182 "calc.y"
{System.out.println("[Parser | Linea " + Lexico.linea + "] se realizó una asignacion");}
break;
case 109:
//#line 186 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " falta '=' en la asignacion");}
break;
case 110:
//#line 187 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " falta el ID en la asignacion");}
break;
case 111:
//#line 188 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " falta una expresión para ser asignada al ID");}
break;
case 112:
//#line 192 "calc.y"
{System.out.println("[Parser | Linea " + Lexico.linea + "] se realizó una invocacion a una funcion");}
break;
case 114:
//#line 196 "calc.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó una invocacion mal declarada, falta el identificador");}
break;
case 115:
//#line 197 "calc.y"
{System.out.println("Error sint�ctico: Linea " + Lexico.linea + " se detect� una invocacion mal declarada, falta el '('");}
break;
case 116:
//#line 198 "calc.y"
{System.out.println("Error sint�ctico: Linea " + Lexico.linea + " se detect� una invocacion mal declarada, faltan los parametros");}
break;
case 117:
//#line 199 "calc.y"
{System.out.println("Error sint�ctico: Linea " + Lexico.linea + " se detect� una invocacion mal declarada, falta el ')'");}
break;
case 121:
//#line 207 "calc.y"
{System.out.println("Error sint�ctico: Linea " + Lexico.linea + " se detectaron parametros mal declarados, falta el id de la izquierda");}
break;
case 122:
//#line 208 "calc.y"
{System.out.println("Error sint�ctico: Linea " + Lexico.linea + " se detectaron parametros mal declarados, falta ':' entre los id");}
break;
case 123:
//#line 209 "calc.y"
{System.out.println("Error sint�ctico: Linea " + Lexico.linea + " se detectaron parametros mal declarados, falta ',' para separar los id");}
break;
case 124:
//#line 210 "calc.y"
{System.out.println("Error sint�ctico: Linea " + Lexico.linea + " se detectaron parametros mal declarados, falta el id de la derecha");}
break;
//#line 1153 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
