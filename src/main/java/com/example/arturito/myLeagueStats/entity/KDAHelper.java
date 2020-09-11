package com.example.arturito.myLeagueStats.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.ui.Model;

public class KDAHelper implements IKDAHelper {
	
	private ScoreChecker scoreChecker;
	private KDA kda;
	
	public KDAHelper() {
		
	}
	
	public KDAHelper(ScoreChecker scoreChecker, KDA kda) {
		super();
		this.scoreChecker = scoreChecker;
		this.kda = kda;
	}
		
	public void addKdaToModel(Model model) {
		countKDA();
		model.addAttribute("kills", kda.getKills());
		model.addAttribute("assists", kda.getAssits());
		model.addAttribute("deaths", (int) kda.getDeaths());	
		model.addAttribute("kda", kda.getKdaResult());
	}
	
	@Override
	public void countKDA() {
		scoreChecker.setKdaForScore();
		BigDecimal bd = new BigDecimal((kda.getKills() + kda.getAssits())/(kda.getDeaths())).setScale(2, RoundingMode.HALF_DOWN);
		kda.setKdaResult(bd.doubleValue());
	}
}
