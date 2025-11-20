#include <stdio.h>

void eve(int size,int arr[]);
void odd(int size, int arr[]);

int main() {
   int n;
    printf("Enter size of array: ");
    scanf("%d", &n);
    int arr[n];
    printf("Enter %d elements: ", n);
    for (int i = 0; i < n; i++)
        scanf("%d", &arr[i]);


    printf("Even numbers: ");
    eve(n,arr);

    printf("odd numbers: ");
    odd(n,arr);

    return 0;
}
void eve(int size,int arr[])
{

    for (int i = 0; i < size; i++)
        if (arr[i] % 2 == 0) printf("%d ", arr[i]);
        printf("\n");

    return ;
}
void odd(int size, int arr[])
{
   for (int i = 0; i < size; i++)
        if (arr[i] % 2 != 0) printf("%d ", arr[i]);

return ;
}
