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
public class JobConfiguration {

    //注入创建任务对象的对象
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    //任务执行由step决定
    //注入创建step对象的对象
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    //创建任务对象
    @Bean
    public Job helloWorldJob(){
        return jobBuilderFactory.get("helloWorldJob")
                .start(step1())  //这里要放入步骤，然后去执行
                .build();
    }
    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
//                .chunk();//可以使用chunk和tasklet
                  .tasklet(new Tasklet() {
                      @Override
                      public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                          try {
                              System.out.println("Hello World!");
                              return RepeatStatus.FINISHED;
                          } catch (Exception e) {
                              return RepeatStatus.CONTINUABLE;

                          }
                          //返回值必须是RepeatStatus，这样任务可以判断是否继续执行下一步

                      }
                  }).build();
    }
}
