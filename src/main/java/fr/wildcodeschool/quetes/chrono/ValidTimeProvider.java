package fr.wildcodeschool.quetes.chrono;

import java.time.LocalDateTime;
import java.util.Date;


public class ValidTimeProvider implements TimeProvider {

    private boolean isStarted = false;
    private Date refDate = new Date();
    private long diff;
    private long startingTime;

    public ValidTimeProvider(long startingTime){
        this.startingTime = startingTime;
    }

    @Override
    public void startStop() {
        isStarted = !isStarted;
        if(isStarted)
            refDate = new Date();


    }

    @Override
    public void initialize(){
        refDate = new Date();
        diff = startingTime;
        isStarted = false;
    }

    @Override
    public void reset() {
        refDate = new Date();
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
            diff += new Date().getTime() - refDate.getTime();

        }
        refDate = new Date();
        //System.out.println(diff);
        return diff/1000;
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
