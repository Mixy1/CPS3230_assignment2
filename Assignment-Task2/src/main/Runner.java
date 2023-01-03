package main;

import java.util.Random;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

import main.MarketAlertUmAPI;
import main.MarketAlertUmAPI.InvalidKeyException;

public class Runner {

	MarketAlertUmAPI account;

	public Runner(MarketAlertUmAPI account) {
		this.account = account;
	}

	private void invalidId(){
		System.out.println("Invalid ID");
	}
	
	private void alertsReturnedByService(int n){
		int num = n;
		System.out.println("Service returned " + num + " alerts");
	}

	public boolean validLogin(){
		JsonArray events = Json.createArrayBuilder().build();
		// get event log
		try {
			events = account.getEventLog();
		} catch (MarketAlertUmAPI.InvalidKeyException e) {
			return false;
		}

		// get last event
		if (events.size() > 0) {
			JsonObject lastEvent = events.getJsonObject(events.size() - 1);
			// validate that it is a login event i.e. event type 5
			if (lastEvent.getInt("eventLogType") == 5){
				return true;
			}
		}
		return false;		
	}

	// pseudo 3rd party client that has no restrictions on when a function can be called.
	// runner written for it that executes these api functions completely randomly.
	// Sort of fuzzing the client and letting the runtime monitor do it's thing.
	public void run() {
		final Random rand = new Random();

		JsonObject dummyObject = Json.createObjectBuilder()
				.add("heading", "Test Heading")
				.add("description", "Test Description")
				.add("url", "http://www.google.com")
				.add("imageUrl", "http://www.google.com")
				.add("priceInCents", "100")
				.add("alertType", 5)
				.build();

		while(true){
			final int randomNumber = rand.nextInt(100);
			
			if (randomNumber < 15){
				System.out.println("Purging");
				try {
					account.purgeAlerts();
				} catch (MarketAlertUmAPI.InvalidKeyException e) {
					this.invalidId();
				}
			} else if (randomNumber < 30) {
				System.out.println("Viewing");
				try {
					this.alertsReturnedByService(account.getAlerts().size());
				} catch (MarketAlertUmAPI.InvalidKeyException e) {
					this.invalidId();
				}
			} else if (randomNumber < 80) {
				System.out.println("Creating");
				dummyObject = Json.createObjectBuilder(dummyObject).add("alertType", Json.createValue(rand.nextInt(6) + 1)).build();

				try {
					account.createAlert(dummyObject);
				} catch (MarketAlertUmAPI.InvalidKeyException e) {
					this.invalidId();
				}
				
			} else if (randomNumber < 90) {
				System.out.println("Logging in");
				try {
					account.login();
					if (!this.validLogin()) {
						this.invalidId();
					}
				} catch (MarketAlertUmAPI.InvalidKeyException e) {
					this.invalidId();
				}
			} else {
				System.out.println("Logging out");
				if (!account.logout()) {
					System.out.println("Logout failed");
				}
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[] args) {
		final MarketAlertUmAPI account = new MarketAlertUmAPI("3ce46c25-f585-4e3c-b01d-2a34afb95757");
		try {
			// clear up alerts and event log before starting execution			
			account.purgeAlerts();
			account.getEventLog();
		} catch (InvalidKeyException e) {
			
		}
		final Runner m = new Runner(account);
		System.out.println("Starting...");
		m.run();
	}
}