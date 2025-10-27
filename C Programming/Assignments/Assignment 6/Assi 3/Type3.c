#include <stdio.h>
#include <math.h>

int printNumbers();
int printTable();
int primeCheck();
int armstrongCheck();
int perfectCheck();
int factorialCheck();
int strongCheck();
int palindromeCheck();
int sumFirstLast();

int main() {
    printNumbers();
    printTable();
    primeCheck();
    armstrongCheck();
    perfectCheck();
    factorialCheck();
    strongCheck();
    palindromeCheck();
    sumFirstLast();
    return 0;
}

int printNumbers() {
    int i;
    for(i=1;i<=10;i++)
        printf("%d ",i);
    printf("\n");
    return 0;
}

int printTable() {
    int n,i;
    printf("Enter a number for table: ");
    scanf("%d",&n);
    for(i=1;i<=10;i++)
        printf("%d ", n*i);
    printf("\n");
    return 0;
}

int primeCheck() {
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
    return 0;
}

int armstrongCheck() {
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
    return 0;
}

int perfectCheck() {
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
    return 0;
}

int factorialCheck() {
    int n,i,fact=1;
    printf("Enter number to find factorial: ");
    scanf("%d",&n);
    for(i=1;i<=n;i++)
        fact*=i;
    printf("Factorial = %d\n",fact);
    return 0;
}

int strongCheck() {
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
    return 0;
}

int palindromeCheck() {
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
    return 0;
}

int sumFirstLast() {
    int n,first,last,temp;
    printf("Enter number to sum first and last digit: ");
    scanf("%d",&n);
    temp=n;
    last=temp%10;
    while(temp>=10)
        temp/=10;
    first=temp;
    printf("Sum of first and last digit = %d\n",first+last);
    return 0;
}
