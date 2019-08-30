package com.example.meetingsystemandroid.user_history;

import java.lang.reflect.Array;

public class UserHistoryBean {
    private HistoryActivity[] list_activity;
    private String message;

    public HistoryActivity[] getList_activity() {
        return list_activity;
    }

    public void setList_activity(HistoryActivity[] list_activity) {
        this.list_activity = list_activity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    class HistoryActivity {
        private String uuid_act;
        private String name_act;
        private String start_time;
        private String end_time;

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
    }
}
