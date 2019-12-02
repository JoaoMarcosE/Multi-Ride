package TesteAppium.TesteAppium;

import java.io.IOException;
import java.util.List;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	List<Estimates> estimates = GetRideEstimates(-26.8620346, -49.0938558, -26.9205934, -49.0716664);
    	
    	System.out.printf("Uber time estimate: " + estimates.get(0).getTime());
    	System.out.printf("Uber price estimate: " + estimates.get(0).getPrice());
    	
    	System.out.printf("99 time estimate: " + estimates.get(1).getTime());
    	System.out.printf("99 price estimate: " + estimates.get(1).getPrice());
    }
    
    public static List<Estimates> GetRideEstimates (double initialLat, double initialLon, double finalLat, double finalLon) throws IOException {
        AppiumGoogleMaps teste = new AppiumGoogleMaps();
        teste.OpenGoogleMaps();
        teste.SetRoute(initialLat, initialLon, finalLat, finalLon);
        
        return teste.GetEstimates();
    }
}