package mongo.example;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;

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

        Initialize init = new Initialize();
        init.populate(dataSetSize, documentFormat);
    }

    //TODO populate a provided dataset file

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
        String listingsFile = getClass().getClassLoader().getResource("listings.csv").getFile();
        try(CSVReader csvReader = new CSVReader(new FileReader( listingsFile ) ) ) {

            csvReader.readAll().forEach( line -> Arrays.stream(line).forEach(System.out::println) );

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        } ;
    }

    private static void populatePlaintext( int numberOfDocuments )
    {

    }
}
