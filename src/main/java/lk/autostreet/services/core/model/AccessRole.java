package lk.autostreet.services.core.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccessRole {

    public static final String ROLE_COMPANY_USER = "ROLE_COMPANY_USER";
    public static final String ROLE_COMPANY_ADMIN = "ROLE_COMPANY_ADMIN";
    public static final String ROLE_SYSTEM_ADMIN = "ROLE_SYSTEM_ADMIN";
}
