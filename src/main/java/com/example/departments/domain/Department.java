package com.example.departments.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Доменный объект подразделения.
 */
@Entity
@Table(name = "departments")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Department {
    /**
     * ID подразделения.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Наименование подразделения.
     */
    @Column(name = "name")
    private String name;

    /**
     * Ссылка на родительское подразделение.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", foreignKey = @ForeignKey(name = "FK_PARENT_ID"))
    private Department parent;

    /**
     * Список дочерних подразделений.
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private List<Department> children = new ArrayList<>();

    /**
     * Дата и время начала действия подразделения.
     */
    @Column(name = "dt_from")
    private LocalDate dateFrom;

    /**
     * Дата и время конца действия подразделения.
     * Если null, то бессрочно.
     */
    @Column(name = "dt_till")
    private LocalDate dateTo;

    /**
     * Приоритет для сортировки при отображении.
     */
    @Column(name = "sort_priority")
    private int sortPriority;

    /**
     * Признак Системный. Только для подразделений верхнего уровня.
     */
    @Column(name = "is_system")
    private boolean isSystem;

    /**
     * Дата создания подразделения.
     */
    @Column(name = "creation_date")
    private LocalDate creationDate;

    /**
     * Дата последнего редактирования подразделения.
     */
    @Column(name = "correction_date")
    private LocalDate correctionDate;

    /**
     * Конструктор с параметрами.
     *
     * @param name
     * @param parent
     * @param sortPriority
     * @param dateTo
     * @param dateFrom
     */
    public Department(String name, Department parent, int sortPriority, LocalDate dateTo, LocalDate dateFrom) {
        this.name = name;
        this.parent = parent;
        this.sortPriority = sortPriority;
        this.dateTo = dateTo;
        this.dateFrom = dateFrom;
        this.creationDate = LocalDate.now();
        this.correctionDate = LocalDate.now();
        if (parent == null) {
            this.isSystem = true;
        } else {
            this.isSystem = false;
        }
    }

}
