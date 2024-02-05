package com.nbu.project;

import com.nbu.project.entities.Address;
import com.nbu.project.repos.AddressRepository;
import com.nbu.project.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@RequiredArgsConstructor
public class AddressServiceTests {

    @MockBean
    private AddressRepository addressRepository;

    @Test
    void contextLoads() {

    }

    @Test
    void testGetAllAddresses() {
        final AddressService addressService = new AddressService(addressRepository);

        final List<Address> addresses = List.of(new Address(1, "type1", "123 main st"),
                new Address(2, "type2", "456 main st"),
                new Address(3, "type3", "789 main st"));

        when(addressRepository.findAll()).thenReturn(addresses);
        List<Address> result = addressService.getAllAddresses();
        Assertions.assertEquals(3, result.size());
        addresses.forEach(address -> Assertions.assertTrue(result.contains(address)));
    }

    @Test
    void testCreateAddress() {
        final AddressService addressService = new AddressService(addressRepository);
        final Address address = new Address(1, "type1", "123 main st");

        when(addressRepository.save(any(Address.class))).thenReturn(address);
        Address result = addressService.createAddress(address);
        Assertions.assertEquals(address, result);
    }

    @Test
    void testDeleteAddressById() {
        final AddressService addressService = new AddressService(addressRepository);
        int id = 1;

        doNothing().when(addressRepository).deleteById(id);
        addressService.deleteAddressById(id);

        verify(addressRepository, times(1)).deleteById(id);
    }
}
