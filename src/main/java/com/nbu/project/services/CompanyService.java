package com.nbu.project.services;

import com.nbu.project.entities.Package;
import com.nbu.project.repos.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private final PackageRepository packageRepository;
    @Autowired
    public CompanyService(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    public Double getIncome() {
        List<Package> packages = packageRepository.findAllByStatus("paid");
        double income = 0.0;
        for (Package p: packages) {
            income += p.price();
        }
        return income;
    }
}
