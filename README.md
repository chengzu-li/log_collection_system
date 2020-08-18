# log_collection_system
log collection system based on flume, zookeeper, kafka, docker


CuratorHelloWorld.java is responsible for sending the config file of flume in docker 'flume-docker.conf' to the zookeeper for storage.
Connect.java sends the message by thrift architecture(RPC) to flume in the docker container. After receiving the event, flume sinks it to kafka where the data is stored.

Chinese guide: https://blog.csdn.net/magicpenta/article/details/106817731
