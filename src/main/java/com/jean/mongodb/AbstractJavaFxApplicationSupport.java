package com.jean.mongodb;

import javafx.application.Application;
import javafx.application.Preloader;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by jinshubao on 2017/3/20.
 */
public abstract class AbstractJavaFxApplicationSupport extends Application {
    protected static String[] args;

    protected ConfigurableApplicationContext applicationContext;

    @Override
    public void init() throws Exception {
        notifyPreloader(new Preloader.StateChangeNotification(Preloader.StateChangeNotification.Type.BEFORE_INIT));
        applicationContext = SpringApplication.run(getClass(), args);
        applicationContext.getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        applicationContext.close();
    }

    protected static void launchApp(Class<? extends AbstractJavaFxApplicationSupport> applicationClass, String[] args) {
        AbstractJavaFxApplicationSupport.args = args;
        Application.launch(applicationClass, args);
    }
}
