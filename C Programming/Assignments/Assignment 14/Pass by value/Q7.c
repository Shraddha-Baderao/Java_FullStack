#include<stdio.h>
#include<string.h>

struct time
{
   int hours;
   int min;
   int sec;
};
void main()
{
    int i,n;
   struct time t[n];

   printf("Enter number of times: ");
   scanf("%d",&n);

   for(i=0;i<n;i++)
   {
     printf("\ntime %d\n\n",i+1);
     printf("Enter time in hours: ");
     scanf("%d",&t[i].hours);
     printf("Enter time in min: ");
     scanf("%d",&t[i].min);
     printf("Enter time in sec: ");
     scanf("%d",&t[i].sec);
   }
   printf("\n\n-----------------------------\n\n");
   for(int i=0;i<n;i++)
   {
       printf("\ntime %d\n\n",i+1);
       printf("time in hours: %d\n", t[i].hours);
       printf("time in min: %d\n",t[i].min);
       printf("time in sec: %d\n",t[i].sec);
   }
}
