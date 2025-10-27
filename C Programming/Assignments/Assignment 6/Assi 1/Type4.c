#include <stdio.h>

int evenOdd(int n);
int palindrome(int n);
int leapYear(int year);
int vowelConsonant(char ch);
int votingEligibility(int age);
int caseCheck(char ch);
float totalSalary(float basic);

int main() {
    int n, year, age, result;
    char ch;
    float basic, salary;

    printf("Enter a number to check even/odd: ");
    scanf("%d", &n);
    result = evenOdd(n);
    if (result == 1)
        printf("%d is Even\n", n);
    else
        printf("%d is Odd\n", n);

    printf("Enter a 3-digit number to check palindrome: ");
    scanf("%d", &n);
    result = palindrome(n);
    if (result == 1)
        printf("%d is a palindrome\n", n);
    else
        printf("%d is not a palindrome\n", n);

    printf("Enter a year to check leap year: ");
    scanf("%d", &year);
    result = leapYear(year);
    if (result == 1)
        printf("%d is a leap year\n", year);
    else
        printf("%d is not a leap year\n", year);

    printf("Enter a character to check vowel/consonant: ");
    scanf(" %c", &ch);
    result = vowelConsonant(ch);
    if (result == 1)
        printf("%c is a vowel\n", ch);
    else
        printf("%c is a consonant\n", ch);

    printf("Enter age to check voting eligibility: ");
    scanf("%d", &age);
    result = votingEligibility(age);
    if (result == 1)
        printf("You are eligible to vote\n");
    else
        printf("You are not eligible to vote\n");

    printf("Enter a character to check uppercase/lowercase: ");
    scanf(" %c", &ch);
    result = caseCheck(ch);
    if (result == 1)
        printf("%c is Uppercase\n", ch);
    else if (result == 2)
        printf("%c is Lowercase\n", ch);
    else
        printf("%c is not an alphabet\n", ch);

    printf("Enter basic salary to calculate total salary: ");
    scanf("%f", &basic);
    salary = totalSalary(basic);
    printf("Total salary = %.2f\n", salary);

    return 0;
}

int evenOdd(int n) {
    if (n % 2 == 0)
        return 1;
    else
        return 0;
}

int palindrome(int n) {
    int rev = 0, temp = n;
    while (temp != 0) {
        rev = rev * 10 + temp % 10;
        temp /= 10;
    }
    if (rev == n)
        return 1;
    else
        return 0;
}

int leapYear(int year) {
    if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
        return 1;
    else
        return 0;
}

int vowelConsonant(char ch) {
    if (ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u'||ch=='A'||ch=='E'||ch=='I'||ch=='O'||ch=='U')
        return 1;
    else
        return 0;
}

int votingEligibility(int age) {
    if (age >= 18)
        return 1;
    else
        return 0;
}

int caseCheck(char ch) {
    if (ch >= 'A' && ch <= 'Z')
        return 1;
    else if (ch >= 'a' && ch <= 'z')
        return 2;
    else
        return 0;
}

float totalSalary(float basic) {
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
    return total;
}
