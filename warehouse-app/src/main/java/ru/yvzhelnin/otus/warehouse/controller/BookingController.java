package ru.yvzhelnin.otus.warehouse.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yvzhelnin.otus.warehouse.dto.BookingDto;
import ru.yvzhelnin.otus.warehouse.service.BookingService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/book")
    public void bookEquipment(@Valid @RequestBody BookingDto bookingDto) {
        bookingService.bookEquipment(bookingDto.getModels(), bookingDto.getCustomerPhoneNumber());
    }

    @PostMapping("/unbook/{customerPhoneNumber}")
    public void unBookEquipment(@PathVariable("customerPhoneNumber") String customerPhoneNumber) {
        bookingService.unBookEquipment(customerPhoneNumber);
    }
}
