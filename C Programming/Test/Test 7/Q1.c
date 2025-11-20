#include <stdio.h>

void replace(int size,int arr[], int x, int y);

void main()
{
   int arr[7]={11,23,30,4,21,45,50};
   int x,y;

   printf("array: ");
   for(int i=0;i<7;i++)
   {
   printf("%d ",arr[i]);
   }
   printf(" \nEnter 1st value: ");
   scanf("%d",&x);
    printf("\nEnter 2nd value: ");
   scanf("%d",&y);

   replace(7,arr,x,y);

}
void replace(int size,int arr[],int x, int y)
{
int i,j,temp;
    for( i=0;i<size;i++)
    {
        if(arr[i]==x)
        {
           temp =arr[i];


        for(j=0;j<size;j++)
        {
            if (arr[j]==y)
          {
              arr[i]=arr[j];
              arr[j]=temp;

          }

        }
        break;
        }
    }
    for(i=0;i<size;i++)
    {
        printf("%d ",arr[i]);
    }
}


