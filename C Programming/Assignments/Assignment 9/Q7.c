#include <stdio.h>

void sumarr(int size, int arr[], int brr[]);
int main() {
    int n;
    printf("Enter size of arrays: ");
    scanf("%d", &n);

    int arr[n], brr[n];

    printf("Enter elements of first array: ");
    for (int i = 0; i < n; i++) {
        scanf("%d", &arr[i]);
    }

    printf("Enter elements of second array: ");
    for (int i = 0; i < n; i++) {
        scanf("%d", &brr[i]);
    }

    sumarr(n, arr, brr);

    return 0;
}

void sumarr(int size, int arr[], int brr[])
{
    int crr[size];

    for (int i = 0; i < size; i++) {
        crr[i] = arr[i] + brr[i];
    }

    printf("Sum array: ");
    for (int i = 0; i < size; i++) {
        printf("%d ", crr[i]);
    }
    printf("\n");
}
