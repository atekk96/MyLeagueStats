package com.example.arturito.myLeagueStats.service;

import java.util.List;

import com.example.arturito.myLeagueStats.entity.Scores;

public interface ScoresService {
	
	public List<Scores> getScoresForPlayer(String name);
	
	public List<Scores> getScoresForLane(String player, String lane);
	
	public List<Scores> getScoresForChampion(String player, String champion);
	
	public List<Scores> getScoresForChampionOnLane(String player, String lane, String champion);

}
