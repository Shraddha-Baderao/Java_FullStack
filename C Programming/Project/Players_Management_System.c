
#include<stdio.h>
#include<string.h>

typedef struct {
    int jursey_no;
    char name[20];
    int runs;
    int wickets;
    int matches_played;
} players;

void storedetails(players arr[],int *n);
void displaydetails(players arr[],int n);
void addnew(players arr[],int *n);
void searchplayer(players arr[],int n);
int searchbyjersey(players arr[],int n);
int searchbyname(players arr[],int n);


void removeplayers(players arr[],int *n);
void updateplayers(players arr[],int *n);
void sortedplayers(players arr[], int n);
void sortbyruns(players tarr[],int n);
void sortbywickets(players tarr[],int n);
void top3players(players arr[], int n);
void top3byruns(players arr[], int n);
void top3bywickets(players arr[], int n);


int main()
{
    int choice;
    int n=0;

    players p[100];

    while(1){
            printf("---------------------Players Management System----------------------------");
            printf("\nEnter 1 to store players\n");
            printf("Enter 2 to display All players\n");
            printf("Enter 3 to add new players\n");
            printf("Enter 4 to search players by (jersey Number/Player Name\n");
            printf("Enter 5 to Remove players by (Jersey Number)\n");
            printf("Enter 6 to update players by (Runs,Matches,Wickets)\n");
            printf("Enter 7 to Display sorted (max/min) Players by (Runs/Wickets) players\n");
            printf("Enter 8 to Display sorted (max/min) 3 Players by (Runs/Wickets) players\n");
            printf("Enter 9 to Exit\n");


            printf("\n---Enter your choice:---\n-> ");
            scanf("%d",&choice);

            if(choice==1)
            {
                    printf("Enter Players  info: \n");
                    printf("Jersey, Player name, Runs, Wickets, Matches Played\n");
                    storedetails(p,&n);
            }

            else if(choice==2)
                {
                    printf("\n All Players info \n\n");
                    displaydetails(p,n);
            }
            else if(choice==3){
                    addnew(p,&n);
                    printf("\n---Sucessfully Added New Player---\n");
            }
            else if(choice==4)
            {
                printf("\nsearch by (Jersy Number/ Player Name)\n");
                searchplayer(p, n);
                printf("\n---Sucessfully Searched Player---\n");
    }

               else if(choice==5)
               {
                   removeplayers(p,&n);
               }

               else if(choice==6)
               {
                   printf("\nUpdate Player (Runs, Matches Played, Wickets) By Jersey Number\n");
                    updateplayers(p,&n);
               }
               else if(choice==7)
               {
                   printf("\nSort Players By (Runs/Wickets)\n");
                   sortedplayers(p,n);
               }

               else if(choice==8)
               {
                   top3players(p,n);
               }

               else if(choice == 9)
                {
                    printf("\nExiting Program... Goodbye!\n");
               break;
               }

            else
                {
                    printf("\nInvalid choice! Try again.\n");
            }
            }
}

void storedetails(players arr[], int *n)
{
    int count;
    printf("How many players you want to enter: ");
    scanf("%d", &count);

    for(int i = 0; i < count; i++)
    {
        printf("\nPlayer %d\n", *n + 1);
        printf("Enter Player Jersey number: ");
        scanf("%d", &arr[*n].jursey_no);

        printf("Enter Player Name: ");
        scanf("%s", arr[*n].name);

        printf("Enter Player Runs: ");
        scanf("%d", &arr[*n].runs);

        printf("Enter Player Wickets: ");
        scanf("%d", &arr[*n].wickets);

        printf("Enter Matches Played: ");
        scanf("%d", &arr[*n].matches_played);

        (*n)++;
    }
}

void displaydetails(players arr[],int n)
{
    if(n==0)
    {
        printf("Player Info Not Entered");
        return;
    }


    printf("----------------------------------------------------------------------------------------------------\n");
    printf("|sr.no.|jursey no.|            | name |           |runs|          |wickets|         |matches_played|\n");
    printf("*------*--------*--------------*------*-----------*----*----------*-------*---------*--------------*\n");
    int i;

    for(int i=0;i<n;i++)
   {

       printf("|  %d.  |   %d    |            |%s |           | %d |       |%d|           |%d| \n", i+1,arr[i].jursey_no,arr[i].name,arr[i].runs,arr[i].wickets,arr[i].matches_played);
       printf("-------------------------------------------------------------------------------------------------\n");

   }


}
void addnew(players arr[],int *n)
{
    printf("\nExisting Players:\n");
    displaydetails(arr, *n);

    printf("\nAdd New Players:\n");
    storedetails(arr, n);
}

