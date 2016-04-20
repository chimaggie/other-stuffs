package com.faradayfuture.test.commons.rest.databind;

import com.faradayfuture.test.commons.rest.runner.RestTestGroupJsonPath;
import org.junit.Test;

import java.net.URL;

/**
 * Created by maggie on 4/19/16.
 */
public class RestTestGroupParsingTest {
    @Test
    public void parseJson() throws Exception {
        RestTestGroup group = RestTestGroup.build(getClass().getResource("testGroup.json"));
        int i = 0;
    }
}
