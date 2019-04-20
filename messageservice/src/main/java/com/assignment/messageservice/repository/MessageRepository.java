package com.assignment.messageservice.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.assignment.messageservice.model.Message;

public interface MessageRepository extends CassandraRepository<Message , Integer> {

}
