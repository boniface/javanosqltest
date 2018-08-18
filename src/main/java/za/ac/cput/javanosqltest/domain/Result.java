package za.ac.cput.javanosqltest.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Result implements Serializable {
    private LocalDateTime start;
    private LocalDateTime end;
    private Long duration;
    private Long objects;

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Long getObjects() {
        return objects;
    }

    public void setObjects(Long objects) {
        this.objects = objects;
    }
}
