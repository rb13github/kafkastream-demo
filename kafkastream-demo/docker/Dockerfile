FROM openjdk:latest
ENV DB_URL = jdbc:postgresql://postgre:5432/zenithomdb
ENV DB_USER = root
ENV DB_PASSWORD = root

ENV KAFKA_BOOTSTRAP_URL = localhost:9092
ENV KAFKA_BROKERS_URL = localhost:9092
ENV KAFKA_TOPIC_OMS_PRODUCT_STATUS = oms-product-status
ENV KAFKA_TOPIC_OMS_RESERVE_EVENT = oms-reserve-event



#ENV LOGGING_LEVEL_WEB = DEBUG
#ENV LOGGING_LEVEL_ROOT = WARN
#EXPOSE 8080
# moved the Copy command from 2 step to follow best practices as the image layers will be same from 1 to 7
COPY kafkastream-demo-0.0.1-SNAPSHOT.jar kafkastream-demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","kafkastream-demo-0.0.1-SNAPSHOT.jar"]