#include <stdio.h>

void main()
{
    int unit;
    int  ele_bill;
     printf("Enter the Unit: ");
    scanf("%d", &unit);
    
    if(unit>=1 && unit<=50)
    {
        ele_bill=unit*30;
        printf("The electrisity bill is: %d",ele_bill);
    }
    else if(unit>=51 && unit<=150)
    {
        ele_bill=unit*40;
        printf("The electrisity bill is: %d",ele_bill);
    }
    else
    {
        ele_bill=unit*50;
        printf("The electrisity bill is: %d",ele_bill);
    }
    
}