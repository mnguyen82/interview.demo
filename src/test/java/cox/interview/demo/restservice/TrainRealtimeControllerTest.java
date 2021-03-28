package cox.interview.demo.restservice;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import cox.interview.demo.domain.Train;
import cox.interview.demo.http.RestClient;


/**
 * Unit test for simple TrainRealtimeController.
 * 
 * As per DEMO purpose, just one unit test case implemented. 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = TrainRealtimeController.class)
public class TrainRealtimeControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private TrainRealtimeController trainRealtimeController;
	@Mock
	private RestClient restClient;
	/** Mock test data*/
	private Train[] mockTrains = createTrainArrivalsTestData();
	
	/**
	 * Mocks a valid request and verifies its response.
	 * @throws Exception
	 */
    @org.junit.Test
	public void getTrainRealtimeData() throws Exception {
    	// Mock a return for fetchData call
    	Mockito.when(
    			restClient.fetchData(Mockito.anyString(),
    					Mockito.eq(Train[].class))).thenReturn(mockTrains);
    	
    	RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/getTrainRealtimeData").accept(
				MediaType.APPLICATION_JSON);
    	
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		// verify response code
		assertEquals(200, response.getStatus());// OK
		
		String actualResponseAsString = response.getContentAsString();
		String expected = "[{\"destination\":\"destination1\",\"direction\":\"direction1\",\"lastUpdate\":\"01/01/9999 0:00:00 AM\",\"line\":\"line1\",\"nextArrival\":\"nextArrival1\",\"station\":\"station1\",\"trainId\":\"trainId1\",\"waitingSeconds\":\"-1\",\"waitingTime\":\"100\"}]";
		
		// Verify response data
		assertEquals(expected, actualResponseAsString);
    }
    
    /**
     * Sets up for test cases
     * @throws Exception unexpected error
     */
    @Before
    public void setUp() throws Exception {
    	// Set data for a private field(restClient) of the TrainRealtimeController instance
        ReflectionTestUtils.setField(trainRealtimeController, "restClient", restClient);
    }
    
    /**
     * Mocks a {@code Train} object
     * @return an array of {@code Train} object
     */
    private Train[] createTrainArrivalsTestData() {
    	Train trainTest = new Train();
    	trainTest.setDestination("destination1");
    	trainTest.setDirection("direction1");
    	trainTest.setLastUpdate("01/01/9999 0:00:00 AM");
    	trainTest.setLine("line1");
    	trainTest.setNextArrival("nextArrival1");
    	trainTest.setStation("station1");
    	trainTest.setTrainId("trainId1");
    	trainTest.setWaitingSeconds("-1");
    	trainTest.setWaitingTime("100");
    	
    	Train[] trains = new Train[1];
    	trains[0] = trainTest;
    	
    	return trains;
    }
}
