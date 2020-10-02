%{
import main.*
%}

%token IDE CTE_UINT MAYOR_IGUAL MENOR_IGUAL IGUAL_IGUAL DISTINTO CTE_DOUBLE CADENA IF THEN ELSE END_IF OUT FUNC RETURN UINT DOUBLE
NI FOR REF FOR UP DOWN PROC
%start programa

%%

programa: bloque
        ;

bloque : sentencia
       | '{'bloque_sentencias'}'
       ;

bloque_sentencias  :  sentencia
                   |  bloque_sentencias sentencia
                   ;

sentencia  : declaracion ';'
           | ejecucion   ';'
           ;

declaracion  : tipo lista_de_variables
    	     | procedimiento
             ;


lista_de_variables : lista_de_variables ',' IDE
		   | IDE
	           ;

procedimiento : PROC IDE '(' lista_de_parametros ')' NI '=' CTE_UINT '{' bloque_sentencias '}'
              ;

lista_de_parametros : param
		    | param ',' param
		    | param ',' param ',' param
	            ;

param : tipo IDE
      | REF tipo IDE
      ;

tipo : UINT
     | DOUBLE
     ;

ejecucion : control
	  | seleccion
	  | salida
	  | asignacion
	  | invocacion
	  ;

control : FOR '(' UINT '=' CTE_UINT ';' condicion ';' inc_decr CTE_UINT ')' '{' bloque_sentencias '}'
	;

condicion :  expresion comparador expresion
          ;

expresion : expresion '+' termino
	  | expresion '-' termino
	  | termino
	  | DOUBLE '(' expresion ')'
          ;

termino : termino '*' factor
	| termino '/' factor
	| factor
        ;

factor 	: cte
	| factor_negado
	| IDE
        ;

cte : CTE_DOUBLE
    | CTE_UINT
    ;

factor_negado : '-' CTE_DOUBLE
              ;

comparador : '<'
	   | '>'
	   | IGUAL_IGUAL
           | MAYOR_IGUAL
	   | MENOR_IGUAL
	   | DISTINTO
           ;

inc_decr : UP
	 | DOWN
	 ;

seleccion : IF '(' condicion ')' '{' bloque_sentencias '}' END_IF
	  | IF '(' condicion ')' '{' bloque_sentencias '}' ELSE '{' bloque_sentencias '}' END_IF
	  ;

salida : OUT '(' CADENA ')'
       ;

asignacion : IDE '=' expresion
           ;

invocacion : IDE '(' parametros ')'
	   ;

parametros : IDE ':' IDE
	   | parametros ',' IDE ':' IDE
	   ;

%%

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