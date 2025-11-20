#include<stdio.h>
#include<string.h>

struct admin
{
   int id;
   char name[20];
   int salary;
   double allowance;
};
void main()
{
    int i,n;
   struct admin a[n];

   printf("Enter number of admin: ");
   scanf("%d",&n);

   for(i=0;i<n;i++)
   {
     printf("\nadmin %d\n\n",i+1);
     printf("Enter admin id: ");
     scanf("%d",&a[i].id);
     printf("Enter admin name: ");
     scanf("%s",a[i].name);
     printf("Enter admin salary: ");
     scanf("%d",&a[i].salary);
     printf("Enter admin allowance: ");
     scanf("%ld",&a[i].allowance);
   }
   printf("\n\n-----------------------------\n\n");
   for(int i=0;i<n;i++)
   {
       printf("\nadmin %d\n\n",i+1);
       printf("id of admin: %d\n", a[i].id);
       printf("name of admin: %s\n",a[i].name);
       printf("salary of admin: %d\n",a[i].salary);
       printf("allowance of admin: %ld\n",a[i].allowance);
   }
}
