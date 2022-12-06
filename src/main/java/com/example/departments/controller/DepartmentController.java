package com.example.departments.controller;

import com.example.departments.domain.Department;
import com.example.departments.service.DepartmentService;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/**
 * REST-контроллер для работы с департаментами.
 */
@RestController
public class DepartmentController {

    private final DepartmentService departmentService;

    DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * Мапинг для получения департаментов по дате.
     *
     * @param date - дата по который ищутся департаменты.
     * @return
     */
    @GetMapping("/findAllByDate")
    public List<?> findAllByDate(@RequestParam("date") @NonNull LocalDate date) {
        return departmentService.findAllByDate(date);
    }

    /**
     * Мапинг для добавления департамента.
     *
     * @param department       - департамент для добавления.
     * @param parentDepartment - родительский департамент.
     */
    @PostMapping("/addDepartment")
    public void addDepartment(@RequestBody Department department, @RequestBody Department parentDepartment) {
        departmentService.addDepartment(department, parentDepartment);
    }

    /**
     * Мапинг для редактирования департамента.
     *
     * @param department - департамент для редактирования.
     */
    @PostMapping("/modifyDepartment")
    public void modifyDepartment(@RequestBody Department department) {
        departmentService.modifyDepartment(department);
    }

    /**
     * Мапинг для удаления департамента.
     *
     * @param department - департамент для удаления.
     */
    @PostMapping("/deleteDepartment")
    public void deleteDepartment(@RequestBody Department department) {
        departmentService.deleteDepartment(department);
    }
}
