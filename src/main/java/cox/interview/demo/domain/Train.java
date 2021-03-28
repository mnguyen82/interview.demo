/**
 * 
 */
package cox.interview.demo.domain;

import com.google.gson.annotations.SerializedName;

/**
 * An object represents information of a train.
 * This will be used to map to a MARTA train json entity
 * 
 * @author minhnguyen
 */
public class Train {
	@SerializedName(value = "DESTINATION")
	private String destination;
	@SerializedName(value = "DIRECTION")
	private String direction;
	@SerializedName(value = "EVENT_TIME")
	private String lastUpdate;
	@SerializedName(value = "LINE")
	private String line;
	@SerializedName(value = "NEXT_ARR")
	private String nextArrival;
	@SerializedName(value = "STATION")
	private String station;
	@SerializedName(value = "TRAIN_ID")
	private String trainId;
	@SerializedName(value = "WAITING_SECONDS")
	private String waitingSeconds;
	@SerializedName(value = "WAITING_TIME")
	private String waitingTime;
	
	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}
	/**
	 * @param destination the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}
	/**
	 * @return the direction
	 */
	public String getDirection() {
		return direction;
	}
	/**
	 * @param direction the direction to set
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}
	/**
	 * @return the lastUpdate
	 */
	public String getLastUpdate() {
		return lastUpdate;
	}
	/**
	 * @param lastUpdate the lastUpdate to set
	 */
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	/**
	 * @return the line
	 */
	public String getLine() {
		return line;
	}
	/**
	 * @param line the line to set
	 */
	public void setLine(String line) {
		this.line = line;
	}
	/**
	 * @return the nextArrival
	 */
	public String getNextArrival() {
		return nextArrival;
	}
	/**
	 * @param nextArrival the nextArrival to set
	 */
	public void setNextArrival(String nextArrival) {
		this.nextArrival = nextArrival;
	}
	/**
	 * @return the station
	 */
	public String getStation() {
		return station;
	}
	/**
	 * @param station the station to set
	 */
	public void setStation(String station) {
		this.station = station;
	}
	/**
	 * @return the trainId
	 */
	public String getTrainId() {
		return trainId;
	}
	/**
	 * @param trainId the trainId to set
	 */
	public void setTrainId(String trainId) {
		this.trainId = trainId;
	}
	/**
	 * @return the waitingSeconds
	 */
	public String getWaitingSeconds() {
		return waitingSeconds;
	}
	/**
	 * @param waitingSeconds the waitingSeconds to set
	 */
	public void setWaitingSeconds(String waitingSeconds) {
		this.waitingSeconds = waitingSeconds;
	}
	/**
	 * @return the waitingTime
	 */
	public String getWaitingTime() {
		return waitingTime;
	}
	/**
	 * @param waitingTime the waitingTime to set
	 */
	public void setWaitingTime(String waitingTime) {
		this.waitingTime = waitingTime;
	}
}
