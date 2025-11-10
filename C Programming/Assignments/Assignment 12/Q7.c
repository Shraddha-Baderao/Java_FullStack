#include <stdio.h>
#include <string.h>
char* removeodd(char* str);
void main()
{
  char str[50];

  printf("enter string: ");
  fgets(str, sizeof(str), stdin);


  char* finalstr = removeodd(str);

  printf("After removing charecter of Odd Index Values in a String : %s ",finalstr);


}
char* removeodd(char* str)
{


    int i = 0,j=0;
    while(str[i]!='\0')
    {

       if(i%2==0)
       {
         str[j]=str[i];

         j++;

       }

       i++;

    }
    str[j]='\0';


    return str;
}

