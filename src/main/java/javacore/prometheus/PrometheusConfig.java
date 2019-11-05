package javacore.prometheus;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.exporter.PushGateway;

public class PrometheusConfig
{
    public static final Counter counterDemo = Counter.build()
            .name("push_way_counter")
            .labelNames("wy","zxjr","ocs","xxjf","unit","instance")
            .help("Counter 实例")
            .register();

    static final Gauge gaugeDemo = Gauge.build()
            .name("gaugeDemo")
            .labelNames("label1","label2","label3","label4","label5")
            .help("gauge 实例").register();
    
    //测试发送
    public static void main(String[] args)
    {
        PushGateway prometheusPush = new PushGateway("prometheus-gateway.sh.prod.urtc.com.cn");

        try
        {
            for(int i=0; i<100; i++ ){
//                counterDemo.labels("网元","在线接入","OCS","消息计费","byte","localhsot").inc();
//                prometheusPush.push(counterDemo,"sp-getway");

                gaugeDemo.labels("1","2","3","4","5").set(i);
                prometheusPush.push(gaugeDemo,"gaugeDemo");

                Thread.sleep(1000);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
