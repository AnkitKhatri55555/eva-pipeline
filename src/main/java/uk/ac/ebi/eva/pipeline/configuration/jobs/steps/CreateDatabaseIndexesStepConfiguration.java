/*
 * Copyright 2015-2017 EMBL - European Bioinformatics Institute
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.ac.ebi.eva.pipeline.configuration.jobs.steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import uk.ac.ebi.eva.pipeline.jobs.steps.tasklets.CreateDatabaseIndexesTasklet;
import uk.ac.ebi.eva.pipeline.parameters.JobOptions;
import uk.ac.ebi.eva.utils.TaskletUtils;

import static uk.ac.ebi.eva.pipeline.configuration.BeanNames.CREATE_DATABASE_INDEXES_STEP;

/**
 * Configuration class that inject a step created with the tasklet {@link CreateDatabaseIndexesTasklet}
 */
@Configuration
@EnableBatchProcessing
public class CreateDatabaseIndexesStepConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(CreateDatabaseIndexesStepConfiguration.class);

    @Bean
    @StepScope
    public CreateDatabaseIndexesTasklet createDatabaseIndexesTasklet() {
        return new CreateDatabaseIndexesTasklet();
    }

    @Bean(CREATE_DATABASE_INDEXES_STEP)
    public TaskletStep createDatabaseIndexesStep(StepBuilderFactory stepBuilderFactory, JobOptions jobOptions) {
        logger.debug("Building '" + CREATE_DATABASE_INDEXES_STEP + "'");
        return TaskletUtils.generateStep(stepBuilderFactory, CREATE_DATABASE_INDEXES_STEP,
                createDatabaseIndexesTasklet(), jobOptions.isAllowStartIfComplete());
    }

}
