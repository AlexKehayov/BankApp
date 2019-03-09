package com.alex.bankapp;

import java.util.Scanner;

public class Account {

	private String name;
	private long id;
	private double balance;

	public Account(String name, double balance) {
		this.name = name;
		this.balance = balance;
	}
	
	
	
	public Account() {
		super();
	}

public static Account AddUser() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter name: ");
		String name = scan.nextLine();
		System.out.print("Enter balance: ");
		double balance = scan.nextDouble();
		return new Account(name, balance);
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
		return "Account [name=" + name + ", id=" + id + ", balance=" + balance + "]";
	}

}
