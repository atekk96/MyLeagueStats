package com.example.arturito.myLeagueStats.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.arturito.myLeagueStats.entity.Champions;
import com.example.arturito.myLeagueStats.entity.Scores;



public interface ChampionDAO {
	
	public void addChampion(Scores score);

	public Champions getChampion(String name);
	
	public List<Champions> getChampions();

	public void deleteChampion(int theId);
	
}
