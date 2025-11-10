#include <stdio.h>

float sellprice(float cost, float discount);

void main()
{
    float cost, discount, selling;

    printf("Enter cost price of book: ");
    scanf("%f", &cost);

    printf("Enter discount percentage: ");
    scanf("%f", &discount);

    selling = sellprice(cost, discount);

    printf("Selling price of book = %.2f\n", selling);
}

float sellprice(float cost, float discount)
{
    float sp;

    if(discount >= 0)  
    {
        if(discount <= 50)  
        {
            sp = cost - (cost * discount / 100);
        }
        else           
        {
            printf("Discount too high! Limiting to 50%%.\n");
            sp = cost - (cost * 50 / 100);
        }
    }
    else
    {
        printf("Invalid discount! Setting to 0.\n");
        sp = cost;
    }

    return sp;
}

