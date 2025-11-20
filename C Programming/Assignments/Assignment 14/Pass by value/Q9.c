#include<stdio.h>
#include<string.h>

struct complex
{
   int real;
   int imaginary;
};
void main()
{
    int i,n;
   struct complex c[n];

   printf("Enter number of complexes: ");
   scanf("%d",&n);

   for(i=0;i<n;i++)
   {
     printf("\ncomplex %d\n\n",i+1);
     printf("Enter complex real: ");
     scanf("%d",&c[i].real);
     printf("Enter complex in imaginary: ");
     scanf("%d",&c[i].imaginary);
   }
   printf("\n\n-----------------------------\n\n");
   for(int i=0;i<n;i++)
   {
       printf("\ncomplex %d\n\n",i+1);
       printf("complex in real: %d\n", c[i].real);
       printf("complex in imaginary: %d\n",c[i].imaginary);
   }
}
