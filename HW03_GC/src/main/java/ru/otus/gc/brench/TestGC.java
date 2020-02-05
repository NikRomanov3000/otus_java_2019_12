package ru.otus.gc.brench;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.MBeanServer;
import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;
/*
-Xms2048m
-Xmx2048m
-Xlog:gc=debug:file=./logs/gc-%p-%t.log:tags,uptime,time,level:filecount=5,filesize=10m
-XX:+HeapDumpOnOutOfMemoryError
-XX:HeapDumpPath=./logs/dump
-XX:+UseG1GC

Прогоны с примерами из урока
1) UseG1GC; time: 103, time2: 352
2) Using Parallel GC; time: 36; time2: 153
3) Using Serial GC; time: 35; time: 151
4) Using ConcMark Sweep GC; time: 37; time2: 160

 */
public class TestGC {
    public static void main(String... args) throws Exception {
        System.out.println( "Starting pid: " + ManagementFactory.getRuntimeMXBean().getName() );
        goToMonitor();
        long beginTime=System.currentTimeMillis();

        int size = 6 *1000*1000;
        int loopCounter=4000;

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("ru.otus:type=Benchmark");

        Benchmark mbean = new Benchmark(loopCounter);
        mbs.registerMBean(mbean,name);
        mbean.setSize(size);
        mbean.run();

        System.out.println("time:" + ( System.currentTimeMillis() - beginTime ) / 1000 );

    }


    //Для логов взял метод из вебинара
    private static void goToMonitor(){
        List<GarbageCollectorMXBean> gcbeans = java.lang.management.ManagementFactory.getGarbageCollectorMXBeans();
        for ( GarbageCollectorMXBean gcbean : gcbeans ) {
            System.out.println( "GC name:" + gcbean.getName() );
            NotificationEmitter emitter = (NotificationEmitter) gcbean;
            NotificationListener listener = ( notification, handback ) -> {
                if ( notification.getType().equals( GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION ) ) {
                    GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from( (CompositeData) notification.getUserData() );
                    String gcName = info.getGcName();
                    String gcAction = info.getGcAction();
                    String gcCause = info.getGcCause();

                    long startTime = info.getGcInfo().getStartTime();
                    long duration = info.getGcInfo().getDuration();

                    System.out.println( "start:" + startTime + " Name:" + gcName + ", action:" + gcAction + ", gcCause:" + gcCause + "(" + duration + " ms)" );
                }
            };
            emitter.addNotificationListener( listener, null, null );
        }
    }

}


