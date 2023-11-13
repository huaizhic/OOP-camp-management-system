package com.example.cms;

import com.example.cms.Camp.campData;
import com.example.cms.Staff.StaffMember;
import com.example.cms.user_Login.account_Manager;

import java.util.Scanner;

public class StaffAcc extends account_Manager {

	public StaffAcc(Scanner input){
		super(input);
	}
	public void Start(StaffMember staffMember) {
		System.out.println("===== Welcome =====");
		System.out.println("What would you like to do today?");
		System.out.println("1. Create a new camp");
		System.out.println("2. Edit an existing camp");
		Scanner input = new Scanner(System.in);
		int choice = input.nextInt();
		switch(choice){
			case(1):
				staffMember.createCamp();
				staffMember.setCampsCreated(campData.getCampList().get(-1));
				break;
			case(2):
				staffMember.editCamp(staffMember);
		}
	}

}