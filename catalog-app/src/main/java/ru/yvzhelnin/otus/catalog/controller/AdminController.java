package ru.yvzhelnin.otus.catalog.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yvzhelnin.otus.catalog.service.AdminService;

@RestController
@RequestMapping("/api/catalog/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/update")
    public void update() {
        adminService.updateCatalog();
    }
}
