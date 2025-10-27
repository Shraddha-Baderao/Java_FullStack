#include <stdio.h>

int add();
float areaOfCircle();
float celsiusToFahrenheit();
void swapNumbers();
float average();
int square();
int cube();
int hours(int minutes);
int remainingMinutes(int minutes);
float perimeterOfRectangle();
float areaOfTriangle();
float totalMarks();
float percentage();

int main() {
    int sum = add();
    printf("Sum = %d\n", sum);

    float area = areaOfCircle();
    printf("Area of circle = %.2f\n", area);

    float f = celsiusToFahrenheit();
    printf("Temperature in Fahrenheit = %.2f\n", f);

    swapNumbers();

    float avg = average();
    printf("Average = %.2f\n", avg);

    int sq = square();
    int cb = cube();
    printf("Square = %d, Cube = %d\n", sq, cb);

    int min;
    printf("Enter minutes to convert: ");
    scanf("%d", &min);
    int h = hours(min);
    int r = remainingMinutes(min);
    printf("%d minutes = %d hour(s) and %d minute(s)\n", min, h, r);

    float peri = perimeterOfRectangle();
    printf("Perimeter of rectangle = %.2f\n", peri);

    float triArea = areaOfTriangle();
    printf("Area of triangle = %.2f\n", triArea);

    float total = totalMarks();
    float perc = percentage();
    printf("Total marks = %.2f\nPercentage = %.2f%%\n", total, perc);

    return 0;
}

int add() {
    int a, b;
    printf("Enter two integers: ");
    scanf("%d %d", &a, &b);
    return a + b;
}

float areaOfCircle() {
    float r;
    printf("Enter radius of circle: ");
    scanf("%f", &r);
    return 3.14 * r * r;
}

float celsiusToFahrenheit() {
    float c;
    printf("Enter Celsius temperature: ");
    scanf("%f", &c);
    return (c * 9 / 5) + 32;
}

void swapNumbers() {
    int a, b, temp;
    printf("Enter two numbers to swap: ");
    scanf("%d %d", &a, &b);
    temp = a;
    a = b;
    b = temp;
    printf("After swapping: a = %d, b = %d\n", a, b);
}

float average() {
    int n1, n2, n3, n4, n5;
    printf("Enter five numbers: ");
    scanf("%d %d %d %d %d", &n1, &n2, &n3, &n4, &n5);
    return (n1 + n2 + n3 + n4 + n5) / 5.0;
}

int square() {
    int n;
    printf("Enter a number for square: ");
    scanf("%d", &n);
    return n * n;
}

int cube() {
    int n;
    printf("Enter a number for cube: ");
    scanf("%d", &n);
    return n * n * n;
}

int hours(int minutes) {
    return minutes / 60;
}

int remainingMinutes(int minutes) {
    return minutes % 60;
}

float perimeterOfRectangle() {
    float l, w;
    printf("Enter length and width of rectangle: ");
    scanf("%f %f", &l, &w);
    return 2 * (l + w);
}

float areaOfTriangle() {
    float b, h;
    printf("Enter base and height of triangle: ");
    scanf("%f %f", &b, &h);
    return 0.5 * b * h;
}

float totalMarks() {
    float m1, m2, m3, m4, m5;
    printf("Enter marks of five subjects: ");
    scanf("%f %f %f %f %f", &m1, &m2, &m3, &m4, &m5);
    return m1 + m2 + m3 + m4 + m5;
}

float percentage() {
    float m1, m2, m3, m4, m5;
    printf("Enter marks of five subjects: ");
    scanf("%f %f %f %f %f", &m1, &m2, &m3, &m4, &m5);
    return ((m1 + m2 + m3 + m4 + m5) / 500) * 100;
}
