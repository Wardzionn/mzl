package pl.lodz.p.it.ssbd2023.ssbd04.roles;

public enum RoleTypeEnum {
    ROLE_ADMIN (Roles.ADMIN),
    ROLE_CLIENT (Roles.MANAGER),
    ROLE_MANAGER (Roles.REFEREE),
    ROLE_COACH (Roles.COACH),
    ROLE_CAPITAN (Roles.CAPTAIN);

    public final String name;

    private RoleTypeEnum(String name) {
        this.name = name;
    }

    public boolean equalsName(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
