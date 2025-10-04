#include <stdio.h>

void main()
{
    int a,b,c;
    
    printf("Enter side a: ");
    scanf("%d",&a);
    
    printf("Enter side b: ");
    scanf("%d",&b);
    
    printf("Enter side c: ");
    scanf("%d",&c);
    if(a==b && b==c)
    {
        printf("it's equilateral Triangle");
    }
    else if(a==b || a==c || b==c)
    {
         printf("it's isosceles Triangle");
    }
    else
    {
         printf("it's scalene Triangle");
    }
    
}