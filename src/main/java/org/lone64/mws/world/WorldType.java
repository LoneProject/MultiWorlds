package org.lone64.mws.world;

public enum WorldType {
    normal("&a오버월드"), nether("&c지옥월드"), the_end("&5엔드월드");

    private final String name;

    WorldType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
