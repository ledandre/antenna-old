package br.com.ledtom.antenna.model.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "schedules")
public class Schedule {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "period")
    private int period;
    
    @ManyToOne
    @JoinColumn(name = "channel")
    private Channel channel;
    
    @OneToMany(cascade = CascadeType.ALL)
    @OrderBy("position")
    List<VideoList> videoList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public List<VideoList> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<VideoList> videoList) {
        this.videoList = videoList;
    }
}