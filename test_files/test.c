#include <stdio.h>
#include <string.h>

#define nop() ;
#define count(x) (sizeof(x) / sizeof((x)[0]))

typedef int bool;
#define true 1
#define false 0

/********************* Declarations ****************/
bool a = true;
bool b = false;

/********************* Functions declaration *******/

/********************* Functions definition  *******/
int main() {
  {
    bool a = true;
    bool b = false;
    nop();
  }
  printf("%s", "risultato: ");
  printf("%d", a > b);
}
