package ImageViewer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageObject extends ImageObjectAbstract {
	


	ImageObject(){
		
	}

	@Override
	public int[][][] getImage() {
		// TODO Auto-generated method stub
		return image;
	}

	@Override
	public int[] getPixel(int y, int x) throws IndexOutOfBoundsException {
	try {
		int [] colors = new int [3];
		colors[0] = image[0][y][x];
		colors[1]= image[1][y][x];
		colors[2] = image[2][y][x];
		return colors;
	}catch(IndexOutOfBoundsException e) {
		throw e;
	}
	}

	@Override
	public BufferedImage toBI() {
		BufferedImage bi0 = new BufferedImage(image[0][0].length, image[0].length, BufferedImage.TYPE_INT_RGB);
		for (int y = 0; y < bi0.getHeight(); ++y) {
			for (int x = 0; x < bi0.getWidth(); ++x) {
				int rgb =
						(image[0][y][x] << 16) |
						(image[1][y][x] <<  8) |
						(image[2][y][x] <<  0);
				bi0.setRGB(x,  y,  rgb);
				
				
			}
		}
		return bi0;
		
	}
	public int[][][] FromBI(BufferedImage im){
		image = new int[3][im.getHeight()][im.getWidth()];
		for (int y = 0; y < im.getHeight(); ++y) {
			for (int x = 0; x < im.getWidth(); ++x) {
				int argb = im.getRGB(x, y);
				image[0][y][x]  = (argb >> 16) & 0xFF; // -- RED
				image[1][y][x]  = (argb >>  8) & 0xFF; // -- GREEN
				image[2][y][x]  = (argb >>  0) & 0xFF; // -- BLUE
			}
		
		
		
		}
		return image;
	}

	@Override
	public void loadImage(String filename) throws IOException {
		try {
		BufferedImage bi = ImageIO.read(new File(filename));
		// -- BufferedImage -> int[][][]
					image = new int[3][bi.getHeight()][bi.getWidth()];
					for (int y = 0; y < bi.getHeight(); ++y) {
						for (int x = 0; x < bi.getWidth(); ++x) {
							int argb = bi.getRGB(x, y);
							image[0][y][x]  = (argb >> 16) & 0xFF; // -- RED
							image[1][y][x]  = (argb >>  8) & 0xFF; // -- GREEN
							image[2][y][x]  = (argb >>  0) & 0xFF; // -- BLUE
						}
						
					}
		}catch (IOException e) {
			System.out.println(e);
		}
					
		
		
	}

	@Override
	public void saveImage(String filename, String format) throws IOException {
		try {
			BufferedImage bi0 = new BufferedImage(image[0][0].length,image[0].length, BufferedImage.TYPE_INT_RGB);
			for (int y = 0; y < bi0.getHeight(); ++y) {
				for (int x = 0; x < bi0.getWidth(); ++x) {
					int rgb =
							(image[0][y][x] << 16) |
							(image[1][y][x] <<  8) |
							(image[2][y][x] <<  0);
					bi0.setRGB(x,  y,  rgb);
					
				}
		}
		ImageIO.write(this.toBI(), format,new File(filename));
		}catch (IOException e) {
			System.out.println(e);
		}
	}

	@Override
	public int[][] getComponent(COMPONENTS color) {
		if(color == COMPONENTS.RED) {
			return image[0];
			}
			
		
      if(color == COMPONENTS.GREEN) {
    	 
				return image[1];
				}
			
			
		
      if(color == COMPONENTS.BLUE) {
    	 
				
					return image[2];
	
}
			
      
		 
			
		
		return null;
	}

}
