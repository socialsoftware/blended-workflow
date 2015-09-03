package org.blended.goal.terminal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ManageSplitModel1Test extends ManageSplitTest {
    private static final Logger logger = LoggerFactory.getLogger(ManageSplitModel1Test.class);
    
    public ManageSplitModel1Test() throws Exception {
		super.model = loadXtextResouce("model1.gm");
    	logger.debug("model1.gm loaded");
	}
    
    @Test
    public void checkParametersOK() {
    	CommandSplit command = createParameters("g100", "s", "g15", "Data");
    	assertThat(command.name, is("g100"));
    	assertThat(command.type, is("s"));
    	assertThat(command.goal, is("g15"));
    	assertThat(command.successConditions, is("Data"));
    }
   
}
