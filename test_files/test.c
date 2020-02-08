#include <stdbool.h>
#include <stdio.h>
#define nop() \
  { ; }

/********************* Declarations ****************/
int result;
int array[50] = {};

/********************* Functions declaration *******/

int count(int a);

/********************* Functions definition  *******/
int main() {
  result = count(array);
  printf("%s", "risultato: ");
  printf("%d", result);
}

int count(int a) { return #TODO; }
