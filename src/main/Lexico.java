package main;

public class Lexico {
    private int transiciones[][] = new int[19][20];
    private int acciones[][] = new int[19][20];

    public Lexico(){
        cargarMatrizTrancisiones();
        cargarMatrizAcciones();
    }

    private void cargarMatrizTrancisiones(){

       transiciones ={
                //L  l  d  .  %  <  >  =  "  !  +  -  _  u  i bt  d  o  nl $
                //0  1  2  3  4  5  6  7  8  9 10  11 12 13 14 15 16 17 18 19
                { 1, 2, 3, 6,11,13,14,15,17,16, F, F,-1, 2, 2, 0, 2, F, 0, F},//0
                { 1, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F},//1
                { F, 2, 2, F, F, F, F, F, F, F, F, F, 2, F, F, F, F, F, F, F},//2
                {-1,-1, 3, 7,-1,-1,-1,-1,-1,-1,-1,-1, 4,-1,-1,-1,-1,-1,-1, F},//3
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 5,-1,-1,-1,-1,-1, F},//4
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, F,-1,-1,-1,-1, F},//5
                {-1,-1, 7,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, F},//6
                { F, F, 7, F, F, F, F, F, F, F, F, F, F, F, F, F, 8, F, F, F},//7
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 9, 9,-1,-1,-1,-1,-1,-1,-1, F},//8
                {-1,-1,10,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, F},//9
                { F, F,10, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F},//10
                { F, F, F, F,12, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F},//11
                {12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12, 0, F},//12
                { F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F},//13
                { 1, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F},//14
                { F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F},//15
                {-1,-1,-1,-1,-1,-1,-1, F,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, F},//16
                {17,17,17,17,17,17,17,17, F,17,17,18,17,17,17,17,17,17,-1, F},//17
                {17,17,17,17,17,17,17,17,17,17,17,18,17,17,17,17,17,17,17, F},//18
        };

    }

    private void cargarMatrizAcciones(){
        acciones ={
                // L    l    d    .    %    <    >    =    "    !    +    -    _    u    i   bl   'd'  <>   /n
                // 0    1    2    3    4    5    6    7    8    9   10   11   12   13   14   15   16   17   18
                { as1, as1, as1, as1,null,null,null,null, as1,null, as7, as7,err1, as1, as1,null, as1, as7,null},//0
                { as2, as3, as3, as3, as3, as3, as3, as3, as3, as3, as3, as3, as3, as3, as3, as3, as3, as3, as3},//1
                { as4, as2, as2, as4, as4, as4, as4, as4, as4, as4, as4, as4, as2, as4, as4, as4, as4, as4, as4},//2
                {err2,err2, as2, as2,err2,err2,err2,err2,err2,err2,err2,err2,null,err2,err2,err2,err2,err2,err2},//3
                {err3,err3,err3,err3,err3,err3,err3,err3,err3,err3,err3,err3,err3,null,err3,err3,err3,err3,err3},//4
                {err4,err4,err4,err4,err4,err4,err4,err4,err4,err4,err4,err4,err4,err4, as5,err4,err4,err4,err4},//5
                {err5,err5, as2,err5,err5,err5,err5,err5,err5,err5,err5,err5,err5,err5,err5,err5,err5,err5,err5},//6
                { as6, as6, as2, as6, as6, as6, as6, as6, as6, as6, as6, as6, as6, as6, as6, as6, as2, as6, as6},//7
                {err6,err6,err6,err6,err6,err6,err6,err6,err6,err6, as2, as2,err6,err6,err6,err6,err6,err6,err6},//8
                {err5,err5, as2,err5,err5,err5,err5,err5,err5,err5,err5,err5,err5,err5,err5,err5,err5,err5,err5},//9
                { as6, as6, as2, as6, as6, as6, as6, as6, as6, as6, as6, as6, as6, as6, as6, as6, as6, as6, as6},//10
                { as8, as8, as8, as8,null, as8, as8, as8, as8, as8, as8, as8, as8, as8, as8, as8, as8, as8, as8},//11
                {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},//12
                {as10,as10,as10,as10,as10,as10,as10, as9,as10,as10,as10,as10,as10,as10,as10,as10,as10,as10,as10},//13
                {as12,as12,as12,as12,as12,as12,as12,as11,as12,as12,as12,as12,as12,as12,as12,as12,as12,as12,as12},//14
                {as14,as14,as14,as14,as14,as14,as14,as13,as14,as14,as14,as14,as14,as14,as14,as14,as14,as14,as14},//15
                {err7,err7,err7,err7,err7,err7,err7,as15,err7,err7,err7,err7,err7,err7,err7,err7,err7,err7,err7},//16
                { as2, as2, as2, as2, as2, as2, as2, as2,as16, as2, as2, as2, as2, as2, as2, as2, as2, as2,err8},//17
                { as2, as2, as2, as2, as2, as2, as2, as2, as2, as2, as2, as2, as2, as2, as2, as2, as2, as2, as2} //18
        };
    }

}
