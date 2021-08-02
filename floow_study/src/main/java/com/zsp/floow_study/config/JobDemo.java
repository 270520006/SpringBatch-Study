package com.zsp.floow_study.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class JobDemo {
    //注入创建任务对象的对象
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    //任务执行由step决定
    //注入创建step对象的对象
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    //创建任务对象
    @Bean
    public Job jobDemo1(){
        return jobBuilderFactory.get("abc")
                .start(step11())
                .next(step22())
                .next(step33()).build();
    }
    @Bean
    public Step step33() {
        return stepBuilderFactory.get("step3")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("step3");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }
    @Bean
    public Step step22() {
        return stepBuilderFactory.get("step2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("step2");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }
    @Bean
    public Step step11() {

        return stepBuilderFactory.get("step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("step1");
                        return RepeatStatus.FINISHED;
                    }
                }).build();

    }
}
