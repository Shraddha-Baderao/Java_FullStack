#include <stdio.h>

void main() {
   
   int n=40585;
   
    int temp=n;
    int a=0;
    
    
    while(temp!=0){
        int r=temp%10;
        int i = 1; 
    int sum=1;
    
    
    while(i<=r)
    {
       
            sum=sum*i;
        i++;
    
    }

    a=a+sum;
    temp=temp/10;
    }
    if(a==n)
    {
        printf("given number is strong");
    }else{
    printf("given number is not  strong");
    }
    
    

}
