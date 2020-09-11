package com.example.arturito.myLeagueStats.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="scores", schema="mylol")
public class Scores {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idScore")
	private int idScore;
	
	@Column(name="player")
	@Size(max=6)
	private String player;
	
	@Column(name="champion")
	@NotNull(message="is required")
	@Size(min=1, max=30)
	private String champion;
	
	@Column(name="lane")
	@NotNull(message="is required")
	@Size(max=8)
	private String lane;
	
	@Column(name="kills")
	@NotNull(message="is required")
	private double kills;
	
	@Column(name="assists")
	@NotNull(message="is required")
	private double assists;
	
	@Column(name="deaths")
	@NotNull(message="is required")
	private double deaths;
	
	@Column(name="kda")
	@NotNull(message="is required")
	private double kda;
	
	public Scores() {
		
	}
	
	public int getIdScore() {
		return idScore;
	}

	public void setIdScore(int idScore) {
		this.idScore = idScore;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public String getChampion() {
		return champion;
	}

	public void setChampion(String champion) {
		this.champion = champion;
	}

	public String getLane() {
		return lane;
	}

	public void setLane(String lane) {
		this.lane = lane;
	}

	public double getKills() {
		return kills;
	}

	public void setKills(double kills) {
		this.kills = kills;
	}

	public double getAssists() {
		return assists;
	}

	public void setAssists(double assists) {
		this.assists = assists;
	}

	public double getDeaths() {
		return deaths;
	}

	public void setDeaths(double deaths) {
		this.deaths = deaths;
	}

	public double getKda() {
		return kda;
	}

	public void setKda(double kda) {
		this.kda = kda;
	}

}
