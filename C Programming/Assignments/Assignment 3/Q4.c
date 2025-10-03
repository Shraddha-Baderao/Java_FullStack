#include <stdio.h>

void main()
{
    int i = 2, n = 7, flag = 0;
    
    while(i<=n/2)
    {
       if(n%i==0){
           flag=1;
           break;
       }
           i++;
       
    }
       if(flag==0 && n>1){
            printf("The given number is prime");
       }
       else{
       printf("The given number is not prime");
       }
    

}