void searchplayer(players arr[],int n)
{
    int ch;
    printf("\nPress 1 to search by Jersey number\n");
    printf("Press 2 to seach by name\n");
    printf("Enter your choice: ");
    scanf("%d\n", &ch);

    if(ch == 1) {
        int i=searchbyjersey(arr, n);
        if(i==-1)
               {
                   printf("\nPlayer not found \n");

            }
else{
        printf("\nFound at:\n" ,i+1);
        printf("----------------------------------------------------------------------------------------------------\n");
    printf("|sr.no.|jursey no.|            | name |           |runs|          |wickets|         |matches_played|\n");
    printf("*------*--------*--------------*------*-----------*----*----------*-------*---------*--------------*\n");
    int i;



       printf("|  %d.  |   %d    |            |%s |           | %d |       |%d|           |%d| \n", i+1,arr[i].jursey_no,arr[i].name,arr[i].runs,arr[i].wickets,arr[i].matches_played);
       printf("--------------------------------------------------------------------------------------------------\n\n");


    }
    }
    else if(ch == 2) {
        int i=searchbyname(arr, n);
        if(i==-1)
               {
                   printf("\nPlayer not found\n");

            }
else{
        printf("----------------------------------------------------------------------------------------------------\n");
printf("|sr.no.|jursey no.| name | runs | wickets | matches_played |\n");
printf("----------------------------------------------------------------------------------------------------\n");

printf("|  %d.  |   %d    |  %s  |  %d  |   %d    |      %d        |\n",
       i+1, arr[i].jursey_no, arr[i].name, arr[i].runs, arr[i].wickets, arr[i].matches_played);

printf("----------------------------------------------------------------------------------------------------\n");

    }
    }
    else {
        printf("Invalid choice\n");
        return;
    }
}
int searchbyjersey(players arr[],int n)
{
    int jersey;
    printf("Enter jersey number to search: ");
    scanf("%d", &jersey);

    for(int i=0;i<n;i++)
    {
        if(arr[i].jursey_no==jersey)
        {
            return i;
        }

    }
return -1;
}


int searchbyname(players arr[],int n)
{
     char name[20];
     printf("Enter name which you want to search: ");
     scanf("%s",name);


    for(int i=0;i<n;i++)
    {
        if(strcmp(arr[i].name,name)==0)
        {
            return i;
        }

    }
return -1;
}
void removeplayers(players arr[],int *n)
{
    int jursey;
    printf("Enter juresey number: ");
    scanf("%d",&jursey);
               int found = -1;

               for(int i=0; i<*n; i++)
                {
                    if(arr[i].jursey_no == jursey) {
                    found = i;
                    break;
                 }
                }

               if(found == -1)
                {
                    printf("\n---Player not found---\n");
               return;
               }

               for(int i = found; i < *n-1; i++)
               {
                    arr[i] = arr[i+1];
               }
            (*n)--;
            printf("Player removed successfully!\n");

}
void updateplayers(players arr[],int *n)
{
    int i= searchbyjersey(arr, *n);
               if(i==-1)
               {
                   printf("Player not found");

            }

    int ch;
    printf("\nEnter 1 to update runs\n");
    printf("Enter 2 to update Matches\n");
    printf("Enter 3 to update wickets\n");
    printf("\nEnter your choice: ");
    scanf("%d", &ch);



    switch(ch)
    {
    case 1:{
        int new_runs;
    printf("\nEnter new runs: ");
    scanf("%d",&new_runs);

    arr[i].runs=new_runs;
              printf("\n---Successfully update runs---\n");


               break;

    }
    case 2:
        {
            int new_matches;
    printf("Enter new Matches: ");
    scanf("%d",&new_matches);

    arr[i].matches_played=new_matches;
              printf("\n---Successfully update matches---\n");

               break;
        }

        case 3:
        {
            int new_wickets;
    printf("Enter new wickets: ");
    scanf("%d",&new_wickets);

    arr[i].wickets=new_wickets;
              printf("\n---Successfully update matches---\n");


               break;

        }

    default:
        printf("Invalid\n");
}

}

