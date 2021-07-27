package com.ibm.zenithom.productreserve.stream;

import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding({ProductReservationStatusProducerStream.class,ProductReservationProducerStream.class,ProductReservationConsumerStream.class})
public class StreamConfig {

}
