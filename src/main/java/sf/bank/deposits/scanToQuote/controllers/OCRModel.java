package sf.bank.deposits.scanToQuote.controllers;

public class OCRModel {

	String base64Image;
	String language;
	boolean isOverlayRequired = false;
	boolean iscreatesearchablepdf = false;
	boolean issearchablepdfhidetextlayer = false;

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
