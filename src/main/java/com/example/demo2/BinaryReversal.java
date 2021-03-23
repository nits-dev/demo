package com.example.demo2;

import java.util.Scanner;

import com.google.common.base.Strings;

public class BinaryReversal {

	public static void main(String[] args) {
				
		Scanner myInput = new Scanner( System.in );

		System.out.print( "Enter Integer for Binary Reversal: " );
				
		String binaryForm = Integer.toBinaryString(myInput.nextInt());
		
		System.out.println("binaryForm "+ binaryForm);
		
		String paddedString = Strings.padStart(binaryForm, 8, '0'); 
		
		System.out.println("paddedString "+ paddedString);
		
		StringBuilder stringBuilder = new StringBuilder();
		 
		stringBuilder.append(paddedString);
        
		stringBuilder.reverse();
		
		System.out.println("Reverse of binaryForm "+ stringBuilder.toString());
        
        System.out.println("Final value is: " + Integer.parseInt(stringBuilder.toString(), 2));
	}	
}

