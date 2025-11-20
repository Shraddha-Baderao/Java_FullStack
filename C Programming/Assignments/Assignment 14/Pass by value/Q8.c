#include<stdio.h>
#include<string.h>

struct distance
{
   int feet;
   float inch;
};
void main()
{
    int i,n;
   struct distance d[n];

   printf("Enter number of distances: ");
   scanf("%d",&n);

   for(i=0;i<n;i++)
   {
     printf("\ndistance %d\n\n",i+1);
     printf("Enter distance in feet: ");
     scanf("%d",&d[i].feet);
     printf("Enterdistancee in inch: ");
     scanf("%f",&d[i].inch);
   }
   printf("\n\n-----------------------------\n\n");
   for(int i=0;i<n;i++)
   {
       printf("\ndistance %d\n\n",i+1);
       printf("distance in feet: %d\n", d[i].feet);
       printf("distance in inch: %f\n",d[i].inch);
   }
}
