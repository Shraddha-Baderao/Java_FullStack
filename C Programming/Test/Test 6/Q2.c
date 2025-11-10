#include <stdio.h>

int main() {
    int arr[10], n, i;
    int firstMax, secondMax;

    printf("Enter number of elements: ");
    scanf("%d", &n);

    printf("Enter %d elements: ", n);
    for(i = 0; i < n; i++) {
        scanf("%d", &arr[i]);
    }

    
    if (arr[0] > arr[1]) {
        firstMax = arr[0];
        secondMax = arr[1];
    } else {
        firstMax = arr[1];
        secondMax = arr[0];
    }

    for(i = 2; i < n; i++) {
        if (arr[i] > firstMax) {
            secondMax = firstMax;
            firstMax = arr[i];
        }
        else if (arr[i] > secondMax && arr[i] != firstMax) {
            secondMax = arr[i];
        }
    }

    printf("First Maximum = %d\n", firstMax);
    printf("Second Maximum = %d\n", secondMax);

    return 0;
}
