package com.example.arturito.myLeagueStats.entity;

public class ChampionHelper {
	
	private Champions champ = new Champions();
	
	public ChampionHelper() {
		
	}

	public ChampionHelper(Champions champ) {
		super();
		this.champ = champ;
	}

	public boolean ifChampIsEmpty() {
		boolean result = champ == null ? true : false;
		return result;
	}
}
