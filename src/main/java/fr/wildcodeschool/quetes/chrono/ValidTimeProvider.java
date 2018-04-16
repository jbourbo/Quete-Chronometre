package fr.wildcodeschool.quetes.chrono;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Date.*;
import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.SECONDS;

public class ValidTimeProvider implements TimeProvider {

    private boolean isStarted = false;
    private LocalDateTime refDate = LocalDateTime.now();
    private long diff;
    private long startingTime;

    public ValidTimeProvider(long startingTime){
        this.startingTime = startingTime;
    }

    @Override
    public void startStop() {
        isStarted = !isStarted;
        if(isStarted)
            refDate = LocalDateTime.now();


    }

    @Override
    public void initialize(){
        refDate = LocalDateTime.now();
        diff = startingTime;
        isStarted = false;
    }

    @Override
    public void reset() {
        refDate = LocalDateTime.now();
        diff = 0;
        isStarted = false;
    }

    @Override
    public boolean isStarted() {
        return isStarted;
    }

    @Override
    public long getSecondsTotalRuntime() {
        if(isStarted){
            diff += SECONDS.between(refDate, LocalDateTime.now());

        }
        refDate = LocalDateTime.now();
        //System.out.println(diff);
        return diff;
    }

    @Override
    public long getHoursRuntime() {
        return ((diff-getSecondsRuntime())/1000)/3600;
    }

    @Override
    public long getMinutesRuntime() {
        return ((diff-getSecondsRuntime())/1000)/60;
    }

    @Override
    public long getSecondsRuntime() {
        return getSecondsTotalRuntime()%60;
    }
}
