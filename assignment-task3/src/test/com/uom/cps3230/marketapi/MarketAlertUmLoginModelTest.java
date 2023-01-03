package com.uom.cps3230.marketapi;

import com.uom.cps3230.marketapi.enums.LoginStates;
import com.uom.cps3230.marketapi.enums.MarketAlertStates;
import junit.framework.Assert;
import nz.ac.waikato.modeljunit.*;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionPairCoverage;
import org.junit.Test;
import org.uispec4j.utils.Log;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import java.io.FileNotFoundException;
import java.util.Random;

public class MarketAlertUmLoginModelTest implements FsmModel {
    private LoginStates modelState;
    private boolean logged_in;
    private MarketAlertUmAPI sut;

    private MarketAlertUmAPI api_verified = new MarketAlertUmAPI("3ce46c25-f585-4e3c-b01d-2a34afb95757");


    public LoginStates getState() {
        return modelState;
    }

    public void reset(final boolean b) {
        try {
            api_verified.purgeAlerts();
            api_verified.getEventLog();
        } catch (MarketAlertUmAPI.InvalidKeyException e) {
            // This should never happen.
            sut = new MarketAlertUmAPI("3ce46c25-f585-4e3c-b01d-2a34afb95757");
            return;
        }
        modelState = LoginStates.LOGGED_OUT;
        logged_in = false;
        if (b) {
            // 0.05 chance of using a bad key.
            Random rand = new Random();
            if (rand.nextInt(100) < 80)
                sut = new MarketAlertUmAPI("3ce46c25-f585-4e3c-b01d-2a34afb95757");
            else 
                sut = new MarketAlertUmAPI("BAD_KEY");
        }
    }

    public boolean loginGuard() {
        return true;
    }
    public @Action void login() {

        JsonArray events = null;
        try {
            sut.login();
            events = api_verified.getEventLog();
        } catch (MarketAlertUmAPI.InvalidKeyException e) {
            modelState = LoginStates.INVALID_KEY;
            return;
        }
        logged_in = true;
        modelState = LoginStates.LOGGED_IN;
        Assert.assertEquals("The event log returned an unexpected event type.", 5, events.getJsonObject(0).getInt("eventLogType"));
    }

    public boolean logoutGuard() {
        return true;
    }
    public @Action void logout() {

        JsonArray events = null;

        try {
            sut.logout();
            events = api_verified.getEventLog();
        } catch (MarketAlertUmAPI.InvalidKeyException e) {
            return;
        }

        logged_in = false;
        modelState = LoginStates.LOGGED_OUT;
        if (events.size() > 0)
            Assert.assertEquals("The event log returned an unexpected event type.", 6, events.getJsonObject(0).getInt("eventLogType"));
    }

    @Test
    public void MarketAlertLoginTestRunner() throws FileNotFoundException {
        final Tester tester = new GreedyTester(new MarketAlertUmLoginModelTest());
        tester.setRandom(new Random());
        tester.addListener(new StopOnFailureListener());
        tester.addListener("verbose");
        tester.addCoverageMetric(new TransitionPairCoverage());
        tester.addCoverageMetric(new StateCoverage());
        tester.addCoverageMetric(new ActionCoverage());
        tester.generate(50);
        tester.printCoverage();
    }
}
