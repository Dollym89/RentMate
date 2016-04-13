package com.example.michal.navigationdrawer.model.repositories;

import com.example.michal.navigationdrawer.model.Apartment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michal on 12/04/2016.
 */
public class ApartmentRepository {


    private static ApartmentRepository repository;


    private List<Apartment> apartmentListList;

    private ApartmentRepository() {
        this.apartmentListList = new ArrayList<>();

    }

    public static ApartmentRepository getInstance() {
        if (repository == null) {
            repository = new ApartmentRepository();
        }
        return repository;
    }


}
