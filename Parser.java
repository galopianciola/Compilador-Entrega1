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
import main.*
//#line 19 "Parser.java"




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
public final static short FUNC=270;
public final static short RETURN=271;
public final static short UINT=272;
public final static short DOUBLE=273;
public final static short NI=274;
public final static short FOR=275;
public final static short REF=276;
public final static short UP=277;
public final static short DOWN=278;
public final static short PROC=279;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    3,    3,    2,    2,    4,    4,    7,
    7,    8,    9,    9,    9,   10,   10,    6,    6,    5,
    5,    5,    5,    5,   11,   16,   18,   18,   18,   18,
   20,   20,   20,   21,   21,   21,   22,   22,   23,   19,
   19,   19,   19,   19,   19,   17,   17,   12,   12,   13,
   14,   15,   24,   24,
};
final static short yylen[] = {                            2,
    1,    1,    3,    1,    2,    2,    2,    2,    1,    3,
    1,   11,    1,    3,    5,    2,    3,    1,    1,    1,
    1,    1,    1,    1,   14,    3,    3,    3,    1,    4,
    3,    3,    1,    1,    1,    1,    1,    1,    2,    1,
    1,    1,    1,    1,    1,    1,    1,    8,   12,    4,
    3,    4,    3,    5,
};
final static short yydefred[] = {                         0,
    0,    0,    0,   18,   19,    0,    0,    0,    0,    1,
    2,    0,    0,    0,    9,   20,   21,   22,   23,   24,
    0,    0,    0,    0,    0,    0,    4,    0,    6,    7,
   11,    0,    0,    0,   36,   38,   37,    0,    0,    0,
    0,   33,   34,   35,    0,    0,    0,    0,    0,    3,
    5,    0,    0,    0,   52,    0,   39,    0,    0,    0,
    0,    0,   43,   44,   42,   45,   40,   41,    0,   50,
    0,    0,    0,    0,    0,   10,   53,    0,    0,    0,
    0,   31,   32,    0,    0,    0,    0,   16,    0,    0,
    0,   30,    0,    0,   17,    0,    0,   54,    0,    0,
    0,    0,    0,   48,    0,    0,   15,    0,   46,   47,
    0,    0,    0,    0,    0,    0,    0,   12,   49,    0,
    0,   25,
};
final static short yydgoto[] = {                          9,
   10,   27,   28,   12,   13,   14,   32,   15,   74,   75,
   16,   17,   18,   19,   20,   45,  111,   46,   69,   41,
   42,   43,   44,   34,
};
final static short yysindex[] = {                      -121,
  -24,  -36,  -28,    0,    0,  -23, -221, -199,    0,    0,
    0,   -4,   13, -192,    0,    0,    0,    0,    0,    0,
 -182,  -45,  -45, -185, -187,   48,    0, -116,    0,    0,
    0,   45,   32,   12,    0,    0,    0,   51, -171,    6,
   -3,    0,    0,    0,   52,  -22,   53,   34, -209,    0,
    0, -161, -160, -159,    0,  -45,    0,  -14,  -14,  -14,
  -14,  -21,    0,    0,    0,    0,    0,    0,  -45,    0,
 -157, -204, -158,   62,   60,    0,    0,   47,    2,   -3,
   -3,    0,    0, -199,    6,   49, -151,    0, -167, -209,
 -148,    0,  -97,  -45,    0,   50,   66,    0, -190,   54,
 -146, -209,   -9,    0, -196,   -7,    0, -199,    0,    0,
 -143, -199,  -92,   76,  -73, -150,   -2,    0,    0, -199,
  -68,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   64,    0,    0,    0,    0,    0,    0,    0,   65,
  -40,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   78,    0,    0,    0,    0,  -35,
  -30,    0,    0,    0,    1,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   84,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,
};
final static short yygindex[] = {                         0,
    0,    7,  -58,    0,    0,  -31,    0,    0,    0,  -56,
    0,    0,    0,    0,    0,   33,    0,   -8,    0,   25,
   26,    0,    0,    0,
};
final static int YYTABLESIZE=249;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         39,
   29,    8,   29,   23,   29,   27,   11,   27,   50,   27,
   28,   24,   28,   40,   28,   21,   25,   73,   29,   29,
   58,   29,   59,   27,   27,   93,   27,   99,   28,   28,
   39,   28,  116,   97,   51,   26,   22,   67,   60,   68,
   87,   26,   92,   61,   58,  107,   59,   79,   58,  113,
   59,  118,   55,  115,   29,   54,  122,    1,   73,   26,
   85,  121,    4,    5,   31,    2,   72,    4,    5,    3,
   73,   30,    4,    5,   33,    6,  103,  104,   47,    7,
  109,  110,   80,   81,   48,   82,   83,   49,   52,   53,
   56,   57,   62,   70,   71,   76,   77,   78,   88,   51,
   86,   84,   89,   90,   91,   95,   96,   94,   98,  102,
  101,  106,  105,  108,  114,  112,  117,  119,   13,   51,
  120,   51,    8,   51,   14,    0,  100,   51,    0,    0,
    0,    0,    0,    0,    0,    1,    0,    0,    0,    0,
    1,    0,    0,    2,    0,    0,    0,    3,    2,    0,
    4,    5,    3,    6,    0,    4,    5,    7,    6,    1,
    0,    0,    7,    0,    1,    0,    0,    2,    0,    0,
    0,    3,    2,    0,    4,    5,    3,    6,    0,    4,
    5,    7,    6,    1,    0,    0,    7,    0,    1,    0,
    0,    2,    0,    0,    0,    3,    2,    0,    4,    5,
    3,    6,    0,    4,    5,    7,    6,    0,    0,    0,
    7,   35,   36,    0,    0,    0,    0,   37,   29,   29,
   29,   29,    0,   27,   27,   27,   27,   38,   28,   28,
   28,   28,    0,    0,    0,    0,   63,   64,   65,   66,
    0,    0,   35,   36,    0,    0,    0,    0,   37,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         45,
   41,  123,   43,   40,   45,   41,    0,   43,  125,   45,
   41,   40,   43,   22,   45,   40,   40,   49,   59,   60,
   43,   62,   45,   59,   60,   84,   62,  125,   59,   60,
   45,   62,  125,   90,   28,  257,   61,   60,   42,   62,
   72,   41,   41,   47,   43,  102,   45,   56,   43,  108,
   45,  125,   41,  112,   59,   44,  125,  257,   90,   59,
   69,  120,  272,  273,  257,  265,  276,  272,  273,  269,
  102,   59,  272,  273,  257,  275,  267,  268,  264,  279,
  277,  278,   58,   59,  272,   60,   61,   40,   44,   58,
   40,  263,   41,   41,   61,  257,  257,  257,  257,   93,
  258,  123,   41,   44,   58,  257,  274,   59,  257,   44,
   61,  258,   59,  123,  258,  123,   41,  268,   41,  113,
  123,  115,   59,   59,   41,   -1,   94,  121,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  257,   -1,   -1,   -1,   -1,
  257,   -1,   -1,  265,   -1,   -1,   -1,  269,  265,   -1,
  272,  273,  269,  275,   -1,  272,  273,  279,  275,  257,
   -1,   -1,  279,   -1,  257,   -1,   -1,  265,   -1,   -1,
   -1,  269,  265,   -1,  272,  273,  269,  275,   -1,  272,
  273,  279,  275,  257,   -1,   -1,  279,   -1,  257,   -1,
   -1,  265,   -1,   -1,   -1,  269,  265,   -1,  272,  273,
  269,  275,   -1,  272,  273,  279,  275,   -1,   -1,   -1,
  279,  257,  258,   -1,   -1,   -1,   -1,  263,  259,  260,
  261,  262,   -1,  259,  260,  261,  262,  273,  259,  260,
  261,  262,   -1,   -1,   -1,   -1,  259,  260,  261,  262,
   -1,   -1,  257,  258,   -1,   -1,   -1,   -1,  263,
};
}
final static short YYFINAL=9;
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
"OUT","FUNC","RETURN","UINT","DOUBLE","NI","FOR","REF","UP","DOWN","PROC",
};
final static String yyrule[] = {
"$accept : programa",
"programa : bloque",
"bloque : sentencia",
"bloque : '{' bloque_sentencias '}'",
"bloque_sentencias : sentencia",
"bloque_sentencias : bloque_sentencias sentencia",
"sentencia : declaracion ';'",
"sentencia : ejecucion ';'",
"declaracion : tipo lista_de_variables",
"declaracion : procedimiento",
"lista_de_variables : lista_de_variables ',' IDE",
"lista_de_variables : IDE",
"procedimiento : PROC IDE '(' lista_de_parametros ')' NI '=' CTE_UINT '{' bloque_sentencias '}'",
"lista_de_parametros : param",
"lista_de_parametros : param ',' param",
"lista_de_parametros : param ',' param ',' param",
"param : tipo IDE",
"param : REF tipo IDE",
"tipo : UINT",
"tipo : DOUBLE",
"ejecucion : control",
"ejecucion : seleccion",
"ejecucion : salida",
"ejecucion : asignacion",
"ejecucion : invocacion",
"control : FOR '(' UINT '=' CTE_UINT ';' condicion ';' inc_decr CTE_UINT ')' '{' bloque_sentencias '}'",
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
"salida : OUT '(' CADENA ')'",
"asignacion : IDE '=' expresion",
"invocacion : IDE '(' parametros ')'",
"parametros : IDE ':' IDE",
"parametros : parametros ',' IDE ':' IDE",
};

//#line 117 "calc.y"

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
//#line 348 "Parser.java"
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
