
package com.cactuses.uni_system_integration_3.model;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wrapper {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("page")
    @Expose
    private Page page;
    @SerializedName("videos")
    @Expose
    private List<Video> videos = null;


    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }



}
