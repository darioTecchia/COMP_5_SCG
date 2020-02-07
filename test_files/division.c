#include <stdio.h>
#include <stdbool.h>
#define nop(){;}

/********************* Declarations ****************/
float result;
int b = 0;
int a = 0;

/********************* Functions declaration *******/

float division(int x, int y);

/********************* Functions definition  *******/
int main(){
scanf("%d", &a);
scanf("%d", &b);
result = division(a, b);
printf("%s", "la divisione risulta: ");
printf("%f", result);
}

float division(int x, int y){
return x / y;
}
