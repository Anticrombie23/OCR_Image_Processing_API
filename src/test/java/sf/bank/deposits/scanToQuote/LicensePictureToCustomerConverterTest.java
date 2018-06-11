package sf.bank.deposits.scanToQuote;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import sf.bank.deposits.scanToQuote.allthestuffs.CustomerObj;
import sf.bank.deposits.scanToQuote.allthestuffs.LicensePictureToCustomerConverter;

public class LicensePictureToCustomerConverterTest {

	LicensePictureToCustomerConverter converter = new LicensePictureToCustomerConverter();

	@Test
	public void testConverter() throws Exception {
		 byte[] mybytestream = new byte[1];
		 CustomerObj conversion =  converter.convertByteStreamToCustomerObj(getByteArray());
	}

	public byte[] getByteArray() throws IOException {
		BufferedImage bImage = ImageIO.read(new File("C:\\DEV\\GIT_VIEWS\\scanToQuote\\OCR_Image_Processing_API\\texas-drivers-license.jpg"));
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(bImage, "jpg", bos);
		byte[] data = bos.toByteArray();
		return data;
	}

}
