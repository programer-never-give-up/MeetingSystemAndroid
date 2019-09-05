package com.example.meetingsystemandroid.main.search;

public class SearchResultBean {

    private String message;
    private ActivityInfo[] list_activity;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ActivityInfo[] getActivities() {
        return list_activity;
    }

    public void setActivities(ActivityInfo[] activities) {
        this.list_activity = activities;
    }

    class ActivityInfo {
        private String uuid_act;
        private String name_act;
        private String start_time;
        private String end_time;
        private String logo;
        private String location;

        public String getUuid_act() {
            return uuid_act;
        }

        public void setUuid_act(String uuid_act) {
            this.uuid_act = uuid_act;
        }

        public String getName_act() {
            return name_act;
        }

        public void setName_act(String name_act) {
            this.name_act = name_act;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }
}
