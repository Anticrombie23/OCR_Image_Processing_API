package sf.bank.deposits.scanToQuote.controllers;

import java.io.File;

import org.springframework.stereotype.Component;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@Component
public class LicensePictureToCustomerConverter {

	public CustomerObj convertByteStreamToCustomerObj(Byte[] pictureByteStream) {
		
		
		File imageFile = new File("C:\\DEV\\Views\\scanToQuote\\texas-drivers-license.jpg");
		ITesseract instance = new Tesseract();
		instance.setDatapath("C:\\DEV\\Views\\scanToQuote\\tessdata");
		
		try {
			String result = instance.doOCR(imageFile);
			System.out.println(result);
		}catch(TesseractException e) {
			System.out.println(e.getMessage());
		}
		
		
		return null;
	}

}
