package com.example.arturito.myLeagueStats.service;

import java.util.List;

import com.example.arturito.myLeagueStats.entity.Champions;
import com.example.arturito.myLeagueStats.entity.Scores;



public interface ChampionService {
	
	public void addChampion(Scores score);

	public Champions getChampion(String name);
	
	public List<Champions> getChampions();
	
	public void deleteChampion(int theId);	

}
