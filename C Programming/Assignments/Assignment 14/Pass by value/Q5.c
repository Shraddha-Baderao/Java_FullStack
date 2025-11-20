#include<stdio.h>
#include<string.h>

struct salesManager
{
   int id;
   char name[20];
   int salary;
   double incentive;
   double target;
};
void main()
{
    int i,n;
   struct salesManager sm[n];

   printf("Enter number of salesManager: ");
   scanf("%d",&n);

   for(i=0;i<n;i++)
   {
     printf("\nsalesManager %d\n\n",i+1);
     printf("Enter salesManager id: ");
     scanf("%d",&sm[i].id);
     printf("Enter salesManager name: ");
     scanf("%s",sm[i].name);
     printf("Enter salesManager salary: ");
     scanf("%d",&sm[i].salary);
     printf("Enter salesManager incentive: ");
     scanf("%ld",&sm[i].incentive);
     printf("Enter salesManager target: ");
     scanf("%ld",&sm[i].target);
   }
   printf("\n\n-----------------------------\n\n");
   for(int i=0;i<n;i++)
   {
       printf("\nsalesManager %d\n\n",i+1);
       printf("id of salesManager: %d\n", sm[i].id);
       printf("name of salesManager: %s\n",sm[i].name);
       printf("salary of salesManager: %d\n",sm[i].salary);
       printf("incentive of salesManager: %ld\n",sm[i].incentive);
       printf("target of salesManager: %ld\n",sm[i].target);
   }
}
