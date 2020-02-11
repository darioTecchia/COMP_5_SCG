#include <stdio.h>
#include <string.h>

#define nop() ;
#define count(x) (sizeof(x) / sizeof((x)[0]))

typedef int bool;
#define true 1
#define false 0

/********************* Declarations ****************/
int result;

/********************* Functions declaration *******/

int somma(int a, int b);
int sott(int a, int b);
int executor(int (* % s)(int, int) ptr, int a, int b);

/********************* Functions definition  *******/
int main() {
  executor(sott, 1, 2);
  executor(somma, 1, 2);
  nop();
}

int somma(int a, int b) { return a + b; }

int sott(int a, int b) { return a - b; }

int executor(int (*ptr)(int, int), int a, int b) { printf("%d", ptr(a, b)); }
