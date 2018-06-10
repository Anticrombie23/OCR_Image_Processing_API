package sf.bank.deposits.scanToQuote.controllers;

public class OCRModel {

	String filetype;
	String url;
	String base64Image;
	String language;
	boolean isOverlayRequired = false;
	boolean iscreatesearchablepdf = false;
	boolean issearchablepdfhidetextlayer = false;

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isOverlayRequired() {
		return isOverlayRequired;
	}

	public void setOverlayRequired(boolean isOverlayRequired) {
		this.isOverlayRequired = isOverlayRequired;
	}

	public boolean isIscreatesearchablepdf() {
		return iscreatesearchablepdf;
	}

	public void setIscreatesearchablepdf(boolean iscreatesearchablepdf) {
		this.iscreatesearchablepdf = iscreatesearchablepdf;
	}

	public boolean isIssearchablepdfhidetextlayer() {
		return issearchablepdfhidetextlayer;
	}

	public void setIssearchablepdfhidetextlayer(boolean issearchablepdfhidetextlayer) {
		this.issearchablepdfhidetextlayer = issearchablepdfhidetextlayer;
	}

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
