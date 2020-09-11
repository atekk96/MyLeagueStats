package com.example.arturito.myLeagueStats.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.arturito.myLeagueStats.entity.Scores;

@Repository
public class ScoreDAOImplementation implements ScoreDAO {
	
	//need to inject session factory
	private EntityManager entityManager;
	
	@Autowired
	public ScoreDAOImplementation(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	@Transactional
	public List<Scores> getScoresForPlayer(String name) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Scores> theQuery = currentSession.createQuery("from Scores where player=:playerName order by idScore DESC", Scores.class);
		theQuery.setParameter("playerName", name);
		List<Scores> scores = theQuery.getResultList();
		return scores;
	}

	@Override
	@Transactional
	public List<Scores> getScoresForLane(String player, String lane) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Scores> theQuery = currentSession.createQuery("from Scores where lane=:laneName and player=:playerName " + 
		"order by idScore DESC", Scores.class);
		theQuery.setParameter("laneName", lane);
		theQuery.setParameter("playerName", player);
		List<Scores> theScores = theQuery.getResultList();
		return theScores;
	}

	@Override
	@Transactional
	public List<Scores> getScoresForChampion(String player, String champion) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Scores> theQuery = currentSession.createQuery("from Scores where champion=:championName and player=:playerName " +
		"order by idScore DESC", Scores.class);
		theQuery.setParameter("championName", champion);
		theQuery.setParameter("playerName", player);
		List<Scores> theScores = theQuery.getResultList();
		return theScores;
	}

	@Override
	@Transactional
	public List<Scores> getScoresForChampionOnLane(String player, String lane, String champion) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Scores> theQuery = currentSession.createQuery("from Scores where champion=:championName and player=:playerName" +
		" and lane=:laneName order by idScore DESC", Scores.class);
		theQuery.setParameter("playerName", player);
		theQuery.setParameter("laneName", lane);
		theQuery.setParameter("championName", champion);
		List<Scores> theScores = theQuery.getResultList();
		return theScores;
	}

}
