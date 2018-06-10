package sf.bank.deposits.scanToQuote.controllers;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.lang.reflect.Field;
import java.util.stream.IntStream;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import sf.bank.deposits.scanToQuote.ImageOperations;

public class ImagePreProcessor {

	public static BufferedImage processImage(BufferedImage bufferedImage) throws Exception {

		// Init the openCV libraries. Just leave this code alone, it works for now.
		loadOpenCV_Lib();

		//Apply grayscale. This seems to help a bit. 
		BufferedImage changedImage = applyGrayScaleToImage(bufferedImage);


		//TBD if this helps
	//	changedImage = blur(changedImage);
		
		
		//Threshold the image?
	//	changedImage = Threshold(changedImage, 30);
		
		
		
		return changedImage;
	}
	
	
	
	public static BufferedImage Threshold(BufferedImage img,int requiredThresholdValue) {
		int height = img.getHeight();
		int width = img.getWidth();
		BufferedImage finalThresholdImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB) ;
		
		int red = 0;
		int green = 0;
		int blue = 0;
		
		for (int x = 0; x < width; x++) {
			try {

				for (int y = 0; y < height; y++) {
					int color = img.getRGB(x, y);

					red = ImageOperations.getRed(color);
					green = ImageOperations.getGreen(color);
					blue = ImageOperations.getBlue(color);

					if((red+green+green)/3 < (int) (requiredThresholdValue)) {
							finalThresholdImage.setRGB(x,y,ImageOperations.mixColor(0, 0,0));
						}
						else {
							finalThresholdImage.setRGB(x,y,ImageOperations.mixColor(255, 255,255));
						}
					
				}
			} catch (Exception e) {
				 e.getMessage();
			}
		}
		
		return finalThresholdImage;
	}



	private static BufferedImage applyGrayScaleToImage(BufferedImage bufferedImage) {
		// ImageFilter filter = new GrayFilter(true, 50);
		// ImageProducer producer = new FilteredImageSource(bufferedImage.getSource(),
		// filter);
		// Image mage = Toolkit.getDefaultToolkit().createImage(producer);

		byte[] data = ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData();
		Mat mat = new Mat(bufferedImage.getHeight(), bufferedImage.getWidth(), CvType.CV_8UC3);
		mat.put(0, 0, data);

		Mat mat1 = new Mat(bufferedImage.getHeight(), bufferedImage.getWidth(), CvType.CV_8UC1);
		Imgproc.cvtColor(mat, mat1, Imgproc.COLOR_RGB2GRAY);

		byte[] data1 = new byte[mat1.rows() * mat1.cols() * (int) (mat1.elemSize())];
		mat1.get(0, 0, data1);
		BufferedImage image1 = new BufferedImage(mat1.cols(), mat1.rows(), BufferedImage.TYPE_BYTE_GRAY);
		image1.getRaster().setDataElements(0, 0, mat1.cols(), mat1.rows(), data1);

		return image1;
	}

	public static void loadOpenCV_Lib() throws Exception {
		// get the model
		String model = System.getProperty("sun.arch.data.model");
		// the path the .dll lib location
		String libraryPath = "C:/opencv/build/java/x86/";
		// check for if system is 64 or 32
		if (model.equals("64")) {
			libraryPath = "C:/opencv/build/java/x64/";
		}
		// set the path
		System.setProperty("java.library.path", libraryPath);
		Field sysPath = ClassLoader.class.getDeclaredField("sys_paths");
		sysPath.setAccessible(true);
		sysPath.set(null, null);
		// load the lib
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	public static BufferedImage blur(BufferedImage image) {
		int[] filter = { 1, 2, 1, 2, 4, 2, 1, 2, 1 };
		int filterWidth = 3;

		if (filter.length % filterWidth != 0) {
			throw new IllegalArgumentException("filter contains a incomplete row");
		}

		final int width = image.getWidth();
		final int height = image.getHeight();
		final int sum = IntStream.of(filter).sum();

		int[] input = image.getRGB(0, 0, width, height, null, 0, width);

		int[] output = new int[input.length];

		final int pixelIndexOffset = width - filterWidth;
		final int centerOffsetX = filterWidth / 2;
		final int centerOffsetY = filter.length / filterWidth / 2;

		// apply filter
		for (int h = height - filter.length / filterWidth + 1, w = width - filterWidth + 1, y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				int r = 0;
				int g = 0;
				int b = 0;
				for (int filterIndex = 0, pixelIndex = y * width
						+ x; filterIndex < filter.length; pixelIndex += pixelIndexOffset) {
					for (int fx = 0; fx < filterWidth; fx++, pixelIndex++, filterIndex++) {
						int col = input[pixelIndex];
						int factor = filter[filterIndex];

						// sum up color channels seperately
						r += ((col >>> 16) & 0xFF) * factor;
						g += ((col >>> 8) & 0xFF) * factor;
						b += (col & 0xFF) * factor;
					}
				}
				r /= sum;
				g /= sum;
				b /= sum;
				// combine channels with full opacity
				output[x + centerOffsetX + (y + centerOffsetY) * width] = (r << 16) | (g << 8) | b | 0xFF000000;
			}
		}

		BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		result.setRGB(0, 0, width, height, output, 0, width);
		return result;
	}

}
