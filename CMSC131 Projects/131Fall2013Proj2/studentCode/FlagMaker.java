package studentCode;
import java.awt.Color;

import GridTools.MyGrid;


public class FlagMaker {

	/* Draws a single flag as indicated by the second parameter */
	/* into the grid passed as the first parameter */
	public static void drawFlag(MyGrid grid, int countryCode) {
		//countryCode 99 gives you the standard Error Flag
		if(countryCode == 99) {
			errorFlag(4, 8, grid);
		}
		//Indonesia
		if (countryCode == 1){
			//error flag
			int height = grid.getHt();
			int width = grid.getWd();
			if (height%2!=0){
				for (int c=0; c<height; c++)
					for(int d=0; d<width; d++){
						grid.setColor(c,d, Color.WHITE);
					}
				grid.setColor(0,0, Color.RED);
				grid.setColor(0,width-1, Color.YELLOW);
				grid.setColor(height-1,0, Color.BLUE);
				grid.setColor(height-1,width-1, Color.GREEN);
			}
			else{
				for (int c=0; c<height; c++) {
					for (int d=0; d<width; d++){
						grid.setColor(c, d, Color.WHITE); //Full thing white
						grid.setColor(c/2, d, Color.RED); // Half flag Red
					}
				}

			}
		}
		//Lithuania
		else if(countryCode==2){
			int height = grid.getHt();
			int width = grid.getWd();
			if (height%3!=0){
				for (int c=0; c<height; c++)
					for(int d=0; d<width; d++){
						grid.setColor(c,d, Color.WHITE);
					}
				grid.setColor(0,0, Color.RED);
				grid.setColor(0,width-1, Color.YELLOW);
				grid.setColor(height-1,0, Color.BLUE);
				grid.setColor(height-1,width-1, Color.GREEN);
			}else{
				for (int c=0; c<height; c++){
					for (int d=0; d<width; d++){
						grid.setColor(c,d, Color.RED);//Full Thing REd
						grid.setColor(2*c/3,d,Color.GREEN);//Two-thirds Green
						grid.setColor(c/3,d,Color.YELLOW);//One-Third Yellow
					}
				}
			}
		} 
		//Rwanda
		else if(countryCode==3){
			int height = grid.getHt();
			int width = grid.getWd();
			if (height%4!=0){
				for (int c=0; c<height; c++)
					for(int d=0; d<width; d++){
						grid.setColor(c,d, Color.WHITE);
					}
				grid.setColor(0,0, Color.RED);
				grid.setColor(0,width-1, Color.YELLOW);
				grid.setColor(height-1,0, Color.BLUE);
				grid.setColor(height-1,width-1, Color.GREEN);
			}else{
				for (int c=0; c<height;c++){
					for (int d=0; d<width; d++){
						grid.setColor(c,d, Color.GREEN);//Full thing Green
						grid.setColor(3*c/4,d,Color.YELLOW);//Three-Fourth's Yellow
						grid.setColor(c/2,d,Color.BLUE);//Half Blue
					}
				}
			}
		} 
		//Malta
		else if(countryCode==4){
			int height = grid.getHt();
			int width = grid.getWd();
			for (int c=0; c<width;c++){
				for (int d=0; d<height;d++){
					grid.setColor(d,c, Color.RED);//Full Thing Red
					grid.setColor(d,c/2,Color.WHITE);//White on the left
				}
			}
		} 
		//Afghanistan
		else if(countryCode==5){
			int height = grid.getHt();
			int width = grid.getWd();
			if (height%3!=0){
				for (int c=0; c<height; c++){
					for(int d=0; d<width; d++){
						grid.setColor(c,d, Color.WHITE);
					}
					grid.setColor(0,0, Color.RED);
					grid.setColor(0,width-1, Color.YELLOW);
					grid.setColor(height-1,0, Color.BLUE);
					grid.setColor(height-1,width-1, Color.GREEN);
				}
			} else{
				for(int c=0; c<width;c++){
					for (int d=0; d<height;d++){
						grid.setColor(d,c,Color.GREEN);//Full Green
						grid.setColor(d,2*c/3,Color.RED);//thirds of it Red
						grid.setColor(d,c/3, Color.BLACK);//One Third Black
					}
				}
			}
		} 
		//Eritrea
		else if(countryCode==6){
			int height = grid.getHt();
			int width = grid.getWd();
			int temp=3;//Temp Variable controls increments
			for(int c=0;c<height;c++){
				for(int d=0;d<width;d++){
					grid.setColor(c,d,Color.RED);//Entire thing red
				}
			}
			//Green Triangle
			for(int c=0;c<height;c++){
				for(int d=0;d<width;d++){
					if(d>temp){
						grid.setColor(c,d,Color.GREEN);
					}
				}
				temp+=4;//increments by four
			}
			//Blue Triangle
			int increment=3;
			for(int c=height-1;c>height/2;c--){
				for(int d=0;d<width;d++){
					if(d>increment){
						grid.setColor(c,d,Color.BLUE);
					}
				}
				increment+=4;
			}
		} 
		//Macedonia
		else if(countryCode==7){
			if(grid.getHt() % 2 == 0 && grid.getHt() >= 8){
				int height = grid.getHt();
				int width = grid.getWd();
				//paint all red
				for(int c=0; c<height; c++){
					for(int d=0; d<width; d++){
						grid.setColor(c, d, Color.RED);
					}
				}
				//orange code
				for(int c=0; c<height; c++) {
					for(int d=(width/2)-1; d<=width/2; d++){
						grid.setColor(c, d, Color.ORANGE);   //vertical lines
						grid.setColor(d/2, c, Color.ORANGE); //horizontal pt1
						grid.setColor(d/2, c+height, Color.ORANGE); //hori pt2
					}
				}
				//Orange Square in the Middle
				for(int c=(height/2)-2;c<(height/2)+2;c++){
					for(int d=(width/2)-2;d<(width/2)+2;d++){
						grid.setColor(c,d,Color.ORANGE);
					}
				}
				//Diagonal going left
				int temp;
				temp = 1;
				for(int c=0;c<height;c++){
					for(int d=temp-1;d<=temp;d++){
						grid.setColor(c, d, Color.ORANGE);
					}
					temp+=2;
				}
				//Diagonal going right
				temp=1;
				for(int c=0;c<height;c++){
					for(int d=width-(temp+1);d<=width-temp;d++ ){
						grid.setColor(c,d,Color.ORANGE);
					}
					temp+=2;
				}

			} else
				errorFlag(grid.getHt(), grid.getWd(), grid);
		}
		//The Bahamas
		else if(countryCode==8){
			if(grid.getHt() % 3==0 && grid.getHt() % 2 != 0){
				int height = grid.getHt();
				int width = grid.getWd();
				//Paint everything Blue
				for(int c=0;c<height;c++){
					for(int d=0;d<width;d++){
						grid.setColor(c,d,Color.BLUE);
					}
					//Paint a Yellow Line
				}for(int c=(height/3);c<((height*2)/3);c++){
					for(int d=0;d<width;d++){
						grid.setColor(c, d, Color.YELLOW);
					}
				}
				//makes the triangle
				int temp;
				temp = 0;
				for(int d=0;d<(width-2)/4;d++){
					for(int c=0+temp;c<height-temp;c++){
						grid.setColor(c,d,Color.BLACK);
					}
					temp++;
				}
				//Makes that last black dot at the end of the triangle
				for(int c=(height-1)/2;;){
					for(int d=0;d<=(width-2)/4;d++){
						grid.setColor(c,d,Color.BLACK);
					}
				}
			} else {
				errorFlag(grid.getHt(), grid.getWd(), grid);
			}
		} 
		//Zimbabwe
		else if(countryCode==9){
			if(grid.getHt() % 7 == 0){
				int height = grid.getHt();
				int width = grid.getWd();
				for(int c=0;c<height;c++){
					for(int d=0;d<width;d++){
						grid.setColor(c,d,Color.BLACK);//Whole thing black
					}
				}
				//Next couple for loops make different colored sections
				for(int c=0;c<height/7;c++){
					for(int d=0;d<width;d++){
						grid.setColor(c,d,Color.GREEN);
					}
				}
				for(int c=(height+1)/7;c<(height*2)/7;c++){
					for(int d=0;d<width;d++){
						grid.setColor(c,d,Color.YELLOW);
					}
				}
				for(int c=((height*2)+1)/7;c<(height*3)/7;c++){
					for(int d=0;d<width;d++){
						grid.setColor(c,d,Color.RED);
					}
				}
				for(int c=((height*4)+1)/7;c<(height*5)/7;c++){
					for(int d=0;d<width;d++){
						grid.setColor(c, d, Color.RED);
					}
				}
				for(int c=((height*5)+1)/7;c<(height*6)/7;c++){
					for(int d=0;d<width;d++){
						grid.setColor(c, d,Color.YELLOW);
					}
				}
				for(int c=((height*6)+1)/7;c<height;c++){
					for(int d=0;d<width;d++){
						grid.setColor(c,d,Color.GREEN);
					}
				}
				//White Triangle
				int temp;
				temp = 0;
				for(int d=0;d<=(width*3)/12;d++){
					for(int c=0+temp;c<height-temp;c++){
						grid.setColor(c,d,Color.WHITE);
					}
					temp++;
				}
			} else {
				errorFlag(grid.getHt(),grid.getWd(),grid);
			}

		}
	}




	//Error Flag Method
	public static void errorFlag(int height, int width, MyGrid grid){
		for (int c=0; c<height; c++)
			for(int d=0; d<width; d++){
				grid.setColor(c,d, Color.WHITE);
			}
		grid.setColor(0,0, Color.RED);
		grid.setColor(0,width-1, Color.YELLOW);
		grid.setColor(height-1,0, Color.BLUE);
		grid.setColor(height-1,width-1, Color.GREEN);
	}
}
