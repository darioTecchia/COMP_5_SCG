#include <stdbool.h>
#include <stdio.h>
#define nop() \
  { ; }

/********************* Declarations ****************/
int result;
int b = 0;
int a = 0;

/********************* Functions declaration *******/

int add(int x, int y);

/********************* Functions definition  *******/
int main() {
  scanf("%d", &a);
  scanf("%d", &b);
  result = add(a, b);
  printf("%s", "la somma risulta: ");
  printf("%d", result);
}

int add(int x, int y) { return x + y; }
