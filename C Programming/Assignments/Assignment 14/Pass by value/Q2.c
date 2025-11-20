#include<stdio.h>
#include<string.h>

struct employee
{
   int id;
   char name[20];
   int salary;
};
void main()
{
    int i,n;
   struct employee e[n];

   printf("Enter number of employees: ");
   scanf("%d",&n);

   for(i=0;i<n;i++)
   {
     printf("Employee %d\n",i+1);
     printf("Enter employee id: ");
     scanf("%d",&e[i].id);
     printf("Enter employee name: ");
     scanf("%s",e[i].name);
     printf("Enter employee salary: ");
     scanf("%d",&e[i].salary);
   }
   for(int i=0;i<n;i++)
   {
       printf("id of employee: %d\n", e[i].id);
       printf("name of employee: %s\n",e[i].name);
       printf("salary of employee: %d\n",e[i].salary);
   }
}
