#include <stdio.h>
#include<string.h>

char* replace(char* str,char ch,char rep);

void main()
{
  char str[50],ch,rep='$';
  printf("enter your name");
  scanf("%s",str);


  printf("enter charecter to replace");
  scanf("%c",&ch);

  char* res= replace(str,ch,rep);

   printf("print name%s:",res);
}

 char* replace(char* str,char ch,char rep)
 {
     int i=0;
  while(str[i]!='\0')
  {
     if(str[i]==ch)
     {
        str[i]=rep;
     }
     i++;

  }
 return str;
 }



