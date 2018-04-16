package fr.wildcodeschool.quetes.chrono;

public class DummyTimeProvider implements TimeProvider {
    @Override
    public void startStop() {
        // nothing
    }

    @Override
    public void reset() {
        // nothing
    }

    @Override
    public void initialize() {
        // nothing
    }

    @Override
    public boolean isStarted() {
        return true;
    }

    @Override
    public long getSecondsTotalRuntime() {
        return 370;
    }

    @Override
    public long getHoursRuntime() {
        return 0;
    }

    @Override
    public long getMinutesRuntime() {
        return 6;
    }

    @Override
    public long getSecondsRuntime() {
        return 10;
    }
}
