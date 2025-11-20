#include <stdio.h>

void Alt(int size,int arr[]);

int main() {
   int n;
    printf("Enter size of array: ");
    scanf("%d", &n);
    int arr[n];
    printf("Enter %d elements: ", n);
    for (int i = 0; i < n; i++)
        scanf("%d", &arr[i]);


    printf("Alternate numbers: ");
    Alt(n,arr);

    return 0;
}
void Alt(int size,int arr[])
{

    for (int i = 0; i < size; i += 2) printf("%d ", arr[i]);
    printf("\n");
}
