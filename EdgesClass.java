package ImageViewer;

public class EdgesClass {

public static int [] GetNeighbors(int I[][][],int x ,int y ,int colorval) {
 int neigh [] = new int[7];
neigh[0]= I[colorval][x+1][y];
neigh[1]= I[colorval][x+1][y-1];
neigh[2]= I[colorval][x][y-1];
neigh[3]= I[colorval][x-1][y-1];
neigh[4]= I[colorval][x-1][y];
neigh[5]= I[colorval][x-1][y+1];
neigh[6]= I[colorval][x][y+1];
neigh[7]= I[colorval][x+1][y+1];


return neigh;
	
}

public static boolean IsLocalMax(int I[][][],int x , int y , int colorval) {
	
	int[] N = GetNeighbors(I,x,y,colorval);
	int MaxValue = N[0];
	for(int i = 1; i<N.length; i++) {
		if(N[i]>MaxValue) {
			MaxValue = N[i];
		}
	}
	return I[colorval][x][y] >MaxValue;
	
}

public static int [][][] HoughTransform(int image[][][], int m, int n, int a ){
	
int M = image[0].length;
int N = image[0][0].length;
int xc = M/2;
int yc = N/2;


	return image;
	
}




}
