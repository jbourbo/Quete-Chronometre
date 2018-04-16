package fr.wildcodeschool.quetes.chrono;

public interface TimeProvider {

    void startStop();
    void reset();
    void initialize();

    boolean isStarted();

    long getSecondsTotalRuntime();
    long getHoursRuntime();
    long getMinutesRuntime();
    long getSecondsRuntime();
}
