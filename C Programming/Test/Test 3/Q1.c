#include <stdio.h>

void main() {
    int s = 10, e = 25, i;
    
    printf("Even numbers: ");
    for (i = s; i <= e; i++) {
        if (i % 2 == 0)
            printf("%d ", i);
    }

    printf("\nOdd numbers: ");
    for (i = s; i <= e; i++) {
        if (i % 2 != 0)
            printf("%d ", i);
    }
}
