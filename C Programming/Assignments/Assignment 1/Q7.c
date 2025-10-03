#include <stdio.h>

void main()
{
    int bs = 5000;
    int da,ta,hra,ttlsal;
    
    if(bs<=5000)
    {
       da = bs*0.10;
       ta = bs*0.20;
       hra = bs*0.25;
    }
    else{
       da = bs*0.15;
       ta = bs*0.25;
       hra = bs*0.30;
    }
    
    ttlsal = bs+da+ta+hra;
        printf("Total salary is:%d",ttlsal);
    

}