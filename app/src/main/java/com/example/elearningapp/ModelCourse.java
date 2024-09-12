package com.example.elearningapp;

public class ModelCourse {
    String category, title, totalLessons, tutor, timestamp;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTotalLessons() {
        return totalLessons;
    }

    public void setTotalLessons(String totalLessons) {
        this.totalLessons = totalLessons;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public ModelCourse() {
    }

    public ModelCourse(String category, String title, String totalLessons, String tutor, String timestamp) {
        this.category = category;
        this.title = title;
        this.totalLessons = totalLessons;
        this.tutor = tutor;
        this.timestamp = timestamp;
    }
}
