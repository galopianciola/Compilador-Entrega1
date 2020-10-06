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






//#line 2 "gramatica.y"
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
    5,    5,    5,   10,   10,    8,    8,    8,   11,    9,
    9,   13,   13,   13,   13,   13,   13,   13,   13,   13,
   13,   12,   12,   12,   12,   15,   15,   15,   15,   15,
   14,   14,    7,    7,    6,    6,    6,    6,    6,    6,
   21,   21,   21,   21,   21,   16,   16,   24,   24,   24,
   24,   24,   24,   24,   24,   24,   24,   24,   24,   24,
   22,   25,   25,   25,   25,   27,   27,   27,   28,   28,
   28,   28,   26,   26,   26,   26,   26,   26,   23,   23,
   17,   17,   17,   29,   29,   29,   29,   29,   29,   29,
   29,   29,   29,   29,   29,   18,   18,   30,   30,   30,
   30,   30,   19,   19,   31,   31,   31,   20,   20,   33,
   33,   33,   33,   32,   32,   32,   34,   34,   34,   34,
};
final static short yylen[] = {                            2,
    1,    1,    3,    1,    2,    2,    1,    2,    1,    1,
    3,    2,    1,    3,    2,    1,    3,    1,    2,   11,
    1,   10,   10,   10,   10,   10,   10,   10,   10,   10,
   10,    1,    3,    5,    1,    7,    2,    3,    4,    4,
    2,    3,    1,    1,    2,    2,    2,    2,    2,    1,
    2,    2,    2,    2,    2,   14,    1,   13,   13,   13,
   13,   13,   13,   13,   13,   13,   13,   13,   13,   13,
    3,    1,    3,    3,    4,    3,    3,    1,    1,    1,
    2,    1,    1,    1,    1,    1,    1,    1,    1,    1,
    8,   12,    1,    7,    7,    7,    7,    7,    7,    7,
   11,   11,   11,   11,   11,    4,    1,    3,    3,    2,
    4,    3,    3,    1,    2,    2,    2,    4,    1,    3,
    3,    3,    3,    3,    5,    1,    2,    2,    4,    3,
};
final static short yydefred[] = {                         0,
    0,    0,    0,   43,   44,    0,    0,    0,    0,    0,
    0,    1,    0,    0,    4,    9,   10,    0,    0,   13,
   21,    0,    0,    0,    0,    0,   50,   57,   93,  107,
  114,  119,    0,   80,   79,    0,    0,    0,    0,    0,
    0,    0,   78,    0,  126,   82,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    7,    0,    0,    0,    0,
    5,    8,   16,    0,   18,   15,   12,   51,   45,   52,
   46,   53,   47,   54,   48,   55,   49,  128,    0,    0,
  122,    0,    0,   81,  127,    0,    0,    0,    0,    0,
    0,  121,    0,    0,    0,   86,   87,   85,   88,   83,
   84,    0,  108,    0,    0,  112,    0,    0,    0,    0,
    0,    0,    0,    0,   35,    0,    3,  120,   14,   19,
   11,    0,  130,  124,    0,  118,    0,    0,   76,   77,
    0,    0,    0,    0,    0,    0,    0,  111,  106,    0,
    0,    0,    0,    0,    0,    0,   41,    0,    0,    0,
    0,   17,   75,  129,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   42,    0,    0,    0,    0,
    0,    0,   38,    0,  125,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   39,   40,    0,   95,   96,   98,   99,
    0,   97,   94,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   91,
    0,   89,   90,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   36,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   24,   25,   27,   30,
    0,   29,   28,   26,   23,   22,  103,  104,    0,  102,
  101,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   20,   92,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   58,   60,   63,   65,
   67,   69,    0,   68,   66,   64,   62,   61,   59,   56,
};
final static short yydgoto[] = {                         11,
   12,   62,   14,   15,   16,   17,   18,   64,   19,   20,
   65,  113,   21,  114,  115,   22,   23,   24,   25,   26,
   27,   48,  224,   28,   49,  102,   42,   43,   29,   30,
   31,   44,   32,   45,
};
final static short yysindex[] = {                       103,
  -40,   78,  -37,    0,    0,  -33,  -29,  833,  -54,  207,
    0,    0,    0,  118,    0,    0,    0, -233,  -47,    0,
    0,  -45,   21,   54,   82,  113,    0,    0,    0,    0,
    0,    0,  -52,    0,    0,   16,   -8,  207,  121, -220,
    9,  -11,    0,   22,    0,    0,  180,   19,  366,   23,
  106,   24,   83,  -15, -203,    0,  128,  -52,   50,    9,
    0,    0,    0,  110,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0, -214,  207,
    0,   56,    9,    0,    0,  121,  121,  121,  121,    4,
 -199,    0,  -46,  -18,  -41,    0,    0,    0,    0,    0,
    0,  207,    0,   37,   40,    0, -172,  -42, -171, -225,
    3, -169,   49,   17,    0,   52,    0,    0,    0,    0,
    0, -162,    0,    0,    8,    0,  -11,  -11,    0,    0,
 -161,   41,  833,  833,  143,  833,    9,    0,    0,   39,
   42,  -51,   43, -150, -160,  -25,    0, -158, -203,   35,
 -157,    0,    0,    0, -147,  153,  179,  189,  214,  251,
  207,  207,  416,  207,  207,    0,   55,   58,  -59,   60,
   45, -203,    0,   63,    0, -146, -143, -142,   77, -140,
 -139,   71,   72,  427,   73,   80,   86, -116, -109,   85,
 -108, -107, -203,    0,    0, -106,    0,    0,    0,    0,
  -97,    0,    0, -204, -204, -204,    6, -204, -204, -204,
   30,   32,   33, -101,   44,   57,  134,   59,  292,    0,
  833,    0,    0,  -93,  -77,  -72, -228,  -71,  -70,  -68,
  -67,  833,  833,  833,  310,  833,  833,  833, -203,  833,
  269,  342,  364,  154,  156,  157,  159,  -28,  165,  167,
  174,  290,  379,  394,  412,  434,  452,  463,  489,  507,
    0,  530,  -76,   87,   97,  122,  278,  283,  293,  308,
  -14,  309,  312,  316,  318,  322,    0,    0,    0,    0,
  545,    0,    0,    0,    0,    0,    0,    0,  194,    0,
    0,  833,  833,  833,  833,  833,  474,  833,  833,  833,
  833,  833,    0,    0,  563,  582,  600,  632,  654,  701,
  725,  735,  750,  766,  785,  801,    0,    0,    0,    0,
    0,    0,  823,    0,    0,    0,    0,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   15,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   61,    0,    0,    0,    0,  125,    0,    0,
  135,  875,    0,    0,    0,    0,    0,    0,    0,  140,
    0,    0,    0,    0,    0,    0,  447,    0,    0,  151,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  173,  274,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  300,    0,    0,    0,    0,    0,
    0,    0,    0,  -21,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  902,  924,    0,    0,
    0,    0,    0,    0,    0,    0,  -31,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   -3,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   -2,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  304,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   -1,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  327,    0,    0,    0,    0,    0,    0,    0,  362,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  383,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    1,   27,    0,    0,    0,  934,    0,    0,    0,
    0,  -38,    0,  422,    0,    0,    0,    0,    0,    0,
    0,   12,  291,    0,   31,    0,  -12,   -5,    0,    0,
    0,   20,    0,    0,
};
final static int YYTABLESIZE=1200;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         37,
   13,  191,   51,   40,   39,   79,   53,  164,   56,   71,
   55,   67,  272,   69,    2,  169,  116,   40,  142,   32,
   38,  236,  135,   63,  111,  221,  297,   71,   59,  247,
   88,   41,   81,   84,   57,   89,   85,   37,   33,   34,
   60,  123,  124,  145,    4,    5,  222,  223,  153,   40,
   86,   86,   87,   87,    7,   80,   82,  132,   94,   95,
  149,  131,   92,  103,  227,   91,    4,    5,   83,  110,
  222,  223,  146,  127,  128,    7,  133,  138,  172,   71,
  139,  136,  129,  130,  107,  140,  143,  147,  193,  148,
  118,   71,  151,   91,  152,  154,  126,  161,  155,   91,
  162,  165,   82,   82,  134,   82,  166,   82,  296,  175,
  125,  167,   73,  170,  174,  188,    9,   47,  189,   82,
  192,  197,   39,  196,  198,  199,    9,  202,  203,  204,
  205,  208,  137,   56,   56,   56,   56,   10,  209,    7,
   75,  211,    9,  109,  210,  214,  106,   10,  212,  215,
  216,  218,  232,  122,  233,  234,  235,    9,   56,  156,
  157,  159,  160,   10,  244,   39,  237,    9,  121,  219,
  220,   77,  182,  183,  185,  186,  187,  239,   10,  238,
  245,  240,    9,  117,  179,  246,  249,  250,   10,  251,
  252,  287,    9,  115,  267,  207,  268,  269,  110,  270,
  261,  201,   58,   10,   78,  273,  163,  274,   66,  116,
   68,  289,  190,   10,  275,  141,   33,   34,    9,   56,
   93,   56,   35,   52,   39,    8,   50,   54,    9,  271,
   36,  123,   56,   56,   56,   56,   56,   56,   56,   10,
   56,   56,   61,   71,   71,  242,  168,  243,   58,   10,
   32,   39,  117,    9,    4,    5,   56,  110,  253,  254,
  255,  257,  258,  259,  260,  158,  262,  264,   37,   33,
   34,    7,    4,    5,   10,  110,   70,  176,   90,    7,
  222,  223,  281,    7,    7,    7,    4,    5,    7,  110,
    9,    7,   56,   56,   56,   56,   56,   56,   56,   56,
   56,   56,   56,  177,    4,    5,   90,  110,    9,   72,
   56,   10,   90,  178,    4,    5,   82,  110,  305,  306,
  307,  308,  309,  311,  312,  313,  314,  315,  316,   10,
  276,    9,  113,    1,   46,   34,  323,   74,  180,  108,
   35,    2,  213,    1,  200,    3,    4,    5,   36,    9,
    6,    2,   10,    7,  288,    3,    4,    5,  109,    1,
    6,  104,  100,    7,  290,  119,  120,    2,   76,  105,
   10,    3,    4,    5,    1,  181,    6,   46,   34,    7,
  117,    9,    2,   35,    1,   31,    3,    4,    5,  291,
  115,    6,    2,  263,    7,  110,    3,    4,    5,    1,
  292,    6,   10,    9,    7,  293,  116,    2,   86,    1,
   87,    3,    4,    5,  241,  294,    6,    2,    9,    7,
  105,    3,    4,    5,   10,  100,    6,  101,  123,    7,
  295,  298,  256,    9,  299,    1,   46,   34,  300,   10,
  301,   70,   35,    2,  302,    1,    6,    3,    4,    5,
   36,    9,    6,    2,   10,    7,    0,    3,    4,    5,
   39,  304,    6,   46,   34,    7,  265,    0,    0,   35,
    1,   39,   10,    9,  184,    0,    0,   36,    2,    0,
    0,    0,    3,    4,    5,  206,    0,    6,  266,    0,
    7,    9,    0,    0,   10,  225,  226,  228,  229,  230,
  231,    0,    9,  277,    0,    0,    0,    1,    0,    0,
    0,    0,   10,    9,    0,    2,    0,  248,  278,    3,
    4,    5,    0,   10,    6,    1,    0,    7,    9,  113,
    0,    0,    0,    2,   10,  150,  279,    3,    4,    5,
    0,    0,    6,    0,    0,    7,    9,    0,    1,   10,
    0,    0,    0,    0,    0,  109,    2,    0,  280,  100,
    3,    4,    5,    0,    0,    6,    1,   10,    7,    9,
  171,  173,    0,    0,    2,    0,  282,    0,    3,    4,
    5,    0,   31,    6,    9,    0,    7,  283,    0,    0,
   10,    0,  194,  195,    0,    0,  310,    0,    1,    0,
    0,    0,    9,    0,    0,   10,    2,    0,    0,    0,
    3,    4,    5,  284,  217,    6,    0,  105,    7,    0,
    1,    9,    0,   10,   96,   97,   98,   99,    2,    0,
    0,  285,    3,    4,    5,    1,    0,    6,   70,    9,
    7,    0,   10,    2,    0,    0,    0,    3,    4,    5,
    1,    0,    6,    0,  286,    7,    0,    0,    2,    0,
   10,    0,    3,    4,    5,    0,    0,    6,    1,  303,
    7,    9,   46,   34,    0,    0,    2,    0,   35,    0,
    3,    4,    5,   46,   34,    6,   36,  317,    7,   35,
    1,    0,   10,    9,    0,    0,    0,   36,    2,    0,
    0,    0,    3,    4,    5,    0,  318,    6,    1,    0,
    7,    0,    0,    0,   10,    0,    2,    0,    0,    1,
    3,    4,    5,    0,  319,    6,    0,    2,    7,    0,
    1,    3,    4,    5,    0,    0,    6,    0,    2,    7,
    9,    0,    3,    4,    5,    1,    0,    6,    0,    0,
    7,    0,    0,    2,    0,    0,  320,    3,    4,    5,
    0,   10,    6,    1,    9,    7,    0,    0,    0,    0,
    0,    2,    0,    0,    9,    3,    4,    5,  321,    0,
    6,    0,    0,    7,    0,   10,    1,    0,    0,    9,
    0,    0,    0,    0,    2,   10,    0,    0,    3,    4,
    5,    1,    0,    6,    0,    9,    7,    0,    0,    2,
   10,    0,    0,    3,    4,    5,    0,    0,    6,    1,
    0,    7,    0,    0,    9,  322,   10,    2,    0,    0,
    0,    3,    4,    5,    0,    0,    6,    0,    1,    7,
    9,    0,    0,    0,    0,   10,    2,    0,    0,  324,
    3,    4,    5,    0,    0,    6,    1,    0,    7,  325,
    0,   10,    9,    0,    2,    0,    0,    0,    3,    4,
    5,    0,    9,    6,  326,    0,    7,    0,    0,    0,
    0,    0,    0,   10,    0,    0,    0,    0,    1,    0,
  327,    0,    0,   10,    0,    0,    2,    0,    0,    0,
    3,    4,    5,    0,    0,    6,    0,    0,    7,  328,
    1,    0,    0,    0,    0,   72,    0,   72,    2,   72,
    0,    0,    3,    4,    5,  329,    0,    6,    0,    0,
    7,    0,    0,   72,   72,    0,   72,    0,    0,    0,
    0,    0,   73,    0,   73,    0,   73,  330,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    1,    0,    0,
   73,   73,    0,   73,   74,    2,   74,    0,   74,    3,
    4,    5,    0,    0,    6,    0,    0,    7,    0,    0,
    0,    1,   74,   74,    0,   74,    0,  112,  112,    2,
    0,    1,    0,    3,    4,    5,    0,   72,    6,    2,
    0,    7,    0,    3,    4,    5,    1,    0,    6,    0,
    0,    7,    0,    0,    2,    0,    0,    0,    3,    4,
    5,    0,    1,    6,   73,    0,    7,    0,    0,    0,
    2,    0,    0,    0,    3,    4,    5,    0,    0,    6,
    0,    1,    7,  144,  112,    0,   74,  112,    0,    2,
    0,    0,    0,    3,    4,    5,    0,    1,    6,    0,
    0,    7,    0,    0,    0,    2,    0,    0,    0,    3,
    4,    5,    0,    0,    6,    0,    0,    7,    0,    1,
    0,    0,  112,  112,    0,    0,    0,    2,    0,    1,
    0,    3,    4,    5,    0,    0,    6,    2,    0,    7,
    0,    3,    4,    5,  112,  112,    6,    0,    0,    7,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  112,    0,    0,    0,
   72,    0,    0,   72,   72,   72,   72,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   72,
   72,    0,    0,    0,    0,    0,    0,   73,    0,    0,
   73,   73,   73,   73,    0,    0,    0,    0,    0,    0,
    0,    0,  112,    0,    0,    0,   73,   73,    0,   74,
    0,    0,   74,   74,   74,   74,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   74,   74,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
    0,   61,   40,   58,   45,   58,   40,   59,    8,   41,
   40,   59,   41,   59,    0,   41,   55,   58,   61,   41,
   61,  123,   41,  257,   40,  123,   41,   59,    9,  258,
   42,    1,   41,   39,    8,   47,  257,   41,   41,   41,
   10,  256,  257,   41,  270,  271,  275,  276,   41,   58,
   43,   43,   45,   45,   40,   40,   37,  257,   47,   41,
   44,   58,   41,   41,   59,   44,  270,  271,   38,  273,
  275,  276,  111,   86,   87,   61,  123,   41,   44,   59,
   41,  123,   88,   89,   61,  258,  258,  257,   44,   41,
   41,  123,   41,   44,  257,  257,   41,   59,   58,   44,
   59,   59,   42,   43,  123,   45,  257,   47,  123,  257,
   80,  272,   59,  272,  272,   61,   40,   40,   61,   59,
   61,  268,   45,   61,  268,  268,   40,  268,  268,   59,
   59,   59,  102,  133,  134,  135,  136,   61,   59,  125,
   59,  258,   40,   61,   59,   61,   41,   61,  258,  258,
  258,  258,  123,   44,  123,  123,  258,   40,  158,  133,
  134,  135,  136,   61,  258,   45,  123,   40,   59,  267,
  268,   59,  161,  162,  163,  164,  165,   44,   61,  123,
  258,  123,   40,   59,  158,  258,  258,  258,   61,  258,
  258,  268,   40,   59,   41,  184,   41,   41,   59,   41,
  239,  125,  257,   61,  257,   41,  258,   41,  256,   59,
  256,  125,  272,   61,   41,  258,  257,  258,   40,  219,
   41,  221,  263,  257,   45,  123,  264,  257,   40,  258,
  271,   59,  232,  233,  234,  235,  236,  237,  238,   61,
  240,  241,  125,  275,  276,  219,  272,  221,  257,   61,
  272,   45,  125,   40,  270,  271,  256,  273,  232,  233,
  234,  235,  236,  237,  238,  123,  240,  241,  272,  272,
  272,  257,  270,  271,   61,  273,  256,  125,  257,  265,
  275,  276,  256,  269,  270,  271,  270,  271,  274,  273,
   40,  277,  292,  293,  294,  295,  296,  297,  298,  299,
  300,  301,  302,  125,  270,  271,  257,  273,   40,  256,
  310,   61,  257,  125,  270,  271,  256,  273,  292,  293,
  294,  295,  296,  297,  298,  299,  300,  301,  302,   61,
   41,   40,   59,  257,  257,  258,  310,  256,  125,  257,
  263,  265,  258,  257,  268,  269,  270,  271,  271,   40,
  274,  265,   61,  277,  268,  269,  270,  271,   59,  257,
  274,  256,   59,  277,  268,  256,  257,  265,  256,  264,
   61,  269,  270,  271,  257,  125,  274,  257,  258,  277,
  256,   40,  265,  263,  257,   59,  269,  270,  271,  268,
  256,  274,  265,  125,  277,  256,  269,  270,  271,  257,
  123,  274,   61,   40,  277,  123,  256,  265,   43,  257,
   45,  269,  270,  271,  123,  123,  274,  265,   40,  277,
   59,  269,  270,  271,   61,   60,  274,   62,  256,  277,
  123,  123,  123,   40,  123,  257,  257,  258,  123,   61,
  123,   59,  263,  265,  123,  257,    0,  269,  270,  271,
  271,   40,  274,  265,   61,  277,   -1,  269,  270,  271,
   45,  268,  274,  257,  258,  277,  125,   -1,   -1,  263,
  257,   45,   61,   40,   59,   -1,   -1,  271,  265,   -1,
   -1,   -1,  269,  270,  271,   59,   -1,  274,  125,   -1,
  277,   40,   -1,   -1,   61,  205,  206,  207,  208,  209,
  210,   -1,   40,  125,   -1,   -1,   -1,  257,   -1,   -1,
   -1,   -1,   61,   40,   -1,  265,   -1,  227,  125,  269,
  270,  271,   -1,   61,  274,  257,   -1,  277,   40,  256,
   -1,   -1,   -1,  265,   61,  114,  125,  269,  270,  271,
   -1,   -1,  274,   -1,   -1,  277,   40,   -1,  257,   61,
   -1,   -1,   -1,   -1,   -1,  256,  265,   -1,  125,  256,
  269,  270,  271,   -1,   -1,  274,  257,   61,  277,   40,
  149,  150,   -1,   -1,  265,   -1,  125,   -1,  269,  270,
  271,   -1,  256,  274,   40,   -1,  277,  125,   -1,   -1,
   61,   -1,  171,  172,   -1,   -1,  123,   -1,  257,   -1,
   -1,   -1,   40,   -1,   -1,   61,  265,   -1,   -1,   -1,
  269,  270,  271,  125,  193,  274,   -1,  256,  277,   -1,
  257,   40,   -1,   61,  259,  260,  261,  262,  265,   -1,
   -1,  125,  269,  270,  271,  257,   -1,  274,  256,   40,
  277,   -1,   61,  265,   -1,   -1,   -1,  269,  270,  271,
  257,   -1,  274,   -1,  125,  277,   -1,   -1,  265,   -1,
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
   40,   -1,   -1,   -1,   -1,   61,  265,   -1,   -1,  125,
  269,  270,  271,   -1,   -1,  274,  257,   -1,  277,  125,
   -1,   61,   40,   -1,  265,   -1,   -1,   -1,  269,  270,
  271,   -1,   40,  274,  125,   -1,  277,   -1,   -1,   -1,
   -1,   -1,   -1,   61,   -1,   -1,   -1,   -1,  257,   -1,
  125,   -1,   -1,   61,   -1,   -1,  265,   -1,   -1,   -1,
  269,  270,  271,   -1,   -1,  274,   -1,   -1,  277,  125,
  257,   -1,   -1,   -1,   -1,   41,   -1,   43,  265,   45,
   -1,   -1,  269,  270,  271,  125,   -1,  274,   -1,   -1,
  277,   -1,   -1,   59,   60,   -1,   62,   -1,   -1,   -1,
   -1,   -1,   41,   -1,   43,   -1,   45,  125,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,   -1,   -1,
   59,   60,   -1,   62,   41,  265,   43,   -1,   45,  269,
  270,  271,   -1,   -1,  274,   -1,   -1,  277,   -1,   -1,
   -1,  257,   59,   60,   -1,   62,   -1,   54,   55,  265,
   -1,  257,   -1,  269,  270,  271,   -1,  123,  274,  265,
   -1,  277,   -1,  269,  270,  271,  257,   -1,  274,   -1,
   -1,  277,   -1,   -1,  265,   -1,   -1,   -1,  269,  270,
  271,   -1,  257,  274,  123,   -1,  277,   -1,   -1,   -1,
  265,   -1,   -1,   -1,  269,  270,  271,   -1,   -1,  274,
   -1,  257,  277,  110,  111,   -1,  123,  114,   -1,  265,
   -1,   -1,   -1,  269,  270,  271,   -1,  257,  274,   -1,
   -1,  277,   -1,   -1,   -1,  265,   -1,   -1,   -1,  269,
  270,  271,   -1,   -1,  274,   -1,   -1,  277,   -1,  257,
   -1,   -1,  149,  150,   -1,   -1,   -1,  265,   -1,  257,
   -1,  269,  270,  271,   -1,   -1,  274,  265,   -1,  277,
   -1,  269,  270,  271,  171,  172,  274,   -1,   -1,  277,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  193,   -1,   -1,   -1,
  256,   -1,   -1,  259,  260,  261,  262,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  275,
  276,   -1,   -1,   -1,   -1,   -1,   -1,  256,   -1,   -1,
  259,  260,  261,  262,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  239,   -1,   -1,   -1,  275,  276,   -1,  256,
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
"sentencia : declaracion",
"sentencia : ejecucion",
"declaracion : tipo lista_de_variables ';'",
"declaracion : procedimiento ';'",
"declaracion : error_declaracion",
"error_declaracion : tipo lista_de_variables error",
"error_declaracion : procedimiento error",
"lista_de_variables : IDE",
"lista_de_variables : lista_de_variables ',' IDE",
"lista_de_variables : error_lista_de_variables",
"error_lista_de_variables : lista_de_variables IDE",
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
"ejecucion : control ';'",
"ejecucion : seleccion ';'",
"ejecucion : salida ';'",
"ejecucion : asignacion ';'",
"ejecucion : invocacion ';'",
"ejecucion : error_ejecucion",
"error_ejecucion : control error",
"error_ejecucion : seleccion error",
"error_ejecucion : salida error",
"error_ejecucion : asignacion error",
"error_ejecucion : invocacion error",
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
"expresion : termino",
"expresion : expresion '+' termino",
"expresion : expresion '-' termino",
"expresion : DOUBLE '(' expresion ')'",
"termino : termino '*' factor",
"termino : termino '/' factor",
"termino : factor",
"factor : CTE_DOUBLE",
"factor : CTE_UINT",
"factor : '-' factor",
"factor : IDE",
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
"error_invocacion : IDE '(' parametros",
"parametros : IDE ':' IDE",
"parametros : parametros ',' IDE ':' IDE",
"parametros : error_parametros",
"error_parametros : ':' IDE",
"error_parametros : IDE IDE",
"error_parametros : parametros IDE ':' IDE",
"error_parametros : IDE ':' error",
};

