package classes;

import java.util.Scanner;

public class UserAcc {
	
	private String name;
	private long id;
	private double balance;

	public UserAcc(String name, double balance) {
		this.name = name;
		this.balance = balance;
	}
	
	
	
	public UserAcc() {
		super();
	}

public static UserAcc AddUser() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter name: ");
		String name = scan.nextLine();
		System.out.print("Enter balance: ");
		double balance = scan.nextDouble();
		return new UserAcc(name, balance);
	}

	public String getName() {
		return name;
	}

	public long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	@Override
	public String toString() {
		return "UserAcc [name=" + name + ", id=" + id + ", balance=" + balance + "]";
	}

}
