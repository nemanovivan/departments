package com.example.departments.service;

import com.example.departments.dao.DepartmentsDao;
import com.example.departments.domain.Department;
import com.example.departments.exception.DeleteException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Сервис для работы с департаментами.
 */
@Service
public class DepartmentService {

    private final DepartmentsDao departmentsDao;

    public DepartmentService(DepartmentsDao departmentsDao) {
        this.departmentsDao = departmentsDao;
    }

    /**
     * Метод возвращаюший департаменты по конкретной дате.
     *
     * @param date - дата, по которой ищутся департаменты.
     * @return - список департаментов по дате, отсортированный по sort_priority.
     */
    public List<?> findAllByDate(LocalDate date) {
        return departmentsDao.findAllByDate(date);
    }

    /**
     * Метод добавляющий департамент.
     *
     * @param department       - департамент для добавления.
     * @param parentDepartment - родительский департамент, если такого нет, то null.
     */
    public void addDepartment(Department department, Department parentDepartment) {
        departmentsDao.addDepartment(department, parentDepartment);
    }

    /**
     * Метод редактирующий департамент.
     *
     * @param department - департамент для редактирования.
     */
    public void modifyDepartment(Department department){
        departmentsDao.modifyDepartment(department);
    }

    /**
     * Метод удаляющий департамент. Кидает исключение, если у него есть дочерние подразделения.
     *
     * @param department - департамент для удаления
     */
    public void deleteDepartment(Department department){
        if (department.getChildren() == null) {
            departmentsDao.deleteDepartment(department);
        } else {
            throw new DeleteException("Подразделение имеет дочерние узлы и не может быть удалено");
        }
    }
}
