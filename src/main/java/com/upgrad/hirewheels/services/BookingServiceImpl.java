package com.upgrad.hirewheels.services;

import com.upgrad.hirewheels.dao.BookingDao;
import com.upgrad.hirewheels.dao.UserDao;
import com.upgrad.hirewheels.entities.Booking;
import com.upgrad.hirewheels.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService{
    @Autowired
    BookingDao bookingDao;

    @Autowired
    UserDao userDao;

    /**
     * This method adds booking for a particular vehicle in the database. While adding the booking,
     * the booking amount should be deducted from the wallet balance of the user.
     * @param booking
     * @return
     */

    @Override
    public Booking addBooking(Booking booking) {

        User user = booking.getUser();
        if (user.getWalletMoney() < booking.getAmount()) {
            System.out.println("Insufficient Balance. Please Check With Admin.");

        } else {
            user.setWalletMoney(user.getWalletMoney() - booking.getAmount());
            userDao.save(user);
        }
        Booking savedBooking = bookingDao.save(booking);
        return savedBooking;
    }
}
