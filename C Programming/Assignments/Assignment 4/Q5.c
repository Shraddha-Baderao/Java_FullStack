#include <stdio.h>

void main() {
    int num, choice, i, flag, temp, rev, sum, digit;

    printf("Enter a number: ");
    scanf("%d", &num);

    printf("Menu:\n");
    printf("1. Check even or odd\n");
    printf("2. Check prime or not\n");
    printf("3. Check palindrome\n");
    printf("4. Check positive, negative or zero\n");
    printf("5. Reverse number\n");
    printf("6. Sum of digits\n");
    printf("Enter your choice: ");
    scanf("%d", &choice);

    if(choice == 1) {
        if(num % 2 == 0)
            printf("%d is even\n", num);
        else
            printf("%d is odd\n", num);
    }
    else if(choice == 2) {
        if(num < 2)
            printf("%d is not prime\n", num);
        else {
            flag = 0;
            for(i = 2; i <= num/2; i++) {
                if(num % i == 0) {
                    flag = 1;
                    break;
                }
            }
            if(flag == 0)
                printf("%d is prime\n", num);
            else
                printf("%d is not prime\n", num);
        }
    }
    else if(choice == 3) {
        temp = num;
        rev = 0;
        while(temp != 0) {
            rev = rev*10 + temp%10;
            temp /= 10;
        }
        if(rev == num)
            printf("%d is a palindrome\n", num);
        else
            printf("%d is not a palindrome\n", num);
    }
    else if(choice == 4) {
        if(num > 0)
            printf("%d is positive\n", num);
        else if(num < 0)
            printf("%d is negative\n", num);
        else
            printf("Number is zero\n");
    }
    else if(choice == 5) {
        temp = num;
        rev = 0;
        while(temp != 0) {
            rev = rev*10 + temp%10;
            temp /= 10;
        }
        printf("Reverse of %d is %d\n", num, rev);
    }
    else if(choice == 6) {
        temp = num;
        sum = 0;
        while(temp != 0) {
            sum += temp%10;
            temp /= 10;
        }
        printf("Sum of digits of %d is %d\n", num, sum);
    }
    else
        printf("Invalid choice\n");
}
