package com.amolgupta.bookdesk.entity;

import java.util.UUID;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;

// import lombok.ToString;

@Entity
@NamedQueries({
    @NamedQuery(name = "Desk.findAll", query = "SELECT desk from Desk desk"),
	@NamedQuery(name = "Desk.findAllByDate", query = "select desk from Desk desk where desk.id not in ( select deskId from Booking booking where not ((:paramStopBookTime <= booking.startBookTime) or (:paramStartBookTime >= booking.stopBookTime)) )")
})
public class Desk {    

    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;

    @JsonProperty(value = "deskNumber")
    private String deskNumber;

    @JsonProperty(value = "covidCompliant")
    private Boolean covidCompliant;

    // @ToString.Exclude
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "roomId", referencedColumnName = "id")
    @JsonProperty(value = "roomId")
    private String roomId;

    /**
     * Constructor
	 */
	public Desk() {
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
	 * @return the deskNumber
	 */
	public String getDeskNumber() {
		return deskNumber;
	}

	/**
	 * @param deskNumber the deskNumber to set
	 */
	public void setDeskNumber(String deskNumber) {
		this.deskNumber = deskNumber;
	}

	/**
	 * @return the covidCompliant
	 */
	public Boolean getCovidCompliant() {
		return covidCompliant;
	}

	/**
	 * @param covidCompliant the covidCompliant to set
	 */
	public void setCovidCompliant(Boolean covidCompliant) {
		this.covidCompliant = covidCompliant;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	/**
	 * @return the roomId
	 */
	public String getRoomId() {
		return roomId;
	}

	/**
	 * @param roomId the roomId to set
	 */
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	@Override
	public String toString() {
		return "Desk [covidCompliant=" + covidCompliant + ", deskNumber=" + deskNumber + ", id=" + id + ", roomId=" + roomId
				+ "]";
	}

	

}
