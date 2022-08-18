
package main;

import java.util.*;

//withdraw, deposit, transfer, history in an ATM
/**
//Operational ATM Program
**/

public class ATM {

	static LinkedList<String> history = new LinkedList<String>();
	Account checking = new Account("Checking", 1000.0);
	Account savings = new Account("Savings", 1000.0);

	// Front-End User Interface
	public static void main(String[] args) {
		Account checking = new Account("Checking", 1000.0);
		Account savings = new Account("Savings", 1000.0);
		ATM machine = new ATM(checking, savings, history);

		System.out.println("This is your ATM.");
		System.out.println("Balances:");
		System.out.println("Checking: " + machine.checking.amount);
		System.out.println("Savings: " + machine.savings.amount);
		System.out.println("\nEnter [1] for withdraw, [2] for deposit, [3] for transfer, [4] to check history");

		while (true) {
			Scanner scan = new Scanner(System.in);
			double input = scan.nextDouble();
			if (input == 1) {
				System.out.println("Which account would you like to withdraw from? [1] = Checking, [2] = Savings");
				double inputNext = scan.nextDouble();
				if (inputNext == 1) {
					System.out.println("How much would you like to withdraw?");
					double inputAmount = scan.nextDouble();
					System.out.println(machine.withdraw(machine, checking, inputAmount));

				}
				if (inputNext == 2) {
					System.out.println("How much would you like to withdraw?");
					double inputAmount = scan.nextDouble();
					System.out.println(machine.withdraw(machine, savings, inputAmount));
				}
			}
			if (input == 2) {
				System.out.println("Which account would you like to deposit into? [1] = Checking, [2] = Savings");
				double inputNext = scan.nextDouble();
				if (inputNext == 1) {
					System.out.println("How much would you like to deposit?");
					double inputAmount = scan.nextDouble();
					System.out.println(machine.deposit(machine, checking, inputAmount));

				}
				if (inputNext == 2) {
					System.out.println("How much would you like to deposit?");
					double inputAmount = scan.nextDouble();
					System.out.println(machine.deposit(machine, savings, inputAmount));

				}
			}
			if (input == 3) {
				System.out.println("Which account would you like to transfer from? [1] = Checking, [2] = Savings");
				double inputNext = scan.nextDouble();
				if (inputNext == 1) {
					System.out.println("How much would you like to transfer?");
					double inputAmount = scan.nextDouble();
					System.out.println(machine.transfer(machine, checking, savings, inputAmount));

				}
				if (inputNext == 2) {
					System.out.println("How much would you like to transfer?");
					double inputAmount = scan.nextDouble();
					System.out.println(machine.transfer(machine, savings, checking, inputAmount));
				}
			}
			if (input == 4) {
				System.out.println(machine.getHistory(machine));
			}

			System.out.println("\nBalances:");
			System.out.println("Checking: " + machine.checking.amount);
			System.out.println("Savings: " + machine.savings.amount);

			System.out.println("\nEnter [1] for withdraw, [2] for deposit, [3] for transfer, [4] to check history");

		}
	}

	// Back-End
	public static String deposit(ATM machine, Account account, double amount) {
		if (account.name == "Checking") {
			String output = amount + " added to checking";
			machine.checking.amount += amount;
			machine.history.add(output);
			return output;
		} else if (account.name == "Savings") {
			String output = amount + " added to savings";
			machine.savings.amount += amount;
			machine.history.add(output);
			return output;
		}

		return "Account not found";

	}

	public static String withdraw(ATM machine, Account account, double amount) {
		if (account.name == "Checking" && account.amount - amount >= 0) {
			String output = amount + " taken from checking";
			machine.checking.amount -= amount;
			machine.history.add(output);
			return output;
		} else if (account.name == "Savings" && account.amount - amount >= 0) {
			String output = amount + " taken from savings";
			machine.savings.amount -= amount;
			machine.history.add(output);
			return output;
		} else if (account.name == "Checking" && account.amount - amount < 0) {
			return "Insufficient funds";
		} else if (account.name == "Savings" && account.amount - amount < 0) {
			return "Insufficient funds";
		}

		return "Account not found";

	}

	public static String transfer(ATM machine, Account withdraw, Account deposit, double amount) {
		if (withdraw.name == "Checking" && machine.checking.amount - amount >= 0) {
			machine.checking.amount -= amount;
			machine.savings.amount += amount;
			String output = amount + " transfered to savings";
			machine.history.add(output);
			return output;
		} else if (withdraw.name == "Savings" && machine.savings.amount - amount >= 0) {
			machine.savings.amount -= amount;
			machine.checking.amount += amount;
			String output = amount + " transfered to checking";
			machine.history.add(output);
			return output;
		} else if (withdraw.name == "Checking" && machine.checking.amount - amount < 0) {
			return "Checking account has insufficient fuinds";
		} else if (withdraw.name == "Savings" && machine.checking.amount - amount < 0) {
			return "Savings account has insufficient fuinds";
		}

		return "Account not found.";
	}

	public static String getHistory(ATM machine) {
		String returnString = "";
		for (String histories : machine.history) {
			returnString += histories + "\n";
		}

		return returnString;
	}

	public ATM(Account checking, Account savings, LinkedList<String> history) {
		this.savings = savings;
		this.checking = checking;
		this.history = history;
		this.history.add("Transaction History:");
	}

	private static class Account {
		double amount;
		String name;

		private Account(String name, double amount) {
			this.name = name;
			this.amount = amount;
		}
	}

}
