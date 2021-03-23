package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecevieResouce {

	@Autowired
	PalindromeRepository repository;

	@GetMapping("/{palindrome}")
	public String getPalindrome(@PathVariable String palindrome) {
		if (palindrome.length() < 1000) {
			return findLongestPalindromicSubstring(palindrome, palindrome.length() - 1);
		} else {
			return "Length of string exceeded 1000";
		}
	}

	@PostMapping(value = "/createPalindrome", consumes = "application/json")
	public String createPalindrome(@RequestBody Palindrome palindrome) throws Exception {
		System.out.println("palindrome :: " + palindrome);
		if (palindrome.getPalindrome().length() < 1000) {
			try {
				String newPalindrome = findLongestPalindromicSubstring(palindrome.getPalindrome(),
						palindrome.getPalindrome().length() - 1);
				palindrome.setPalindrome(newPalindrome);
				repository.save(palindrome);
				return "Successfully stored palindrome: "+ palindrome.getPalindrome() +" in DB" + " with ID: " + palindrome.getId();
			} catch (Exception e) {
				System.out.println(e);
				return "Excpetion occured";
			}
		} else {
			return "Length of string exceeded 1000";
		}
	}

	@GetMapping("/getpalindrome/{palindromeID}")
	public Palindrome getPalindromeByID(@PathVariable String palindromeID) {
		long id = Long.parseLong(palindromeID);
		System.out.println("id: " + id);
		return repository.getPalindromeById(id);
	}

	public static String findLongestPalindromicSubstring(String str, int len) {
		// `max_str` stores the maximum length palindromic substring
		// found so far

		String max_str = "", curr_str;

		// `max_length` stores the maximum length of palindromic
		// substring found so far

		int max_length = 0, curr_length;

		// consider every character of the given string as a midpoint and expand
		// in both directions to find maximum length palindrome

		for (int i = 0; i < len; i++) {
			// find the longest odd length palindrome with `str[i]` as a midpoint

			curr_str = expand(str, i, i);
			curr_length = curr_str.length();

			// update maximum length palindromic substring if odd length
			// palindrome has a greater length

			if (curr_length > max_length) {
				max_length = curr_length;
				max_str = curr_str;
			}

			// Find the longest even length palindrome with str[i] and
			// str[i+1] as midpoints. Note that an even length palindrome
			// has two midpoints.

			curr_str = expand(str, i, i + 1);
			curr_length = curr_str.length();

			// update maximum length palindromic substring if even length
			// palindrome has a greater length

			if (curr_length > max_length) {
				max_length = curr_length;
				max_str = curr_str;
			}
		}

		return max_str;
	}

	public static String expand(String str, int low, int high) {
		int len = str.length();

		// expand in both directions
		while (low >= 0 && high < len && (str.charAt(low) == str.charAt(high))) {
			low--;
			high++;
		}

		// return palindromic substring
		return str.substring(low + 1, high);
	}

}
