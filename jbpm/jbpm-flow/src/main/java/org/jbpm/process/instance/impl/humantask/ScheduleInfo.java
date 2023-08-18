package org.jbpm.process.instance.impl.humantask;

import java.io.Serializable;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Objects;

public class ScheduleInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Duration duration;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private int numRepetitions;

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public int getNumRepetitions() {
        return numRepetitions;
    }

    public void setNumRepetitions(int numRepetions) {
        this.numRepetitions = numRepetions;
    }

    @Override
    public String toString() {
        return "ScheduleInfo [duration=" + duration + ", startDate=" + startDate + ", endDate=" + endDate +
                ", numRepetions=" + numRepetitions + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(duration, endDate, numRepetitions, startDate);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof ScheduleInfo))
            return false;
        ScheduleInfo other = (ScheduleInfo) obj;
        return Objects.equals(duration, other.duration) && Objects.equals(endDate, other.endDate) &&
                numRepetitions == other.numRepetitions && Objects.equals(startDate, other.startDate);
    }

}
