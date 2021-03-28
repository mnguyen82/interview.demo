/**
 * 
 */
package cox.interview.demo.restservice;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cox.interview.demo.domain.Train;
import cox.interview.demo.exception.CoxRuntimeException;
import cox.interview.demo.http.RestClient;

/**
 * Rest controller that serves ReactJs front-end
 * @author minhnguyen
 */
@RestController
@EnableConfigurationProperties
public class TrainRealtimeController {
	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(RestClient.class);
	/** Remote url that the real-time train data located.*/
	@Value("${external.marta.api.train.url}")
	private String trainDataRestApiUrl;
	
	/** Instance of http rest client that helps perform http rest calls */
	private RestClient restClient = null;

	/**
	 * Entry point to return real-time train data.
	 * @return
	 * @throws CoxRuntimeException
	 */
	@GetMapping("/getTrainRealtimeData")
	public ResponseEntity<Train[]> getTrainRealtimeData() throws CoxRuntimeException {
		if(restClient == null) {
			LOGGER.error("RestClient is null. The application has not been started properly....");
			throw new CoxRuntimeException("Failed to initialize rest http client utility. Contact administrator!");
		}
		Train[] trains = restClient.fetchData(trainDataRestApiUrl, Train[].class);
		
		return new ResponseEntity<Train[]>(trains, HttpStatus.OK);
	}
	
	/**
	 * Inits params for the controller.
	 */
	@PostConstruct
	private void init() {
		restClient = RestClient.getInstance();
		LOGGER.debug("RestClient instance successfully created...");
	}
}
