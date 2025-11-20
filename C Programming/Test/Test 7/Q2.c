#include <stdio.h>

int pali(int size,int arr[]);

void main()
{
   int arr[5],i;
   printf("Enter array: \n");
   for(i=0;i<5;i++)
   {
       scanf("%d",&arr[i]);

   }
   int res=pali(5,arr);
   if(res)
   {
       printf("array is a palindrom\n");
   }
   else{
    printf("array is not a palindrom\n");
   }
}
int pali(int size,int arr[])
{
    int j=size-1;

    for(int i=0;i<j;i++,j--)
    {
        if(arr[i]!=arr[j])
        {
            return 0;
        }
    }
}


