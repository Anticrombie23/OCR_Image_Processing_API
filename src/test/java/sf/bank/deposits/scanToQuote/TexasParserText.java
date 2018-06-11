package sf.bank.deposits.scanToQuote;

import org.junit.Test;

import sf.bank.deposits.scanToQuote.allthestuffs.CustomerObj;
import sf.bank.deposits.scanToQuote.allthestuffs.GenericParser;
import sf.bank.deposits.scanToQuote.allthestuffs.ParserFactory;

public class TexasParserText {

	String testString = "\\\"ParsedText\\\": \\\"Texas USA DRIVER LICENSE DL 12345678 0410/2014\\n\\t\\t DOB 09/21/1990 • \\' SAMPLE N.\\n\\t\\t Joy DRIVING 2120 OLD MAIN STREET ANYTOWN, TX 12345 04/10/2018\\n\\t\\t VETERAN • Hgt5-09 „s„F GRN DD 12345678900000000000 \\\",";

	
	
	@Test
	public void testStringParsing() {
		GenericParser parser = ParserFactory.determineParseable(testString);
		CustomerObj obj = parser.parseCustomer();
		
	}
}
