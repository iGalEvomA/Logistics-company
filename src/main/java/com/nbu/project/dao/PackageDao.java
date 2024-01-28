package com.nbu.project.dao;

import com.nbu.project.entities.Package;

import java.util.List;

public interface PackageDao {

    void save(Package pkg);

    Package getById(int id);

    List<Package> getAll();

    void update(Package pkg);

    void deleteById(int id);
}
