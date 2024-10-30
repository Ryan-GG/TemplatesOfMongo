package mongo.example;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.opencsv.CSVReader;
import org.bson.Document;

import java.io.FileReader;
import java.util.Map;

public class Initialize {

    public static final int DEFAULT_DATA_SET_SIZE = 5000;

    public static final MongoClientSettings DEFAULT_CONNECTION_SETTINGS = MongoClientSettings.builder()
            .applyConnectionString(new ConnectionString("mongodb://localhost:27017/"))
            .build();

    //TODO, make this a map from collection to POJO
    public static final Map<String, Class<?>> COLLECTION_MAP = Map.of(
            Listing.class.getName(), Listing.class,
            Neighbourhood.class.getName(), Neighbourhood.class,
            Reviews.class.getName(), Reviews.class
    );

    private MongoDatabase mongoDatabase;

    public static void main(String[] args)
    {
        int dataSetSize = Integer.parseInt(
                System.getProperty( "dataSetSize",
                String.valueOf(DEFAULT_DATA_SET_SIZE) )
        );

        DocumentFormat documentFormat = new DocumentFormat(System.getProperty("format", DocumentFormat.JSON ) );

        Initialize init = new Initialize();
        init.connectToDatabase();
        init.populate(dataSetSize, documentFormat);
    }

    private void connectToDatabase()
    {
        try (MongoClient mongoClient = MongoClients.create(DEFAULT_CONNECTION_SETTINGS)) {
            this.mongoDatabase = mongoClient.getDatabase("example");
        }
    }

    private void populate(int numberOfDocuments, DocumentFormat documentFormat){

        switch ( documentFormat.getFormat() )
        {
            case DocumentFormat.JSON:
            {
                populateJson(numberOfDocuments);
                break;
            }
            case DocumentFormat.CSV: {
                populateCsv(numberOfDocuments);
                break;
            }
            case DocumentFormat.PLAINTEXT: {
                populatePlaintext(numberOfDocuments);
                break;
            }
            default: throw new IllegalStateException("Require a format type .json, .csv, .txt");
        }
    }


    private static void populateJson( int numberOfDocuments )
    {

    }

    private void populateCsv( int numberOfDocuments )
    {
        for( String collectionName : COLLECTION_MAP.keySet())
        {
            mongoDatabase.createCollection(collectionName);

            MongoCollection<Document> collection = mongoDatabase.getCollection( collectionName );
            String collectionInputFile = getClass().getClassLoader().getResource( collectionName.concat(".csv")).getFile();
            try(CSVReader csvReader = new CSVReader(new FileReader( collectionInputFile ) ) ) {

                String[] documentFields = csvReader.readNext();


                String[] documentContent;
                while ((documentContent = csvReader.readNext()) != null && 0 < documentContent.length) {

                }
            } catch (Exception e)
            {
                //TODO
            }
        }
    }

    private static void populatePlaintext( int numberOfDocuments )
    {

    }
}
