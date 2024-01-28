package com.nbu.project.controllers;

import com.nbu.project.entities.Package;
import com.nbu.project.services.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/package")
public class PackageController {

    private final PackageService packageService;

    @Autowired
    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @PostMapping
    public Package createPackage(@RequestBody Package pkg) {
        return packageService.createPackage(pkg);
    }

    @GetMapping
    public List<Package> getAllPackages() {
        return packageService.getAllPackages();
    }

    @GetMapping("/{id}")
    public Package getPackageById(@PathVariable int id) {
        return packageService.getPackageById(id);
    }

    @PutMapping("/{id}")
    public void updatePackage(@PathVariable int id, @RequestBody Package pack) {
        pack = new Package(id, pack.status(), pack.weight(), pack.price(),
                pack.dateOfPayment(), pack.timeOfPayment(), pack.senderEmail(),
                pack.receiverEmail(), pack.deliveryAddressId(), pack.registerEmail(),
                pack.courierEmail());
        packageService.updatePackage(pack);
    }

    @DeleteMapping("/{id}")
    public void deletePackageById(@PathVariable int id) {
        packageService.deletePackageById(id);
    }
}
