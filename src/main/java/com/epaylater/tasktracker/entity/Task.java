package com.epaylater.tasktracker.entity;

import java.util.Date;
import java.util.UUID;

import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.UniqueElements;

import jakarta.annotation.Generated;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
@Getter
@Setter
public class Task {
		@Id
		@Column(unique = true)
    	private String id;
		private String title;
		private String description;
		@Temporal(TemporalType.DATE) 
	    private Date dueDate;
		
		 

	
}
