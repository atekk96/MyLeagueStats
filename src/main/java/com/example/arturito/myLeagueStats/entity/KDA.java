package com.example.arturito.myLeagueStats.entity;

public class KDA {
	
	private double kills;
	private double assits;
	private double deaths;
	private double kdaResult;
	
	public KDA() {
		
	}
	
	public KDA(double kills, double assits, double deaths, double kdaResult) {
		this.kills = kills;
		this.assits = assits;
		this.deaths = deaths;
		this.kdaResult = kdaResult;
	}
	
	public double getKills() {
		return kills;
	}
	public void setKills(double kills) {
		this.kills = kills;
	}
	public double getAssits() {
		return assits;
	}
	public void setAssits(double assits) {
		this.assits = assits;
	}
	public double getDeaths() {
		return deaths;
	}
	public void setDeaths(double deaths) {
		this.deaths = deaths;
	}
	public double getKdaResult() {
		return kdaResult;
	}
	public void setKdaResult(double kdaResult) {
		this.kdaResult = kdaResult;
	}

}
