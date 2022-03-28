# MafanaLevelAPI
<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
  
  <dependency>
	    <groupId>com.github.MaskedWasTaken</groupId>
	    <artifactId>MafanaLevelAPI</artifactId>
	    <version>Tag</version>
	</dependency>
  
  How to use:
  1. Add it to your maven dependency while the server is active you can use the database
  new PlayerLevelSQLGetter(Main.getInstance()).createPlayer(player);
  new PlayerLevels(player).getPlayer();
  new PlayerLevels(player).getXP();
  new PlayerLevels(player).getLevel();
  new PlayerLevels(player).setLevel();
  new PlayerLevels(player).setXP();
  
  
