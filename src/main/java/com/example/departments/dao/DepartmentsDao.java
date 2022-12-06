package com.example.departments.dao;

import com.example.departments.domain.Department;
import com.example.departments.exception.DeleteException;
import com.example.departments.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * DAO департаментов.
 */
@Repository
public class DepartmentsDao {

    /**
     * Метод возвращаюший департаменты по конкретной дате.
     *
     * @param date - дата, по которой ищутся департаменты.
     * @return - список департаментов по дате, отсортированный по sort_priority.
     */
    public List<?> findAllByDate(LocalDate date) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<?> departments = session.
                createQuery("select from departments where " + date + " between dt_from and dt_till order by sort_priority").
                list();
        session.close();
        return departments;
    }

    /**
     * Метод добавляющий департамент.
     *
     * @param department       - департамент для добавления.
     * @param parentDepartment - родительский департамент, если такого нет, то null.
     */
    public void addDepartment(Department department, Department parentDepartment) {
        department.setParent(parentDepartment);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(department);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Метод редактирующий департамент.
     *
     * @param department - департамент для редактирования.
     */
    public void modifyDepartment(Department department) {
        Department oldDepartment;
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        oldDepartment = (Department) session.createQuery("From departments where id =" + department.getId());
        oldDepartment.setDateTo(LocalDate.now());
        department.setCorrectionDate(LocalDate.now());
        session.beginTransaction();
        session.update(department);
        session.update(oldDepartment);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Метод удаляющий департамент.
     *
     * @param department - департамент для удаления
     */
    public void deleteDepartment(Department department) {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(department);
            session.getTransaction().commit();
            session.close();
    }
}
