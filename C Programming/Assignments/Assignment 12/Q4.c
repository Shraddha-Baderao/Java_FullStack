#include <stdio.h>
#include <string.h>
char* exchange1stlast(char* str);
void main()
{
  char str[10];

  printf("enter string: ");
  scanf("%s",str);



  char* finalstr = exchange1stlast(str);

  printf("After replacing 1st and last charecter the final string is: %s ",finalstr);


}
char* exchange1stlast(char* str)
{


    int i = 0,count=0;
    while(str[i]!='\0')
    {
        count=i;
        i++;

    }

if(count>0){
     int temp = str[0];
    str[0]= str[count];
    str[count] = temp;
}



    return str;
}



