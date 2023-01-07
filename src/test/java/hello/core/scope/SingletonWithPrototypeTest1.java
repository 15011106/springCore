package hello.core.scope;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeTest(){

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ProtoTypeBean.class);
        ProtoTypeBean prototypeBean1 = ac.getBean(ProtoTypeBean.class);
        prototypeBean1.addCount();
        Assertions.assertEquals(1, prototypeBean1.getCount());

        ProtoTypeBean prototypeBean2 = ac.getBean(ProtoTypeBean.class);
        prototypeBean2.addCount();
        Assertions.assertEquals(1, prototypeBean2.getCount());

    }

    @Test
    void SingletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ProtoTypeBean.class,ClientBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        Assertions.assertEquals(1, count1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        Assertions.assertEquals(1, count2);

    }

    @Scope("singleton")
    static class ClientBean{

        @Autowired
        private Provider<ProtoTypeBean> protoTypeBeansProvide;

//        private final ProtoTypeBean protoTypeBean; // 생성시점에 주입

//        @Autowired
//        ApplicationContext applicationContext;

//        @Autowired
//        public ClientBean(ProtoTypeBean protoTypeBean){
//            this.protoTypeBean = protoTypeBean;
//        }
        public int logic(){
            ProtoTypeBean protoTypeBean = protoTypeBeansProvide.get();
            protoTypeBean.addCount();
            int count = protoTypeBean.getCount();

//            ProtoTypeBean protoTypeBean = applicationContext.getBean(ProtoTypeBean.class);
//            protoTypeBean.addCount();
//            int count = protoTypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class ProtoTypeBean{
        private int count = 0;

        void addCount(){
            count++;
    }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init" + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");

        }
    }
}
