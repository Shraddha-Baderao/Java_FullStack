#include <stdio.h>

int maxnum(int size, int arr[]);
int minnum(int size, int arr[]);

int main() {
    int n;
    printf("Enter size of array: ");
    scanf("%d", &n);
    int arr[n];
    printf("Enter %d elements: ", n);
    for (int i = 0; i < n; i++)
    {
    scanf("%d", &arr[i]);
    }
    int max = maxnum(n,arr);
     printf("\nMaximum: %d\n", max);
    int min = minnum(n,arr);
     printf("\nMinimum: %d\n", min);
}
int maxnum(int size, int arr[])
{
    int max = arr[0];
    for (int i = 1; i < size; i++) {
        if (arr[i] > max) max = arr[i];
    }

    return max;
}
int minnum(int size, int arr[])
{
    int min = arr[0];
    for (int i = 1; i < size; i++) {
        if (arr[i] < min)
            {
                min = arr[i];
            }
    }
    return min;
}
