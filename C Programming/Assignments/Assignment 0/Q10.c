#include <stdio.h>

void main()
{
    int a = 90;
    int b = 80;
    int c = 95;
    int d = 85;
    int e = 75;
    
    int ttl;
    float per;
    
    ttl = a+b+c+d+e;
    printf("The total marks are: %d", ttl);
    
    per = (ttl/500.0)*100;
    printf("The percentage: %f", per);

}