package net.forcaweb.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import net.forcaweb.entities.enums.RoleUser;

@Entity
@Table(name = "tb_user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(unique = true, nullable = false)
	private String email;
	@Column(nullable = true)
	private String phone;
	@Column(nullable = true)
	private LocalDate birthDay;
	@Column(nullable = false)
	private String password;
	
	private RoleUser roleUser;
	
	@JsonIgnore
	@OneToMany(mappedBy = "userPosting")
	private Set<Posting> postings = new HashSet<>();
	
	public User() {}

	public User(Long id, String name, String email, String phone, LocalDate birthDay, String password, RoleUser roleUser) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.birthDay = birthDay;
		this.password = password;
		this.roleUser = roleUser;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDate getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(LocalDate birthDay) {
		this.birthDay = birthDay;
	}
	
	public Set<Posting> getPostings() {
		return postings;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<String> getRoleUser() {
		return Arrays.asList(String.valueOf(roleUser.getCode()), roleUser.getDescription());
	}

	public void setRoleUser(RoleUser roleUser) {
		this.roleUser = roleUser;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

}
