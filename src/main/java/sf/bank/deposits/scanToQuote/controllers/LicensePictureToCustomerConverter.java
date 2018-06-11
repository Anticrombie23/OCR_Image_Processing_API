package sf.bank.deposits.scanToQuote.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import io.github.kschaap1994.imgur.ImgurClient;
import io.github.kschaap1994.imgur.model.ImgurImage;

@Component
public class LicensePictureToCustomerConverter {

	// ITesseract instance;

	// public LicensePictureToCustomerConverter() {
	// instance = initTesseract();
	// }

	// TODO tasks

	// Convert to Grayscale.
	// Gaussian Blur with 3x3 or 5x5 filter.
	// Apply Sobel Filter to find vertical edges.
	//
	// Sobel(gray, dst, -1, 1, 0)
	//
	// Threshold the resultant image to get a binary image.
	// Apply a morphological close operation using suitable structuring element.
	// Find contours of the resulting image.
	// Find minAreaRect of each contour. Select rectangles based on aspect ratio and
	// minimum and maximum area.
	// For each selected contour, find edge density. Set a threshold for edge
	// density and choose the rectangles breaching that threshold as possible plate
	// regions.
	// Few rectangles will remain after this. You can filter them based on
	// orientation or any criteria you deem suitable.
	// Clip these detected rectangular portions from the image after
	// adaptiveThreshold and apply OCR.

	// https://docparser.com/blog/improve-ocr-accuracy/
	// OCR api potentially https://ocr.space/ocrapi
	// http://www.ocr-it.com/ocr-cloud-2-0-api/

	public static final String URI_PREFIX = "https://api.ocr.space/parse/image";
	public static final String apiKey = "bb80db454d88957";
	RestTemplate template = new RestTemplate();

	public CustomerObj convertByteStreamToCustomerObj(byte[] pictureByteStream) throws Exception {

		// MUST UPLOAD TO IMGUR FIRST, then we need to extract the location of the image
		// (all the way to .PNG or JPG)
		ImgurImage returnedImage = contactImgur(pictureByteStream);
		// pass the imgur URL into contact ocr service

		// Attempt client instead
		String returnedInfo = contactOCRService(returnedImage);

		// Convert to obj to send back to UI
		CustomerObj obj = convertReturnedInfoToCustObj(returnedInfo);

		// BufferedImage bufferedImage = createImageFromByteStream(pictureByteStream);
		// bufferedImage = processImageBeforeOCR(bufferedImage);
		// String ocrString = performOCR(bufferedImage, instance);
		// CustomerObj obj = extractCustomerObjFromString(ocrString);
		return obj;
	}

	private ImgurImage contactImgur(byte[] pictureByteStream) throws IOException {
		String imgurClientID = "50031ab084d80fe";
		String imgurClientSecret = "d0e2425b95469f365c0ef6b03816ae57e4bd137b";
		// Must change byte stream into Base64 encoded String to send to imgur for
		// upload. TBD how.

		ImgurClient client = new ImgurClient(imgurClientID);

		try {
			ImgurImage image = client.uploadImage(pictureByteStream);
			return image;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private CustomerObj convertReturnedInfoToCustObj(String returnedInfo) {

		// OBJ returned looks like this. Must try multiple IDs though
		// "ParsedText": "Texas \r\nUSA \r\nDRIVER LICENSE \r\nDL 12345678 \r\n0410/2014
		// \r\nDOB 09/21/1990 \r\n• ' SAMPLE \r\nN.
		// Joy DRIVING \r\n2120 OLD MAIN STREET \r\nANYTOWN, TX 12345 \r\n04/10/2018
		// \r\nVETERAN \r\n• Hgt5-09 „s„F GRN \r\nDD 12345678900000000000 \r\n",

		return null;
	}

	private String contactOCRService(ImgurImage image) throws IOException {
		
		
		 URL obj = new URL(URI_PREFIX); // OCR API Endpoints
	     HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
	     con.setRequestMethod("POST");
	     con.setRequestProperty("User-Agent", "Mozilla/5.0");
	     con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("apiKey", apiKey);
//
//		ResponseEntity<ResponseObj> response = null;
//		OCRModel model = prepareModel(image.link);
//		HttpEntity<OCRModel> entity = new HttpEntity<OCRModel>(model, headers);
//		try {
//			response = template.exchange(URI_PREFIX, HttpMethod.POST, entity, ResponseObj.class, UUID.randomUUID());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return null;
	}

	private OCRModel prepareModel(String imgurURL) {
		OCRModel model = new OCRModel();
		model.setLanguage("eng");
		model.setUrl(imgurURL);
		model.setFiletype("jpg");
		return model;
	}

	// private BufferedImage processImageBeforeOCR(BufferedImage bufferedImage)
	// throws Exception {
	// return ImagePreProcessor.processImage(bufferedImage);
	// }

	private CustomerObj extractCustomerObjFromString(String ocrString) {
		// TODO Auto-generated method stub
		return null;
	}

	// private String performOCR(BufferedImage bufferedImage, ITesseract instance) {
	// try {
	// String result = instance.doOCR(bufferedImage);
	// System.out.println(result);
	// return result;
	// } catch (TesseractException e) {
	// System.out.println(e.getMessage());
	// return null;
	// }
	// }

	private BufferedImage createImageFromByteStream(byte[] pictureByteStream) {
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new ByteArrayInputStream(pictureByteStream));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bufferedImage;
	}

	// private ITesseract initTesseract() {
	// ITesseract instance = new Tesseract();
	// instance.setDatapath("C:\\DEV\\Views\\scanToQuote\\tessdata");
	// return instance;
	// }

}
