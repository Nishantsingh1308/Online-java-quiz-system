package java;

public class Timer {
    private long startTime;
    private long elapsedTime;
    private boolean running;

    public Timer() {
        this.running = false;
    }

    public void start() {
        if (!running) {
            startTime = System.currentTimeMillis();
            running = true;
        }
    }

    public void stop() {
        if (running) {
            elapsedTime = System.currentTimeMillis() - startTime;
            running = false;
        }
    }

    public long getElapsedTime() {
        if (running) {
            return System.currentTimeMillis() - startTime;
        }
        return elapsedTime;
    }

    public String getFormattedTime() {
        long seconds = getElapsedTime() / 1000;
        long minutes = seconds / 60;
        seconds = seconds % 60;
        long hours = minutes / 60;
        minutes = minutes % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
