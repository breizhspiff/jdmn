/**
 * Copyright 2016 Goldman Sachs.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.gs.dmn.tck;

import com.gs.dmn.dialect.DMNDialectDefinition;
import com.gs.dmn.dialect.StandardDMNDialectDefinition;
import com.gs.dmn.feel.analysis.semantics.environment.DefaultDMNEnvironmentFactory;
import com.gs.dmn.log.BuildLogger;
import com.gs.dmn.runtime.DefaultDMNBaseDecision;
import com.gs.dmn.serialization.DMNReader;
import com.gs.dmn.transformation.AbstractTestTransformerTest;
import com.gs.dmn.transformation.DMNTransformer;
import com.gs.dmn.transformation.FileTransformer;
import com.gs.dmn.transformation.ToJavaNameTransformer;

import java.net.URLDecoder;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractTCKTestCasesToJUnitTransformerTest extends AbstractTestTransformerTest {
    public void doTest(String dmnFileName, String testFileName) throws Exception {
        String dmnPath = getDMNInputPath() + "/";
        String testCasesPath = getTestCasesInputPath() + "/";
        String expectedPath = getExpectedPath() + "/" + friendlyFolderName(dmnFileName);
        String inputTestFilePath = testCasesPath + testFileName + TestCasesReader.TEST_FILE_EXTENSION;
        String inputModelFilePath = dmnPath + dmnFileName + DMNReader.DMN_FILE_EXTENSION;
        String decodedInputTestFilePath = URLDecoder.decode(resource(inputTestFilePath).getPath(), "UTF-8");
        String decodedInputModelFilePath = URLDecoder.decode(resource(inputModelFilePath).getPath(), "UTF-8");
        super.doTest(decodedInputTestFilePath, decodedInputModelFilePath, expectedPath);
    }

    @Override
    protected DMNDialectDefinition getDialectDefinition() {
        return new StandardDMNDialectDefinition();
    }

    @Override
    protected DMNTransformer makeDMNTransformer(BuildLogger logger) {
        return new ToJavaNameTransformer(logger);
    }

    @Override
    protected FileTransformer makeTransformer(Path inputModelPath, Map<String, String> inputParameters, BuildLogger logger) {
        return new TCKTestCasesToJUnitTransformer(getDialectDefinition(), makeDMNTransformer(logger), inputModelPath, inputParameters, logger);
    }

    @Override
    protected Map<String, String> makeInputParameters() {
        return new LinkedHashMap<String, String>() {{
            put("environmentFactoryClass", DefaultDMNEnvironmentFactory.class.getName());
            put("decisionBaseClass", DefaultDMNBaseDecision.class.getName());
            put("semanticValidation", "true");
        }};
    }

    protected abstract String getDMNInputPath();

    protected abstract String getTestCasesInputPath();

    protected abstract String getExpectedPath();
}