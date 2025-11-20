#include <stdio.h>

void sort(int size,int arr[],int t);

int main() {
    int n, temp;
    printf("Enter size of array: ");
    scanf("%d", &n);
    int arr[n];
    printf("Enter %d elements: ", n);
    for (int i = 0; i < n; i++) scanf("%d", &arr[i]);

        sort(n,arr,temp);
}
void sort(int size,int arr[],int t)
{
    for (int i = 0; i < size-1; i++) {
        for (int j = i+1; j < size; j++) {
            if (arr[i] > arr[j]) {
                t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }
    }

    printf("Sorted array: ");
    for (int i = 0; i < size; i++) printf("%d ", arr[i]);
    printf("\n");

}
