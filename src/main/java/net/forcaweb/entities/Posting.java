package net.forcaweb.entities;

import java.text.Normalizer;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_posting")
public class Posting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	@Lob
    @Column 
	private String textPost;
	private Instant moment;
	@Column(unique = true)
	private String urlIdentify;

	@ManyToOne
	@JoinColumn(name = "user_post_id")
	private User userPosting;

	@ManyToMany
	@JoinTable(name = "tb_post_category", joinColumns = @JoinColumn(name = "posting_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<Category> categories = new ArrayList<>();

	public Posting() {
	}

	public Posting(Long id, String title, String textPost, Instant moment, User userPosting) {

		this.id = id;
		this.title = title;
		this.textPost = textPost;
		this.moment = moment;
		this.userPosting = userPosting;
		this.urlIdentify = this.getUrlIdentify();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTextPost() {
		return textPost;
	}

	public void setTextPost(String textPost) {
		this.textPost = textPost;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public User getUserPosting() {
		return userPosting;
	}

	public void setUserPosting(User userPosting) {
		this.userPosting = userPosting;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	
	public String getUrlIdentify() {
		urlIdentify = removerAcentos(this.title);
		return urlIdentify.replace(" ", "-").toLowerCase();
	}
	
	public static String removerAcentos(String str) {
	    return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
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
		Posting other = (Posting) obj;
		return Objects.equals(id, other.id);
	}

}
