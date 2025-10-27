#include <stdio.h>

void evenodd();
void palindrome();
void leapyear();
void vowelconsonant();
void votingeligibility();
void casecheck();
void totalsalary();

int main() {
    evenodd();
    palindrome();
    leapyear();
    vowelconsonant();
    votingeligibility();
    casecheck();
    totalsalary();

    return 0;
}

void evenodd() {
    int n;
    printf("Enter a number: ");
    scanf("%d", &n);

    if (n % 2 == 0)
        printf("%d is Even\n", n);
    else
        printf("%d is Odd\n", n);
}

void palindrome() {
    int n, rev = 0, temp;
    printf("Enter a 3-digit number: ");
    scanf("%d", &n);

    temp = n;
    while (temp != 0) {
        rev = rev * 10 + temp % 10;
        temp /= 10;
    }

    if (rev == n)
        printf("%d is a palindrome\n", n);
    else
        printf("%d is not a palindrome\n", n);
}

void leapyear() {
    int year;
    printf("Enter a year: ");
    scanf("%d", &year);

    if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
        printf("%d is a leap year\n", year);
    else
        printf("%d is not a leap year\n", year);
}

void vowelconsonant() {
    char ch;
    printf("Enter a character: ");
    scanf(" %c", &ch);

    if (ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u'||
        ch=='A'||ch=='E'||ch=='I'||ch=='O'||ch=='U')
        printf("%c is a vowel\n", ch);
    else
        printf("%c is a consonant\n", ch);
}

void votingeligibility() {
    int age;
    printf("Enter age: ");
    scanf("%d", &age);

    if (age >= 18)
        printf("You are eligible to vote\n");
    else
        printf("You are not eligible to vote\n");
}

void casecheck() {
    char ch;
    printf("Enter a character: ");
    scanf(" %c", &ch);

    if (ch >= 'A' && ch <= 'Z')
        printf("%c is Uppercase\n", ch);
    else if (ch >= 'a' && ch <= 'z')
        printf("%c is Lowercase\n", ch);
    else
        printf("%c is not an alphabet\n", ch);
}


void totalsalary() {
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
    printf("Total salary = %.2f\n", total);
}
