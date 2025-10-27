#include<stdio.h>
{


  int n,pow,res=1;

  printf("Enter number: ");
  scanf("%d",&n);

  printf("Enter power of number: ");
  scanf("%d",&pow);

  for(int i=1;i<=pow;i++)
      {

        res=res*n;

      }
      printf("The power of %d is: %d\n ",n,res);

}

