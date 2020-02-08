#include <stdbool.h>
#include <stdio.h>
#define nop() \
  { ; }

/********************* Declarations ****************/
int result;
int b = 0;
int a = 0;

/********************* Functions declaration *******/

int power(int x, int y);

/********************* Functions definition  *******/
int main() {
  scanf("%d", &a);
  scanf("%d", &b);
  result = power(a, b);
  printf("%s", "risultato: ");
  printf("%d", result);
}

int power(int x, int y) {
  {
    int toReturn = 1;
    {
      int i;
      for (i = 0; i < y; i++) {
        toReturn = toReturn * x;
      }
    }
    return toReturn;
  }
}