//#line 215 "gramatica.y"

private Lexico lexico;
public Parser(Lexico lexico)
{
  this.lexico= lexico;
}

public int yylex(){
   Token token = this.lexico.getToken();
   if(token != null ){
   	int val =token.getId();
   	yylval = new ParserVal(token.getLexema());
   	return val;
   }
   return 0;
}

public void yyerror(String s){
    System.out.println("Parser: " + s);
}

public void chequearFactorNegado(){
	String lexema = yylval.sval;
	int id = Main.tSimbolos.getId(lexema);
	if(id == Lexico.CTE_UINT){
		System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó una constante UINT fuera de rango");
		Main.tSimbolos.eliminarSimbolo(lexema);
	}
	else if (id == Lexico.CTE_DOUBLE) {
		double valor = -1*Double.parseDouble(lexema.replace('d','e'));
		if(( valor > 2.2250738585272014e-308 && valor < 1.7976931348623157e+308) || (valor > -1.7976931348623157e+308 && valor < -2.2250738585072014e-308) || (valor == 0.0))
                	Main.tSimbolos.modificarSimbolo(lexema, String.valueOf(valor));
                else {
                	System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó una constante DOUBLE fuera de rango");
	               	Main.tSimbolos.eliminarSimbolo(lexema);
	 	}
	}
}
//#line 709 "Parser.java"
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
//#line 21 "gramatica.y"
{System.out.println("Error sináctico: Linea " + Lexico.linea + " se detectó un bloque de sentencias mal declarado, falta '{'");}
break;
case 6:
//#line 22 "gramatica.y"
{System.out.println("[Parser | Linea " + Lexico.linea + "] se detectó un bloque de sentencias mal declarado, falta '}'");}
break;
case 11:
//#line 33 "gramatica.y"
{System.out.println("[Parser | Linea " + Lexico.linea + "] se detectó una declaracion de variables");}
break;
case 14:
//#line 38 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó una sentencia mal declarada, falta ';'");}
break;
case 15:
//#line 39 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó una sentencia mal declarada, falta ';'");}
break;
case 16:
//#line 42 "gramatica.y"
{System.out.println("[Parser | Linea " + Lexico.linea + "] se leyo el identificador -> " + val_peek(0).sval);}
break;
case 19:
//#line 47 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó una sentencia mal declarada, falta ',' entre los identificadores");}
break;
case 20:
//#line 49 "gramatica.y"
{System.out.println("[Parser | Linea " + Lexico.linea + "]se declaró un procedimiento");}
break;
case 22:
//#line 54 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un procedimiento mal declarado, falta el identificador");}
break;
case 23:
//#line 55 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un procedimiento mal declarado, falta '('");}
break;
case 24:
//#line 56 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un procedimiento mal declarado, falta lista de parametros");}
break;
case 25:
//#line 57 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un procedimiento mal declarado, falta ')'");}
break;
case 26:
//#line 58 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un procedimiento mal declarado, falta la palabra reservada NI ");}
break;
case 27:
//#line 59 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un procedimiento mal declarado, '=' despues de NI ");}
break;
case 28:
//#line 60 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un procedimiento mal declarado, falta la constante UINT ");}
break;
case 29:
//#line 61 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un procedimiento mal declarado, falta '{' que abre el bloque de sentecias ");}
break;
case 30:
//#line 62 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un procedimiento mal declarado, falta el bloque de sentencias");}
break;
case 31:
//#line 63 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un procedimiento mal declarado, falta '}' que cierra el bloque de sentencias");}
break;
case 36:
//#line 72 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectaron más parametros de los permitidos (3)");}
break;
case 37:
//#line 73 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectaron parametros mal declarados, falta ','");}
break;
case 38:
//#line 74 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectaron parametros mal declarados, falta ','");}
break;
case 39:
//#line 75 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectaron parametros mal declarados, falta ','");}
break;
case 40:
//#line 76 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectaron parametros mal declarados, falta ','");}
break;
case 43:
//#line 83 "gramatica.y"
{System.out.println("[Parser | Linea " + Lexico.linea + "] se leyó un tipo UINT");}
break;
case 44:
//#line 84 "gramatica.y"
{System.out.println("[Parser | Linea " + Lexico.linea + "] se leyó un tipo DOUBLE");}
break;
case 51:
//#line 95 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó una sentencia mal declarada, falta ';'");}
break;
case 52:
//#line 96 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó una sentencia mal declarada, falta ';'");}
break;
case 53:
//#line 97 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó una sentencia mal declarada, falta ';'");}
break;
case 54:
//#line 98 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó una sentencia mal declarada, falta ';'");}
break;
case 55:
//#line 99 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó una sentencia mal declarada, falta ';'");}
break;
case 56:
//#line 102 "gramatica.y"
{ System.out.println("[Parser | Linea " + Lexico.linea + "] se leyó una sentencia FOR");}
break;
case 58:
//#line 106 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta '('");}
break;
case 59:
//#line 107 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta el identificador ");}
break;
case 60:
//#line 108 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta '='");}
break;
case 61:
//#line 109 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta una constante UINT");}
break;
case 62:
//#line 110 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta ';'");}
break;
case 63:
//#line 111 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta la condición");}
break;
case 64:
//#line 112 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta ';'");}
break;
case 65:
//#line 113 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta la palabra UP o DOWN");}
break;
case 66:
//#line 114 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta una constante CTE_UINT");}
break;
case 67:
//#line 115 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta ')'");}
break;
case 68:
//#line 116 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta '{'");}
break;
case 69:
//#line 117 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta el bloque de sentencias");}
break;
case 70:
//#line 118 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta '}'");}
break;
case 73:
//#line 124 "gramatica.y"
{ System.out.println("[Parser | Linea " + Lexico.linea + "] se realizó una suma");}
break;
case 74:
//#line 125 "gramatica.y"
{ System.out.println("[Parser | Linea " + Lexico.linea + "] se realizó una resta");}
break;
case 75:
//#line 126 "gramatica.y"
{ System.out.println("[Parser | Linea " + Lexico.linea + "] se realizó una conversión");}
break;
case 76:
//#line 129 "gramatica.y"
{ System.out.println("[Parser | Linea " + Lexico.linea + "] se realizó una multiplicacion");}
break;
case 77:
//#line 130 "gramatica.y"
{ System.out.println("[Parser | Linea " + Lexico.linea + "] se realizó una division");}
break;
case 79:
//#line 134 "gramatica.y"
{System.out.println("[Parser | Linea " + Lexico.linea + "] se leyó la constante double -> " + val_peek(0).sval);}
break;
case 80:
//#line 135 "gramatica.y"
{System.out.println("[Parser | Linea " + Lexico.linea + "] se leyó la constante uint -> " + val_peek(0).sval);}
break;
case 81:
//#line 136 "gramatica.y"
{chequearFactorNegado();}
break;
case 82:
//#line 137 "gramatica.y"
{System.out.println("[Parser | Linea " + Lexico.linea + "] se leyó el identificador -> " + val_peek(0).sval);}
break;
case 91:
//#line 152 "gramatica.y"
{System.out.println("[Parser | Linea " + Lexico.linea + "] se leyó una sentencia IF");}
break;
case 92:
//#line 153 "gramatica.y"
{System.out.println("[Parser | Linea " + Lexico.linea + "] se leyó una sentencia IF con ELSE");}
break;
case 94:
//#line 157 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un IF mal declarado, falta '('");}
break;
case 95:
//#line 158 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un IF mal declarado, falta la condicion");}
break;
case 96:
//#line 159 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un IF mal declarado, falta ')'");}
break;
case 97:
//#line 160 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un IF mal declarado, falta '{'");}
break;
case 98:
//#line 161 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un IF mal declarado, falta el bloque de sentencias");}
break;
case 99:
//#line 162 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un IF mal declarado, falta '}'");}
break;
case 100:
//#line 163 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un IF mal declarado, falta el END_IF o ELSE");}
break;
case 101:
//#line 164 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un IF mal declarado, falta el ELSE");}
break;
case 102:
//#line 165 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un IF mal declarado, falta '{'");}
break;
case 103:
//#line 166 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un IF mal declarado, falta el bloque de sentencias del ELSE");}
break;
case 104:
//#line 167 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un IF mal declarado, falta '}'");}
break;
case 105:
//#line 168 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un IF mal declarado, falta el END_IF");}
break;
case 106:
//#line 173 "gramatica.y"
{System.out.println("[Parser | Linea " + Lexico.linea + "] se realizó una sentencia OUT");}
break;
case 108:
//#line 177 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un OUT mal declarado, falta '('");}
break;
case 109:
//#line 178 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + "  se detectó un OUT mal declarado, falta ')'");}
break;
case 110:
//#line 179 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + "  se detectó un OUT mal declarado, faltan '(' y ')'");}
break;
case 111:
//#line 180 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un OUT mal declarado, entre los paréntesis debe ir una cadena");}
break;
case 112:
//#line 181 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un OUT mal declarado, falta la cadena entre los parentésis en el OUT");}
break;
case 113:
//#line 184 "gramatica.y"
{System.out.println("[Parser | Linea " + Lexico.linea + "] se realizó una asignación");}
break;
case 115:
//#line 188 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " falta '=' en la asignación");}
break;
case 116:
//#line 189 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " falta el identificador del lado izquierdo de la asignación");}
break;
case 117:
//#line 190 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " falta una expresión aritmética del lado derecho de la asignación");}
break;
case 118:
//#line 194 "gramatica.y"
{System.out.println("[Parser | Linea " + Lexico.linea + "] se realizó una invocacion a un procedimiento");}
break;
case 120:
//#line 198 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó una invocación mal declarada, falta el identificador");}
break;
case 121:
//#line 199 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó una invocación mal declarada, falta el '('");}
break;
case 122:
//#line 200 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó una invocación mal declarada, faltan los parámetros");}
break;
case 123:
//#line 201 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó una invocación mal declarada, falta el ')'");}
break;
case 127:
//#line 209 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectaron parámetros mal declarados, falta el identificador de la izquierda");}
break;
case 128:
//#line 210 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectaron parámetros mal declarados, falta ':' entre los identificadores");}
break;
case 129:
//#line 211 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectaron parámetros mal declarados, falta la ',' que separa los identificadores");}
break;
case 130:
//#line 212 "gramatica.y"
{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectaron parámetros mal declarados, falta el identificador de la derecha");}
break;
//#line 1202 "Parser.java"
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
