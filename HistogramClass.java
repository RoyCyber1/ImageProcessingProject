package ImageViewer;

import java.awt.image.BufferedImage;
import java.lang.Math;


public class HistogramClass {

private int [] RedHistogram = new int [256];
private int [] GreenHistogram= new int [256];
private int [] BlueHistogram= new int [256];

private int []CumulativeHistogramRed = new int [256];
private int []CumulativeHistogramGreen = new int [256];
private int []CumulativeHistogramBlue = new int [256];

private int H; 

private int W;



public int[] getRedHistogram() {
	return RedHistogram;
}
public void setRedHistogram(int[] redHistogram) {
	RedHistogram = redHistogram;
}
public int[] getGreenHistogram() {
	return GreenHistogram;
}
public void setGreenHistogram(int[] greenHistogram) {
	GreenHistogram = greenHistogram;
}
public int[] getBlueHistogram() {
	return BlueHistogram;
}
public void setBlueHistogram(int[] blueHistogram) {
	BlueHistogram = blueHistogram;
}

public int[] getCumulativeHistogramRed() {
	return CumulativeHistogramRed;
}
public void setCumulativeHistogramRed(int[] cumuHistogram) {
	CumulativeHistogramRed = cumuHistogram;
}

public int[] getCumulativeHistogramGreen() {
	return CumulativeHistogramGreen;
}
public void setCumulativeHistogramGreen(int[] cumuHistogram) {
	CumulativeHistogramGreen = cumuHistogram;
}

public int[] getCumulativeHistogramBlue() {
	return CumulativeHistogramBlue;
}
public void setCumulativeHistogramBlue(int[] cumuHistogram) {
	CumulativeHistogramBlue = cumuHistogram;
}



//Means 
public double GetMeanRed() {
	double RedM = 0;
	int redsum = 0;
	
	for(int k= 0; k<RedHistogram.length;++k) {
		redsum += RedHistogram[k]*k;
		
	}
	RedM = redsum/(H*W);
	
	return RedM;	
	
}
public double GetMeanGreen() {
double GreenM = 0;
int greensum = 0;

for(int k= 0; k<GreenHistogram.length;k++) {
	greensum += GreenHistogram[k]*k;	
}
GreenM = greensum/(H*W);
return GreenM;
}

public double GetMeanBlue() {
	double BlueM = 0;
	int bluesum = 0;
for(int r= 0; r<BlueHistogram.length;r++) {
	bluesum += r* BlueHistogram[r];
	
}
BlueM = bluesum/(H*W);
return BlueM;
}






// Standard Deviations For Each Color

// add Mean to rest of colors 
public double GetSDevRed() {
	double MeanR = GetMeanRed();
	double STDev = 0;
	double STDevA = 0;
	double STDevB = 0;
	for(int i =0; i<RedHistogram.length;i++) {
	STDevA += RedHistogram[i]*i;
	STDevB += RedHistogram[i]*(i*i);
	}
	double STA2 = STDevA*STDevA;
	double InnerTerm = (STDevB - (STA2/(W*H)))/(W*H);
	STDev = Math.sqrt(InnerTerm);
		return STDev;
		
	
}




public double GetSDevGreen() {
	double MeanG = GetMeanGreen();
	double STDev = 0;
	double STDevA = 0;
	double STDevB = 0;
	for(int i =0; i<GreenHistogram.length;i++) {
	STDevA += GreenHistogram[i]*i;
	STDevB += GreenHistogram[i]*(i*i);
	}
	double STA2 = STDevA*STDevA;
	double InnerTerm = (STDevB - (STA2/(W*H)))/(W*H);
	STDev = Math.sqrt(InnerTerm);
		return STDev;
		
}

public double GetSDevBlue() {
	double MeanB = GetMeanBlue();
	double STDev = 0;
	double STDevA = 0;
	double STDevB = 0;
	for(int i =0; i<BlueHistogram.length;i++) {
	STDevA += BlueHistogram[i]*i;
	STDevB += BlueHistogram[i]*(i*i);
	}
	double STA2 = STDevA*STDevA;
	double InnerTerm = (STDevB - (STA2/(W*H)))/(W*H);
	STDev = Math.sqrt(InnerTerm);
		return STDev;

}


public int GetMaxValueRed() {
	int MaxValue = RedHistogram[0];
	for(int i = 1; i<RedHistogram.length; i++) {
		if(RedHistogram[i]>MaxValue) {
			MaxValue = RedHistogram[i];
		}
	}
	
	return MaxValue;
	
}




public int GetMaxValueGreen() {
	int MaxValue = GreenHistogram[0];
	for(int i = 1; i<GreenHistogram.length; i++) {
		if(GreenHistogram[i]>MaxValue) {
			MaxValue = GreenHistogram[i];
		}
	}
	
	return MaxValue;
	
}
public int GetMaxValueBlue() {
	int MaxValue = BlueHistogram[0];
	for(int i = 1; i<BlueHistogram.length; i++) {
		if(BlueHistogram[i]>MaxValue) {
			MaxValue = BlueHistogram[i];
		}
	}
	
	return MaxValue;
	
}


//Minimum Value

public int GetMinValueRed() {
	int MinValue = RedHistogram[0];
	for(int i = 1; i<RedHistogram.length; i++) {
		if(RedHistogram[i]<MinValue) {
			MinValue = RedHistogram[i];
		}
	}
	
	return MinValue;
}

public int GetMinValueGreen() {
	int MinValue = GreenHistogram[0];
	for(int i = 1; i<GreenHistogram.length; i++) {
		if(GreenHistogram[i]<MinValue) {
			MinValue = GreenHistogram[i];
		}
	}
	
	return MinValue;
}


public int GetMinValueBlue() {
	int MinValue = BlueHistogram[0];
	for(int i = 1; i<BlueHistogram.length; i++) {
		if(BlueHistogram[i]<MinValue) {
			MinValue = BlueHistogram[i];
		}
	}
	
	return MinValue;
}






public int GetModeRed() {
	int maxValue = 0, maxCount = 0;
	 
    for (int i = 0; i < RedHistogram.length; ++i) 
    {
        int count = 0;
        for (int j = 0; j < RedHistogram.length; ++j) 
        {
            if (RedHistogram[j] == RedHistogram[i])
                ++count;
        }
        if (count > maxCount) 
        {
            maxCount = count;
            maxValue = RedHistogram[i];
        }
    }
	return maxValue;

}



public int GetModeGreen() {
	int maxValue = 0, maxCount = 0;
	 
    for (int i = 0; i < GreenHistogram.length; ++i) 
    {
        int count = 0;
        for (int j = 0; j < GreenHistogram.length; ++j) 
        {
            if (GreenHistogram[j] == GreenHistogram[i])
                ++count;
        }
        if (count > maxCount) 
        {
            maxCount = count;
            maxValue = GreenHistogram[i];
        }
    }
	return maxValue;

}



public int GetModeBlue() {
	int maxValue = 0, maxCount = 0;
	 
    for (int i = 0; i < BlueHistogram.length; ++i) 
    {
        int count = 0;
        for (int j = 0; j < BlueHistogram.length; ++j) 
        {
            if (BlueHistogram[j] == BlueHistogram[i])
                ++count;
        }
        if (count > maxCount) 
        {
            maxCount = count;
            maxValue = BlueHistogram[i];
        }
    }
	return maxValue;

}



public void ComputeHistogramRed(int im [][][]) {
	W = im[0][0].length;
	H = im[0].length;
	for (int y = 0; y < im[0].length; ++y) {
		for (int x = 0; x < im[0][0].length; ++x) {
			
		RedHistogram[im[0][y][x]]++;
		
			}
		}
	}


public void ComputeHistogramGreen(int im [][][]) {
	W = im[0][0].length;
	H = im[0].length;
	for (int y = 0; y < im[0].length; ++y) {
		for (int x = 0; x < im[0][0].length; ++x) {
		GreenHistogram[im[1][y][x]]++;
		
			}
		}
	}


public void ComputeHistogramBlue(int im [][][]) {
	W = im[0][0].length;
	H = im[0].length;
	for (int y = 0; y < im[0].length; ++y) {
		for (int x = 0; x < im[0][0].length; ++x) {
		BlueHistogram[im[2][y][x]]++;
			}
		}
	}

public void ComputeCumulativeHistogramRed() {
CumulativeHistogramRed[0] = RedHistogram[0];
for (int j = 1; j < 256; j++) { 
	CumulativeHistogramRed[j] = CumulativeHistogramRed[j - 1] + RedHistogram[j];  
	} 
	
}

public void ComputeCumulativeHistogramGreen() {
CumulativeHistogramGreen[0] = GreenHistogram[0];
for (int j = 1; j < 256; j++) { 
	CumulativeHistogramGreen[j] = CumulativeHistogramGreen[j - 1] + GreenHistogram[j];  
	} 
	
}

public void ComputeCumulativeHistogramBlue() {
	CumulativeHistogramBlue[0] = BlueHistogram[0];
for (int j = 1; j < 256; j++) { 
	CumulativeHistogramBlue[j] = CumulativeHistogramBlue[j - 1] + BlueHistogram[j];  
	} 
	
}



			

	



	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		

	}

}
