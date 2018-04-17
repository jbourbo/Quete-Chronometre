package fr.wildcodeschool.quetes.chrono;

public interface TimeProvider {

    void startStop();
    void reset();

    boolean isStarted();
    void initialize();

    long getSecondsTotalRuntime();
    long getHoursRuntime();
    long getMinutesRuntime();
    long getSecondsRuntime();
}
