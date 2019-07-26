package lk.autostreet.services.core.model;

public enum UserType {

    SYSTEM_ADMIN("SYSTEM_ADMIN"),
    COMPANY_ADMIN("COMPANY_ADMIN"),
    COMPANY_USER("COMPANY_USER"),
    GUEST("GUEST");

    private String role;

    UserType(String role) {
        this.role = role;
    }

    public String getValue() {
        return role;
    }
}
