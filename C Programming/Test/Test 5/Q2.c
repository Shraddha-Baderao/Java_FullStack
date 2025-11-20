#include <stdio.h>

int deposit(int amount, int dep);
int withdraw(int amount, int wd);

int main()
{
    int amount, choice, money;

    printf("Enter initial amount in your account: ");
    scanf("%d", &amount);

    printf("\n1. Deposit\n2. Withdraw\nEnter your choice: ");
    scanf("%d", &choice);

    switch(choice)
    {
        case 1:
            printf("Enter amount to deposit: ");
            scanf("%d", &money);
            amount = deposit(amount, money);
            printf("Updated Balance = %d\n", amount);
            break;

        case 2:
            printf("Enter amount to withdraw: ");
            scanf("%d", &money);
            amount = withdraw(amount, money);
            break;

        default:
            printf("Invalid choice!");
    }

    return 0;
}

int deposit(int amount, int dep)
{
    amount = amount + dep;
    return amount;
}

int withdraw(int amount, int wd)
{
    if(amount < 3000)
    {
        printf("Can't withdraw, balance is not sufficient.\n");
    }
    else if(wd > amount)
    {
        printf("Can't withdraw, amount exceeds balance.\n");
    }
    else
    {
        amount = amount - wd;
        printf("Withdrawal successful.\nUpdated Balance = %d\n", amount);
    }
    return amount;
}
