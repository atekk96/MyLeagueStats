package com.example.arturito.myLeagueStats.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.arturito.myLeagueStats.dao.ChampionDAO;
import com.example.arturito.myLeagueStats.entity.Champions;
import com.example.arturito.myLeagueStats.entity.Scores;



@Service
public class ChampionServiceImplementation implements ChampionService {
	
	@Autowired
	private ChampionDAO championDAO;

	@Override
	@Transactional
	public void addChampion(Scores score) {
		championDAO.addChampion(score);
	}

	@Override
	public Champions getChampion(String name) {
		return championDAO.getChampion(name);
	}

	@Override
	public void deleteChampion(int theId) {
		championDAO.deleteChampion(theId);	
	}

	@Override
	public List<Champions> getChampions() {
		return championDAO.getChampions();
	}

}
