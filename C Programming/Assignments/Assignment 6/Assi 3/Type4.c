#include <stdio.h>
#include <math.h>

int printNumbers(int n);
int printTable(int n);
int primeCheck(int n);
int armstrongCheck(int n);
int perfectCheck(int n);
int factorialCheck(int n);
int strongCheck(int n);
int palindromeCheck(int n);
int sumFirstLast(int n);

int main() {
    int num;
    printNumbers(10);
    
    printf("Enter number for table: ");
    scanf("%d",&num);
    printTable(num);

    printf("Enter number to check prime: ");
    scanf("%d",&num);
    primeCheck(num);

    printf("Enter number to check Armstrong: ");
    scanf("%d",&num);
    armstrongCheck(num);

    printf("Enter number to check Perfect: ");
    scanf("%d",&num);
    perfectCheck(num);

    printf("Enter number to find factorial: ");
    scanf("%d",&num);
    factorialCheck(num);

    printf("Enter number to check Strong: ");
    scanf("%d",&num);
    strongCheck(num);

    printf("Enter number to check Palindrome: ");
    scanf("%d",&num);
    palindromeCheck(num);

    printf("Enter number to sum first and last digit: ");
    scanf("%d",&num);
    sumFirstLast(num);

    return 0;
}

int printNumbers(int n){
    for(int i=1;i<=n;i++)
        printf("%d ",i);
    printf("\n");
    return 0;
}

int printTable(int n){
    for(int i=1;i<=10;i++)
        printf("%d ",n*i);
    printf("\n");
    return 0;
}

int primeCheck(int n){
    int flag=1;
    if(n<2) flag=0;
    for(int i=2;i<=n/2;i++)
        if(n%i==0) flag=0;
    if(flag) printf("%d is Prime\n",n);
    else printf("%d is Not Prime\n",n);
    return 0;
}

int armstrongCheck(int n){
    int temp=n,sum=0,rem;
    temp=n;
    while(temp!=0){
        rem=temp%10;
        sum+=rem*rem*rem;
        temp/=10;
    }
    if(sum==n) printf("%d is Armstrong\n",n);
    else printf("%d is Not Armstrong\n",n);
    return 0;
}

int perfectCheck(int n){
    int sum=0;
    for(int i=1;i<n;i++)
        if(n%i==0) sum+=i;
    if(sum==n) printf("%d is Perfect\n",n);
    else printf("%d is Not Perfect\n",n);
    return 0;
}

int factorialCheck(int n){
    int fact=1;
    for(int i=1;i<=n;i++)
        fact*=i;
    printf("Factorial = %d\n",fact);
    return 0;
}

int strongCheck(int n){
    int temp=n,sum=0,rem,fact;
    temp=n;
    while(temp!=0){
        rem=temp%10;
        fact=1;
        for(int i=1;i<=rem;i++) fact*=i;
        sum+=fact;
        temp/=10;
    }
    if(sum==n) printf("%d is Strong\n",n);
    else printf("%d is Not Strong\n",n);
    return 0;
}

int palindromeCheck(int n){
    int temp=n,rev=0,rem;
    temp=n;
    while(temp!=0){
        rem=temp%10;
        rev=rev*10+rem;
        temp/=10;
    }
    if(rev==n) printf("%d is Palindrome\n",n);
    else printf("%d is Not Palindrome\n",n);
    return 0;
}

int sumFirstLast(int n){
    int temp=n,first,last;
    last=temp%10;
    while(temp>=10) temp/=10;
    first=temp;
    printf("Sum of first and last digit = %d\n",first+last);
    return 0;
}
