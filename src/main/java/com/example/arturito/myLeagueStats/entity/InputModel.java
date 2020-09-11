package com.example.arturito.myLeagueStats.entity;

import org.springframework.ui.Model;

public class InputModel {
	
	private Model model;
	private SingleScoreChecker singleScoreChecker;
	
	
	
	public InputModel(Model model, SingleScoreChecker singleScoreChecker) {
		super();
		this.model = model;
		this.singleScoreChecker = singleScoreChecker;
	}

	public void objectNotFound(String whatIsEmpty) {
			model.addAttribute(NotFoundInformationConst.EMPTY_DB, NotFoundInformationConst.NOTHING_IN_DATABASE);
			if(whatIsEmpty == NotFoundInformationConst.LANE) {
				model.addAttribute(NotFoundInformationConst.LANE_NOT_FOUND, singleScoreChecker.setLaneToUppercase());
			} else if(whatIsEmpty == NotFoundInformationConst.CHAMPION) {
				model.addAttribute(NotFoundInformationConst.CHAMPION_NOT_FOUND, singleScoreChecker.setChampionToUppercase());
			} else {
				model.addAttribute(NotFoundInformationConst.CHAMPION_ON_LANE_NOT_FOUND, singleScoreChecker.setChampionToUppercase());
			}
			
	}

}
