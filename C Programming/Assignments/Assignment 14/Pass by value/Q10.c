#include<stdio.h>
#include<string.h>

struct product
{
   int id;
   char name[20];
   int quantity;
   float price;
};
void main()
{
    int i,n;
   struct product p[n];

   printf("Enter number of product: ");
   scanf("%d",&n);

   for(i=0;i<n;i++)
   {
     printf("\nproduct %d\n\n",i+1);
     printf("Enter product id: ");
     scanf("%d",&p[i].id);
     printf("Enter product name: ");
     scanf("%s",p[i].name);
     printf("Enterproduct quantity: ");
     scanf("%d",&p[i].quantity);
     printf("Enter product price: ");
     scanf("%f",&p[i].price);
   }
   printf("\n\n-----------------------------\n\n");
   for(int i=0;i<n;i++)
   {
       printf("\nproduct %d\n\n",i+1);
       printf("id of product: %d\n", p[i].id);
       printf("name of product: %s\n",p[i].name);
       printf("quantity of product: %d\n",p[i].quantity);
       printf("price of product: %f\n",p[i].price);
   }
}
