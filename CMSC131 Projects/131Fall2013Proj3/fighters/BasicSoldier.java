package fighters;

import framework.BattleField;
import framework.Random131;

public class BasicSoldier {
	//Values for directions and the stats of the soldiers
	public final static int INITIAL_HEALTH = 10;
	public final static int ARMOR = 20;
	public final static int STRENGTH = 30;
	public final static int SKILL = 40;
	public final static int UP = 0;
	public final static int RIGHT = 1;
	public final static int DOWN = 2;
	public final static int LEFT = 3;
	public final static int UP_AND_RIGHT = 4;
	public final static int DOWN_AND_RIGHT = 5;
	public final static int DOWN_AND_LEFT = 6;
	public final static int UP_AND_LEFT = 7;
	public final static int NEUTRAL = -1;
	public final BattleField battleField;
	public int row, col;
	public int health;
	public final int team;
	//BasicSoldier Constructor
	public BasicSoldier(BattleField battleFieldIn, int teamIn, int rowIn, int colIn) {
		battleField = battleFieldIn;
		team = teamIn;
		row = rowIn;
		col = colIn;
		health = INITIAL_HEALTH;
	}
	//Lets the Soldier know if it can move
	public boolean canMove(){
		battleField.get(row, col);
		if(row+1==BattleField.EMPTY||row-1==BattleField.EMPTY||col+1==BattleField.EMPTY||col-1==BattleField.EMPTY);
		return true; //if false is returned they cannot move
	}
	//Gives back the number of Enemies Remaining
	public int numberOfEnemiesRemaining(){
		int numberofEnemies = 0;
		/*Scans Battlefield for your enemies then adds them to counter*/
		if(team==BattleField.RED_TEAM){
			for (int r=0;r<battleField.getRows();r++){
				for (int c=0;c<battleField.getCols();c++){
					if(battleField.get(r,c)!=team){
						numberofEnemies=numberofEnemies+1;
					}
				}
			}
		}else{
			if(team==BattleField.BLUE_TEAM){
			for(int r=0;r<battleField.getRows();r++){
				for(int c=0;c<battleField.getCols();c++){
					if(battleField.get(r,c)!=team){
						numberofEnemies=numberofEnemies+1;
					}}
				}
			}
		}
		return numberofEnemies;
	}
	//Gives the distance (in moves) from your destination
	public int getDistance(int destinationRow, int destinationCol){
		int distance;
		distance = (Math.abs((destinationRow-row))+Math.abs((destinationCol-col)));
		return Math.abs(distance);
	}
	//Gives the direction of your location
	public int getDirection(int destinationRow, int destinationCol){
		int direction;
		if(row==destinationRow && col<destinationCol){
			direction = RIGHT;
		}
		else if(row==destinationRow && col>destinationCol){
			direction =LEFT;
		}
		else if(col==destinationCol && row<destinationRow){
			direction =DOWN;
		}
		else if(col==destinationCol && row>destinationRow){
			direction = UP;
		}
		else if(row>destinationRow && col<destinationCol){
			direction = UP_AND_RIGHT;
		}
		else if(row>destinationRow && col>destinationCol){
			direction = UP_AND_LEFT;
		}
		else if(row<destinationRow && col<destinationCol){
			direction = DOWN_AND_RIGHT;
		}
		else if(row<destinationRow && destinationCol<col){
			direction = DOWN_AND_LEFT;
		}else{
			direction = NEUTRAL;
		}
		return direction;
	}
	//Gives you the direction of your nearest friend
	public int getDirectionOfNearestFriend(){
		int direction = NEUTRAL; //Variable allows me to return neutral if there are no friends left
		int closeDistance = 0; //Variable I will use to store the distances of friends
		int closeRow = row; // Stores the row of nearest friend
		int closeCol = col;//Stores the col of nearest friend
		if(battleField.get(row, col)==BattleField.BLUE_TEAM){
			for (int r=0;r<battleField.getRows();r++){
				for (int c=0;c<battleField.getCols();c++){
					if(battleField.get(r,c)==BattleField.BLUE_TEAM && (r!=row || c!=col)){
						//repeats the loop below until it has the nearest friend distance and location. Changes direction value.
						if(closeDistance>getDistance(r,c)||closeDistance==0){
							closeDistance=getDistance(r,c);
							closeRow=r;
							closeCol=c;
							direction = getDirection(closeRow,closeCol);
						}
					}
				}
			}
		}else{
			for(int r=0;r<battleField.getRows();r++){
				for(int c=0;c<battleField.getCols();c++){
					if(battleField.get(r, c)==BattleField.RED_TEAM && (r!=row || c!=col)){
						if(closeDistance>getDistance(r,c)||closeDistance==0){
							closeDistance=getDistance(r,c);
							closeRow=r;
							closeCol=c;
							direction = getDirection(closeRow,closeCol);
						}
					}
				}
			}
		}
		return direction;
	}
	//Counts all of your friends within a given radius
	public int countNearbyFriends(int radius){
		int nearbyFriends = 0;
		if(battleField.get(row,col)==BattleField.BLUE_TEAM){
			for(int r=0;r<battleField.getRows();r++){
				for(int c=0;c<battleField.getCols();c++){
					//if statement below only gets friends within the radius and the adds them to counter.
					if(battleField.get(r,c)==BattleField.BLUE_TEAM){
						if(getDistance(r,c)<=radius){
							nearbyFriends++;
						}
					}
				}
			}
		} else{
			for(int r=0;r<battleField.getRows();r++){
				for(int c=0; c<battleField.getCols();c++){
					if(battleField.get(r, c)==BattleField.RED_TEAM){
						if(battleField.get(r,c)<=radius){
							nearbyFriends++;
						}
					}
				}
			}
		}
		return nearbyFriends - 1; //subtract one because you don't want to count yourself.
	}
	//Gets the direction of the nearest Enemy within a specified radius
	public int getDirectionOfNearestEnemy(int radius){
		int direction=NEUTRAL;// This variable allows me to return neutral if there are no enemies
		int closeDistance = 0;// Holds the distance of nearest enemy
		int closeRow = row;//Holds the row of nearest enemy
		int closeCol = col;//Holds the col of nearest enemy
		if(battleField.get(row, col)==BattleField.BLUE_TEAM){
			for(int r=0;r<battleField.getRows();r++){
				for(int c=0;c<battleField.getCols();c++){
					if(battleField.get(r,c)==BattleField.RED_TEAM){
						if(getDistance(r,c)<=radius){
							//if statement below repeats until it has nearest enemy in distance from the Soldier
							if(closeDistance>getDistance(r,c)||closeDistance==0){
								closeDistance=getDistance(r,c);
								closeRow=r;
								closeCol=c;
								direction = getDirection(r,c);
							}

						}
					}
				}
			}
		} else {
			for(int r=0; r<battleField.getRows();r++){
				for(int c=0; c<battleField.getCols();c++){
					if(battleField.get(r,c)==BattleField.BLUE_TEAM){
						if(getDistance(r,c)<=radius){
							if(closeDistance>getDistance(r,c)||closeDistance==0){
								closeDistance=getDistance(r,c);
								closeRow=r;
								closeCol=c;
								direction = getDirection(r,c);
							}
						}
					}
				}
			}
		}
		return direction;
	}
	//Gives the soldier a list of things to perform in order when it's their turn.
	public void performMyTurn () {
		//Blue Team Commands (ineffective in combat because if they can't attack they will only walk right)
		if(battleField.get(row, col)==BattleField.BLUE_TEAM){
			if(battleField.get(row,col+1)==BattleField.RED_TEAM){
				battleField.attack(row, col+1);
			}else if(battleField.get(row, col-1)==BattleField.RED_TEAM){
				battleField.attack(row, col-1);
			}else if(battleField.get(row+1, col)==BattleField.RED_TEAM){
				battleField.attack(row+1,col);
			}else if(battleField.get(row-1,col)==BattleField.RED_TEAM){
				battleField.attack(row-1, col);
			}else if(battleField.get(row, col+1)==BattleField.EMPTY){
				col=col+1;
			}else if(battleField.get(row, col-1)==BattleField.EMPTY){
				col=col-1;
			}else if(battleField.get(row+1, col)==BattleField.EMPTY){
				row=row+1;
			}else if(battleField.get(row-1, col)==BattleField.EMPTY){
				row=row-1;
			} else{

			}
		}
		//Red Team Commands (still ineffective in combat because if they can't attack they will only walk right)
		else if(battleField.get(row,col)==BattleField.RED_TEAM){
			if(battleField.get(row,col+1)==BattleField.BLUE_TEAM){
				battleField.attack(row, col+1);
			}else if(battleField.get(row, col-1)==BattleField.BLUE_TEAM){
				battleField.attack(row, col-1);
			}else if(battleField.get(row+1, col)==BattleField.BLUE_TEAM){
				battleField.attack(row+1,col);
			}else if(battleField.get(row-1,col)==BattleField.BLUE_TEAM){
				battleField.attack(row-1, col);
			}else if(battleField.get(row, col+1)==BattleField.EMPTY){
				col=col+1;
			}else if(battleField.get(row, col-1)==BattleField.EMPTY){
				col=col-1;
			}else if(battleField.get(row+1, col)==BattleField.EMPTY){
				row=row+1;
			}else if(battleField.get(row-1, col)==BattleField.EMPTY){
				row=row-1;
			} else{

			}
		}
	}
}




