package com.example.arturito.myLeagueStats.entity;

public class SingleScoreChecker implements IScoreChecker {
	
	private Scores score;
	private KDA kda;
	
	public SingleScoreChecker() {
		
	}
	
	public SingleScoreChecker(Scores score, KDA kda) {
		super();
		this.score = score;
		this.kda = kda;
	}
	
	public void setDeathsWithCheckingIfZero() {
		if(checkIfNumberIsZero()) {
			kda.setDeaths(1);
		}
	}
	
	public boolean checkIfNumberIsZero() {
		boolean result = score.getDeaths() == 0 ? true : false;
		return result;
	}

	@Override
	public void setKdaForScore() {
		kda.setKills(kda.getKills() + score.getKills());
		kda.setAssits(kda.getAssits() + score.getAssists());
		kda.setDeaths(kda.getDeaths() + score.getDeaths());
	}
	
	public boolean checkIfLaneIsEmpty() {
		boolean result = score.getLane() == "" ? true : false;
		return result;
	}
	
	public boolean checkIfLaneIsNull() {
		boolean result = score.getLane() == null ? true : false;
		return result;
	}
	
	public boolean checkIfChampionIsEmpty() {
		boolean result = score.getChampion() == "" ? true : false;
		return result;
	}
	
	public String setLaneToUppercase() {
		return score.getLane().toUpperCase();
	}
	
	public String setChampionToUppercase() {
		return score.getChampion().toUpperCase();
	}
}
