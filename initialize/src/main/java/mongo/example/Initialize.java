package mongo.example;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.eq;

public class Initialize {

    public static final int DEFAULT_DATA_SET_SIZE = 5000;

    public static final MongoClientSettings DEFAULT_CONNECTION_SETTINGS = MongoClientSettings.builder()
            .applyConnectionString(
                    new ConnectionString("mongodb://localhost:27017/")
            ).build();

    public static void main(String[] args)
    {
        int dataSetSize = Integer.parseInt(
                System.getProperty( "dataSetSize",
                String.valueOf(DEFAULT_DATA_SET_SIZE) )
        );

        DocumentFormat documentFormat = new DocumentFormat(System.getProperty("format", DocumentFormat.JSON ) );
        populate(dataSetSize, documentFormat);
    }

    //TODO populate a provided dataset file

    private static void populate(int numberOfDocuments, DocumentFormat documentFormat){

        switch ( documentFormat.getFormat() )
        {
            case DocumentFormat.JSON: populateJson(numberOfDocuments);
            case DocumentFormat.CSV: populateCsv(numberOfDocuments);
            case DocumentFormat.PLAINTEXT: populatePlaintext(numberOfDocuments);
            default: throw new IllegalStateException("Require a format type .json, .csv, .txt");
        }
    }
    

    private static void populateJson( int numberOfDocuments )
    {

    }

    private static void populateCsv( int numberOfDocuments )
    {

    }

    private static void populatePlaintext( int numberOfDocuments )
    {

    }
}
