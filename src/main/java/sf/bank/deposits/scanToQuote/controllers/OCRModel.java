package sf.bank.deposits.scanToQuote.controllers;

public class OCRModel {

	String filetype;
	String url;
	String base64Image;
	String language;
	String isOverlayRequired = "false";
	String iscreatesearchablepdf = "false";
	String issearchablepdfhidetextlayer = "false";

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

	public String getIsOverlayRequired() {
		return isOverlayRequired;
	}

	public void setIsOverlayRequired(String isOverlayRequired) {
		this.isOverlayRequired = isOverlayRequired;
	}

	public String getIscreatesearchablepdf() {
		return iscreatesearchablepdf;
	}

	public void setIscreatesearchablepdf(String iscreatesearchablepdf) {
		this.iscreatesearchablepdf = iscreatesearchablepdf;
	}

	public String getIssearchablepdfhidetextlayer() {
		return issearchablepdfhidetextlayer;
	}

	public void setIssearchablepdfhidetextlayer(String issearchablepdfhidetextlayer) {
		this.issearchablepdfhidetextlayer = issearchablepdfhidetextlayer;
	}

}
