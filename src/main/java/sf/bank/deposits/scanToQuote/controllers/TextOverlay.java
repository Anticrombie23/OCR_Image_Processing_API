package sf.bank.deposits.scanToQuote.controllers;

public class TextOverlay
{
    private String[] Lines;

    private String Message;

    private String HasOverlay;

    public String[] getLines ()
    {
        return Lines;
    }

    public void setLines (String[] Lines)
    {
        this.Lines = Lines;
    }

    public String getMessage ()
    {
        return Message;
    }

    public void setMessage (String Message)
    {
        this.Message = Message;
    }

    public String getHasOverlay ()
    {
        return HasOverlay;
    }

    public void setHasOverlay (String HasOverlay)
    {
        this.HasOverlay = HasOverlay;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Lines = "+Lines+", Message = "+Message+", HasOverlay = "+HasOverlay+"]";
    }
}
