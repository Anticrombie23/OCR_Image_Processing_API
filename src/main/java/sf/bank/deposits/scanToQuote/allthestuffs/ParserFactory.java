package sf.bank.deposits.scanToQuote.allthestuffs;

public class ParserFactory {

	public static GenericParser determineParseable(String returnedInfo) {

		GenericParser parser;

		if (returnedInfo.contains("Texas") || returnedInfo.contains("TEXAS") || returnedInfo.contains("texas")) {
			return parser = new TexasParser(returnedInfo);
		}

		return null;
	}

}
