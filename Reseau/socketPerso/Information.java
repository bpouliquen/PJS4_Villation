package Reseau.socketPerso;

import java.io.Serializable;

public class Information implements Serializable{
	private String message;
	public Information(String mesg){
		this.message=mesg;
	}
	public String toString(){
		return message; 
	}
}
