#include <stdio.h>

int evenOdd();
int palindrome();
int leapYear();
int vowelConsonant();
int votingEligibility();
int caseCheck();
float totalSalary();

int main() {
    int result;
    float salary;

    result = evenOdd();
    printf("Even/Odd result = %d\n", result);

    result = palindrome();
    printf("Palindrome result = %d\n", result);

    result = leapYear();
    printf("Leap Year result = %d\n", result);

    result = vowelConsonant();
    printf("Vowel/Consonant result = %d\n", result);

    result = votingEligibility();
    printf("Voting eligibility = %d\n", result);

    result = caseCheck();
    printf("Case check = %d\n", result);

    salary = totalSalary();
    printf("Total salary = %.2f\n", salary);

    return 0;
}

int evenOdd() {
    int n;
    printf("Enter a number: ");
    scanf("%d", &n);
    if (n % 2 == 0)
        return 1;
    else
        return 0;
}

int palindrome() {
    int n, rev = 0, temp;
    printf("Enter a 3-digit number: ");
    scanf("%d", &n);
    temp = n;
    while (temp != 0) {
        rev = rev * 10 + temp % 10;
        temp /= 10;
    }
    if (rev == n)
        return 1;
    else
        return 0;
}

int leapYear() {
    int year;
    printf("Enter a year: ");
    scanf("%d", &year);
    if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
        return 1;
    else
        return 0;
}

int vowelConsonant() {
    char ch;
    printf("Enter a character: ");
    scanf(" %c", &ch);
    if (ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u'||ch=='A'||ch=='E'||ch=='I'||ch=='O'||ch=='U')
        return 1;
    else
        return 0;
}

int votingEligibility() {
    int age;
    printf("Enter age: ");
    scanf("%d", &age);
    if (age >= 18)
        return 1;
    else
        return 0;
}

int caseCheck() {
    char ch;
    printf("Enter a character: ");
    scanf(" %c", &ch);
    if (ch >= 'A' && ch <= 'Z')
        return 1;
    else if (ch >= 'a' && ch <= 'z')
        return 2;
    else
        return 0;
}

float totalSalary() {
    float basic, da, ta, hra, total;
    printf("Enter basic salary: ");
    scanf("%f", &basic);
    if (basic <= 5000) {
        da = basic * 0.10;
        ta = basic * 0.20;
        hra = basic * 0.25;
    } else {
        da = basic * 0.15;
        ta = basic * 0.25;
        hra = basic * 0.30;
    }
    total = basic + da + ta + hra;
    return total;
}
