package com.example.arturito.myLeagueStats.entity;

import java.util.ArrayList;
import java.util.List;

public class ScoreChecker implements IScoreChecker {

	private List<Scores> score = new ArrayList<Scores>();
	private KDA kda = new KDA();
	
	public ScoreChecker() {
		
	}
	
	public ScoreChecker(List<Scores> score, KDA kda) {
		super();
		this.score = score;
		this.kda = kda;
	}

	public boolean checkIfIsEmpty() {
		boolean result = score.size() == 0 ? true : false;
		return result;
	}
	
	public void setKdaForScore() {
		for(Scores scores : score) {
			kda.setKills(kda.getKills() + scores.getKills());
			kda.setAssits(kda.getAssits() + scores.getAssists());
			kda.setDeaths(kda.getDeaths() + scores.getDeaths());
		}
	}
}
