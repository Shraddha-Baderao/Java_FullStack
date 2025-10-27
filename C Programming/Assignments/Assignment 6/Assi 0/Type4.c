#include <stdio.h>

int add(int a, int b);
float areaOfCircle(float r);
float celsiusToFahrenheit(float c);
void swapNumbers(int a, int b);
float average(int n1, int n2, int n3, int n4, int n5);
int square(int n);
int cube(int n);
int hours(int minutes);
int remainingMinutes(int minutes);
float perimeterOfRectangle(float l, float w);
float areaOfTriangle(float b, float h);
float totalMarks(float m1, float m2, float m3, float m4, float m5);
float percentage(float m1, float m2, float m3, float m4, float m5);

int main() {
    int a, b, n1, n2, n3, n4, n5, n, min;
    float r, c, l, w, base, height, m1, m2, m3, m4, m5;

    printf("Enter two integers for addition: ");
    scanf("%d %d", &a, &b);
    printf("Sum = %d\n", add(a, b));

    printf("Enter radius of circle: ");
    scanf("%f", &r);
    printf("Area of circle = %.2f\n", areaOfCircle(r));

    printf("Enter Celsius temperature: ");
    scanf("%f", &c);
    printf("Temperature in Fahrenheit = %.2f\n", celsiusToFahrenheit(c));

    printf("Enter two numbers to swap: ");
    scanf("%d %d", &a, &b);
    swapNumbers(a, b);

    printf("Enter five numbers to find average: ");
    scanf("%d %d %d %d %d", &n1, &n2, &n3, &n4, &n5);
    printf("Average = %.2f\n", average(n1, n2, n3, n4, n5));

    printf("Enter a number to find square and cube: ");
    scanf("%d", &n);
    printf("Square = %d, Cube = %d\n", square(n), cube(n));

    printf("Enter minutes to convert: ");
    scanf("%d", &min);
    printf("%d minutes = %d hour(s) and %d minute(s)\n", min, hours(min), remainingMinutes(min));

    printf("Enter length and width of rectangle: ");
    scanf("%f %f", &l, &w);
    printf("Perimeter of rectangle = %.2f\n", perimeterOfRectangle(l, w));

    printf("Enter base and height of triangle: ");
    scanf("%f %f", &base, &height);
    printf("Area of triangle = %.2f\n", areaOfTriangle(base, height));

    printf("Enter marks of five subjects: ");
    scanf("%f %f %f %f %f", &m1, &m2, &m3, &m4, &m5);
    printf("Total marks = %.2f\nPercentage = %.2f%%\n", totalMarks(m1, m2, m3, m4, m5), percentage(m1, m2, m3, m4, m5));

    return 0;
}

int add(int a, int b) {
    return a + b;
}

float areaOfCircle(float r) {
    return 3.14 * r * r;
}

float celsiusToFahrenheit(float c) {
    return (c * 9 / 5) + 32;
}

void swapNumbers(int a, int b) {
    int temp = a;
    a = b;
    b = temp;
    printf("After swapping: a = %d, b = %d\n", a, b);
}

float average(int n1, int n2, int n3, int n4, int n5) {
    return (n1 + n2 + n3 + n4 + n5) / 5.0;
}

int square(int n) {
    return n * n;
}

int cube(int n) {
    return n * n * n;
}

int hours(int minutes) {
    return minutes / 60;
}

int remainingMinutes(int minutes) {
    return minutes % 60;
}

float perimeterOfRectangle(float l, float w) {
    return 2 * (l + w);
}

float areaOfTriangle(float b, float h) {
    return 0.5 * b * h;
}

float totalMarks(float m1, float m2, float m3, float m4, float m5) {
    return m1 + m2 + m3 + m4 + m5;
}

float percentage(float m1, float m2, float m3, float m4, float m5) {
    return ((m1 + m2 + m3 + m4 + m5) / 500) * 100;
}
