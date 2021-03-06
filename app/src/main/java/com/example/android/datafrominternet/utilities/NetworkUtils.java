package com.example.android.datafrominternet.utilities;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.Scanner;


/**
 * These utilities will be used to communicate with the network.
 */
public class NetworkUtils {

    // TODO(1): Created a constants for base URl
    final static String GITHUB_BASE_URL =
            "https://api.github.com/search/repositories";

    final static String PARAM_QUERY = "q";

    /*
     * The sort field. One of stars, forks, or updated.
     * Default: results are sorted by best match if no field is specified.
     */
    final static String PARAM_SORT = "sort";
    final static String sortBy = "stars";

    // TODO(3): Build the URL and pass the parameter
    /**
     * Builds the URL used to query Github.
     *
     * @param githubSearchQuery The keyword that will be queried for.
     * @return The URL to use to query the weather server.
     */
    public static URL buildUrl(String githubSearchQuery) {
        // Android Uri.Builder framework class allows to create well-formed URI
        // without having to worry about URI components.
        Uri buildUri = Uri.parse(GITHUB_BASE_URL).buildUpon()
                          .appendQueryParameter(PARAM_QUERY, githubSearchQuery)
                          .appendQueryParameter(PARAM_SORT, sortBy)
                          .build();

        // Convert the Android new built Uri to Java URL
        // by passing it as String parameter to the Java URL constructor
        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    // TODO(2): Receive HTTP response form the Url
    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl (URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            /**
             * Convert Input stream to a String.
             */
            // Scanner used to tokenize Streams
            // separate tokens using "beginning of the input boundary"
            // (\A) thus giving us only one token for the entire contents of the stream
            Scanner scanner = new Scanner(in);
            // Setting the delimiter to \A beginning of the stream,
            // we force the scanner to read the entire contents of stream into the next token stream
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }

        } finally {
            urlConnection.disconnect();
        }
    }

    public static String ReadStream (InputStream inputStream) throws IOException {
        return
    }
}
