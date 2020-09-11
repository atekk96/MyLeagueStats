package com.example.arturito.myLeagueStats.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.arturito.myLeagueStats.entity.Champions;
import com.example.arturito.myLeagueStats.entity.Scores;


@Repository
public class ChampionDAOImplementation implements ChampionDAO {

	//need to inject session factory
	private EntityManager entityManager;
	
	@Autowired
	public ChampionDAOImplementation(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}	

	@Override
	@Transactional
	public void addChampion(Scores score) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(score);
	}

	@Override
	@Transactional
	public Champions getChampion(String name) {
		Session currentSession = entityManager.unwrap(Session.class);
		
//		Champions champion = currentSession.get(Champions.class, name);
		Query<Champions> theQuery = currentSession.createQuery("from Champions where label=:nameChamp", Champions.class);
		theQuery.setParameter("nameChamp", name);
		Champions champ = (Champions) theQuery.getResultList().stream().findFirst().orElse(null);
		if(champ == null) {
			return null;
		} else {
			return champ;
		}
		
	}
	
	@Override
	@Transactional
	public List<Champions> getChampions() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Champions> theQuery = currentSession.createQuery("from Champions", Champions.class);
		List<Champions> theChamps = theQuery.getResultList();
		return theChamps;
	}

	@Override
	@Transactional
	public void deleteChampion(int theId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Champions> theQuery = currentSession.createQuery("delete from Champions where id=:championId", Champions.class);
		theQuery.setParameter("championId", theId);
		theQuery.executeUpdate();
	}
	
}
