package unit08.weighted;

public enum CellType {
    PELLET("PE"),
    POWER_PELLET("PP"),
    GHOST("GH"),
    PAC_MAN("PM");

    private final String code;

    CellType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

