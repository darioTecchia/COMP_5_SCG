#include <stdbool.h>
#include <stdio.h>
#define nop() ;
#define count(x) (sizeof(x) / sizeof((x)[0]))

/********************* Declarations ****************/
int result;
int array[50] = {};

/********************* Functions declaration *******/

int conta(int a[]);

/********************* Functions definition  *******/
int main() {
  result = conta(array);
  printf("%s", "risultato: ");
  printf("%d", result);
}

int conta(int a[]) { return count(a); }
