package ImageViewer;

import java.util.Arrays;

public class FilterOps {
	
	public static enum PStyle {MIRROR, IGNORE, COPY};
	
	public static int [][][] PadTheImage(int[][][]im , int pad[], PStyle p){
	
		int [][][]PadImage = new int [3][im[0].length+pad[1]+pad[3]][im[0][0].length+pad[0]+pad[2]];
		
		for(int i = 0; i<im[0].length;i++) {
			for(int j=0; j<im[0][0].length; j++) {
				PadImage[0][i+pad[1]][j+pad[0]]= im[0][i][j];
				PadImage[1][i+pad[1]][j+pad[0]]= im[1][i][j];
				PadImage[2][i+pad[1]][j+pad[0]]= im[2][i][j];
			}
		}
		
		switch(p) {
		case MIRROR:
			//left Border
			for(int i =0; i < im[0].length;++i) {
				for(int j = 0,x = 2*pad[0]-1; j<pad[0];++j,--x ) {
					PadImage[0][i][j] = PadImage[0][i][x];
					PadImage[1][i][j] = PadImage[1][i][x];
					PadImage[2][i][j] = PadImage[2][i][x];
				}
			}
			
			//Right Border
			for(int i =0; i < im[0].length;++i) {
				for(int j = PadImage[0][0].length-pad[2], x= PadImage[0][0].length-pad[2]-1;j<pad[2];++j,--x ) {
					PadImage[0][i][j] = PadImage[0][i][x];
					PadImage[1][i][j] = PadImage[1][i][x];
					PadImage[2][i][j] = PadImage[2][i][x];
				}
			}
			
			//Top Border
           for(int i =0; i < im[0][0].length+pad[0]+pad[2];++i) {
				for(int j = 0, x= 2*pad[1]-1;j<pad[1];++j,--x ) {
					PadImage[0][j][i] = PadImage[0][x][i];
					PadImage[1][j][i] = PadImage[1][x][i];
					PadImage[2][j][i] = PadImage[2][x][i];
				}
			}
           
           //Bottom Border
           for(int i =0; i < im[0][0].length+pad[0]+pad[2];++i) {
				for(int j = PadImage[0].length-pad[3], x= PadImage[0].length-pad[3]-1;j<pad[3];++j,--x ) {
					PadImage[0][j][i] = PadImage[0][x][i];
					PadImage[1][j][i] = PadImage[1][x][i];
					PadImage[2][j][i] = PadImage[2][x][i];
				}
           }
           
			
			break;
			
			
		case COPY:
			//Left Border
			for(int i = 0; i<im[0].length;++i) {
				for(int j = 0; j<pad[0];++j) {
					PadImage[0][i][j]= PadImage[0][i][pad[0]];
					PadImage[1][i][j]= PadImage[1][i][pad[0]];
					PadImage[2][i][j]= PadImage[2][i][pad[0]];
				}
			}
			//Right Border
			for(int i = 0; i<im[0].length;++i) {
				for(int j = PadImage[0][0].length-pad[2]; j<PadImage[0][0].length;++j) {
					PadImage[0][i][j]= PadImage[0][i][PadImage[0][0].length-pad[2]-1];
					PadImage[1][i][j]= PadImage[1][i][PadImage[0][0].length-pad[2]-1];
					PadImage[2][i][j]= PadImage[2][i][PadImage[0][0].length-pad[2]-1];
				}
			}
			//Top Border
			for(int i = 0; i<im[0][0].length+pad[0]+pad[2];++i) {
				for(int j = 0; j<pad[1];++j) {
					PadImage[0][j][i]= PadImage[0][pad[1]][i];	
					PadImage[1][j][i]= PadImage[1][pad[1]][i];	
					PadImage[2][j][i]= PadImage[2][pad[1]][i];	
				}
			}
			//Bottom Border
			for(int i = 0; i<im[0][0].length+pad[0]+pad[2];++i) {
				for(int j = PadImage[0].length-pad[3]; j<PadImage[0].length;++j) {
					PadImage[0][j][i]= PadImage[0][PadImage[0].length-pad[3]-1][i];	
					//added
					PadImage[1][j][i]= PadImage[1][PadImage[1].length-pad[3]-1][i];	
					//added
					PadImage[2][j][i]= PadImage[2][PadImage[2].length-pad[3]-1][i];	
				}
			}
		
		break;
		
		case IGNORE:
			break;
			
			
		}
		return PadImage;
		
	}
	
