package com.amolgupta.bookdesk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
@NamedQueries({
    @NamedQuery(name = "Room.findAll", query = "SELECT room from Room room"),
    @NamedQuery(name = "Room.findById", query = "SELECT room FROM Room room WHERE room.id=:paramRoomId"),
    @NamedQuery(name = "Room.findAllByDate", query = "select room from Room room where room.id in (select distinct roomId from Desk desk where desk.id not in ( select deskId from Booking booking where not ((:paramStopBookTime <= booking.startBookTime) or (:paramStartBookTime >= booking.stopBookTime)) ))")
})
public class Room {
    
    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "deskList")
    @OneToMany(targetEntity = Desk.class, mappedBy = "room", fetch=FetchType.LAZY)
    @Transient
    private List<Desk> deskList;

    public Room() {
        this.id = UUID.randomUUID().toString();
        deskList = new ArrayList<>();
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the deskList
     */
    public List<Desk> getDeskList() {
        return deskList;
    }

    /**
     * @param deskList the deskList to set
     */
    public void setDeskList(List<Desk> deskList) {
        this.deskList = deskList;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    
    @Override
    public String toString() {
        return "Room [deskList=" + deskList + ", id=" + id + ", name=" + name + "]";
    }

}

class DeskDeserializer extends JsonDeserializer<List<Desk>> {
    
    @Override
    public List<Desk> deserialize(JsonParser jsonParser,
            DeserializationContext deserializationContext) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonParser);
        List<Desk> deskList = mapper.convertValue(node.findValue("desk"), new TypeReference<List<Desk>>() {});
        return deskList;
    }
}
