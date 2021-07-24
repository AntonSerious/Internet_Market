package com.anemchenko.repositories;

import com.anemchenko.utils.DBFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
public class Customer_x_ProductDao {

    @Autowired
    DBFactory dbFactory;
}