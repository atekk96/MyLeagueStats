package com.example.arturito.myLeagueStats.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SingleKDAHelper implements IKDAHelper {
	
	private SingleScoreChecker singleScoreChecker;
	private KDA kda;	

	public SingleKDAHelper(SingleScoreChecker singleScoreChecker, KDA kda) {
		super();
		this.singleScoreChecker = singleScoreChecker;
		this.kda = kda;
	}

	@Override
	public void countKDA() {
		singleScoreChecker.setKdaForScore();
		singleScoreChecker.setDeathsWithCheckingIfZero();
		BigDecimal bd = new BigDecimal((kda.getKills()+kda.getAssits())/kda.getDeaths()).setScale(2, RoundingMode.HALF_DOWN);
		kda.setKdaResult(bd.doubleValue());
	}

}
