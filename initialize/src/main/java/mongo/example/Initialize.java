package mongo.example;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.opencsv.CSVReader;
import org.bson.Document;
import org.springframework.ai.autoconfigure.vectorstore.mongo.MongoDBAtlasVectorStoreAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;

import java.io.FileReader;
import java.util.Map;

@SpringBootApplication(exclude = MongoDBAtlasVectorStoreAutoConfiguration.class)
public class Initialize {

    public static final int DEFAULT_DATA_SET_SIZE = 5000;

    public static final Map<String, Class<?>> COLLECTION_MAP = Map.of(
            Listing.class.getName(), Listing.class,
            Neighbourhood.class.getName(), Neighbourhood.class,
            Reviews.class.getName(), Reviews.class
    );

    private MongoDatabase mongoDatabase;

    @Autowired
    private AuthenticationConfig authConfig;

    public static void main(String[] args) {
        // Boot up the Spring context
        ApplicationContext context = SpringApplication.run(Initialize.class, args);

        // Access AuthenticationConfig and other beans from the context
        Initialize init = context.getBean(Initialize.class);
        AuthenticationConfig authConfig = context.getBean(AuthenticationConfig.class);

        int dataSetSize = Integer.parseInt(
                System.getProperty( "dataSetSize",
                String.valueOf(DEFAULT_DATA_SET_SIZE) )
        );

        DocumentFormat documentFormat = new DocumentFormat(System.getProperty("format", DocumentFormat.JSON ) );

        init.connectToDatabase();
        init.populate(dataSetSize, documentFormat);
    }

    private void connectToDatabase()
    {
        MongoCredential authCredential = MongoCredential.createCredential(
                authConfig.getUsername(),
                authConfig.getAuthenticationDatabase(),
                authConfig.getPassword().toCharArray()
        );

        MongoClientSettings DEFAULT_CONNECTION_SETTINGS = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString("mongodb://localhost:27017/"))
                .credential( authCredential )
                .build();

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
