#include <stdio.h>

void main() {
    int M = 110;
    int hrs = M/60;
    int R_min = M%60;
    
    printf("converted minutes into hours and remaining minutes: %d:%d",hrs,R_min);

}