	public static int[][][] Convolution(int im[][][], double kernel[][], int origin[], PStyle p , boolean normal){
		
		//Normalizes image if needed/wanted
		if(normal) {
		double sum = 0;
		for(int i =0; i<kernel.length;++i) {
			for(int j =0; j<kernel[0].length; ++j) {
				sum+= kernel[i][j];
			}
			
		}
		for(int i =0; i<kernel.length;++i) {
			for(int j =0; j<kernel[0].length; ++j) {
				kernel[i][j] /= sum; 
				
			   }
			}
		}
		// left, top, right, bottom padding 
		int padding[] = {origin[1],origin[0],kernel[0].length-origin[1]-1,kernel[0].length-origin[0]-1};
		
		
		int PaddedImage[][][] = PadTheImage(im,padding,p);
		
		int NewImage[][][] = new int [3][im[0].length][im[0][0].length];
		
		
		
		
		
		//Convolution Op
		
		for(int i = origin[0]; i< PaddedImage[0].length-(kernel.length-origin[0]-1);++i) {
			for(int j = origin[1]; j< PaddedImage[0][0].length-(kernel[0].length-origin[1]-1);++j) {
				double sumR = 0;
				double sumG = 0;
				double sumB = 0;
			for(int a =0; a<kernel.length;++a) {	
				for(int b =0; b<kernel[0].length;++b) {
					sumR+= PaddedImage[0][i+a - origin[0]][j+b - origin[1]]*kernel[a][b];
					sumG+= PaddedImage[1][i+a - origin[0]][j+b - origin[1]]*kernel[a][b];
					sumB+= PaddedImage[2][i+a - origin[0]][j+b - origin[1]]*kernel[a][b];
					
				}
			}
			NewImage[0][i-origin[0]][j-origin[1]] = (int)sumR;
			NewImage[1][i-origin[0]][j-origin[1]] = (int)sumG;
			NewImage[2][i-origin[0]][j-origin[1]] = (int)sumB;
			}
		}
		
		
		return (NewImage);
		
	}
	
	
	// x = size of filter , x =1 , filter is 3x3, x = 2, filter is 5x5
	public static int[][][] MedianFilter(int im[][][], int x){
		int M = im[0].length;
		int N = im[0][0].length;
		
		int[] AR = new int[(2 * x + 1) * (2 * x + 1)]; 
		int[] AG = new int[(2 * x + 1) * (2 * x + 1)]; 
		int[] AB = new int[(2 * x + 1) * (2 * x + 1)]; 
		
	int n = 2 * (x * x + x);
 for (int a = x; a <= M - x - 2; a++) {  
	 for (int b = x; b<= N - x - 2; b++) { 
		int k = 0;  
		for (int i = -x; i <= x; i++) { 
			for (int j = -x; j <= x; j++) {  
			AR[k] = im[0][a+i][b+j];
			AG[k] =im[1][a+i][b+j];
			AB[k] = im[2][a+i][b+j];
			k++;  
			   }  
			}  
		Arrays.sort(AR); 
		Arrays.sort(AG); 
		Arrays.sort(AB); 
		im[0][a][b] = AR[n]; 
		im[1][a][b] = AG[n]; 
		im[2][a][b] = AB[n]; 
		
		
		}  
	 }  

 

		
		return im;
		
	}
	
	//x = size
	public static int [][][] OutlierFilterOp(int im [][][], int x , int Threshold){
		int M = im[0].length;
		int N = im[0][0].length;
		
		
		
		
		int[] AR = new int[(2 * x + 1) * (2 * x + 1)]; 
		int[] AG = new int[(2 * x + 1) * (2 * x + 1)]; 
		int[] AB = new int[(2 * x + 1) * (2 * x + 1)]; 

		
		for (int a = x; a <= M - x - 2; a++) {  
			 for (int b = x; b<= N - x - 2; b++) { 
				 
				 double meanR =0;
					double meanG = 0;
					double meanB = 0; 
					
					int sumR = 0;
					
					int sumG = 0;
					
					int sumB = 0; 

				 
				int k = 0;  
				for (int i = -x; i <= x; i++) { 
					for (int j = -x; j <= x; j++) {  
					AR[k] = im[0][a+i][b+j];
					AG[k] =im[1][a+i][b+j];
					AB[k] = im[2][a+i][b+j];
					k++;  
					   }  
					}
				for(int c = 0; c<AR.length;c++) {
					sumR += AR[c];
					meanR = sumR/AR.length;
				}
				for(int c = 0; c<AG.length;c++) {
					sumG += AG[c];
					meanG = sumG/AG.length;
				}
				for(int c = 0; c<AB.length;c++) {
					sumB += AB[c];
					meanB = sumB/AB.length;
				}
				
				//Red Threshold Check
				if(Math.abs(im[0][a][b]-meanR)> Threshold) {
					im[0][a][b] = (int) meanR;
					
				}
				
				//Green Threshold check 
				if(Math.abs(im[1][a][b]-meanG)> Threshold) {
					im[1][a][b] = (int) meanG;
					
				}
				
				//Blue Threshold Check
				if(Math.abs(im[2][a][b]-meanB)> Threshold) {
					im[2][a][b] = (int) meanB;
					
				}
				
				//Red Threshold less than check
				if(Math.abs(im[0][a][b]-meanR) <= Threshold){
					im[0][a][b] = im[0][a][b];
				}
				//Green Threshold less than check
				if(Math.abs(im[1][a][b]-meanG) <= Threshold){
					im[1][a][b] = im[1][a][b];
				}
				
				//blue Threshold less than check
				if(Math.abs(im[2][a][b]-meanB) <= Threshold){
					im[2][a][b] = im[2][a][b];
				}
								
					
				
			 }
		}
		
		
		return im;
	}
	
	public static double[][] Gaussian2DKernelOp(int width,int height,double Sigmax, double Sigmay) {
		int w = width; 
		int h = height; 
		
		double kernel [][] = new double [h][w];
		
		for (int y = -h / 2; y <= h / 2; ++y) {
			for (int x = -w / 2; x <= w / 2; ++x) {
	        kernel[y + h / 2][x + w / 2] =
			Math.exp(-(((x * x) / (2 * Sigmax * Sigmax)) +((y * y) / (2 * Sigmay * Sigmay))));
			    }
			}
		
		
		return kernel;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
