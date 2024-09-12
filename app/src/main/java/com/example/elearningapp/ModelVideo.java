package com.example.elearningapp;

public class ModelVideo {
    String title, videoUrl;

    public ModelVideo() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public ModelVideo(String title, String videoUrl) {
        this.title = title;
        this.videoUrl = videoUrl;
    }
}
