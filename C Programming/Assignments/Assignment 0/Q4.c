#include <stdio.h>

void main() {
    
    int a = 10;
    int b = 20;
    int temp;
    
    temp = a;
    a = b;
    b = temp;
    
    printf("The values of a and b after swaping are: \n""a: %d \nb: %d", a, b);
    
}
