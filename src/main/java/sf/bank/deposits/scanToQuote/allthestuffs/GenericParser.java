package sf.bank.deposits.scanToQuote.allthestuffs;

public abstract class GenericParser implements LicenseParsebale {

	private String parseableString;
	private CustomerObj cust;

	GenericParser(String parseableString) {
		this.parseableString = parseableString;
	}

	public String getParseableString() {
		return parseableString;
	}

	public void setParseableString(String parseableString) {
		this.parseableString = parseableString;
	}

	public CustomerObj getCust() {
		if (cust == null) {
			cust = new CustomerObj();
			return cust;
		}else {
			return cust;
		}
	}

	public void setCust(CustomerObj cust) {
		this.cust = cust;
	}

}
