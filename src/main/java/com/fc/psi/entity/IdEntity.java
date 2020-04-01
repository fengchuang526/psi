package com.fc.psi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public class IdEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "uuid")
    @Column(name = "id")
    private String id;

    /**
     *  创建时间
     */
    @Column(name = "CREATE_DATE")
    private Date createDate;
    /**
     *  更新时间
     */
    @Column(name = "UPDATE_DATE")
    private Date updateDate;
}
