#include <stdio.h>

void armstrongRange();
void primeRange();
void perfectRange();
void strongRange();

int main() {
    armstrongRange();
    primeRange();
    perfectRange();
    strongRange();
    return 0;
}

void armstrongRange() {
    int n, i, temp, sum, rem;
    printf("Enter n for Armstrong numbers: ");
    scanf("%d", &n);
    printf("Armstrong numbers from 1 to %d: ", n);
    for(i=1;i<=n;i++){
        temp=i;
        sum=0;
        while(temp!=0){
            rem=temp%10;
            sum+=rem*rem*rem;
            temp/=10;
        }
        if(sum==i)
            printf("%d ", i);
    }
    printf("\n");
}

void primeRange() {
    int n, i, j, flag;
    printf("Enter n for Prime numbers: ");
    scanf("%d", &n);
    printf("Prime numbers from 1 to %d: ", n);
    for(i=2;i<=n;i++){
        flag=1;
        for(j=2;j<=i/2;j++)
            if(i%j==0)
                flag=0;
        if(flag)
            printf("%d ", i);
    }
    printf("\n");
}

void perfectRange() {
    int n, i, j, sum;
    printf("Enter n for Perfect numbers: ");
    scanf("%d", &n);
    printf("Perfect numbers from 1 to %d: ", n);
    for(i=1;i<=n;i++){
        sum=0;
        for(j=1;j<i;j++)
            if(i%j==0)
                sum+=j;
        if(sum==i)
            printf("%d ", i);
    }
    printf("\n");
}

void strongRange() {
    int n, i, temp, rem, sum, fact, j;
    printf("Enter n for Strong numbers: ");
    scanf("%d", &n);
    printf("Strong numbers from 1 to %d: ", n);
    for(i=1;i<=n;i++){
        temp=i;
        sum=0;
        while(temp!=0){
            rem=temp%10;
            fact=1;
            for(j=1;j<=rem;j++)
                fact*=j;
            sum+=fact;
            temp/=10;
        }
        if(sum==i)
            printf("%d ", i);
    }
    printf("\n");
}
