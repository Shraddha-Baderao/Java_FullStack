#include <stdio.h>

void evenOdd(int n);
void palindrome(int n);
void leapYear(int year);
void vowelConsonant(char ch);
void votingEligibility(int age);
void caseCheck(char ch);
void totalSalary(float basic);

int main() {
    int n, year, age;
    char ch;
    float basic;

    printf("Enter a number to check even/odd: ");
    scanf("%d", &n);
    evenOdd(n);

    printf("Enter a 3-digit number to check palindrome: ");
    scanf("%d", &n);
    palindrome(n);

    printf("Enter a year to check leap year: ");
    scanf("%d", &year);
    leapYear(year);

    printf("Enter a character to check vowel/consonant: ");
    scanf(" %c", &ch);
    vowelConsonant(ch);

    printf("Enter age to check voting eligibility: ");
    scanf("%d", &age);
    votingEligibility(age);

    printf("Enter a character to check uppercase/lowercase: ");
    scanf(" %c", &ch);
    caseCheck(ch);

    printf("Enter basic salary to calculate total salary: ");
    scanf("%f", &basic);
    totalSalary(basic);

    return 0;
}

void evenOdd(int n) {
    if (n % 2 == 0)
        printf("%d is Even\n", n);
    else
        printf("%d is Odd\n", n);
}

void palindrome(int n) {
    int rev = 0, temp = n;
    while (temp != 0) {
        rev = rev * 10 + temp % 10;
        temp /= 10;
    }
    if (rev == n)
        printf("%d is a palindrome\n", n);
    else
        printf("%d is not a palindrome\n", n);
}

void leapYear(int year) {
    if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
        printf("%d is a leap year\n", year);
    else
        printf("%d is not a leap year\n", year);
}

void vowelConsonant(char ch) {
    if (ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u'||ch=='A'||ch=='E'||ch=='I'||ch=='O'||ch=='U')
        printf("%c is a vowel\n", ch);
    else
        printf("%c is a consonant\n", ch);
}

void votingEligibility(int age) {
    if (age >= 18)
        printf("You are eligible to vote\n");
    else
        printf("You are not eligible to vote\n");
}

void caseCheck(char ch) {
    if (ch >= 'A' && ch <= 'Z')
        printf("%c is Uppercase\n", ch);
    else if (ch >= 'a' && ch <= 'z')
        printf("%c is Lowercase\n", ch);
    else
        printf("%c is not an alphabet\n", ch);
}

void totalSalary(float basic) {
    float da, ta, hra, total;
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
    printf("Total salary = %.2f\n", total);
}
