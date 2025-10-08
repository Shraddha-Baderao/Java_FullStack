#include <stdio.h>
void main()
{
    int age;
    
    printf("Enter the age: ");
    scanf("%d",&age);
    
    if(age<12)
    {
        printf("Person is child");
    }
    else if(age>=12 && age<=19)
    {
        printf("The person is Teenager");
    }
    else if(age>=20 && age<=59){
        printf("The prson is Adult");
    }
    else{
        printf("The person is Senior");
    }
}