package sf.bank.deposits.scanToQuote.allthestuffs;

public class TexasParser extends GenericParser {

	TexasParser(String parseableString) {
		super(parseableString);
	}

	@Override
	public CustomerObj parseCustomer() {

		String replaced = getParseableString();

		replaced = getParseableString().replaceAll("\\\\n", ":");
		replaced = getParseableString().replaceAll("\\\\t", ":");
		replaced = getParseableString().replace("\\", ":");
		String[] splitString = replaced.split(":");
		
		
		String firstToSplit = splitString[4];
		String[] splitOnSpaces = firstToSplit.split(" ");
		String state = splitOnSpaces[0];
		String country = splitOnSpaces[1];
		String dlNumber = splitOnSpaces[5];
		String dateIssued = splitOnSpaces[6];
		if(dateIssued.length() == 9) {
			dateIssued = dateIssued.substring(0, 2) + "/" + dateIssued.substring(2, dateIssued.length());
		}
		
		String secondSplit = splitString[7];
		String dob = secondSplit.substring(6, secondSplit.length());
		
		// OBJ returned looks like this. Must try multiple IDs though
		// "ParsedText": "Texas \r\nUSA \r\nDRIVER LICENSE \r\nDL 12345678 \r\n0410/2014
		// \r\nDOB 09/21/1990 \r\n• ' SAMPLE \r\nN.
		// Joy DRIVING \r\n2120 OLD MAIN STREET \r\nANYTOWN, TX 12345 \r\n04/10/2018
		// \r\nVETERAN \r\n• Hgt5-09 „s„F GRN \r\nDD 12345678900000000000 \r\n",

		return null;
	}

}
