#include <stdio.h>

int calculator(int a, int b, char op);
int triangleType(int a, int b, int c);
int greatestOfThree(int a, int b, int c);
int marksClass(int marks);
float priceDiscount(float price, char student);
int divisibleCheck(int n);
int ageCategory(int age);

int main() {
    int a, b, c, marks, n, age, result;
    char op, student;
    float discount;

    printf("Enter two numbers and an operator (+,-,*,/,%%): ");
    scanf("%d %d %c", &a, &b, &op);
    result = calculator(a, b, op);

    printf("Enter three sides of triangle: ");
    scanf("%d %d %d", &a, &b, &c);
    result = triangleType(a, b, c);

    printf("Enter three numbers: ");
    scanf("%d %d %d", &a, &b, &c);
    result = greatestOfThree(a, b, c);

    printf("Enter marks: ");
    scanf("%d", &marks);
    result = marksClass(marks);

    printf("Enter price and student(y/n): ");
    scanf("%f %c", &discount, &student);  // price stored in discount temporarily
    discount = priceDiscount(discount, student);
    printf("Discount = %.2f\n", discount);

    printf("Enter number: ");
    scanf("%d", &n);
    result = divisibleCheck(n);

    printf("Enter age: ");
    scanf("%d", &age);
    result = ageCategory(age);

    return 0;
}

int calculator(int a, int b, char op) {
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

int triangleType(int a, int b, int c) {
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

int greatestOfThree(int a, int b, int c) {
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

int marksClass(int marks) {
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

float priceDiscount(float price, char student) {
    float discount;
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

int divisibleCheck(int n) {
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

int ageCategory(int age) {
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
