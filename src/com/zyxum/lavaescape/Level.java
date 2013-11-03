package com.zyxum.lavaescape;

public class Level {
	public static void create(int width, int height){
		byte[][] tempArray=new byte[width+2][height+2];
		int[] imageids={1, 1, 1, 1, 2};
		
		for(int i=0;i<width;i++){
			new WallCube(i, 0, 1);
			tempArray[i][0]=1;
			new WallCube(i, height-1, 1);
			tempArray[i][height-1]=1;
		}
		
		for(int i=0;i<=width;i++){
			for(int j=0;j<height;j++){
				if(Math.round(Game.random.nextInt(4))==1 && !(i==Game.player.getX() && j==Game.player.getY()) && j!=0 && j!=height-1){
					new WallCube(i, j, imageids[Game.random.nextInt(imageids.length)]);
					tempArray[i][j]=1;
				}
			}
		}
		
		for(int x=1;x<=width;x++){
			for(int y=1;y<height;y++){
				if(tempArray[x-1][y]==0 && tempArray[x+1][y]==0 && tempArray[x][y-1]==0 && tempArray[x][y+1]==0
						&& tempArray[x-1][y-1]==0 && tempArray[x+1][y+1]==0 && tempArray[x+1][y-1]==0 && tempArray[x-1][y+1]==0
						&& !(x==Game.player.getX() && y==Game.player.getY()) && y!=0 && y!=height-1){
					new WallCube(x, y, imageids[Game.random.nextInt(imageids.length)]);
					tempArray[x][y]=1;
				}
			}
		}
		
		for(int x=1;x<=width;x++){
			for(int y=1;y<height;y++){
				if(tempArray[x-1][y]==1 && tempArray[x+1][y]==1 && Game.random.nextInt(5)!=2
						&& !(x==Game.player.getX() && y==Game.player.getY()) && y!=0 && y!=height-1){
					new WallCube(x, y, imageids[Game.random.nextInt(imageids.length)]); 
				}
			}
		}
		
		for(int x=1;x<=width;x++){
			for(int y=1;y<height;y++){
				if(tempArray[x][y-1]==1 && tempArray[x][y+1]==1 && Game.random.nextInt(10)<2
						&& !(x==Game.player.getX() && y==Game.player.getY()) && y!=0 && y!=height-1){
					new WallCube(x, y, imageids[Game.random.nextInt(imageids.length)]);
				}else if(tempArray[x][y-1]==1 && tempArray[x][y+1]==1 && tempArray[x-1][y]==0 && tempArray[x+1][y]==0 && Game.random.nextInt(10)<2
						&& !(x==Game.player.getX() && y==Game.player.getY()) && y!=0 && y!=height-1){
					new Enemy(x, y, Game.random.nextInt(2)+1);
				}
			}
		}
		
		for(int x=1;x<=width;x++){
			for(int y=1;y<height;y++){
				if(tempArray[x-1][y]==1 && tempArray[x+1][y]==1 && tempArray[x][y-1]==1 && tempArray[x][y+1]==1
						&& !(x==Game.player.getX() && y==Game.player.getY()) && y!=0 && y!=height-1){
					new WallCube(x, y, imageids[Game.random.nextInt(imageids.length)]);
				}
			}
		}
		
		tempArray=new byte[width+2][height+2];
		for(WallCube wc : WallCube.getInstances()) if(wc.exists) tempArray[wc.getX()][wc.getY()]=1;
		for(Enemy e : Enemy.getInstances()) if(e.exists) tempArray[e.getX()][e.getY()]=1;
		for(int x=1;x<=width;x++){
			for(int y=1;y<height;y++){
				if(tempArray[x][y]==0 && tempArray[x][y-1]==1 && tempArray[x][y+1]==1 && tempArray[x-1][y]==1 && tempArray[x+1][y]==0 && x>20 && Game.random.nextInt(2)==1){
					if(Game.random.nextBoolean()) new Key(x, y);
					else new Bomb(x, y);
				}
				
				if(tempArray[x][y]==0 && tempArray[x][y-1]==1 && tempArray[x][y+1]==1 && tempArray[x-1][y]==0 && tempArray[x+1][y]==0 && x>20 && Game.random.nextInt(3)==1){
					new WallCube(x, y, 3);
				}
				
				if(tempArray[x][y]==0 && tempArray[x+1][y]==1 && tempArray[x][y+1]==1 && tempArray[x-1][y]==1 && tempArray[x][y-1]==1){
					new WallCube(x, y, imageids[Game.random.nextInt(imageids.length)]);
				}
			}
		}
	}
}