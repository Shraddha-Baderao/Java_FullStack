#include <stdio.h>

void armstrongRangeParam(int n);
void primeRangeParam(int n);
void perfectRangeParam(int n);
void strongRangeParam(int n);

int main() {
    int n;
    printf("Enter n for Armstrong numbers: ");
    scanf("%d",&n);
    armstrongRangeParam(n);

    printf("Enter n for Prime numbers: ");
    scanf("%d",&n);
    primeRangeParam(n);

    printf("Enter n for Perfect numbers: ");
    scanf("%d",&n);
    perfectRangeParam(n);

    printf("Enter n for Strong numbers: ");
    scanf("%d",&n);
    strongRangeParam(n);

    return 0;
}

void armstrongRangeParam(int n) {
    int i,temp,sum,rem;
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

void primeRangeParam(int n) {
    int i,j,flag;
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

void perfectRangeParam(int n) {
    int i,j,sum;
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

void strongRangeParam(int n) {
    int i,temp,rem,sum,fact,j;
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
