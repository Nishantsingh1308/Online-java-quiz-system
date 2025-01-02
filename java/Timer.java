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
            startTime = System.nanoTime();  // Use nanoTime for higher precision
            running = true;
        }
    }

    public void stop() {
        if (running) {
            elapsedTime = System.nanoTime() - startTime;  // Use nanoTime for elapsed time calculation
            running = false;
        }
    }

    public long getElapsedTime() {
        if (running) {
            return System.nanoTime() - startTime;  // Use nanoTime for accurate current time
        }
        return elapsedTime;
    }

    public String getFormattedTime() {
        long seconds = getElapsedTime() / 1000000000;  // Convert nanoseconds to seconds
        long minutes = seconds / 60;
        seconds = seconds % 60;
        long hours = minutes / 60;
        minutes = minutes % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
