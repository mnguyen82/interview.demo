/**
 * 
 */
package cox.interview.demo.http;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import cox.interview.demo.exception.CoxRuntimeException;

/**
 * @author minhnguyen
 * 
 * A simple utility to perform Rest HTTP calls
 */
public class RestClient {
	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(RestClient.class);
	/** Singleton instance*/
	private static RestClient instance = null;
	/** An instance of a {@code HttpClient}}*/
    private HttpClient client = HttpClientBuilder.create().build();
    /** Gson object which is responsible for parsing json data */
    private Gson gson = new Gson(); 
	
    /**
     * Private constructor
     */
	private RestClient() {
		//Do nothing
	}
	
	/**
	 * Returns the singleton instance
	 * 
	 * @return the singleton instance
	 */
	public static RestClient getInstance() {
		if(instance == null) {
			instance = new RestClient();
		}
		
		return instance;
	}
	
	/**
	 * Fetches data from the given URL then returns result as the given data type
	 * @param <T> data type that the data will be returned
	 * @param url {@code String} The url that data will be retrieved from.
	 * @param type data type that the data will be returned
	 * @return a java object with type as the param provided
	 * @throws CoxRuntimeException unexpected error.
	 */
	public <T> T fetchData(String url, Class<T> type) throws CoxRuntimeException {
		LOGGER.debug("fetchData invoked. classType: {}, restUrl: {}", type.toString(), url);
        HttpGet request = new HttpGet(url);
        
        String json = null;
        try {
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
        	json = EntityUtils.toString(entity, StandardCharsets.UTF_8);

    		return gson.fromJson(json, type);
        } catch (IllegalStateException illEx) {
        	LOGGER.error("Parsing json error." + illEx.getMessage());
        	LOGGER.error("JSON data:" + json);
			throw new CoxRuntimeException("Failed to parse response data.", illEx);
		} catch (IOException ioe) {
        	LOGGER.error(ioe.getMessage(), ioe);
			throw new CoxRuntimeException(String.format("Failed to read data from '%s'. Check your network", url), ioe);
		} catch (Exception ex) {
        	LOGGER.error(ex.getMessage(), ex);
			throw new CoxRuntimeException(String.format("Failed to read data from '%s'.", url), ex);
		}
	}
}
