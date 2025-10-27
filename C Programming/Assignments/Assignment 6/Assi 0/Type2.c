#include <stdio.h>

void add(int a, int b);
void areaOfCircle(float radius);
void celsiusToFahrenheit(float c);
void swapNumbers(int a, int b);
void average(int n1, int n2, int n3, int n4, int n5);
void squareAndCube(int n);
void minutesToHours(int minutes);
void perimeterOfRectangle(float length, float width);
void areaOfTriangle(float base, float height);
void totalMarksPercentage(float m1, float m2, float m3, float m4, float m5);

int main() {
    int a, b, n1, n2, n3, n4, n5, n, minutes;
    float radius, c, length, width, base, height, m1, m2, m3, m4, m5;

    printf("Enter two integers for addition: ");
    scanf("%d %d", &a, &b);
    add(a, b);

    printf("Enter radius of circle: ");
    scanf("%f", &radius);
    areaOfCircle(radius);

    printf("Enter temperature in Celsius: ");
    scanf("%f", &c);
    celsiusToFahrenheit(c);

    printf("Enter two numbers to swap: ");
    scanf("%d %d", &a, &b);
    swapNumbers(a, b);

    printf("Enter five numbers to find average: ");
    scanf("%d %d %d %d %d", &n1, &n2, &n3, &n4, &n5);
    average(n1, n2, n3, n4, n5);

    printf("Enter a number to find square and cube: ");
    scanf("%d", &n);
    squareAndCube(n);

    printf("Enter minutes to convert to hours: ");
    scanf("%d", &minutes);
    minutesToHours(minutes);

    printf("Enter length and width of rectangle: ");
    scanf("%f %f", &length, &width);
    perimeterOfRectangle(length, width);

    printf("Enter base and height of triangle: ");
    scanf("%f %f", &base, &height);
    areaOfTriangle(base, height);

    printf("Enter marks of five subjects: ");
    scanf("%f %f %f %f %f", &m1, &m2, &m3, &m4, &m5);
    totalMarksPercentage(m1, m2, m3, m4, m5);

    return 0;
}

void add(int a, int b) {
    int sum = a + b;
    printf("Sum = %d\n", sum);
}

void areaOfCircle(float radius) {
    float area = 3.14 * radius * radius;
    printf("Area of circle = %.2f\n", area);
}

void celsiusToFahrenheit(float c) {
    float f = (c * 9 / 5) + 32;
    printf("Temperature in Fahrenheit = %.2f\n", f);
}

void swapNumbers(int a, int b) {
    int temp = a;
    a = b;
    b = temp;
    printf("After swapping: a = %d, b = %d\n", a, b);
}

void average(int n1, int n2, int n3, int n4, int n5) {
    float avg = (n1 + n2 + n3 + n4 + n5) / 5.0;
    printf("Average = %.2f\n", avg);
}

void squareAndCube(int n) {
    printf("Square = %d\n", n * n);
    printf("Cube = %d\n", n * n * n);
}

void minutesToHours(int minutes) {
    int hours = minutes / 60;
    int rem = minutes % 60;
    printf("%d minutes = %d hour(s) and %d minute(s)\n", minutes, hours, rem);
}

void perimeterOfRectangle(float length, float width) {
    float perimeter = 2 * (length + width);
    printf("Perimeter of rectangle = %.2f\n", perimeter);
}

void areaOfTriangle(float base, float height) {
    float area = 0.5 * base * height;
    printf("Area of triangle = %.2f\n", area);
}

void totalMarksPercentage(float m1, float m2, float m3, float m4, float m5) {
    float total = m1 + m2 + m3 + m4 + m5;
    float percentage = (total / 500) * 100;
    printf("Total marks = %.2f\n", total);
    printf("Percentage = %.2f%%\n", percentage);
}
