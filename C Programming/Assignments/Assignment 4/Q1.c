#include <stdio.h>

void main()
{
    int n,i,temp, res=0;

     int count;
     int rem,pow, sum;


    printf("Enter the range 1 to ");
    scanf("%d",&n);

    printf("Armstrong numbers between the range 1 to %d are :\n",n);

    for(i=1;i<=n;i++)
    {

        temp=i;
        count=0;
        sum=0;

        int t = temp;
        while(t!=0)
        {

            count++;
            t=t/10;
        }

        int te= temp;

         while(te!=0){



         rem=te%10;

         pow=1;
        for(int k=1;k<=count;k++)
        {
            pow=pow*rem;

        }

        sum=sum+pow;
        te=te/10;

         }

         if(sum==i)

    {
          printf("%d\n",i);
    }


    }






}
