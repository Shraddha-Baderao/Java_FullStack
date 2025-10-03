#include <stdio.h>

void main()
{
    int i = 1, n = 28, sum = 0;
    
    while(i<=n/2)
    {
       if(n%i==0){
          sum = sum+i;
       }
           i++;
       
    }
       if(sum==n){
            printf("The given number is perfect");
       }
       else{
       printf("The given number is not perfect");
       }
    

}