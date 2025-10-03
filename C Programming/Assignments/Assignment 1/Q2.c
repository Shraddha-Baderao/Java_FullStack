#include <stdio.h>

void main()
{
    int n=231;
    
    if(n%10==n/100)
    {
        printf("the given number is Palindrom:%d",n);
    }
    else{
        printf("The given number is not Palindrom:%d",n);
    }

}