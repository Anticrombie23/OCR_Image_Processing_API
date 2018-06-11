package sf.bank.deposits.scanToQuote.allthestuffs;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	
	@Autowired
	LicensePictureToCustomerConverter converter;

	@RequestMapping(value = "/getString", method = RequestMethod.GET, produces = "application/json")
	public String getdashboard(Model model){
		return "TESTING, ATTENTION PLEASE! HERE'S TEN CENTS, MY 2 CENTS IS FREE";
	}
	
	
	@RequestMapping(value = "/getString", method = RequestMethod.POST, produces = "application/json")
	public CustomerObj retrieveCryptoValues(byte[] pictureByteStream) throws Exception {	
		CustomerObj toReturn = converter.convertByteStreamToCustomerObj(pictureByteStream);		
		return toReturn;
	}
	
	

	
	
	
}
