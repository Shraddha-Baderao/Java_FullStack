#include <stdio.h>
#include <string.h>
char* removechar(char* str, char ch);
void main()
{
  char str[10],ch;

  printf("enter string: ");
  fgets(str, sizeof(str), stdin);

  printf("Enter charecter you want to remove: ");
  scanf("%c",&ch);


  char* finalstr = removechar(str,ch);

  printf("After removing charecter the final string is: %s ",finalstr);


}
char* removechar(char* str, char ch)
{


    int i = 0,j;
    while(str[i]!='\0')
    {

       if(str[i]!=ch)
       {

         str[j]=str[i];
        j++;

       }



       i++;

    }
    str[j]='\0';


    return str;
}

