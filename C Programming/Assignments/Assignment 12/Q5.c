#include <stdio.h>
#include <string.h>
int countvowels(char* str);
void main()
{
  char str[10];
  int count;

  printf("enter string: ");
  scanf("%s",str);



  int finalstr = countvowels(str);

  printf("Counted total vowels in string: %d ",finalstr);


}
int countvowels(char* str)
{


    int i = 0,count=0;
    while(str[i]!='\0')
    {
        if(str[i]=='a'||str[i]=='e'||str[i]=='i'||str[i]=='o'||str[i]=='u')
        {
           count++;
        }

        i++;
    }

    return count;
}
