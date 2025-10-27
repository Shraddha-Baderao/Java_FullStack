#include <stdio.h>
#include <math.h>

void printNumbers();
void printTable();
void sumInRange();
void primeCheck();
void armstrongCheck();
void perfectCheck();
void factorialCheck();
void strongCheck();
void palindromeCheck();
void sumFirstLast();

int main() {
    printNumbers();
    printTable();
    sumInRange();
    primeCheck();
    armstrongCheck();
    perfectCheck();
    factorialCheck();
    strongCheck();
    palindromeCheck();
    sumFirstLast();
    return 0;
}

void printNumbers() {
    int i;
    for(i=1;i<=10;i++)
        printf("%d ",i);
    printf("\n");
}

void printTable() {
    int n,i;
    printf("Enter a number for table: ");
    scanf("%d",&n);
    for(i=1;i<=10;i++)
        printf("%d ", n*i);
    printf("\n");
}

void sumInRange() {
    int start,end,sum=0,i;
    printf("Enter start and end of range: ");
    scanf("%d %d",&start,&end);
    for(i=start;i<=end;i++)
        sum+=i;
    printf("Sum = %d\n",sum);
}

void primeCheck() {
    int n,i,flag=1;
    printf("Enter number to check prime: ");
    scanf("%d",&n);
    if(n<2)
        flag=0;
    for(i=2;i<=n/2;i++)
        if(n%i==0)
            flag=0;
    if(flag)
        printf("%d is Prime\n",n);
    else
        printf("%d is Not Prime\n",n);
}

void armstrongCheck() {
    int n,temp,sum=0,rem;
    printf("Enter number to check Armstrong: ");
    scanf("%d",&n);
    temp=n;
    while(temp!=0){
        rem=temp%10;
        sum+=rem*rem*rem;
        temp/=10;
    }
    if(sum==n)
        printf("%d is Armstrong\n",n);
    else
        printf("%d is Not Armstrong\n",n);
}

void perfectCheck() {
    int n,i,sum=0;
    printf("Enter number to check Perfect: ");
    scanf("%d",&n);
    for(i=1;i<n;i++)
        if(n%i==0)
            sum+=i;
    if(sum==n)
        printf("%d is Perfect\n",n);
    else
        printf("%d is Not Perfect\n",n);
}

void factorialCheck() {
    int n,i,fact=1;
    printf("Enter number to find factorial: ");
    scanf("%d",&n);
    for(i=1;i<=n;i++)
        fact*=i;
    printf("Factorial = %d\n",fact);
}

void strongCheck() {
    int n,temp,sum=0,rem,fact;
    printf("Enter number to check Strong: ");
    scanf("%d",&n);
    temp=n;
    while(temp!=0){
        rem=temp%10;
        fact=1;
        for(int i=1;i<=rem;i++)
            fact*=i;
        sum+=fact;
        temp/=10;
    }
    if(sum==n)
        printf("%d is Strong\n",n);
    else
        printf("%d is Not Strong\n",n);
}

void palindromeCheck() {
    int n,temp,rev=0,rem;
    printf("Enter number to check Palindrome: ");
    scanf("%d",&n);
    temp=n;
    while(temp!=0){
        rem=temp%10;
        rev=rev*10+rem;
        temp/=10;
    }
    if(rev==n)
        printf("%d is Palindrome\n",n);
    else
        printf("%d is Not Palindrome\n",n);
}

void sumFirstLast() {
    int n,first,last,temp;
    printf("Enter number to sum first and last digit: ");
    scanf("%d",&n);
    temp=n;
    last=temp%10;
    while(temp>=10)
        temp/=10;
    first=temp;
    printf("Sum of first and last digit = %d\n",first+last);
}
