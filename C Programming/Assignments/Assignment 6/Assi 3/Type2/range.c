#include <stdio.h>

void sumInRangeParam(int start, int end);

int main() {
    int s, e;
    printf("Enter start and end of range: ");
    scanf("%d %d", &s, &e);
    sumInRangeParam(s, e);
    return 0;
}

void sumInRangeParam(int start, int end) {
    int sum = 0, i;
    for(i = start; i <= end; i++)
        sum += i;
    printf("Sum of numbers from %d to %d = %d\n", start, end, sum);
}
