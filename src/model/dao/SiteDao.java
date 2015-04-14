package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Site;

@Path("/site")
public class SiteDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Assignment5");
	EntityManager em = factory.createEntityManager();
	
	//public Site findSite 
	//returns an instance of Site representing a record whose id is siteId
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Site findSite(@PathParam("id") int id)
	{
		return em.find(Site.class,id);
	}
	
	//public List<Site> findAllSites() 
	//returns a list of Site instances
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings("unchecked")
	public List<Site> findAllSites()
	{
		Query query = em.createQuery("select site from Site site");
		return (List<Site>)query.getResultList();
	}
	

	//public List<Site> updateSite(int siteId, Site site)
	//updates the site record whose id is siteId 
	//and then returns all the sites
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> updateSite(@PathParam("id") int siteId, Site site)
	{
		em.getTransaction().begin();
		em.merge(site);
		em.getTransaction().commit();
		return findAllSites();
		
	}

	//public List<Site> removeSite(int siteId) 
	//removes a site record whose id is siteId and the 
	//returns a list of all the sites
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> removeSite(@PathParam("id") int siteId)
	{
		Site site = null;
		em.getTransaction().begin();
		site = em.find(Site.class, siteId);
		em.remove(site);
		em.getTransaction().commit();
		
		return findAllSites();
		
	}	
	
	//public List<Site> createSite(Site site) 
	//inserts a new site record into the database and then 
	//returns a list of all the sites
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> createSite(Site site) 
	{
		em.getTransaction().begin();
		em.persist(site);
		em.getTransaction().commit();
		return findAllSites();
	}
	
	
	public static void main(String[] args){
		SiteDao dao = new SiteDao();
		
//		Site site = new Site(5,"site1", 99.99,99.99);
//		List<Site> sites = dao.createSite(site);
//		
//		for(Site site1:sites)
//		{
//			System.out.println(site1.getLatitude());
//		}	
		
//		Site site = dao.findSiteById(2);
//		System.out.println(site.getName());
		
//		List<Site> sites = dao.findAllSites();
//		for(Site site:sites)
//		{
//			System.out.println(site.getLatitude());
//		}
		
//		Site site = new Site(8,"site8", 88.88,88.88);
//		List<Site> sites = dao.updateSite(5,site);
//		
//		for(Site site1:sites)
//		{
//			System.out.println(site1.getLatitude());
//		}	
		
//		List<Site> sites = dao.removeSite(5);
//		
//		for(Site site1:sites)
//		{
//			System.out.println(site1.getLatitude());
//		}		
//		
		
	}



}
