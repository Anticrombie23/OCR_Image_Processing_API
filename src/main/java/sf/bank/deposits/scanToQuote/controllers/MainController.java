package sf.bank.deposits.scanToQuote.controllers;

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
		return "FUDGE FUDGE FUDGE";
	}
	
	
	@RequestMapping(value = "/getString", method = RequestMethod.POST, produces = "application/json")
	public CustomerObj retrieveCryptoValues(Byte[] pictureByteStream) {	
		CustomerObj toReturn = converter.convertByteStreamToCustomerObj(pictureByteStream);		
		return toReturn;
	}
	
	
}
