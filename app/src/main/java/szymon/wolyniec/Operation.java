package szymon.wolyniec;

public enum Operation {
    NONE(""), ADD("+"), SUBSTRACT("-"), MULTIPLY("*"), DIVIDE("/");

    private final String key;

    Operation(String key) {
        this.key = key;
    }

    public static Operation operationFromKey(String key) {
        for (Operation operation : values()) {
            if (operation.key.equals(key)) {
                return operation;
            }
        }

        return NONE;
    }

}
