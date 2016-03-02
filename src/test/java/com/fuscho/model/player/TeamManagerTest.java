package com.fuscho.model.player;


import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by a614808 on 02/03/2016.
 */
public class TeamManagerTest {

    @Test
    public void getPlayerTeam(){
        IAPlayer player1 = new IAPlayer("IA1");
        IAPlayer player2 = new IAPlayer("IA2");
        IAPlayer player3 = new IAPlayer("IA3");
        IAPlayer player4 = new IAPlayer("IA4");

        Team team1 = new Team(player1,player3);
        Team team2 = new Team(player2,player4);
        List<Team> teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);
        TeamManager teamManager = new TeamManager(teams);

        Assert.assertEquals(team1, teamManager.getPlayerTeam(player1));
        Assert.assertEquals(team2, teamManager.getPlayerTeam(player2));
        Assert.assertEquals(team1, teamManager.getPlayerTeam(player3));
        Assert.assertEquals(team2, teamManager.getPlayerTeam(player4));
    }

    @Test
    public void getAdversaryTeam(){
        IAPlayer player1 = new IAPlayer("IA1");
        IAPlayer player2 = new IAPlayer("IA2");
        IAPlayer player3 = new IAPlayer("IA3");
        IAPlayer player4 = new IAPlayer("IA4");

        Team team1 = new Team(player1,player3);
        Team team2 = new Team(player2,player4);
        List<Team> teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);
        TeamManager teamManager = new TeamManager(teams);

        Assert.assertEquals(team2, teamManager.getAdversaryTeam(player1));
        Assert.assertEquals(team1, teamManager.getAdversaryTeam(player2));
        Assert.assertEquals(team2, teamManager.getAdversaryTeam(player3));
        Assert.assertEquals(team1, teamManager.getAdversaryTeam(player4));
    }

    @Test
    public void getPartnerPlayer(){
        IAPlayer player1 = new IAPlayer("IA1");
        IAPlayer player2 = new IAPlayer("IA2");
        IAPlayer player3 = new IAPlayer("IA3");
        IAPlayer player4 = new IAPlayer("IA4");

        Team team1 = new Team(player1,player3);
        Team team2 = new Team(player2,player4);
        List<Team> teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);
        TeamManager teamManager = new TeamManager(teams);

        Assert.assertEquals(player3, teamManager.getPartner(player1));
        Assert.assertEquals(player4, teamManager.getPartner(player2));
        Assert.assertEquals(player1, teamManager.getPartner(player3));
        Assert.assertEquals(player2, teamManager.getPartner(player4));
    }

    @Test
    public void getIfAdversary(){
        Player player1 = new IAPlayer("IA1");
        Player player2 = new IAPlayer("IA2");
        Player player3 = new IAPlayer("IA3");
        Player player4 = new IAPlayer("IA4");

        Team team1 = new Team(player1,player3);
        Team team2 = new Team(player2,player4);
        List<Team> teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);
        TeamManager teamManager = new TeamManager(teams);

        Assert.assertFalse(teamManager.getIfAdversary(player1,player3));
        Assert.assertTrue(teamManager.getIfAdversary(player1,player2));
        Assert.assertTrue(teamManager.getIfAdversary(player1,player4));

        Assert.assertFalse(teamManager.getIfAdversary(player2,player4));
        Assert.assertTrue(teamManager.getIfAdversary(player2,player1));
        Assert.assertTrue(teamManager.getIfAdversary(player2,player3));

        Assert.assertFalse(teamManager.getIfAdversary(player3,player1));
        Assert.assertTrue(teamManager.getIfAdversary(player3,player2));
        Assert.assertTrue(teamManager.getIfAdversary(player3,player4));

        Assert.assertFalse(teamManager.getIfAdversary(player4,player2));
        Assert.assertTrue(teamManager.getIfAdversary(player4,player1));
        Assert.assertTrue(teamManager.getIfAdversary(player4,player3));
    }
}
