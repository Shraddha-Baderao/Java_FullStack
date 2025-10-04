#include <stdio.h>
void main()
{
    int price;
    char result;

printf("Enter the price:");
scanf("%d",&price);

printf("Enter your student or not in y and n \n");
scanf(" %c",&result);

if(result=='y')
{
    if(price>=500)
    {
        price=price-(price*0.2);
        printf("The price including discount: %d",price);
    }
    else
    {
        price=price-(price*0.1);
        printf("The price including discount: %d",price);
    }
}
else
{
    if(price>=600)
    {
        price=price-(price*0.15);
        printf("The price including discount: %d",price);
    }
    else
    {
        printf("The price including discount: %d",price);
    }
}
}