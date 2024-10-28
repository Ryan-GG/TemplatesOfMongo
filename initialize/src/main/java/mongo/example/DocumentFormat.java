package mongo.example;

public class DocumentFormat {
    public static final String CSV = "CSV";
    public static final String JSON = "JSON";
    public static final String PLAINTEXT = "TEXT";

    private final String format;
    public DocumentFormat(String format) { this.format = format.toUpperCase();}

    public String getFormat() { return this.format;}
}
