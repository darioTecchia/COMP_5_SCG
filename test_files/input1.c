#include <stdio.h>
#include <stdbool.h>
#define nop(){;}

/********************* Declarations ****************/
float array[50] = { };
float buffer = 0.0;
float result = 0.0;

/********************* Functions declaration *******/
$functionsDeclarations$

/********************* Functions definition  *******/
 main(){
buffer = array[0];
array[0] = buffer;
scanf("%f", &buffer);
scanf("%f", &result);
result = addInc(result + 1, buffer);
printf("%s", "la somma risulta: ");
printf("%f", result);
}

float addInc(float x, float y){
x = x + 1;
{
int i;
for(i = 1; i < 10; i++){
printf("%d", i);
}
}
while(true){
nop();
}
if(true){
nop();
}
if(true){
nop();
} else {
nop();
}
{
int i = 1;
float pippo = 3.0;
pippo = 1.0;
}
{
char * pluto = "ciao";
float i = 2.0;
i = i + 1;
{
float paperino;
float i = 1.0;
y = y * i;
}
printf("%f", buffer);
x + y + i
}
}
