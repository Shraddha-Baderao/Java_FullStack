#include <stdio.h>

int sumnum(int size,int arr[]);

int main() {
    int n;
    printf("Enter size of array: ");
    scanf("%d", &n);
    int arr[n];
    printf("Enter %d elements: ", n);
    for (int i = 0; i < n; i++) {
        scanf("%d", &arr[i]);

    }
    int res=sumnum(n,arr);
    printf("Sum of all numbers: %d\n", res);
    return 0;
}
int sumnum(int size,int arr[])
{
    int sum=0;
    for (int i = 0; i < size; i++)
    {
        sum+=arr[i];
    }
    return sum;
}
