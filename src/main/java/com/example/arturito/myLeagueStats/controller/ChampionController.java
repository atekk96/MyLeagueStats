package com.example.arturito.myLeagueStats.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.arturito.myLeagueStats.entity.ChampionHelper;
import com.example.arturito.myLeagueStats.entity.Champions;
import com.example.arturito.myLeagueStats.entity.InputModel;
import com.example.arturito.myLeagueStats.entity.KDA;
import com.example.arturito.myLeagueStats.entity.KDAHelper;
import com.example.arturito.myLeagueStats.entity.NotFoundInformationConst;
import com.example.arturito.myLeagueStats.entity.ScoreChecker;
import com.example.arturito.myLeagueStats.entity.Scores;
import com.example.arturito.myLeagueStats.entity.SingleKDAHelper;
import com.example.arturito.myLeagueStats.entity.SingleScoreChecker;
import com.example.arturito.myLeagueStats.service.ChampionService;
import com.example.arturito.myLeagueStats.service.ScoresService;



@Controller
@RequestMapping("/myLeagueStats")
public class ChampionController {
	
	@Autowired
	private ChampionService championService;
	@Autowired
	private ScoresService scoresService;
	
	@GetMapping("/list")
	public String listChampions(Model theModel) {
		List<Champions> theChampions = championService.getChampions();
		Scores theScore = new Scores();
		theModel.addAttribute("champions", theChampions);		
		theModel.addAttribute("score", theScore);	
		return "list-champions";
	}
	
	@GetMapping("/player")
	public String showPlayerPage(@RequestParam(value="player", required=false) String player, @RequestParam(value="laneNotFound", required=false) String laneNotFound,
								@RequestParam(value="champNotFound", required=false) String champNotFound,
								@RequestParam(value="champOnLaneNotFound", required=false) String champOnLaneNotFound, Model theModel) {
		List<Scores> theScores = scoresService.getScoresForPlayer(player);
		Scores theScore = new Scores();
		Scores filterScore = new Scores();
		KDA kda = new KDA();
		theScore.setPlayer(player);
		filterScore.setPlayer(player);
		theModel.addAttribute("score", theScore);
		theModel.addAttribute("scores", theScores);
		theModel.addAttribute("filterScore", filterScore);
		ScoreChecker scoreChecker = new ScoreChecker(theScores, kda);
		if(scoreChecker.checkIfIsEmpty()) {
			theModel.addAttribute("emptyDB", "Nie znaleziono nic w bazie danych!");
			return "playerPage";
		} else {
			KDAHelper kdaCounter = new KDAHelper(scoreChecker, kda);
			kdaCounter.addKdaToModel(theModel);
			return "playerPage";
		}
	}	
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(@RequestParam("player") String player, Model theModel, 
								 @RequestParam(value = "ifExist", required=false) String ifExist) {
		Scores theScore = new Scores();
		Scores newScore = new Scores();
		theScore.setPlayer(player);
		newScore.setPlayer(player);
		theModel.addAttribute("score", theScore);
		theModel.addAttribute("newScore", newScore);		
		return "champion-add-form";
		
	}
	
	
	@PostMapping("/addChampion")
	public String addChampion(@Valid @ModelAttribute("newScore") Scores score, BindingResult theBindingResult,
							  @RequestParam("champion") String champion, Model theModel) {
		Champions champ = championService.getChampion(champion);
		ChampionHelper championHelper = new ChampionHelper(champ);
		if(championHelper.ifChampIsEmpty()) {
			theModel.addAttribute("haha", "nie ma takiego champa");
			return "champion-add-form";
		} else {
			KDA kda = new KDA(0, 0, 0, 0);
			SingleScoreChecker singleScoreChecker = new SingleScoreChecker(score, kda);
			SingleKDAHelper singleKdaCounter = new SingleKDAHelper(singleScoreChecker, kda);
			singleKdaCounter.countKDA();
			score.setKda(kda.getKdaResult());
		}	
		if(theBindingResult.hasErrors()) {
			return "champion-add-form";
		} else {
			championService.addChampion(score);	
			return "redirect:/myLeagueStats/player?player=" + score.getPlayer();
		}

	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("label") String name, Model theModel) {
		Champions champion = championService.getChampion(name);
		theModel.addAttribute("champion", champion);
		return "champion-add-form";
	}
	
