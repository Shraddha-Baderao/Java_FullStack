#include <stdio.h>

int main() {
    int n = 12345;   
    int first, last, sum;
    
    last = n % 10;  
    
    while(n >= 10) {
        n = n / 10;
    }
    first = n;
    
    sum = first + last;
    
    printf("Sum of first and last digit = %d", sum);
    
    return 0;
}
