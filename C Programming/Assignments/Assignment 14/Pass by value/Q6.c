#include<stdio.h>
#include<string.h>

struct date
{
   int date;
   int month;
   int year;
};
void main()
{
    int i,n;
   struct date d[n];

   printf("Enter number of date: ");
   scanf("%d",&n);

   for(i=0;i<n;i++)
   {
     printf("\ndate %d\n\n",i+1);
     printf("Enter date of date: ");
     scanf("%d",&d[i].date);
     printf("Enter date of month: ");
     scanf("%d",&d[i].month);
     printf("Enter date of year: ");
     scanf("%d",&d[i].year);
   }
   printf("\n\n-----------------------------\n\n");
   for(int i=0;i<n;i++)
   {
       printf("\ndate %d\n\n",i+1);
       printf("date of date: %d\n", d[i].date);
       printf("month of date: %d\n",d[i].month);
       printf("year of date: %d\n",d[i].year);
   }
}
