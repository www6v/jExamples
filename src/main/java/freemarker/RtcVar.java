package freemarker;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by www6v on 2019/4/17.
 */
public class RtcVar {
    private String appId;
    private String roomId;
    private String streamId;
    private String userId;

    public RtcVar(String appId,String roomId, String streamId, String userId ) {
        this.setAppId(appId);
        this.setRoomId(roomId);
        this.setStreamId(streamId);
        this.setUserId(userId);
    }


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getStreamId() {
        return streamId;
    }

    public void setStreamId(String streamId) {
        this.streamId = streamId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object obj) {
        return new EqualsBuilder().append(this.appId, ( (RtcVar)obj ).appId)
                .append(this.roomId, ( (RtcVar)obj ).roomId)
                .append(this.streamId, ( (RtcVar)obj ).streamId)
                .append(this.userId, ( (RtcVar)obj ).userId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(11, 21).append(this.appId)
                .append(this.roomId)
                .append(this.streamId)
                .append(this.userId)
                .toHashCode();
    }

    @Override
    public String toString(){
        return new ToStringBuilder(this)
                .append("appId", appId)
                .append("roomId", roomId)
                .append("streamId", streamId)
                .append("userId", userId)
                .toString();
    }
}
