package com.theironyard.services;

import com.theironyard.entities.Message;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by hoseasandstrom on 6/21/16.
 */
public interface MessageRepository extends CrudRepository<Message, Integer> {
    public Message findById(int id);
}
