#include <stdio.h>

void main()
{
    int start=1, End=5;
    int i = 1, sum = 0;
    
    while(i>=start && i<=End)
    {
        while(i<=5){
            sum= sum+i;
            i++;
        }
       
       printf(" The sum of given numbers is:%d",sum);
       i++;
        
    }

}