#include <stdio.h>

int calculator();
int triangleType();
int greatestOfThree();
int marksClass();
float priceDiscount();
int divisibleCheck();
int ageCategory();

int main() {
    int result, choice;
    float discount;

    result = calculator();
    printf("\n");

    result = triangleType();
    printf("\n");

    result = greatestOfThree();
    printf("\n");

    result = marksClass();
    printf("\n");

    discount = priceDiscount();
    printf("Discount = %.2f\n\n", discount);

    result = divisibleCheck();
    printf("\n");

    result = ageCategory();
    printf("\n");

    return 0;
}

int calculator() {
    int a, b;
    char op;
    printf("Enter two numbers and an operator (+,-,*,/,%%): ");
    scanf("%d %d %c", &a, &b, &op);
    if(op=='+') {
        printf("Result = %d\n", a+b);
        return a+b;
    } else if(op=='-') {
        printf("Result = %d\n", a-b);
        return a-b;
    } else if(op=='*') {
        printf("Result = %d\n", a*b);
        return a*b;
    } else if(op=='/') {
        printf("Result = %d\n", a/b);
        return a/b;
    } else if(op=='%') {
        printf("Result = %d\n", a%b);
        return a%b;
    } else {
        printf("Invalid operator\n");
        return 0;
    }
}

int triangleType() {
    int a, b, c;
    printf("Enter three sides of triangle: ");
    scanf("%d %d %d", &a, &b, &c);
    if(a==b && b==c) {
        printf("Equilateral triangle\n");
        return 1;
    } else if(a==b || b==c || a==c) {
        printf("Isosceles triangle\n");
        return 2;
    } else {
        printf("Scalene triangle\n");
        return 3;
    }
}

int greatestOfThree() {
    int a, b, c;
    printf("Enter three numbers: ");
    scanf("%d %d %d", &a, &b, &c);
    if(a>b && a>c) {
        printf("%d is greatest\n", a);
        return a;
    } else if(b>c) {
        printf("%d is greatest\n", b);
        return b;
    } else {
        printf("%d is greatest\n", c);
        return c;
    }
}

int marksClass() {
    int marks;
    printf("Enter marks: ");
    scanf("%d", &marks);
    if(marks>75) {
        printf("Distinction\n");
        return 4;
    } else if(marks>65) {
        printf("First Class\n");
        return 3;
    } else if(marks>55) {
        printf("Second Class\n");
        return 2;
    } else if(marks>=40) {
        printf("Pass Class\n");
        return 1;
    } else {
        printf("Fail\n");
        return 0;
    }
}

float priceDiscount() {
    float price, discount;
    char student;
    printf("Enter price and student(y/n): ");
    scanf("%f %c", &price, &student);
    if(student=='y' || student=='Y') {
        if(price>500)
            discount = price*0.2;
        else
            discount = price*0.1;
    } else {
        if(price>600)
            discount = price*0.15;
        else
            discount = 0;
    }
    return discount;
}

int divisibleCheck() {
    int n;
    printf("Enter number: ");
    scanf("%d", &n);
    if(n%3==0 && n%5!=0) {
        printf("Divisible by 3 but not by 5\n");
        return 1;
    } else if(n%5==0 && n%3!=0) {
        printf("Divisible by 5 but not by 3\n");
        return 2;
    } else if(n%3==0 && n%5==0) {
        printf("Divisible by both\n");
        return 3;
    } else {
        printf("Divisible by None\n");
        return 0;
    }
}

int ageCategory() {
    int age;
    printf("Enter age: ");
    scanf("%d", &age);
    if(age<12) {
        printf("Child\n");
        return 1;
    } else if(age<=19) {
        printf("Teenager\n");
        return 2;
    } else if(age<=59) {
        printf("Adult\n");
        return 3;
    } else {
        printf("Senior\n");
        return 4;
    }
}
