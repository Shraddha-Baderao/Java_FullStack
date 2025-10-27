#include <stdio.h>

int main() {
    int n;
    printf("Enter size of array: ");
    scanf("%d", &n);
    int arr[n];
    printf("Enter %d elements: ", n);
    for (int i = 0; i < n; i++) scanf("%d", &arr[i]);

    printf("Even numbers: ");
    for (int i = 0; i < n; i++)
        if (arr[i] % 2 == 0) printf("%d ", arr[i]);

    printf("\nOdd numbers: ");
    for (int i = 0; i < n; i++)
        if (arr[i] % 2 != 0) printf("%d ", arr[i]);
    
    printf("\n");
    return 0;
}
