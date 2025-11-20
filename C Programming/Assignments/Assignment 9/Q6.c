#include <stdio.h>
#include <math.h>
int isPrime(int n);

int main() {
    int n;
    printf("Enter size of array: ");
    scanf("%d", &n);
    int arr[n];
    printf("Enter %d elements: ", n);
    for (int i = 0; i < n; i++)
     scanf("%d", &arr[i]);

    printf("Prime numbers: ");
    for (int i = 0; i < n; i++)
    {

        if (isPrime(arr[i]))
        {
            printf("%d ", arr[i]);
        }

    }

    return 0;
}
int isPrime(int n) {
    if (n <= 1) return 0;
    for (int i = 2; i <= n/2; i++)
        if (n % i == 0) return 0;
    return 1;
}

