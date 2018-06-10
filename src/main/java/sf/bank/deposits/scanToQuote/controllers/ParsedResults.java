package sf.bank.deposits.scanToQuote.controllers;

public class ParsedResults
{
    private String parsedText;

    private String fileParseExitCode;

    private String errorMessage;

    private TextOverlay textOverlay;

    private String errorDetails;

	public String getParsedText() {
		return parsedText;
	}

	public void setParsedText(String parsedText) {
		this.parsedText = parsedText;
	}

	public String getFileParseExitCode() {
		return fileParseExitCode;
	}

	public void setFileParseExitCode(String fileParseExitCode) {
		this.fileParseExitCode = fileParseExitCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public TextOverlay getTextOverlay() {
		return textOverlay;
	}

	public void setTextOverlay(TextOverlay textOverlay) {
		this.textOverlay = textOverlay;
	}

	public String getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}

    
    
    
}