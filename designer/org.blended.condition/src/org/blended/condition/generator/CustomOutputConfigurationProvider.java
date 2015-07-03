package org.blended.condition.generator;

import static com.google.common.collect.Sets.newHashSet;

import java.util.Set;

import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IOutputConfigurationProvider;
import org.eclipse.xtext.generator.OutputConfiguration;

public class CustomOutputConfigurationProvider implements
    IOutputConfigurationProvider {

public final static String SRC_OUTPUT = "SRC_OUTPUT";

/**
 * @return a set of {@link OutputConfiguration} available for the generator
 */
public Set<OutputConfiguration> getOutputConfigurations() {
    OutputConfiguration defaultOutput = new OutputConfiguration(IFileSystemAccess.DEFAULT_OUTPUT);
    defaultOutput.setDescription("Output Folder");
    defaultOutput.setOutputDirectory("./src-gen");
    defaultOutput.setOverrideExistingResources(true);
    defaultOutput.setCreateOutputDirectory(true);
    defaultOutput.setCleanUpDerivedResources(true);
    defaultOutput.setSetDerivedProperty(true);

    OutputConfiguration onceOutput = new OutputConfiguration(SRC_OUTPUT);
    onceOutput.setDescription("Output Folder (once)");
    onceOutput.setOutputDirectory("./src");
    onceOutput.setOverrideExistingResources(true);
    onceOutput.setCreateOutputDirectory(true);
    onceOutput.setCleanUpDerivedResources(true);
    onceOutput.setSetDerivedProperty(true);
    return newHashSet(defaultOutput, onceOutput);
}

}