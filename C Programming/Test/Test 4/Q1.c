#include<stdio.h>
void main()
{
  int n;
  int Start=10, End=15, fact=1;
  printf("Enter number in rang 10 to 15: ");
  scanf("%d",&n);

  if(n<=End||n>=Start)
  {

      for(int i=n;i>=1;i--)
      {

        fact=fact*i;

      }
      printf("\nfactorial of %d is : %d\n ",n,fact);
  }

}

