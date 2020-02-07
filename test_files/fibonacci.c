#include <stdio.h>
#include <stdbool.h>
#define nop(){;}

/********************* Declarations ****************/
int result;
int a = 0;

/********************* Functions declaration *******/

int fibonacci(int x);

/********************* Functions definition  *******/
int main(){
scanf("%d", &a);
result = fibonacci(a);
printf("%s", "risultato: ");
printf("%d", result);
}

int fibonacci(int x){
if(x < 0){
return -1;
}
if(x == 0){
return 0;
}
if(x == 1){
return 1;
}
return fibonacci(x - 1) + fibonacci(x - 2);
}
