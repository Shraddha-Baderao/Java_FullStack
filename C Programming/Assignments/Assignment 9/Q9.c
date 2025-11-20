#include <stdio.h>

void rev(int size,int arr[]);

int main() {
    int n;
    printf("Enter size of array: ");
    {
         scanf("%d", &n);

    }
    int arr[n];
    printf("Enter %d elements: ", n);
    for (int i = 0; i < n; i++)
    {
        scanf("%d", &arr[i]);
    }
rev(n,arr);
}
void rev(int size,int arr[])
{
    printf("Reversed array: ");
    for (int i = size-1; i >= 0; i--)
    {
        printf("%d ", arr[i]);

    }
printf("\n");

}