void sortedplayers(players arr[], int n)
{
    players tarr[100];
    for(int i = 0; i < n; i++)
    {
        tarr[i] = arr[i];
    }

    int ch;
    printf("\nEnter 1 to Sort by Runs \n");
    printf("Enter 2 to Sort by Wickets \n");
    printf("Enter your choice: ");
    scanf("%d", &ch);

    if(ch == 1) {
        sortbyruns(tarr, n);
    }
    else if(ch == 2) {
        sortbywickets(tarr, n);
    }
    else {
        printf("Invalid choice\n");
        return;
    }

    printf("\nSorted Players:\n");
    printf("----------------------------------------------------------------------------------------------------\n");
    printf("|sr.no.|jursey no.|            | name |           |runs|          |wickets|         |matches_played|\n");
    printf("*------*--------*--------------*------*-----------*----*----------*-------*---------*--------------*\n");
    int i;

    for(int i=0;i<n;i++)
   {

       printf("|  %d.  |   %d    |            |%s |           | %d |       |%d|           |%d| \n", i+1,tarr[i].jursey_no,tarr[i].name,tarr[i].runs,tarr[i].wickets,tarr[i].matches_played);
       printf("--------------------------------------------------------------------------------------------------\n");

   }
}

void sortbyruns(players tarr[], int n)
    {

        players temp;
        for(int i=0;i<n-1;i++)
         {
             for(int j=i+1;j<n;j++)
                {
                    if(tarr[i].runs < tarr[j].runs)
                    {
                        temp = tarr[i];
                        tarr[i] = tarr[j];
                        tarr[j] = temp;
                    }
                }
        }
    }

void sortbywickets(players tarr[], int n){
    players temp;
        for(int i = 0; i < n-1; i++)
            {
                for(int j = i+1; j < n; j++)
                {
                    if(tarr[i].wickets < tarr[j].wickets)
                    {
                        temp = tarr[i];
                        tarr[i] = tarr[j];
                        tarr[j] = temp;
                    }
                }
            }
    }


void top3players(players arr[], int n)
{
    players tarr[100];

    for(int i = 0; i < n; i++)
        tarr[i] = arr[i];

    int ch;
    printf("\nEnter 1 to Display Top 3 Players by Runs \n");
    printf("Enter 2 to Display Top 3 Players by Wickets \n");
    printf("Enter your choice: ");
    scanf("%d", &ch);

    if(ch == 1) {
        top3byruns(tarr, n);
    }
    else if(ch == 2) {
        top3bywickets(tarr, n);
    }
    else {
        printf("Invalid choice\n");
        return;
    }
printf("----------------------------------------------------------------------------------------------------\n");
    printf("|sr.no.|jursey no.|            | name |           |runs|          |wickets|         |matches_played|\n");
    printf("*------*--------*--------------*------*-----------*----*----------*-------*---------*--------------*\n");
    int i;

    for(int i=0;i<3;i++)
   {

       printf("|  %d.  |   %d    |            |%s |           | %d |       |%d|           |%d| \n", i+1,tarr[i].jursey_no,tarr[i].name,tarr[i].runs,tarr[i].wickets,tarr[i].matches_played);
       printf("--------------------------------------------------------------------------------------------------\n");

    }
}

void top3byruns(players tarr[], int n)
    {

        players temp;
        for(int i=0;i<n-1;i++)
         {
             for(int j=i+1;j<n;j++)
                {
                    if(tarr[i].runs < tarr[j].runs)
                    {
                        temp = tarr[i];
                        tarr[i] = tarr[j];
                        tarr[j] = temp;
                    }
                }
        }

    }

void top3bywickets(players tarr[], int n)
{
    players temp;
        for(int i = 0; i < n-1; i++)
            {
                for(int j = i+1; j < n; j++)
                {
                    if(tarr[i].wickets < tarr[j].wickets)
                    {
                        temp = tarr[i];
                        tarr[i] = tarr[j];
                        tarr[j] = temp;
                    }
                }
            }
    }







