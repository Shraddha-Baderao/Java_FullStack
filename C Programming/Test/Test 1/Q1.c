#include <stdio.h>

void main() {
   int s = 24020;
    
    int hrs = s/10000;
    int min = (s/100)%100;
    int sec = (s%100);
    
    int ttlsec = hrs*3600+min*60+s;
    printf("total secends are: %ld\n",ttlsec);
    printf ("Time is - %d:%d:%d",hrs,min,sec);
}