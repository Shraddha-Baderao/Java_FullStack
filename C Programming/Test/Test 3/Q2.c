#include <stdio.h>

void main() {
    int s = 1, e = 5, i, sum = 0;
    
    for (i = s; i <= e; i++) {
        if (i % 2 != 0){
            sum = sum + i;
        }
    }
    printf("sum = %d", sum);
}