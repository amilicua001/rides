package businessLogic;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.swing.JFrame;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Ride;
import domain.Traveler;
import domain.User;
import domain.Booking;
import domain.Driver;
import exceptions.RideMustBeLaterThanTodayException;
import gui.DriverGUI;
import gui.LoginGUI;
import gui.TravelerGUI;
import exceptions.RideAlreadyExistException;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	DataAccess dbManager;

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		
		
		    dbManager=new DataAccess();
		    

		
	}
	
    public BLFacadeImplementation(DataAccess da)  {
		
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c=ConfigXML.getInstance();
		
		dbManager=da;		
	}
    
    
    /**
     * {@inheritDoc}
     */
    @WebMethod public List<String> getDepartCities(){
    	dbManager.open();	
		
		 List<String> departLocations=dbManager.getDepartCities();		

		dbManager.close();
		
		return departLocations;
    	
    }
    /**
     * {@inheritDoc}
     */
	@WebMethod public List<String> getDestinationCities(String from){
		dbManager.open();	
		
		 List<String> targetCities=dbManager.getArrivalCities(from);		

		dbManager.close();
		
		return targetCities;
	}

	/**
	 * {@inheritDoc}
	 */
   @WebMethod
   public Ride createRide( String from, String to, Date date, int nPlaces, String driverEmail ) throws RideMustBeLaterThanTodayException, RideAlreadyExistException{
	   
		dbManager.open();
		Ride ride=dbManager.createRide(from, to, date, nPlaces, driverEmail);		
		dbManager.close();
		return ride;
   };
	
   /**
    * {@inheritDoc}
    */
	@WebMethod 
	public List<Ride> getRides(String from, String to, Date date){
		dbManager.open();
		List<Ride>  rides=dbManager.getRides(from, to, date);
		dbManager.close();
		return rides;
	}

    
	/**
	 * {@inheritDoc}
	 */
	@WebMethod 
	public List<Date> getThisMonthDatesWithRides(String from, String to, Date date){
		dbManager.open();
		List<Date>  dates=dbManager.getThisMonthDatesWithRides(from, to, date);
		dbManager.close();
		return dates;
	}
	
	
	public void close() {
		DataAccess dB4oManager=new DataAccess();

		dB4oManager.close();

	}

	/**
	 * {@inheritDoc}
	 */
    @WebMethod	
	 public void initializeBD(){
    	dbManager.open();
		dbManager.initializeDB();
		dbManager.close();
	}
    
    public boolean isLogin(String name, String password) {
    	dbManager.open();  	
    	User  dbUser =dbManager.isLogin(name, password);
    	dbManager.close();
    	if(dbUser==null) {
    		return false;
    	}else {
    		if(dbUser instanceof Driver ) {
        		JFrame driverGUI = new DriverGUI(dbUser);
        		driverGUI.setVisible(true);
    		}else if(dbUser instanceof Traveler) {
    			JFrame travelerGUI = new TravelerGUI(dbUser);
        		travelerGUI.setVisible(true);
    		}
    		

			
    	}
    	return true;
    }


	@Override
	public boolean register(User u) {
		dbManager.open();
		boolean ema =dbManager.register(u);
		dbManager.close();
		return ema;
	}

	@Override
	public boolean guardarBooking(Booking reserva) {
		dbManager.open();
		boolean guardado = dbManager.guardarBooking(reserva);
		dbManager.close();
		return guardado;
	}

	@Override
	public ArrayList<Booking> getBookingList(Driver driver) {
		dbManager.open();
		ArrayList<Booking> bookingList = dbManager.getBookingList(driver);
		dbManager.close();
		return bookingList;
	}



}

