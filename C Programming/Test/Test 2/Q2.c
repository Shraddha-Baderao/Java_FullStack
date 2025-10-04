#include <stdio.h>

void main()
{
    int n;
     printf("Enter the number: ");
    scanf("%d", &n);
    
    if(n>0)
    {
        printf("The number is positive: %d",n);
    }
    else if(n<0)
    {
       printf("The number is negative: %d",n);
    }
    else
    {
       printf("The number is nutral: %d",n);
    }
    
}