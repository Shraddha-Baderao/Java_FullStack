#include <stdio.h>

int main() {
    int n;
    printf("Enter size of arrays: ");
    scanf("%d", &n);
    int arr[n], brr[n], crr[n];

    printf("Enter elements of first array: ");
    for (int i = 0; i < n; i++) scanf("%d", &arr[i]);

    printf("Enter elements of second array: ");
    for (int i = 0; i < n; i++) scanf("%d", &brr[i]);

    for (int i = 0; i < n; i++) crr[i] = arr[i] + brr[i];

    printf("Sum array: ");
    for (int i = 0; i < n; i++) printf("%d ", crr[i]);
    printf("\n");
    return 0;
}