	@GetMapping("/delete")
	public String deleteChampion(@RequestParam("championId") int theId) {
		championService.deleteChampion(theId);
		return "redirect:/myLeagueStats/list";
	}
	
	@GetMapping("/filterGames")
	public String filterGames(@ModelAttribute("filterScore") Scores scores, @RequestParam(value = "lane", required=false) String lane, 
								@RequestParam(value = "player", required=false) String player, @RequestParam(value = "champion") String champion,  Model theModel) {
		KDA kda = new KDA();
		SingleScoreChecker singleScoreChecker = new SingleScoreChecker(scores, kda);
		Scores theScore = new Scores();
		Scores filterScore = new Scores();
		if(singleScoreChecker.checkIfChampionIsEmpty() == true && singleScoreChecker.checkIfLaneIsEmpty() == true) {
			return "redirect:/myLeagueStats/player?player=" + scores.getPlayer();
		}
		theScore.setPlayer(player);
		filterScore.setPlayer(player);
		theModel.addAttribute("score", theScore);
		theModel.addAttribute("filterScore", filterScore);
		if(singleScoreChecker.checkIfLaneIsEmpty() == false && singleScoreChecker.checkIfChampionIsEmpty() == true) {
			List<Scores> scoresFilterd = scoresService.getScoresForLane(player, lane);
			ScoreChecker scoreChecker = new ScoreChecker(scoresFilterd, kda);
			InputModel inputModel = new InputModel(theModel, singleScoreChecker);
			if(scoreChecker.checkIfIsEmpty()) {
				inputModel.objectNotFound(NotFoundInformationConst.LANE);
				return "playerPage";
			} else {
				KDAHelper kdaCounter = new KDAHelper(scoreChecker, kda);
				kdaCounter.addKdaToModel(theModel);
			}
			theModel.addAttribute("scores", scoresFilterd);
			return "playerPage";
		} else if(singleScoreChecker.checkIfLaneIsEmpty() == true && singleScoreChecker.checkIfChampionIsEmpty() == false) {
			List<Scores> scoresFilterd = scoresService.getScoresForChampion(player, champion);
			ScoreChecker scoreChecker = new ScoreChecker(scoresFilterd, kda);
			InputModel inputModel = new InputModel(theModel, singleScoreChecker);
			if(scoreChecker.checkIfIsEmpty()) {
				inputModel.objectNotFound(NotFoundInformationConst.CHAMPION);
			} else {
				KDAHelper kdaCounter = new KDAHelper(scoreChecker, kda);
				kdaCounter.addKdaToModel(theModel);
			}
			theModel.addAttribute("scores", scoresFilterd);
			return "playerPage";
		} else if(!singleScoreChecker.checkIfLaneIsNull() && !singleScoreChecker.checkIfChampionIsEmpty()) {
			List<Scores> scoresFilterd = scoresService.getScoresForChampionOnLane(player, lane, champion);
			ScoreChecker scoreChecker = new ScoreChecker(scoresFilterd, kda);
			InputModel inputModel = new InputModel(theModel, singleScoreChecker);
			if(scoreChecker.checkIfIsEmpty()) {
				inputModel.objectNotFound(NotFoundInformationConst.CHAMPION_ON_LANE);
			} else {
				KDAHelper kdaCounter = new KDAHelper(scoreChecker, kda);
				kdaCounter.addKdaToModel(theModel);
			}
			theModel.addAttribute("scores", scoresFilterd);
			return "playerPage";
		} else {
			return "playerPage";
		}		
	}
}
