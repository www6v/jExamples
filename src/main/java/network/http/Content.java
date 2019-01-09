package network.http;

import java.util.Date;

//@Entity
//@Table(name = "t_cont_content", indexes = {@Index(columnList = "developerId"),@Index(columnList = "appId")})
public class Content {
    public static final int CONTENT_STATUS_OK = 0;
    public static final int CONTENT_STATUS_DEL = 1;

    public static final int OBJECT_TYPE__PICTURE = 0;
    public static final int OBJECT_TYPE__TEXT = 1;
    public static final int OBJECT_TYPE__MODEL = 2;
    public static final int OBJECT_TYPE__MUSIC = 3;
    public static final int OBJECT_TYPE__VIDEO = 4;

//    @Id
//    @GeneratedValue
    private Long id;

    private Long developerId;

    private Long appId;
    
    private String name;

    private String description;

    private String note;
    
    private String imgUrl;

    private String dataUrl;

    private String bundleUrl;

    private String metaData;

    private Date createTime;

    private Date updateTime;
    
    private Integer status;
    
    private Integer contentStatus;
    
    private Integer withPic;
    
    private String type;

    private String winBundle;
    
    private String andBundle;
    
    private String uwpBundle;

    private Integer objectType;  //0-图片 1-文字 2-模型 3-音频 4-视频
    
    private Integer isFlag; //1代表从页面上传的。
    
    public String getUwpBundle() {
		return uwpBundle;
	}

	public void setUwpBundle(String uwpBundle) {
		this.uwpBundle = uwpBundle;
	}

	public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getObjectType() {
        return objectType;
    }

    public void setObjectType(Integer objectType) {
        this.objectType = objectType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getWithPic() {
        return withPic;
    }

    public void setWithPic(Integer withPic) {
        this.withPic = withPic;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getContentStatus() {
        return contentStatus;
    }

    public void setContentStatus(Integer contentStatus) {
        this.contentStatus = contentStatus;
    }

    public String getWinBundle() {
        return winBundle;
    }

    public void setWinBundle(String winBundle) {
        this.winBundle = winBundle;
    }

    public String getAndBundle() {
        return andBundle;
    }

    public void setAndBundle(String andBundle) {
        this.andBundle = andBundle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(Long developerId) {
        this.developerId = developerId;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }

    public String getBundleUrl() {
        return bundleUrl;
    }

    public void setBundleUrl(String bundleUrl) {
        this.bundleUrl = bundleUrl;
    }

    public String getMetaData() {
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public Integer getIsFlag() {
		return isFlag;
	}

	public void setIsFlag(Integer isFlag) {
		this.isFlag = isFlag;
	}

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", developerId=" + developerId +
                ", appId=" + appId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", note='" + note + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", dataUrl='" + dataUrl + '\'' +
                ", bundleUrl='" + bundleUrl + '\'' +
                ", metaData='" + metaData + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", contentStatus=" + contentStatus +
                ", withPic=" + withPic +
                ", type='" + type + '\'' +
                ", winBundle='" + winBundle + '\'' +
                ", andBundle='" + andBundle + '\'' +
                ", uwpBundle='" + uwpBundle + '\'' +
                ", objectType=" + objectType +
                ", isFlag=" + isFlag +
                '}';
    }
}
