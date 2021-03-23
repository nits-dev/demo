package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "palindrome")
public class Palindrome {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     
	@Column(name="palindrome")
	private String palindrome;
  
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPalindrome() {
		return palindrome;
	}

	public void setPalindrome(String palindrome) {
		this.palindrome = palindrome;
	}

	@Override
    public String toString() {
        return "Palindrome [id=" + id + ", palindrome=" + palindrome +"]";
    }
}
