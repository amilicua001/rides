package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Booking {
	@Id
	private String id;
	private Driver driver;
	private Traveler traveler;
	private Ride ride;
	private int eserlekuKop;
	
	public Booking(Driver driver, Traveler traveler, Ride ride, int eserlekuKop) {
		super();
		this.id = ride.getRideNumber() + traveler.getEmail();
		this.driver = driver;
		this.traveler = traveler;
		this.ride = ride;
		this.eserlekuKop = eserlekuKop;
	}
	
	
	@Override
	public String toString() {
		return traveler.getName() + " - "+ ride.toString() +" - "+ eserlekuKop;
	}


	/**
	 * @return the eserlekuKop
	 */
	public int getEserlekuKop() {
		return eserlekuKop;
	}


	/**
	 * @param eserlekuKop the eserlekuKop to set
	 */
	public void setEserlekuKop(int eserlekuKop) {
		this.eserlekuKop = eserlekuKop;
	}


	/**
	 * @return the driver
	 */
	public Driver getDriver() {
		return driver;
	}
	/**
	 * @param driver the driver to set
	 */
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	/**
	 * @return the traveler
	 */
	public Traveler getTraveler() {
		return traveler;
	}
	/**
	 * @param traveler the traveler to set
	 */
	public void setTraveler(Traveler traveler) {
		this.traveler = traveler;
	}
	/**
	 * @return the ride
	 */
	public Ride getRide() {
		return ride;
	}
	/**
	 * @param ride the ride to set
	 */
	public void setRide(Ride ride) {
		this.ride = ride;
	}
	
	
	
	
}
