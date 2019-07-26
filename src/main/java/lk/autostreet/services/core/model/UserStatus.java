package lk.autostreet.services.core.model;

public enum UserStatus {

    PENDING("PENDING"),
    ACTIVE("ACTIVE"),
    SUSPENDED("SUSPENDED");

    private String status;

    UserStatus(String status) {
        this.status = status;
    }
}
