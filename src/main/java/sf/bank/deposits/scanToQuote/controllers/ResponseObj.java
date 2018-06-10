package sf.bank.deposits.scanToQuote.controllers;

public class ResponseObj
{
    private ParsedResults[] parsedResults;

    private String isErroredOnProcessing;

    private String errorMessage;

    private String processingTimeInMilliseconds;

    private String searchablePDFURL;

    private String oCRExitCode;

    private String errorDetails;

    public ParsedResults[] getParsedResults ()
    {
        return parsedResults;
    }

    public void setParsedResults (ParsedResults[] parsedResults)
    {
        this.parsedResults = parsedResults;
    }

    public String getIsErroredOnProcessing ()
    {
        return isErroredOnProcessing;
    }

    public void setIsErroredOnProcessing (String isErroredOnProcessing)
    {
        this.isErroredOnProcessing = isErroredOnProcessing;
    }

    public String getErrorMessage ()
    {
        return errorMessage;
    }

    public void setErrorMessage (String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public String getProcessingTimeInMilliseconds ()
    {
        return processingTimeInMilliseconds;
    }

    public void setProcessingTimeInMilliseconds (String processingTimeInMilliseconds)
    {
        this.processingTimeInMilliseconds = processingTimeInMilliseconds;
    }

    public String getSearchablePDFURL ()
    {
        return searchablePDFURL;
    }

    public void setSearchablePDFURL (String searchablePDFURL)
    {
        this.searchablePDFURL = searchablePDFURL;
    }

    public String getOCRExitCode ()
    {
        return oCRExitCode;
    }

    public void setOCRExitCode (String oCRExitCode)
    {
        this.oCRExitCode = oCRExitCode;
    }

    public String getErrorDetails ()
    {
        return errorDetails;
    }

    public void setErrorDetails (String ErrorDetails)
    {
        this.errorDetails = ErrorDetails;
    }

//    @Override
//    public String toString()
//    {
//        return "ClassPojo [ParsedResults = "+ParsedResults+", IsErroredOnProcessing = "+IsErroredOnProcessing+", ErrorMessage = "+ErrorMessage+", ProcessingTimeInMilliseconds = "+ProcessingTimeInMilliseconds+", SearchablePDFURL = "+SearchablePDFURL+", OCRExitCode = "+OCRExitCode+", ErrorDetails = "+ErrorDetails+"]";
//    }
}
