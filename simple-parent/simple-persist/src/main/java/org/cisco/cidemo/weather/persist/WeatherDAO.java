package org.cisco.cidemo.weather.persist;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import org.cisco.cidemo.weather.model.Location;
import org.cisco.cidemo.weather.model.Weather;

public class WeatherDAO extends HibernateDaoSupport {

    public WeatherDAO() {}

    public void save(Weather weather) {
    	getHibernateTemplate().save( weather );
    }

    public Weather load(Integer id) {
    	return (Weather) getHibernateTemplate().load( Weather.class, id);
    }

    @SuppressWarnings("unchecked")
	public List<Weather> recentForLocation( final Location location ) {
    	return (List<Weather>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = getSession().getNamedQuery("Weather.byLocation");
				query.setParameter("location", location);
				return new ArrayList<Weather>( query.list() );
			}
		});
    }
}