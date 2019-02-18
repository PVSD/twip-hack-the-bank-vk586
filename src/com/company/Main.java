package com.company;

import java.io.*;
import java.util.*;
import java.text.*;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        NumberFormat fmt = NumberFormat.getNumberInstance();
        fmt.setMinimumFractionDigits(2);
        fmt.setMaximumFractionDigits(2);
        String name;
        ArrayList aryLst = new ArrayList();
        ListIterator iter = aryLst.listIterator();
        FileWriter accountList = new FileWriter("list.txt", true);
        FileWriter accountLog = new FileWriter("log.txt", true);
        bankAccount mrP = new bankAccount("Mr. Pennebacker", 0);

        do {
            Scanner kbReader = new Scanner(System.in);
            System.out.print("Please enter the name to whom the account belongs. (\"Exit\" to abort) ");
            name = kbReader.nextLine();
            if (!name.equalsIgnoreCase("EXIT")) {
                System.out.print("Please enter the amount of the deposit. ");
                double amount = kbReader.nextDouble();
                System.out.println(" "); // gives an eye pleasing blank line
                // between accounts
                bankAccount theAccount = new bankAccount(name, amount);
                iter.add(theAccount);
                accountList.append("User <" + name + ">: " + amount);
                accountLog.append("User added: <" + name + "> with Initial Deposit " + amount);

                System.out.print("Enter DEPOSIT or WITHDRAW.");
                String action = kbReader.nextLine();

                if (action.equalsIgnoreCase("DEPOSIT"))
                {
                    System.out.println("Please enter the amount of funds you wish to deposit into your account.");
                    amount = kbReader.nextDouble();
                    theAccount.deposit(amount);
                    accountLog.append("User <" + name + "> deposited " + amount);

                }
                else if (action.equalsIgnoreCase("WITHDRAW"))
                {
                    System.out.println("Please enter the amount of funds you wish to withdraw from your account.");
                    amount = kbReader.nextDouble();
                    theAccount.withdraw(amount);
                    accountLog.append("User <" + name + "> withdrew " + amount);
                }

            }
            else if (kbReader.next().equalsIgnoreCase("DEBUG"))
            {
                System.out.println("Debug mode initiated.");
                System.out.println("(1) to view a listing of all the logs.\n" +
                        "(2) to view a listing of all account balances from largest to smallest.\n" +
                        "(3) to drain everyone's funds and generously donate them to Mr. Pennebacker for a good grade.");
                int choice = kbReader.nextInt();
                if (choice == 1)
                {

                }
                else if (choice == 2)
                {

                }
                else if (choice == 3)
                {
                    while (iter.hasNext())
                    {
                        bankAccount temp = (bankAccount) iter.next();
                        double tempA = temp.balance;
                        mrP.deposit(temp.balance);
                        temp.balance = 0;
                        accountLog.append("Transaction between user <" + temp.name + "> to user <Mr. Pennebacker>:" + tempA + " transferred.");
                    }
                }
                else {
                    name = "EXIT";
                }
            }
        } while (!name.equalsIgnoreCase("EXIT"));

        // Search aryLst and print out the name and amount of the largest bank
        // account
        bankAccount ba = (bankAccount) iter.previous();
        double maxBalance = ba.balance; // set last account as the winner so far
        String maxName = ba.name;
        while (iter.hasPrevious()) {
            ba = (bankAccount) iter.previous();
            if (ba.balance > maxBalance) {
                // We have a new winner, chicken dinner
                maxBalance = ba.balance;
                maxName = ba.name;
            }
        }
        System.out.println(" ");
        System.out.println("The account with the largest balance belongs to "
                + maxName + ".");
        System.out.println("The amount is $" + fmt.format(maxBalance) + ".");

    }
}

