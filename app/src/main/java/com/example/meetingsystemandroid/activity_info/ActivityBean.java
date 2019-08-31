package com.example.meetingsystemandroid.activity_info;

public class ActivityBean {
    private String message;
    private String name;
    private String start_time;
    private String end_time;
    private String location;
    private String organizer;
    private String introduction;
    private String logo;
    private String status_publish;
    private String status_process;
    private String type;
    private ActivityFile[] files;

    class ActivityFile {
        private String fileName;
        private String fileSrc;

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFileSrc() {
            return fileSrc;
        }

        public void setFileSrc(String fileSrc) {
            this.fileSrc = fileSrc;
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getStatus_publish() {
        return status_publish;
    }

    public void setStatus_publish(String status_publish) {
        this.status_publish = status_publish;
    }

    public String getStatus_process() {
        return status_process;
    }

    public void setStatus_process(String status_process) {
        this.status_process = status_process;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ActivityFile[] getFiles() {
        return files;
    }

    public void setFiles(ActivityFile[] files) {
        this.files = files;
    }
}
