package lk.autostreet.services.core.model;

public enum UserType {

    ADMIN("ADMIN"),
    SELLER("SELLER"),
    SELLER_AGENT("SELLER_AGENT"),
    GUEST("GUEST");

    private String role;

    UserType(String role) {
        this.role = role;
    }

    public String getValue() {
        return role;
    }
}
