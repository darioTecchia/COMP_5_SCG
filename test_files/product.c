#include <stdbool.h>
#include <stdio.h>
#define nop() \
  { ; }

/********************* Declarations ****************/
int result;
int b = 0;
int a = 0;

/********************* Functions declaration *******/

int mul(int x, int y);

/********************* Functions definition  *******/
int main() {
  scanf("%d", &a);
  scanf("%d", &b);
  result = mul(a, b);
  printf("%s", "la moltiplicazione risulta: ");
  printf("%d", result);
}

int mul(int x, int y) {
  {
    int toReturn = 0;
    {
      int i;
      for (i = 0; i < y; i++) {
        toReturn = toReturn + x;
      }
    }
    return toReturn;
  }
}
