package com.criptonnetwork.util;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;

public class SasukeScore
{
  private Scoreboard sb;
  private String title;
  private Map<String, Integer> scores;
  private HashMap<Integer, Team> teams;
  
  public SasukeScore(String title)
  {
    this.sb = Bukkit.getScoreboardManager().getNewScoreboard();
    this.title = title;
    this.scores = Maps.newLinkedHashMap();
    this.teams = Maps.newLinkedHashMap();
  }
  
  public void blankLine()
  {
    add("  ");
  }
  
  public HashMap<Integer, Team> getTeams()
  {
    return this.teams;
  }
  
  public void add(String text)
  {
    add(text, null);
  }
  
  public void add(String text, Integer score)
  {
    Preconditions.checkArgument(text.length() < 48, "text cannot be over 48 characters in length");
    text = fixDuplicates(text);
    this.scores.put(text, score);
  }
  
  private String fixDuplicates(String text)
  {
    while (this.scores.containsKey(text)) {
      text = text + "";
    }
    if (text.length() > 48) {
      text = text.substring(0, 47);
    }
    return text;
  }
  
  int number = 50;
  
  private Map.Entry<Team, String> createTeam(String text)
  {
    String result = "";
    
    Team team = this.sb.registerNewTeam("" +this.number--);
    if (text.length() <= 16) {
      return new AbstractMap.SimpleEntry(team, text);
    }
    Iterator<String> iterator = Splitter.fixedLength(16).split(text).iterator();
    
    team.setPrefix((String)iterator.next());
    result = (String)iterator.next();
    
    return new AbstractMap.SimpleEntry(team, result);
  }
  
  public void build()
  {
    Objective obj = this.sb.registerNewObjective(
      this.title.length() > 16 ? this.title.substring(0, 15) : this.title, 
      "dummy");
    
    obj.setDisplayName(this.title);
    obj.setDisplaySlot(DisplaySlot.SIDEBAR);
    
    int next = this.scores.size() - 2;
    int index = next + 1;
    int par = 0;
    
    String s = "";
    for (Map.Entry<String, Integer> text : this.scores.entrySet())
    {
      par += 5;
      s = s + ChatColor.getLastColors((String)text.getKey());
      
      Map.Entry<Team, String> team = createTeam((String)text.getKey());
      
      Integer score = Integer.valueOf(text.getValue() != null ? ((Integer)text.getValue()).intValue() : index);
      
      MCHCUtilitiesScoreOfflinePlayer player = new MCHCUtilitiesScoreOfflinePlayer(s);
      ((Team)team.getKey()).setSuffix((String)team.getValue());
      ((Team)team.getKey()).addPlayer(player);
      
      obj.getScore(player).setScore(score.intValue());
      
      this.teams.put(Integer.valueOf(index), (Team)team.getKey());
      
      index--;
    }
  }
  
  public void reset()
  {
    this.title = null;
    this.scores.clear();
    for (Team t : this.teams.values()) {
      t.unregister();
    }
    this.teams.clear();
  }
  
  public Scoreboard getScoreBoard()
  {
    return this.sb;
  }
  
  public void send(Player... players)
  {
    Player[] arrayOfPlayer;
    int j = (arrayOfPlayer = players).length;
    for (int i = 0; i < j; i++)
    {
      Player p = arrayOfPlayer[i];
      p.setScoreboard(this.sb);
    }
  }
  
  public void update(String text, Integer score)
  {
    if (this.teams.containsKey(score))
    {
      Team team = (Team)this.teams.get(score);
      if (text.length() <= 16)
      {
        team.setSuffix(text);
        team.setPrefix("");
        return;
      }
      Iterator<String> iterator = Splitter.fixedLength(16)
        .split(text).iterator();
      team.setPrefix((String)iterator.next());
      team.setSuffix(ChatColor.getLastColors(team.getPrefix()) + (String)iterator.next());
    }
  }
  
  private class MCHCUtilitiesScoreOfflinePlayer
    implements OfflinePlayer
  {
    private final String playerName;
    
    public MCHCUtilitiesScoreOfflinePlayer(String playerName)
    {
      this.playerName = playerName;
    }
    
    public boolean isOnline()
    {
      return false;
    }
    
    public String getName()
    {
      return this.playerName;
    }
    
    public UUID getUniqueId()
    {
      return UUID.nameUUIDFromBytes(this.playerName.getBytes(Charsets.UTF_8));
    }
    
    public boolean isBanned()
    {
      return false;
    }
    
    public void setBanned(boolean banned)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean isWhitelisted()
    {
      return false;
    }
    
    public void setWhitelisted(boolean value)
    {
      throw new UnsupportedOperationException();
    }
    
    public Player getPlayer()
    {
      throw new UnsupportedOperationException();
    }
    
    public long getFirstPlayed()
    {
      return System.currentTimeMillis();
    }
    
    public long getLastPlayed()
    {
      return System.currentTimeMillis();
    }
    
    public boolean hasPlayedBefore()
    {
      return false;
    }
    
    public Location getBedSpawnLocation()
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean isOp()
    {
      return false;
    }
    
    public void setOp(boolean value)
    {
      throw new UnsupportedOperationException();
    }
    
    public Map<String, Object> serialize()
    {
      throw new UnsupportedOperationException();
    }
  }
}