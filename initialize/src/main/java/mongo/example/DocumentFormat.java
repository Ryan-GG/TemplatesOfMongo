package mongo.example;

public class DocumentFormat {
    public static final String CSV = "csv";
    public static final String JSON = "json";
    public static final String PLAINTEXT = "text";

    private final String format;
    public DocumentFormat(String format) { this.format = format;}

    public String getFormat() { return this.format;}
}
