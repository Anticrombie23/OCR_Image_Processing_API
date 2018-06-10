package sf.bank.deposits.scanToQuote;

import org.junit.Test;

import sf.bank.deposits.scanToQuote.controllers.CustomerObj;
import sf.bank.deposits.scanToQuote.controllers.LicensePictureToCustomerConverter;

public class LicensePictureToCustomerConverterTest {

	
	
	LicensePictureToCustomerConverter converter = new LicensePictureToCustomerConverter();
	
	
	@Test
	public void testConverter() {
		CustomerObj conversion = converter.convertByteStreamToCustomerObj(null);
	}
	
	
}
