#include <stdio.h>

void add();
void areaOfCircle();
void celsiusToFahrenheit();
void swapNumbers();
void average();
void squareAndCube();
void minutesToHours();
void perimeterOfRectangle();
void areaOfTriangle();
void totalMarksPercentage();

int main() {
    add();
    areaOfCircle();
    celsiusToFahrenheit();
    swapNumbers();
    average();
    squareAndCube();
    minutesToHours();
    perimeterOfRectangle();
    areaOfTriangle();
    totalMarksPercentage();

    return 0;
}

void add() {
    int a, b, sum;
    printf("Enter two integers: ");
    scanf("%d %d", &a, &b);
    sum = a + b;
    printf("Sum = %d\n", sum);
}

void areaOfCircle() {
    float radius, area;
    printf("Enter radius of circle: ");
    scanf("%f", &radius);
    area = 3.14 * radius * radius;
    printf("Area of circle = %.2f\n", area);
}

void celsiusToFahrenheit() {
    float c, f;
    printf("Enter temperature in Celsius: ");
    scanf("%f", &c);
    f = (c * 9 / 5) + 32;
    printf("Temperature in Fahrenheit = %.2f\n", f);
}

void swapNumbers() {
    int a, b, temp;
    printf("Enter two numbers: ");
    scanf("%d %d", &a, &b);
    temp = a;
    a = b;
    b = temp;
    printf("After swapping: a = %d, b = %d\n", a, b);
}

void average() {
    int a, b, c, d, e;
    float avg;
    printf("Enter five numbers: ");
    scanf("%d %d %d %d %d", &a, &b, &c, &d, &e);
    avg = (a + b + c + d + e) / 5.0;
    printf("Average = %.2f\n", avg);
}

void squareAndCube() {
    int n;
    printf("Enter a number: ");
    scanf("%d", &n);
    printf("Square = %d\n", n * n);
    printf("Cube = %d\n", n * n * n);
}

void minutesToHours() {
    int minutes, hours, remaining;
    printf("Enter total minutes: ");
    scanf("%d", &minutes);
    hours = minutes / 60;
    remaining = minutes % 60;
    printf("%d minutes = %d hour(s) and %d minute(s)\n", minutes, hours, remaining);
}

void perimeterOfRectangle() {
    float length, width, perimeter;
    printf("Enter length and width of rectangle: ");
    scanf("%f %f", &length, &width);
    perimeter = 2 * (length + width);
    printf("Perimeter of rectangle = %.2f\n", perimeter);
}

void areaOfTriangle() {
    float base, height, area;
    printf("Enter base and height of triangle: ");
    scanf("%f %f", &base, &height);
    area = 0.5 * base * height;
    printf("Area of triangle = %.2f\n", area);
}

void totalMarksPercentage() {
    float m1, m2, m3, m4, m5, total, percentage;
    printf("Enter marks of five subjects: ");
    scanf("%f %f %f %f %f", &m1, &m2, &m3, &m4, &m5);
    total = m1 + m2 + m3 + m4 + m5;
    percentage = (total / 500) * 100;
    printf("Total marks = %.2f\n", total);
    printf("Percentage = %.2f%%\n", percentage);
}
