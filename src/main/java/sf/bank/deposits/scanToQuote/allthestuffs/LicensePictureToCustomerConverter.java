package sf.bank.deposits.scanToQuote.allthestuffs;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.UUID;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

	private static final Logger LOGGER = Logger.getLogger("LicensePictureToCustomerConverter");

	private static final String LANGUAGE_PARM = "eng";
	public static final String URI_PREFIX = "https://api.ocr.space/parse/image";
	public static final String API_KEY = "bb80db454d88957";
	RestTemplate template = new RestTemplate();

	public CustomerObj convertByteStreamToCustomerObj(byte[] pictureByteStream) throws Exception {

		// MUST UPLOAD TO IMGUR FIRST, then we need to extract the location of the image
		// (all the way to .PNG or JPG)
		ImgurImage returnedImage = contactImgur(pictureByteStream);
		// pass the imgur URL into contact ocr service

		if (returnedImage == null || returnedImage.link == null) {
			throw new RuntimeException("Image returned from IMGUR never saved. Can't continue");
		}

		// Attempt OCR client service now instead
		String returnedInfo = contactOCRService(returnedImage);

		// Convert to obj to send back to UI
		CustomerObj custObj = convertReturnedInfoToCustObj(returnedInfo);

		// MUST DO!!! Perform DELETE with DELETE has on imgur. We don't want people to
		// have their license photos online!

		// BufferedImage bufferedImage = createImageFromByteStream(pictureByteStream);
		// bufferedImage = processImageBeforeOCR(bufferedImage);
		// String ocrString = performOCR(bufferedImage, instance);
		// CustomerObj obj = extractCustomerObjFromString(ocrString);
		return custObj;
	}

	private ImgurImage contactImgur(byte[] pictureByteStream) throws IOException {
		String imgurClientID = "50031ab084d80fe";
		String imgurClientSecret = "d0e2425b95469f365c0ef6b03816ae57e4bd137b";
		// Must change byte stream into Base64 encoded String to send to imgur for
		// upload. TBD how.

		ImgurClient client = new ImgurClient(imgurClientID);

		try {
			ImgurImage image = client.uploadImage(pictureByteStream);
			LOGGER.info("Image created at :" + image.link);
			LOGGER.info("Delete hash for this image is : " + image.deletehash);
			return image;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private CustomerObj convertReturnedInfoToCustObj(String returnedInfo) {
		GenericParser parseable = ParserFactory.determineParseable(returnedInfo);

		if (parseable != null) {
			return parseable.parseCustomer();
		} else {
			throw new RuntimeException("No states we're familiar with parsing were contained in the OCR string");
		}
	}

	private String contactOCRService(ImgurImage image) throws Exception {

		URL obj = new URL(URI_PREFIX); // OCR API Endpoints
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		JSONObject jsonObj = createJsonObjects(image);

		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(getPostDataString(jsonObj));
		wr.flush();
		wr.close();

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		LOGGER.info("Object before parsing looks like dis: " + String.valueOf(response));

		return String.valueOf(response);

		// HttpHeaders headers = new HttpHeaders();
		// headers.add("apiKey", apiKey);
		//
		// ResponseEntity<ResponseObj> response = null;
		// OCRModel model = prepareModel(image.link);
		// HttpEntity<OCRModel> entity = new HttpEntity<OCRModel>(model, headers);
		// try {
		// response = template.exchange(URI_PREFIX, HttpMethod.POST, entity,
		// ResponseObj.class, UUID.randomUUID());
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		//
		// return null;
	}

	public String getPostDataString(JSONObject params) throws Exception {

		StringBuilder result = new StringBuilder();
		boolean first = true;

		Iterator<String> itr = params.keys();

		while (itr.hasNext()) {

			String key = itr.next();
			Object value = params.get(key);

			if (first)
				first = false;
			else
				result.append("&");

			result.append(URLEncoder.encode(key, "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(value.toString(), "UTF-8"));

		}
		return result.toString();
	}

	private JSONObject createJsonObjects(ImgurImage image) {
		JSONObject postDataParams = new JSONObject();
		postDataParams.put("apikey", API_KEY);
		postDataParams.put("isOverlayRequired", false);
		postDataParams.put("url", image.link);
		postDataParams.put("language", LANGUAGE_PARM);
		return postDataParams;
	}

	// private OCRModel prepareModel(String imgurURL) {
	// OCRModel model = new OCRModel();
	// model.setLanguage(LANGUAGE_PARM);
	// model.setUrl(imgurURL);
	// model.setFiletype("jpg");
	// return model;
	// }

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
