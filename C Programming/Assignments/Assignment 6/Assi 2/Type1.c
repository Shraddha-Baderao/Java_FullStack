#include <stdio.h>

void calculator();
void triangleType();
void greatestOfThree();
void marksClass();
void priceDiscount();
void divisibleCheck();
void ageCategory();

int main() {
    calculator();
    triangleType();
    greatestOfThree();
    marksClass();
    priceDiscount();
    divisibleCheck();
    ageCategory();
    return 0;
}

void calculator() {
    int a, b;
    char op;
    printf("Enter two numbers and an operator (+,-,*,/,%%): ");
    scanf("%d %d %c", &a, &b, &op);
    if(op=='+')
        printf("Result = %d\n", a+b);
    else if(op=='-')
        printf("Result = %d\n", a-b);
    else if(op=='*')
        printf("Result = %d\n", a*b);
    else if(op=='/')
        printf("Result = %d\n", a/b);
    else if(op=='%')
        printf("Result = %d\n", a%b);
    else
        printf("Invalid operator\n");
}

void triangleType() {
    int a, b, c;
    printf("Enter three sides of triangle: ");
    scanf("%d %d %d", &a, &b, &c);
    if(a==b && b==c)
        printf("Equilateral triangle\n");
    else if(a==b || b==c || a==c)
        printf("Isosceles triangle\n");
    else
        printf("Scalene triangle\n");
}

void greatestOfThree() {
    int a, b, c;
    printf("Enter three numbers: ");
    scanf("%d %d %d", &a, &b, &c);
    if(a>b && a>c)
        printf("%d is greatest\n", a);
    else if(b>c)
        printf("%d is greatest\n", b);
    else
        printf("%d is greatest\n", c);
}

void marksClass() {
    int marks;
    printf("Enter marks: ");
    scanf("%d", &marks);
    if(marks>75)
        printf("Distinction\n");
    else if(marks>65)
        printf("First Class\n");
    else if(marks>55)
        printf("Second Class\n");
    else if(marks>=40)
        printf("Pass Class\n");
    else
        printf("Fail\n");
}

void priceDiscount() {
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
    printf("Discount = %.2f\n", discount);
}

void divisibleCheck() {
    int n;
    printf("Enter number: ");
    scanf("%d", &n);
    if(n%3==0 && n%5!=0)
        printf("Divisible by 3 but not by 5\n");
    else if(n%5==0 && n%3!=0)
        printf("Divisible by 5 but not by 3\n");
    else if(n%3==0 && n%5==0)
        printf("Divisible by both\n");
    else
        printf("Divisible by None\n");
}

void ageCategory() {
    int age;
    printf("Enter age: ");
    scanf("%d", &age);
    if(age<12)
        printf("Child\n");
    else if(age<=19)
        printf("Teenager\n");
    else if(age<=59)
        printf("Adult\n");
    else
        printf("Senior\n");
}
