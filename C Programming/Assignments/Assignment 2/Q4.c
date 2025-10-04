#include <stdio.h>

void main()
{
    int Marks;
    
    printf("Enter the Marks: ");
    scanf("%d",&Marks);
    
    if(Marks>=75)
    {
        printf("Distinction");
    }
    else if(Marks>=65)
    {
        printf("First Class");
    }
    else if(Marks>=55)
    {
        printf("Second Class");
    }
    else if(Marks>=40)
    {
        printf("Pass Class");
    }
    else
    {
        printf("Fail");
    }
    
}