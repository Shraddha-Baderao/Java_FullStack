#include<stdio.h>
#include<string.h>

struct HR
{
   int id;
   char name[20];
   int salary;
   double comission;
};
void main()
{
    int i,n;
   struct HR h[n];

   printf("Enter number of HR: ");
   scanf("%d",&n);

   for(i=0;i<n;i++)
   {
     printf("\nHR %d\n\n",i+1);
     printf("Enter HR id: ");
     scanf("%d",&h[i].id);
     printf("Enter HR name: ");
     scanf("%s",h[i].name);
     printf("Enter HR salary: ");
     scanf("%d",&h[i].salary);
     printf("Enter HR comission: ");
     scanf("%ld",&h[i].comission);
   }
   printf("\n\n-----------------------------\n\n");
   for(int i=0;i<n;i++)
   {
       printf("\nHR %d\n\n",i+1);
       printf("id of HR: %d\n", h[i].id);
       printf("name of HR: %s\n",h[i].name);
       printf("salary of HR: %d\n",h[i].salary);
       printf("comission of HR: %ld\n",h[i].comission);
   }
}
