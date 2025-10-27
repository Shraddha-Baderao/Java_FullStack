#include <stdio.h>

int main() {
    int n, m;
    printf("Enter size of first array: ");
    scanf("%d", &n);
    int arr[n];
    printf("Enter %d elements: ", n);
    for (int i = 0; i < n; i++) scanf("%d", &arr[i]);

    printf("Enter size of second array: ");
    scanf("%d", &m);
    int brr[m];
    printf("Enter %d elements: ", m);
    for (int i = 0; i < m; i++) scanf("%d", &brr[i]);

    printf("Merged array: ");
    for (int i = 0; i < n; i++) printf("%d ", arr[i]);
    for (int i = 0; i < m; i++) printf("%d ", brr[i]);
    printf("\n");
    return 0;
}
