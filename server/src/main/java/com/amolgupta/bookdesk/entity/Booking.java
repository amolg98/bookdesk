package com.amolgupta.bookdesk.entity;

import javax.persistence.Column;
// import javax.persistence.CascadeType;
// import javax.persistence.Embedded;
// import javax.persistence.OneToOne;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import java.util.Date;
import java.sql.Timestamp;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@NamedQueries({
    @NamedQuery(name = "Booking.findAll", query = "SELECT booking from Booking booking")
})
public class Booking {
    
    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;

    @JsonProperty(value = "date")
    private Date date;

    @JsonProperty(value = "startTimestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private Timestamp startBookTime;

    @JsonProperty(value = "stopTimestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private Timestamp stopBookTime;
    
    @JsonProperty(value = "userId")
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private String userId;

    @JsonProperty(value = "deskId")
    @JoinColumn(name = "deskId", referencedColumnName = "id")
    private String deskId;

    /**
     * Constructor
	 * @param id
	 */
	public Booking() {
		this.id = UUID.randomUUID().toString();
	}

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the startBookTime
     */
    public Timestamp getStartBookTime() {
        return startBookTime;
    }

    /**
     * @param startBookTime the startBookTime to set
     */
    public void setStartBookTime(Timestamp startBookTime) {
        this.startBookTime = startBookTime;
    }

    /**
     * @return the stopBookTime
     */
    public Timestamp getStopBookTime() {
        return stopBookTime;
    }

    /**
     * @param stopBookTime the stopBookTime to set
     */
    public void setStopBookTime(Timestamp stopBookTime) {
        this.stopBookTime = stopBookTime;
    }

    

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    
    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the deskId
     */
    public String getDeskId() {
        return deskId;
    }

    /**
     * @param deskId the deskId to set
     */
    public void setDeskId(String deskId) {
        this.deskId = deskId;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    
    @Override
    public String toString() {
        return "Booking [date=" + date + ", deskId=" + deskId + ", id=" + id + ", startBookTime=" + startBookTime
                + ", stopBookTime=" + stopBookTime + ", userId=" + userId + "]";
    }

    
}
