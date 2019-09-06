package com.example.meetingsystemandroid.activity_manager;

import com.example.meetingsystemandroid.model.Activity;

public class ManagerActivityInfoResponseBean {
    private int pageNum;
    private ActivityInfo[] activities;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public ActivityInfo[] getActivities() {
        return activities;
    }

    public void setActivities(ActivityInfo[] activities) {
        this.activities = activities;
    }

    class ActivityInfo {
        private String logoSrc;
        private String activityName;
        private String startTime;
        private String endTime;
        private String id;

        public String getLogoSrc() {
            return logoSrc;
        }

        public void setLogoSrc(String logoSrc) {
            this.logoSrc = logoSrc;
        }

        public String getActivityName() {
            return activityName;
        }

        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
