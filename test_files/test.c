#include <stdio.h>
#include <string.h>

#define nop() ;
#define count(x) (sizeof(x) / sizeof((x)[0]))

typedef int bool;
#define true 1
#define false 0

/********************* Declarations ****************/
int array[50] = {};
int result;

/********************* Functions declaration *******/

int somma(int a[], int b[]);
void executor(int (*ptr)(int[], int[]), int a[], int b[]);

/********************* Functions definition  *******/
int main() {
  executor(somma, array, array);
  nop();
}

int somma(int a[], int b[]) { return count(a); }

void executor(int (*ptr)(int[], int[]), int a[], int b[]) {
  printf("%d", ptr(a, a));
}
