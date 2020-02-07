#include <stdio.h>
#include <stdbool.h>
#define nop(){;}

/********************* Declarations ****************/
int result;
int b = 0;
int a = 0;

/********************* Functions declaration *******/

int addInc(int x, int y);

/********************* Functions definition  *******/
int main(){
scanf("%d", &a);
scanf("%d", &b);
result = addInc(a, b);
printf("%s", "la somma risulta: ");
printf("%d", result);
}

int addInc(int x, int y){
return x + y;
}
