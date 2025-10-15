#include <stdio.h>

void main()
{
    int n1;
    int n2;
    char op ;
    int Result;
    printf("Enter 1st number: ");
    scanf("%d",&n1);
    printf("Enter 52nd number: ");
    scanf(" %d",&n2);
    printf("Enter the operation +,-,*,/,%: ");
    scanf(" %c",&op);
    if(op == '+')
    {
        Result=n1+n2;
    }
    else if(op == '-')
    {
        Result=n1+n2;
    }
    else if(op == '*')
    {
        Result=n1+n2;
    }
    else if(op == '/')
    {
        Result=n1+n2;
    }
    else if(op == '%')
    {
        Result=n1+n2;
    }
    else
    {
        printf("Invalide operator");
    }
    printf("The Result of operation is: %d", Result);
    
}