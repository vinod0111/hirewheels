package com.upgrad.hirewheels.services;

import com.upgrad.hirewheels.dao.VehicleDao;
import com.upgrad.hirewheels.entities.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    VehicleDao vehicleDao;

    /**
     * This method interacts with the VehicleDao to store vehicle's data into the database.
     * @param vehicle
     * @return
     */

    @Override
    public Vehicle registerVehicle(Vehicle vehicle){
        boolean testVehicleNumber = vehicleDao.existsByVehicleNumber(vehicle.getVehicleNumber());
        if (testVehicleNumber) {
            System.out.println("Vehicle Number Already Exists");
        }
        Vehicle savedVehicle = vehicleDao.save(vehicle);
        savedVehicle.setAvailabilityStatus(1);
        return savedVehicle;
    }

    /**
     * This method changes the availability_status field of the vehicle based on the input parameters.
     * If the availability_status =0, then the vehicle is not available for booking. Similarly,
     * if the availability_status =1, then the vehicle is available for booking.
     * @param vehicleId
     * @param availabilityStatus
     * @return
     */

    @Override
    public Vehicle changeAvailability(int vehicleId, int availabilityStatus) {
        Vehicle vehicle = vehicleDao.findById(vehicleId).get();

        vehicle.setAvailabilityStatus(availabilityStatus);
        return vehicleDao.save(vehicle);
    }
}
