package api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import com.uber.sdk.core.auth.*;
import com.uber.sdk.core.auth.internal.OAuthScopesAdapter;
import com.uber.sdk.core.client.SessionConfiguration;
import com.uber.sdk.core.auth.*;



import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;

public class ConexaoAPI {

	public static void main(String[] args) {

		com.uber.sdk.core.client.SessionConfiguration config = new com.uber.sdk.core.client.SessionConfiguration.Builder()
				.setClientId("bKXPDCxo9uMV1_ZUo5OYcUztdFbJ1byC")
				.setClientSecret("xdViKYiRkwrRw0Xlfe3_VLlV3mvvDjGTQfdB6W14")
				.setEnvironment(com.uber.sdk.core.client.SessionConfiguration.Environment.SANDBOX)
				.setScopes(Arrays.asList(Scope.PROFILE, Scope.REQUEST))
				.setRedirectUri("http://localhost")
				.build();

		
		
		//OAuth2Credentials credentials = 
		
		//OAuth2Credentials credentials = new OAuth2Credentials.Builder()
		//		.setSessionConfiguration(config)
		//		.build();

//		String authorizationUrl = null;
//		try {
//			authorizationUrl = credentials.getAuthorizationUrl();
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}

//		if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
//		    try {
//				Desktop.getDesktop().browse(new URI(authorizationUrl));
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (URISyntaxException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		
		//Credential credential = credentials.authenticate(authorizationCode, userId); 
		//CredentialsSession session = new CredentialsSession(config, credential);
		//RidesService service = UberRidesApi.with(session).createService();
		
		//Response<TimeEstimatesResponse> response = service.getPickupTimeEstimate(37.79f, -122.39f, productId).execute()
	}
}
