package com.mobiquity.assignment;

import com.typesafe.config.Config;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;

@RequiredArgsConstructor()
public class TestConfig implements Config {

    @Delegate(types = Config.class)
    private final Config delegate;

    public String getTypicodeURL() {
        return delegate.getString("typicodeURL");
    }

    public Integer getDefaultTimeoutMilSec() {
        return delegate.getInt("defaultTimeoutMilSec");
    }

}
