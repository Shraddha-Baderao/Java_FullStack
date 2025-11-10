#include <stdio.h>
#include<string.h>

char* replace(char* str,char rep);

void main()
{
  char str[50],rep='$';
  printf("enter your string: ");
  fgets(str, sizeof(str), stdin);

  char* res= replace(str,rep);

   printf("print name%s:",res);
}

 char* replace(char* str,char rep)
 {
     int i=0;
  while(str[i]!='\0')
  {
     if(str[i]==' ')
     {
        str[i]=rep;
     }
     i++;

  }
 return str;
 }



