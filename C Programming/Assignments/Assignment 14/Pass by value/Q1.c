#include<stdio.h>
#include<string.h>

struct student
{
   int rollno;
   char name[20];
   int marks;
};
void main()
{
  struct student july[2],aug[2];
  storedetails( july,2);
  storedetails( aug,2);

  displaydetails(july,2);
  displaydetails(aug,2);
}

void storedetails(student* arr, int size)
{
  int i;

   printf("Enter 5 students  batch info: \n");
   for(i=0;i<5;i++)
   {
     printf("\n Student %d\n\n",i+1);
     printf("Enter  student rollno: ");
     scanf("%d",&arr[i].rollno);
     printf("Enter  student name: ");
     scanf("%s",arr[i].name);
     printf("Enter  student marks: ");
     scanf("%d",&arr[i].marks);
}
void displaydetails(student* arr, int  size)
  int i;
  
   printf("\n Students added....\n");

   printf("******************************************\n");
   printf("\nStudents in given array: \n");
   for(int i=0;i<5;i++)
   {
       printf("\n Student %d\n\n",i+1);
       printf("roll no of student: %d\n", arr[i].rollno);
       printf("name of student: %s\n",arr[i].name);
       printf("marks of student: %d\n",arr[i].marks);
   }
}


