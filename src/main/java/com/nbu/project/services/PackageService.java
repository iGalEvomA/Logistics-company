package com.nbu.project.services;

import com.nbu.project.entities.Package;
import com.nbu.project.repos.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageService {

    private final PackageRepository packageRepository;

    @Autowired
    public PackageService(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    public List<Package> getAllPackages() {
        return packageRepository.findAll();
    }

    public Package getPackageById(int id) {
        return packageRepository.findById(id);
    }

    public Package createPackage(Package pack) {
        return packageRepository.save(pack);
    }

    public void updatePackage(Package pack) {
        packageRepository.update(pack);
    }

    public void deletePackageById(int id) {
        packageRepository.deleteById(id);
    }

    public List<Package> getAllPackagesByEmployee(String employeeEmail) {
        return packageRepository.findAllByEmployee(employeeEmail);
    }

    public List<Package> getAllPackagesWithStatus(String status) {
        return packageRepository.findAllByStatus(status);
    }

    public List<Package> getAllPackagesFromSender(String senderEmail) {
        return packageRepository.findAllBySender(senderEmail);
    }

    public List<Package> getAllPackagesWithReceiver(String receiverEmail) {
        return packageRepository.findAllByReceiver(receiverEmail);
    }
}
