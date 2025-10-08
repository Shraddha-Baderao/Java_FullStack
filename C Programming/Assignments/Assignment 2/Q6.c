#include <stdio.h>
void main()
{
    int n;
    
    printf("Enter the number: ");
    scanf("%d",&n);
    
    if(n%3 == 0)
    {
        printf("nuber is dvisible by 3");
    }
    else if(n%5 == 0)
    {
        printf("Number is divisible by 5");
    }
    else if(n%3 == 0 && n%5 == 0){
        printf("number is divisible by both");
    }
    else{
        printf("Number is divisible by none");
    }
}