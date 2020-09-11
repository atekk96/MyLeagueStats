package com.example.arturito.myLeagueStats.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.arturito.myLeagueStats.dao.ScoreDAO;
import com.example.arturito.myLeagueStats.entity.Scores;

@Service
public class ScoresServiceImplementation implements ScoresService {
	
	@Autowired
	private ScoreDAO scoreDAO;

	@Override
	public List<Scores> getScoresForPlayer(String name) {
		return scoreDAO.getScoresForPlayer(name);
	}

	@Override
	public List<Scores> getScoresForLane(String player, String lane) {
		return scoreDAO.getScoresForLane(player, lane);
	}

	@Override
	public List<Scores> getScoresForChampion(String player, String champion) {
		return scoreDAO.getScoresForChampion(player, champion);
	}

	@Override
	public List<Scores> getScoresForChampionOnLane(String player, String lane, String champion) {
		return scoreDAO.getScoresForChampionOnLane(player, lane, champion);
	}

}
