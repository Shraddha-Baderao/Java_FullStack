#include<stdio.h>
#include<string.h>

void main()
{
    int arr[5];
    int brr[5];

    int i,j;

    printf("Enter Elements in 1st array: \n");
    for(i=0;i<5;i++){

    scanf("%d",&arr[i]);
    }
printf("Enter Elements in 2nd array: \n");
    for(j=0;j<5;j++){

    scanf("%d",&brr[j]);
    }

    printf("comman element is: ");
    for(i=0;i<5;i++)
    {
        for(j=0;j<5;j++){
        if(arr[i]==brr[j])
        {
            printf("%d,",arr[i]);
        }
    }
    }
}

