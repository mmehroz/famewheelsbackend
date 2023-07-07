package com.fame.famewheels.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fame.famewheels.entities.Category;
import com.fame.famewheels.entities.City;
import com.fame.famewheels.entities.Post;
import com.fame.famewheels.entities.PostType;
import com.fame.famewheels.entities.User;

public interface PostRepository extends JpaRepository<Post, Integer> {
	
	List<Post> findByUserAndStatus (User user, int i);
	List<Post> findByCategoryAndStatus(Category category, int i);
//	List<Post> findByCities (Cities cities);
	// List<Post> findByCity(String city);
	List<Post> findByMakeAndStatus(String make, int i);
	List<Post> findByModelAndStatus(String model, int i);
	
	//vehicle condition
	@Query(value="Select * from post where vehicle_condition=:condition and status=1 and (post_type_id=1 or post_type_id=2)", nativeQuery=true)
	Page<Post> findByVehicleConditionAndStatus(@Param("condition") String condition, Pageable p);
	
	//post Filters
	@Query(value="Select * from post where status=1 and "
			+ "(case when :vehicleCondition='null' then vehicle_condition in ('Used', 'New') "
			+ "else vehicle_condition=:vehicleCondition END)"
			+ " and (case when :city = 0 then city_id in (select city_id from city)"
			+ " else city_id= :city END)"
			+ " and (case when :make= 0 then make_id in (select make_id from make)"
			+ " else make_id=:make END)"
			+ " and price BETWEEN :minPrice and :maxPrice"
			+ " and (case when :category=0 then category_id in"
			+ " (select category_id from category)"
			+ " else category_id=:category END)", nativeQuery = true)
	Page<Post> filterpost(@Param("vehicleCondition") String vehicleCondition,
			@Param("city") Integer city,
			@Param("make") Integer make,
			@Param("minPrice") Integer minPrice,
			@Param("maxPrice") Integer maxPrice,
			@Param("category") Integer category,
			Pageable p);
	

	// @Query(value="Select city from cities")
	// List<String> getcities(); 
	
	//get active posts
	List<Post> findByStatus(int i);
	Page<Post> findByStatus(int i, Pageable p);
	//find by id and status
	Post findByPostIdAndStatus(Integer postId, int i);
//	Post findByCategoryIdAndStatus(Integer categortId, int i);
	Post findBypostIdAndStatus(Integer postId, int i);
	
	@Query(value="SELECT p.post_id, p.added_date, p.counter, p.cover, p.description, p.milage, p.phone, p.price, p.registered_in, p.secondary_phone,"
			+ " p.post_title, p.transmission, p.vehicle_colour, p.vehicle_condition, p.vehicle_fuel, p.status, p.category_id, p.city_id, p.make_id, "
			+ "p.user_id, p.model_id, p.year_id, p.post_type_id, c.city, ca.image_name, ca.title "
			+ "FROM post p join city c on p.city_id=c.city_id join make m on p.make_id=m.make_id join model mo on p.model_id=mo.model_id "
			+ "join category ca on p.category_id = ca.category_id where (lower(post_title) like lower(Concat('%' :searchValue '%')) "
			+ "or lower(description) like lower(Concat('%' :searchValue '%')) or lower(vehicle_condition) like lower(Concat('%' :searchValue '%'))"
			+ " or lower(city) like lower(Concat('%' :searchValue '%')) or lower(make) like lower(Concat('%' :searchValue '%'))"
			+ " or lower(title) like lower(Concat('%' :searchValue '%')) or lower(model_name) like lower(Concat('%' :searchValue '%')))"
			+ " and status= 1", nativeQuery=true)
	Page<Post> searchPost(@Param("searchValue") String searchValue, Pageable page);
	
	
	@Query(value="Select * from post where post_type_id=:typeId and status=1", nativeQuery=true)
	Page<Post> getByTypeAndStatus(@Param("typeId") Integer typeId, Pageable p);
	
	@Query(value="SELECT count(auction_post_id) FROM auction_post WHERE auction_date=:date and auction_start_time=:time", nativeQuery=true)
	int getPostCountForToday(@Param("date") String date, @Param("time") String time);
	
}
