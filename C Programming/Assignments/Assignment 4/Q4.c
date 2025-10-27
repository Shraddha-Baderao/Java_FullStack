#include <stdio.h>

int factorial(int x) {
    int fact = 1;
    for(int i = 1; i <= x; i++) {
        fact *= i;
    }
    return fact;
}

void main() {
    int n, i, temp, sum, digit;
    printf("Enter the range (1 to n): ");
    scanf("%d", &n);
    printf("Strong numbers between 1 and %d are:\n", n);

    for(i = 1; i <= n; i++) {
        temp = i;
        sum = 0;
        while(temp != 0) {
            digit = temp % 10;
            sum += factorial(digit);
            temp /= 10;
        }
        if(sum == i)
            printf("%d ", i);
    }
}
