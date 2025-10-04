#include <stdio.h>

void main()
{
    int a,b,c;
    
    printf("Enter 1st number: ");
    scanf("%d",&a);
    
    printf("Enter 2nd number: ");
    scanf("%d",&b);
    
    printf("Enter 3rd number: ");
    scanf("%d",&c);
    
    if(a>b && a>c)
    {
        printf("the greatest number is: %d",a);
    }
    else if(b>a && b>c)
    {
         printf("the greatest number is: %d",b);
    }
    else if(c>a && c>b)
    {
         printf("the greatest number is: %d",c);
    }
    else
    {
        printf("All numbers are equal");
    }
    
}