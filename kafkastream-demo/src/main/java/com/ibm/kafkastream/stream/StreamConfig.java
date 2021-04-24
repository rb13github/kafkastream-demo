package com.ibm.kafkastream.stream;

import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding({ProductReservationStatusProducerStream.class,ProductReservationProducerStream.class,ProductReservationConsumerStream.class})
public class StreamConfig {

}
