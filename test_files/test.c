#include <stdio.h>
#include <string.h>

#define nop() ;
#define count(x) (sizeof(x) / sizeof((x)[0]))

typedef int bool;
#define true 1
#define false 0

/********************* Declarations ****************/
int a = 1;
int b = 2;

/********************* Functions declaration *******/

int zozo(int a, int b);

/********************* Functions definition  *******/
int main() {
  printf("%s", "risultato: ");
  printf("%d", zozo(a, b));
}

int zozo(int a, int b) {
  return a + b;
  {
    char* a = "Ciao";
    return a;
  }
  return 2.0;
}
