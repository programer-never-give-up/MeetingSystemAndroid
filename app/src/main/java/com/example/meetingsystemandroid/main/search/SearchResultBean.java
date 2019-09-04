package com.example.meetingsystemandroid.main.search;

public class SearchResultBean {

    private String message;
    private ActivityInfo[] activities;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ActivityInfo[] getActivities() {
        return activities;
    }

    public void setActivities(ActivityInfo[] activities) {
        this.activities = activities;
    }

    class ActivityInfo {
        private String mName;
        private String mId;

        public String getmName() {
            return mName;
        }

        public void setmName(String mName) {
            this.mName = mName;
        }

        public String getmId() {
            return mId;
        }

        public void setmId(String mId) {
            this.mId = mId;
        }
    }
}
