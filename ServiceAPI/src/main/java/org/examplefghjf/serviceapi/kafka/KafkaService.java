package org.examplefghjf.serviceapi.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.examplefghjf.serviceapi.db.entity.Group;
import org.examplefghjf.serviceapi.db.entity.NotificationTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class KafkaService {
    private final KafkaProducer kafkaProducer;

    @Autowired
    public KafkaService(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }


//    public void send (Group group,NotificationTemplate template) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            String groupSerialize = objectMapper.writeValueAsString(group);
//            String templateSerialize = objectMapper.writeValueAsString(template);
//            Map<String,String> map = new HashMap<>();
//            map.put("group",groupSerialize);
//            map.put("template",templateSerialize);
//            kafkaProducer.sendMessage(map);
//        }catch (Exception ex){
//            System.err.println(ex);
//        }
//    }
    public void send (Long groupId,Long templateId) {
        try {
            Map<String,String> map = new HashMap<>();
            map.put("group_id",groupId.toString());
            map.put("template_id",templateId.toString());
            kafkaProducer.sendMessage(map);
        }catch (Exception ex){
            System.err.println(ex);
        }
    }
}
