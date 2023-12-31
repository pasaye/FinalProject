package com.skilldistillery.journeyjournals.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPAjourneyJournals");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		user = em.find(User.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
	}

	@Test
	void test() {
		assertNotNull(user);
		assertEquals("admin", user.getUsername());
		assertTrue(user.getBlogs().size() > 0);
		assertTrue(user.getBlogs().contains(em.find(Blog.class, 1)));
		assertTrue(user.getComments().contains(em.find(Comment.class, 1)));
		assertTrue(user.getDestinationsCreated().contains(em.find(Destination.class, 1)));
		assertTrue(user.getFavoriteDestinations().contains(em.find(Destination.class, 1)));
		assertTrue(user.getFollowers().contains(em.find(User.class, 2)));
		assertTrue(user.getPlacesCreated().contains(em.find(Place.class, 1)));
		assertTrue(user.getDestinationImages().contains(em.find(DestinationImage.class, 1)));
		assertTrue(user.getPlaceImages().contains(em.find(PlaceImage.class, 1)));
		assertEquals(user.getBlogRatings().get(0).getRatingValue(), 5);
		assertEquals(user.getPlaceRatings().get(0).getRatingValue(), 5);
	}
	
	@Test
	void test2 () {
		User user2 = em.find(User.class, 2);
		assertTrue(user2.getFollowing().contains(user));
	}

}
