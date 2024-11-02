package mongo.example.replicaset;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.opencsv.CSVReader;
import mongo.example.*;
import org.bson.Document;
import org.springframework.ai.autoconfigure.vectorstore.mongo.MongoDBAtlasVectorStoreAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.io.FileReader;
import java.util.Map;

@SpringBootApplication(exclude = MongoDBAtlasVectorStoreAutoConfiguration.class)
public class InitializeReplicaSet {

    public static final Map<String, Class<?>> COLLECTION_MAP = Map.of(
            Listing.class.getName(), Listing.class,
            Neighbourhood.class.getName(), Neighbourhood.class,
            Reviews.class.getName(), Reviews.class
    );

    private MongoDatabase mongoDatabase;

    public static void main(String[] args) {
        // Boot up the Spring context
        ApplicationContext context = SpringApplication.run(InitializeReplicaSet.class, args);

        // Access AuthenticationConfig and other beans from the context
        InitializeReplicaSet init = context.getBean(InitializeReplicaSet.class);
        AuthenticationConfig authConfig = context.getBean(AuthenticationConfig.class);

        DocumentFormat documentFormat = new DocumentFormat(System.getProperty("format", DocumentFormat.JSON ) );
        String inputFilePath = System.getProperty("file", null);

        init.connectToDatabase( authConfig );
        init.populate( inputFilePath, documentFormat);
    }

    private void connectToDatabase(AuthenticationConfig authenticationConfig)
    {
//        MongoCredential authCredential = MongoCredential.createCredential(
//                authConfig.getUsername(),
//                authConfig.getAuthenticationDatabase(),
//                authConfig.getPassword().toCharArray()
//        );
//
//        MongoClientSettings DEFAULT_CONNECTION_SETTINGS = MongoClientSettings.builder()
//                .applyConnectionString(new ConnectionString("mongodb://localhost:27017/"))
//                .credential( authCredential )
//                .build();
//
//        try (MongoClient mongoClient = MongoClients.create(DEFAULT_CONNECTION_SETTINGS)) {
//            this.mongoDatabase = mongoClient.getDatabase("example");
//        }
    }

    private void populate(String inputFilePath, DocumentFormat documentFormat)
    {

        File inputFile = new File( inputFilePath );


        switch ( documentFormat.getFormat() )
        {
            case DocumentFormat.JSON:
            {
                populateJson(inputFile);
                break;
            }
            case DocumentFormat.CSV: {
                populateCsv(inputFile);
                break;
            }
            case DocumentFormat.PLAINTEXT: {
                populatePlaintext(inputFile);
                break;
            }
            default: throw new IllegalStateException("Require a format type .json, .csv, .txt");
        }
    }


    private static void populateJson( File inputFile )
    {
        //TODO
    }

    private void populateCsv( File inputFile )
    {
        for( Map.Entry<String, Class<?>> entry : COLLECTION_MAP.entrySet())
        {
            String collectionName = entry.getKey();
            Class<?> pojo = entry.getValue();

            mongoDatabase.createCollection(collectionName);

            MongoCollection<Document> collection = mongoDatabase.getCollection( collectionName );

            try(CSVReader csvReader = new CSVReader(new FileReader( inputFile ) ) ) {

            } catch (Exception e)
            {
                //TODO
            }
        }
    }

    private static void populatePlaintext( File inputFile )
    {
        //TODO
    }
}
