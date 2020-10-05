%{
package Parser;
import main.*;
%}

%token IDE CTE_UINT MAYOR_IGUAL MENOR_IGUAL IGUAL_IGUAL DISTINTO CTE_DOUBLE CADENA IF THEN ELSE END_IF OUT UINT DOUBLE
NI REF FOR UP DOWN PROC FUNC RETURN
%start programa

%%

programa: bloque
        ;

bloque : sentencia
       | '{'bloque_sentencias'}'
       | error_bloque
       ;


error_bloque : bloque_sentencias '}' {System.out.println("[Parser | Linea " + Lexico.linea + "] se detectó un bloque de sentencias mal declarado, falta '{'");}
      	     | '{' bloque_sentencias  {System.out.println("[Parser | Linea " + Lexico.linea + "] se detectó un bloque de sentencias mal declarado, falta '}'");}
             ;

bloque_sentencias  :  sentencia
                   |  bloque_sentencias sentencia
                   ;

sentencia  : declaracion ';'{System.out.println("[Parser | Linea " + Lexico.linea + "] se detectó una sentencia declarativa");}
           | ejecucion ';'{System.out.println("[Parser | Linea " + Lexico.linea + "] se detectó una sentencia de ejecución");}
           | error_sentencia
           ;

error_sentencia : declaracion error {System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó una sentencia mal declarada, falta ';'");}
           	| ejecucion error {System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó una sentencia mal declarada, falta ';'");}
           	;

declaracion  : tipo lista_de_variables{System.out.println("[Parser | Linea " + Lexico.linea + "] se detectó una declaracion");}
    	     | procedimiento
             ;

lista_de_variables : IDE {System.out.println("[Parser | Linea " + Lexico.linea + "] lei un ID");}
      		   | lista_de_variables ',' IDE
      		   //| error_lista_de_variables
                   ;

//error_lista_de_variables: lista_de_variables IDE {System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó una sentencia mal declarada, falta ',' entre los Id");}

procedimiento : PROC IDE'('lista_de_parametros')'NI'='CTE_UINT'{'bloque_sentencias'}'{System.out.println("[Parser | Linea " + Lexico.linea + "]se declaro una PROC");}
              | error_proc
              ;


error_proc: PROC    '('lista_de_parametros')'NI'='CTE_UINT'{'bloque_sentencias'}'{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un procedimiento mal declarada, falta IDE");}
	  | PROC IDE   lista_de_parametros')'NI'='CTE_UINT'{'bloque_sentencias'}'{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un procedimiento mal declarada, falta '('");}
	  | PROC IDE'('                   ')'NI'='CTE_UINT'{'bloque_sentencias'}'{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un procedimiento mal declarado, falta lista de parametros");}
	  | PROC IDE'('lista_de_parametros   NI'='CTE_UINT'{'bloque_sentencias'}'{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un procedimiento mal declarado, falta ')'");}
	  | PROC IDE'('lista_de_parametros')'  '='CTE_UINT'{'bloque_sentencias'}'{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un procedimiento mal declarado, falta NI ");}
	  | PROC IDE'('lista_de_parametros')'NI   CTE_UINT'{'bloque_sentencias'}'{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un procedimiento mal declarado, '=' despues de NI ");}
	  | PROC IDE'('lista_de_parametros')'NI'='        '{'bloque_sentencias'}'{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un procedimiento mal declarado, falta la CTE_UINT ");}
	  | PROC IDE'('lista_de_parametros')'NI'='CTE_UINT   bloque_sentencias'}'{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un procedimiento mal declarado, falta '{' que abre el bloque de sentecias ");}
	  | PROC IDE'('lista_de_parametros')'NI'='CTE_UINT'{'                 '}'{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó una sentencia mal declarada, falta el bloque de sentencias");}
	  | PROC IDE'('lista_de_parametros')'NI'='CTE_UINT'{'bloque_sentencias   {System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un procedimiento mal declarado, falta '}' que cierra el bloque de sentencias");}


lista_de_parametros : param
		    | param ',' param
		    | param ',' param ',' param
		    | error_lista_de_parametros
	            ;

error_lista_de_parametros : param ',' param ',' param ',' lista_de_parametros {System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectaron más parametros de los permitidos (3)");}
			  | param param {System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectaron parametros mal declarados, falta ','");}
			  | param param param {System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectaron parametros mal declarados, falta ','");}
			  | param ',' param param {System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectaron parametros mal declarados, falta ','");}
			  | param param ',' param {System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectaron parametros mal declarados, falta ','");}
			  ;

param : tipo IDE
      | REF tipo IDE
      ;

tipo : UINT {System.out.println("[Parser | Linea " + Lexico.linea + "] lei un tipo UINT");}
     | DOUBLE {System.out.println("[Parser | Linea " + Lexico.linea + "] lei un tipo DOUBLE");}
     ;

ejecucion : control
	  | seleccion
	  | salida
	  | asignacion
	  | invocacion
	  ;

control : FOR'('IDE'='CTE_UINT';'condicion';'inc_decr CTE_UINT')''{'bloque_sentencias'}'{ System.out.println("[Parser | Linea " + Lexico.linea + "] lei un FOR");}
	| error_for
	;

error_for: FOR   IDE'='CTE_UINT';'condicion';'inc_decr CTE_UINT')''{'bloque_sentencias'}'{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta '('");}
	 | FOR'('   '='CTE_UINT';'condicion';'inc_decr CTE_UINT')''{'bloque_sentencias'}'{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta id ");}
	 | FOR'('IDE   CTE_UINT';'condicion';'inc_decr CTE_UINT')''{'bloque_sentencias'}'{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta '='");}
	 | FOR'('IDE'='        ';'condicion';'inc_decr CTE_UINT')''{'bloque_sentencias'}'{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta 'CTE_UINT'");}
	 | FOR'('IDE'='CTE_UINT   condicion';'inc_decr CTE_UINT')''{'bloque_sentencias'}'{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta ';'");}
	 | FOR'('IDE'='CTE_UINT';'         ';'inc_decr CTE_UINT')''{'bloque_sentencias'}'{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta la condición");}
	 | FOR'('IDE'='CTE_UINT';'condicion   inc_decr CTE_UINT')''{'bloque_sentencias'}'{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta ';'");}
	 | FOR'('IDE'='CTE_UINT';'condicion';'         CTE_UINT')''{'bloque_sentencias'}'{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta la palabra UP o DOWN");}
	 | FOR'('IDE'='CTE_UINT';'condicion';'inc_decr         ')''{'bloque_sentencias'}'{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta 'CTE_UINT'");}
	 | FOR'('IDE'='CTE_UINT';'condicion';'inc_decr CTE_UINT   '{'bloque_sentencias'}'{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta ')'");}
	 | FOR'('IDE'='CTE_UINT';'condicion';'inc_decr CTE_UINT')'   bloque_sentencias'}'{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta '{'");}
	 | FOR'('IDE'='CTE_UINT';'condicion';'inc_decr CTE_UINT')''{'                 '}'{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta el bloque de sentencias");}
	 | FOR'('IDE'='CTE_UINT';'condicion';'inc_decr CTE_UINT')''{'bloque_sentencias   {System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un FOR mal declarado, falta '}'");}

condicion :  expresion comparador expresion
          ;

expresion : expresion '+' termino { System.out.println("[Parser | Linea " + Lexico.linea + "] se realizo una suma");}
	  | expresion '-' termino { System.out.println("[Parser | Linea " + Lexico.linea + "] se realizo una resta");}
	  | termino
	  | DOUBLE '(' expresion ')'{ System.out.println("[Parser | Linea " + Lexico.linea + "] se realizo una conversion");}
          ;

termino : termino '*' factor { System.out.println("[Parser | Linea " + Lexico.linea + "] se realizo una multiplicacion");}
	| termino '/' factor  { System.out.println("[Parser | Linea " + Lexico.linea + "] se realizo una division");}
	| factor
        ;

factor 	: cte
	| factor_negado
	| IDE {System.out.println("[Parser | Linea " + Lexico.linea + "] se leyó un identificador");}
        ;

cte : CTE_DOUBLE {System.out.println("[Parser | Linea " + Lexico.linea + "] se leyó una cte double");}
    | CTE_UINT {System.out.println("[Parser | Linea " + Lexico.linea + "] se leyó una cte uint");}
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

seleccion : IF '(' condicion ')' '{' bloque_sentencias '}' END_IF {System.out.println("[Parser | Linea " + Lexico.linea + "] se leyó un IF");}
	  | IF '(' condicion ')' '{' bloque_sentencias '}' ELSE '{' bloque_sentencias '}' END_IF {System.out.println("[Parser | Linea " + Lexico.linea + "] se leyó un IF con ELSE");}
	  | error_if
	  ;

error_if: IF  condicion    ')' '{' bloque_sentencias '}' END_IF {System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un IF mal declarado, falta '('");}
	| IF '('           ')' '{' bloque_sentencias '}' END_IF {System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un IF mal declarado, falta la condicion");}
	| IF '(' condicion     '{' bloque_sentencias '}' END_IF {System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un IF mal declarado, falta ')'");}
	| IF '(' condicion ')'     bloque_sentencias '}' END_IF {System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un IF mal declarado, falta '{'");}
	| IF '(' condicion ')' '{'                   '}' END_IF {System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un IF mal declarado, falta el bloque de sentencias");}
	| IF '(' condicion ')' '{' bloque_sentencias     END_IF {System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un IF mal declarado, falta '}'");}
	| IF '(' condicion ')' '{' bloque_sentencias '}'        {System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un IF mal declarado, falta el END_IF o ELSE");}
	| IF '(' condicion ')' '{' bloque_sentencias '}'      '{'bloque_sentencias '}' END_IF{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un IF mal declarado, falta el ELSE");}
	| IF '(' condicion ')' '{' bloque_sentencias '}' ELSE    bloque_sentencias '}' END_IF{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un IF mal declarado, falta '{'");}
	| IF '(' condicion ')' '{' bloque_sentencias '}' ELSE '{'                  '}' END_IF{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un IF mal declarado, falta el bloque de sentencias del ELSE");}
	| IF '(' condicion ')' '{' bloque_sentencias '}' ELSE '{'bloque_sentencias     END_IF{System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un IF mal declarado, falta '}'");}
	| IF '(' condicion ')' '{' bloque_sentencias '}' ELSE '{'bloque_sentencias '}'       {System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó un IF mal declarado, falta el END_IF");}
	;



salida : OUT'('CADENA')'{System.out.println("[Parser | Linea " + Lexico.linea + "] se realizó un OUT");}
       | error_salida
       ;

error_salida : OUT CADENA ')' {System.out.println("Error sintáctico: Linea " + Lexico.linea + " falta '(' en el OUT de cadena");}
	     | OUT '(' CADENA {System.out.println("Error sintáctico: Linea " + Lexico.linea + " falta ')' en el OUT de cadena");}
	     | OUT CADENA {System.out.println("Error sintáctico: Linea " + Lexico.linea + " faltan '(' y ')' en el OUT de cadena");}
	     | OUT '('error')' {System.out.println("Error sintáctico: Linea " + Lexico.linea + " entre '(' y ')' no hay una cadena");}
	     | OUT '(' ')' {System.out.println("Error sintáctico: Linea " + Lexico.linea + " falta cadena entre los parentesis en el OUT");}
	     ;

asignacion : IDE '=' expresion {System.out.println("[Parser | Linea " + Lexico.linea + "] se realizó una asignacion");}
	   | error_asignacion
	   ;

error_asignacion : IDE expresion {System.out.println("Error sintáctico: Linea " + Lexico.linea + " falta '=' en la asignacion");}
		 | '=' expresion {System.out.println("Error sintáctico: Linea " + Lexico.linea + " falta el ID en la asignacion");}
		 | IDE '=' {System.out.println("Error sintáctico: Linea " + Lexico.linea + " falta una expresión para ser asignada al ID");}
		 ;


invocacion : IDE '(' parametros ')' {System.out.println("[Parser | Linea " + Lexico.linea + "] se realizó una invocacion a una funcion");}
	   | error_invocacion
	   ;

error_invocacion: '(' parametros ')' {System.out.println("Error sintáctico: Linea " + Lexico.linea + " se detectó una invocacion mal declarada, falta el identificador");}
		| IDE parametros ')' {System.out.println("Error sint�ctico: Linea " + Lexico.linea + " se detect� una invocacion mal declarada, falta el '('");}
		| IDE '(' ')' {System.out.println("Error sint�ctico: Linea " + Lexico.linea + " se detect� una invocacion mal declarada, faltan los parametros");}
		|'('parametros{System.out.println("Error sint�ctico: Linea " + Lexico.linea + " se detect� una invocacion mal declarada, falta el ')'");}
		;

parametros : IDE ':' IDE
	   | parametros ',' IDE ':' IDE
	   | error_parametros
	   ;

error_parametros : ':' IDE {System.out.println("Error sint�ctico: Linea " + Lexico.linea + " se detectaron parametros mal declarados, falta el id de la izquierda");}
		 | IDE IDE {System.out.println("Error sint�ctico: Linea " + Lexico.linea + " se detectaron parametros mal declarados, falta ':' entre los id");}
		 | parametros IDE ':' IDE {System.out.println("Error sint�ctico: Linea " + Lexico.linea + " se detectaron parametros mal declarados, falta ',' para separar los id");}
		 | IDE ':' error {System.out.println("Error sint�ctico: Linea " + Lexico.linea + " se detectaron parametros mal declarados, falta el id de la derecha");}
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