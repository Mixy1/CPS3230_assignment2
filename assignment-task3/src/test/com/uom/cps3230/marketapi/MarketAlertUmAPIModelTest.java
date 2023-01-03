package com.uom.cps3230.marketapi;

import com.uom.cps3230.marketapi.enums.LoginStates;
import com.uom.cps3230.marketapi.enums.MarketAlertStates;
import junit.framework.Assert;
import nz.ac.waikato.modeljunit.*;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionPairCoverage;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.io.FileNotFoundException;
import java.util.Random;

public class MarketAlertUmAPIModelTest implements FsmModel {
    private MarketAlertStates modelState;
    private int numAlerts;
    private MarketAlertUmAPI sut;

    private MarketAlertUmAPI api_verified = new MarketAlertUmAPI("3ce46c25-f585-4e3c-b01d-2a34afb95757");


    public MarketAlertStates getState() {
        return modelState;
    }

    public void reset(final boolean b) {
        modelState = MarketAlertStates.EMPTY;
        numAlerts = 0;
        try {
            api_verified.purgeAlerts();
            api_verified.getEventLog();
        } catch (MarketAlertUmAPI.InvalidKeyException e) {
            // This should never happen.
            sut = new MarketAlertUmAPI("3ce46c25-f585-4e3c-b01d-2a34afb95757");
            return;
        }
        if (b) {
            // 0.05 chance of using a bad key.
            Random rand = new Random();
            if (rand.nextInt(100) < 80)
                sut = new MarketAlertUmAPI("3ce46c25-f585-4e3c-b01d-2a34afb95757");
            else 
                sut = new MarketAlertUmAPI("BAD_KEY");
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean createGuard() {
        return true;
    }
    public @Action void create() {
        JsonObject dummyObject = Json.createObjectBuilder()
                .add("heading", "Test Heading")
                .add("description", "Test Description")
                .add("url", "http://www.google.com")
                .add("imageUrl", "http://www.google.com")
                .add("priceInCents", "100")
                .add("alertType", 5)
                .build();

        JsonArray events = null;
        try {
            sut.createAlert(dummyObject);
            events = api_verified.getEventLog();
        } catch (MarketAlertUmAPI.InvalidKeyException e) {
            modelState = MarketAlertStates.INVALID_KEY;
            return;
        }
        numAlerts += 1;

        if (numAlerts >= 5) {
            modelState = MarketAlertStates.FULL;
            numAlerts = 5;
        } else {
            modelState = MarketAlertStates.FILLING;
        }
        System.out.println(events.toString());

        if (events.size() > 0) {
            Assert.assertEquals("The event log returned an unexpected event type.", 0, events.getJsonObject(0).getInt("eventLogType"));
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean purgeGuard() {
        return true;
    }
    public @Action void purge() {

        JsonArray events = null;

        try {
            sut.purgeAlerts();
            events = api_verified.getEventLog();
        } catch (MarketAlertUmAPI.InvalidKeyException e) {
            modelState = MarketAlertStates.INVALID_KEY;
            return;
        }

        numAlerts = 0;
        modelState = MarketAlertStates.EMPTY;
        if (events.size() > 0) {
            Assert.assertEquals("The event log returned an unexpected event type.", 1, events.getJsonObject(0).getInt("eventLogType"));
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean viewGuard() {
        return true;
    }
    public @Action void view() {
        JsonArray alerts = Json.createArrayBuilder().build();
        JsonArray events = Json.createArrayBuilder().build();
        try {
            alerts = sut.getAlerts();
            events = api_verified.getEventLog();
        } catch (MarketAlertUmAPI.InvalidKeyException e) {
            modelState = MarketAlertStates.INVALID_KEY;
            return;
        }

        //  For the purposes of this demo I am using the GET /Alert call that was exposed however not documented.
        //  It returns more alerts than 5 and it also doesn't even setup an event.
        //  Taking advantage of this, even though realistically I should be using GET /Alerts/List and parsing the table.
        if (events.size() > 0) {
            Assert.assertEquals("The event log returned an unexpected event type.", 7, events.getJsonObject(0).getInt("eventLogType"));
        }

        Assert.assertTrue("Returned more alerts than 5", alerts.size() <= 5);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void MarketAlertAlertTestRunner() throws FileNotFoundException {
        final Tester tester = new GreedyTester(new MarketAlertUmAPIModelTest());
        tester.setRandom(new Random());
        tester.addListener(new StopOnFailureListener());
        tester.addListener("verbose");
        tester.addCoverageMetric(new TransitionPairCoverage());
        tester.addCoverageMetric(new StateCoverage());
        tester.addCoverageMetric(new ActionCoverage());
        tester.generate(100);
        tester.printCoverage();
    }
}


// That's all for model based testing.
