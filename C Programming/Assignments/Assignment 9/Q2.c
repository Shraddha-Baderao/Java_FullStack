#include <stdio.h>

int searchnum(int size, int arr[], int x);


int main() {
    int n, num;
    printf("Enter size of array: ");
    scanf("%d", &n);
    int arr[n];
    printf("Enter %d elements: ", n);
    for (int i = 0; i < n; i++) scanf("%d", &arr[i]);

    printf("Enter number to search: ");
    scanf("%d", &num);

    int index = searchnum(n,arr,num);
    if(index!=-1)
        printf("%d found at position %d\n", num, index + 1);

    else
        printf("%d not found in array\n", num);
}


int searchnum(int size, int arr[], int x)
{
    for (int i = 0; i < size; i++) {
        if (arr[i] == x) {

            return i;
            break;
        }
    }
    return -1;
}
