package com.zsp.floow_study.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class FlowDemo {

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
        return (Job) jobBuilderFactory.get("abc")
                .start(flowTestDemo())
                .next(step11()).build();
    }
    @Bean
    public Flow flowTestDemo(){
        return new FlowBuilder<Flow>("flowDemoFlow")
                .start(stepF1())
                .next(stepF2())
                .build();
    }
    @Bean
    public Step stepF1() {
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
    public Step stepF2() {
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
